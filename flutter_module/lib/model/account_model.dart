import 'package:flutter_module/api/Api.dart';
import 'package:flutter_module/common/common.dart';
import 'package:flutter_module/bean/user.dart';
import 'package:flutter_module/net/dio_util.dart';
import 'package:flutter_module/util/log_util.dart';

/// 用于登录注册
class AccountModel {
  final String tag = "AccountModel";

  OnRegisterListener _onRegisterListener;
  OnLoginListener _onLoginListener;

  AccountModel({OnRegisterListener registerListener,OnLoginListener loginListener}){
    this._onRegisterListener = registerListener;
    this._onLoginListener = loginListener;
  }

  /// 注册
  void register(String username, String password, String rePassword) {
    var params = <String, dynamic>{};
    params["username"] = username;
    params["password"] = password;
    params["repassword"] = rePassword;
    Http.post<User>(
        Api.apiRegister,
        false,
        Callback(onDataSuccess: (User result) {
          Log.d(tag, 'register > onDataSuccess');
          Log.d(tag, 'register > onDataSuccess > _onRegisterListener:$_onRegisterListener');
          if (_onRegisterListener != null) {
            _onRegisterListener.onRegisterSuccess(result);
          }
        }, onError: (String error) {
          Log.d(tag, 'register > onError');
          if (_onRegisterListener != null) {
            _onRegisterListener.onError(error);
          }
        }),
        queryParameters: params);
  }

  /// 登录
  void login(String username, String password) {
    var params = <String, dynamic>{};
    params["username"] = username;
    params["password"] = password;
    Http.post<User>(
        Api.apiLogin,
        false,
        Callback(onDataSuccess: (User result) {
          if (_onLoginListener != null) {
            _onLoginListener.onLoginSuccess(result);
          }
        }, onError: (String error) {
          if (_onLoginListener != null) {
            _onLoginListener.onError(error);
          }
        }),
        queryParameters: params);
  }
}

/// 登录监听器
abstract class OnLoginListener implements OnErrorListener {
  void onLoginSuccess(User result);
}

/// 注册监听器
abstract class OnRegisterListener implements OnErrorListener {
  void onRegisterSuccess(User result);
}
