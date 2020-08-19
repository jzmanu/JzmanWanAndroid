
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/api/Api.dart';
import 'package:flutter_module/main.dart';
import 'package:flutter_module/model/user.dart';
import 'package:flutter_module/net/dio_util.dart';
import 'package:toast/toast.dart';

/// 登录
class LoginWidget extends StatefulWidget {
  @override
  _LoginWidgetState createState() => _LoginWidgetState();
}

class _LoginWidgetState extends State<LoginWidget> {
  /// 密码是否可见
  bool _isPasswordVisible = false;
  /// 表单是否可以编辑
  bool _isFormWasEdited = false;

  /// MethodChannel
  MethodChannel _platform;

  AccountData _loginData;

  final GlobalKey<FormState> _formKey = new GlobalKey<FormState>();
  final GlobalKey<FormFieldState<String>> _passwordKey = new GlobalKey<FormFieldState<String>>();

  @override
  void initState() {
    super.initState();
    // MethodChannel
    _platform = const MethodChannel('com.manu.startMainActivity');
    _loginData = AccountData();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Stack(
          overflow: Overflow.visible,
          children: <Widget>[
            Container(
              width: MediaQuery.of(context).size.width * 3 / 4,
              height: 200,
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(10),
              ),
              child: Padding(
                  padding: EdgeInsets.all(12),
                  child: Form(
                    key: _formKey,
                    child: Column(
                      children: <Widget>[
                        TextFormField(
                          keyboardType: TextInputType.text,
                          textInputAction: TextInputAction.next,
                          textCapitalization: TextCapitalization.words,
                          maxLines: 1,
                          validator: _validateName,
                          decoration: InputDecoration(
                              hintText: '账号',
                              icon: Icon(
                                Icons.person,
                                color: Colors.black87,
                              ),
                              border: InputBorder.none),
                          onFieldSubmitted: (value){
                            print('onFieldSubmitted:'+value);
                          },
                          onSaved: (value){
                            print('onSaved:'+value);
                            _loginData.name = value;
                          },
                        ),
                        Container(
                          height: 1,
                          color: Colors.grey[350],
                          margin: const EdgeInsets.only(bottom: 16, top: 16),
                        ),
                        TextFormField(
                          key: _passwordKey,
                          textCapitalization: TextCapitalization.words,
                          textInputAction: TextInputAction.done,
                          keyboardType: TextInputType.visiblePassword,
                          maxLines: 1,
                          // 是否是密码
                          obscureText: !_isPasswordVisible,
                          validator: _validatePassword,
                          decoration: InputDecoration(
                            hintText: '密码',
                            icon: Icon(
                              Icons.lock,
                              color: Colors.black87,
                            ),
                            border: InputBorder.none,
                            suffixIcon: IconButton(
                              icon: Icon(
                                // 根据isPasswordVisible状态显示不同的图标
                                _isPasswordVisible
                                    ? Icons.visibility
                                    : Icons.visibility_off,
                                color: Theme.of(context).primaryColorDark,
                              ),
                              onPressed: () {
                                setState(() {
                                  _isPasswordVisible = !_isPasswordVisible;
                                });
                              },
                            ),
                          ),
                          onFieldSubmitted: (value){
                            print('onFieldSubmitted:'+value);
                          },
                          onSaved: (value){
                            print('onSaved:'+value);
                            _loginData.password = value;
                          },
                        ),
                      ],
                    ),
                  )),
            ),
            Positioned(
                bottom: -25,
                left: MediaQuery.of(context).size.width * 3 / 4 / 4,
                child: Container(
                  width: MediaQuery.of(context).size.width * 3 / 4 / 2,
                  height: 50,
                  child: RaisedButton(
                    onPressed: () {
                      Toast.show("Login", context);
                      _login();
                    },
                    textColor: Colors.white,
                    elevation: 20,
                    color: Color(0xFFED7D0D),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(8)),
                    child: const Text('登录', style: TextStyle(fontSize: 20)),
                  ),
                )),
          ],
        ),
      ],
    );
  }

  Future<void> _startMainActivity() async {
    _platform.invokeMethod('startMainActivity').then((value) {
      print("value:startMainActivity");
    }).catchError((e) {
      print(e.message);
    });
  }

  /// 用户名校验
  String _validateName(String value) {
    _isFormWasEdited = true;
    if (value.isEmpty) return '请输入密码';
    final RegExp nameExp = RegExp(r'^[A-Za-z0-9]+$');
    if (!nameExp.hasMatch(value))
      return '请输入[A-Za-z0-9]之内的字符';
    return null;
  }

  /// 密码校验
  String _validatePassword(String value) {
    _isFormWasEdited = true;
    final FormFieldState<String> passwordField = _passwordKey.currentState;
    if (passwordField.value == null || passwordField.value.isEmpty)
      return '请输入密码';
    if (passwordField.value != value)
      return "密码校验失败";
    return null;
  }

  void _login(){
    if( _formKey.currentState.validate()){
      _formKey.currentState.save();
      var params = <String,dynamic>{};
      params["username"] = _loginData.name;
      params["password"] = _loginData.password;
      print(json.encode(params));
      Http.post<User>(Api.apiLogin,false,Callback(
        onDataSuccess: (User result) {
          print("login:"+result.nickname);
          _startMainActivity();
        }
      ),queryParameters: params,);
    }
  }
}





