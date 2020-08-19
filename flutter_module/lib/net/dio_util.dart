import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter_module/common/common.dart';
import 'package:flutter_module/model/base_result_bean.dart';
import 'package:cookie_jar/cookie_jar.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:path_provider/path_provider.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';

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

    /// 持久化Cookie
    Directory directory = await getTemporaryDirectory();
    File file = new File(directory.path + '/cookies/');
    var cookieJar = PersistCookieJar(dir: file.path);
    _getDioInstance().interceptors.add(CookieManager(cookieJar));

    try {
      Response response =
          await _getDioInstance().post(url, queryParameters: queryParameters);

      if (response.statusCode == HttpStatus.ok) {
        print('response:${response.toString()}');
        print('response.data:${response.data.toString()}');

        List<Cookie> cookies = cookieJar.loadForRequest(Uri.parse(Common.baseUrl+url));

        if (cookies != null && cookies.length > 0) {
          var cookie = cookies.firstWhere((element) => element.expires!=null);
          SharedPreferences prefs = await SharedPreferences.getInstance();
          prefs.setInt(Common.expires, cookie.expires.millisecondsSinceEpoch);
        }

        if (isList) {
          List<T> result =
              BaseResultBean.fromJsonArray(response.data).getList<T>();
          print('result:${result.length}');
          print('result:${result.toString()}');
          callback.onListSuccess(result);
        } else {
          T result = BaseResultBean.fromJson(response.data).getObj();
          callback.onDataSuccess(result);
        }
      }
    } on DioError catch (e) {
      print(e.error);
    }
  }
}

class Callback<T> {
  OnListSuccess<T> onListSuccess;
  OnDataSuccess<T> onDataSuccess;
  OnError onError;

  Callback({this.onListSuccess, this.onDataSuccess, this.onError});
}
