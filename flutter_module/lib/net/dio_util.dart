import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter_module/api/Api.dart';
import 'package:flutter_module/common/common.dart';
import 'package:flutter_module/bean/base_result_bean.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:flutter_module/util/log_util.dart';
import 'package:flutter_module/util/sp_util.dart';
import 'package:path_provider/path_provider.dart';
import 'package:cookie_jar/cookie_jar.dart';

String tag = "Http";

typedef OnListSuccess<T> = void Function(List<T> result);
typedef OnDataSuccess<T> = void Function(T result);
typedef OnError = void Function(String error);

/// Http工具类
class Http {
  static Dio _instance;

  static Dio _getDioInstance() {
    if (_instance == null) {
      var options =
          BaseOptions(responseType: ResponseType.json, baseUrl: Common.baseUrl);
      _instance = Dio(options);
    }
    return _instance;
  }

  static void get<T>(String url, bool isList, Callback<T> callback,
      {Map<String, dynamic> queryParameters}) async {
    request(url, isList, callback,
        method: "GET", queryParameters: queryParameters);
  }

  static void post<T>(String url, bool isList, Callback<T> callback,
      {Map<String, dynamic> queryParameters}) async {
    request(url, isList, callback,
        method: "POST", queryParameters: queryParameters);
  }

  static void request<T>(String url, bool isList, Callback<T> callback,
      {String method, Map<String, dynamic> queryParameters}) async {
    if (method == null) method = 'GET';
    if (url.isEmpty) {
      Log.d(tag, "url is empty");
      return;
    }
    var cookieJar;
    if (url == Api.apiLogin) {
      /// 持久化Cookie
      Directory directory = await getTemporaryDirectory();
      File file = new File(directory.path + '/cookies/');
      cookieJar = PersistCookieJar(dir: file.path);
      _getDioInstance().interceptors.add(CookieManager(cookieJar));
    }

    try {
      Response response =
          await _getDioInstance().post(url, queryParameters: queryParameters);

      if (response.statusCode == HttpStatus.ok) {
        Log.d(tag, 'request > response:${response.toString()}');

        /// 登录时保存Cookie
        if (url == Api.apiLogin){
          List<Cookie> cookies = cookieJar
              .loadForRequest(Uri.parse(Common.baseUrl + Api.apiLogin));

          if (cookies != null && cookies.length > 0) {
            cookies.forEach((element) {
              Log.d(tag,"element > name:${element.name},value:${element.value}");
            });
            var cookie =
            cookies.firstWhere((element) => element != null && element.expires != null);
            Sp.putInt(Common.expires, cookie.expires.millisecondsSinceEpoch);
          }
        }

        if (isList) {
          BaseResultBean<T> resultBean =
              BaseResultBean.fromJsonArray(response.data);
          if (resultBean.errorCode == 0) {
            List<T> result = resultBean.getList();
            callback.onListSuccess(result);
          } else {
            callback.onError(resultBean.errorMsg);
          }
        } else {
          BaseResultBean<T> resultBean = BaseResultBean.fromJson(response.data);
          if (resultBean.errorCode == 0) {
            T result = resultBean.getObj();
            callback.onDataSuccess(result);
          } else {
            callback.onError(resultBean.errorMsg);
          }
        }
      } else {
        callback.onError(response.statusMessage);
      }
    } on DioError catch (e) {
      callback.onError(e.toString());
    }
  }
}

class Callback<T> {
  OnListSuccess<T> onListSuccess;
  OnDataSuccess<T> onDataSuccess;
  OnError onError;

  Callback({this.onListSuccess, this.onDataSuccess, this.onError});
}
