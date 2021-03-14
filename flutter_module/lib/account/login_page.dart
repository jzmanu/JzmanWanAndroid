import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/bean/user.dart';
import 'package:flutter_module/common/widget/common_widget.dart';
import 'package:flutter_module/main.dart';
import 'package:flutter_module/model/account_model.dart';
import 'package:flutter_module/util/log_util.dart';
import 'package:flutter_module/util/sp_util.dart';
import 'package:toast/toast.dart';

String tag = "LoginWidget";

/// 登录
class LoginWidget extends StatefulWidget {
  @override
  _LoginWidgetState createState() => _LoginWidgetState();
}

class _LoginWidgetState extends State<LoginWidget> implements OnLoginListener {
  /// 密码是否可见
  bool _isPasswordVisible = false;

  /// MethodChannel
  MethodChannel _platform;

  /// 登录账号数据
  AccountData _accountData;
  AccountModel _accountModel;

  String _account, _password;

  final GlobalKey<FormState> _formKey = new GlobalKey<FormState>();

  @override
  void initState() {
    super.initState();
    // MethodChannel
    _platform = const MethodChannel('com.manu.startMainActivity');
    _accountData = AccountData();
    _accountModel = AccountModel(loginListener: this);
  }

  @override
  Widget build(BuildContext context) {
    Widget formColumn = Column(
      children: <Widget>[
        TextFormField(
          // initialValue: _account,
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
          onFieldSubmitted: (value) {
            print('onFieldSubmitted:' + value);
          },
          onSaved: (value) {
            _accountData.name = value;
          },
        ),
        Container(
          height: 1,
          color: Colors.grey[350],
          margin: const EdgeInsets.only(bottom: 16, top: 16),
        ),
        TextFormField(
          // initialValue: _password,
          textCapitalization: TextCapitalization.words,
          textInputAction: TextInputAction.done,
          keyboardType: TextInputType.visiblePassword,
          maxLines: 1,
          // 是否是密码
          obscureText: !_isPasswordVisible,
          validator: _validatePassword,
          decoration: buildPassInputDecoration(context, _isPasswordVisible, () {
            setState(() {
              _isPasswordVisible = !_isPasswordVisible;
            });
          }),
          onFieldSubmitted: (value) {
            print('onFieldSubmitted:' + value);
          },
          onSaved: (value) {
            _accountData.password = value;
          },
        ),
      ],
    );

    return Column(
      children: <Widget>[
        Stack(
          overflow: Overflow.visible,
          children: <Widget>[
            Container(
              width: MediaQuery
                  .of(context)
                  .size
                  .width * 3 / 4,
              height: 230,
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(10),
              ),
              child: Padding(
                  padding: EdgeInsets.all(12),
                  child: Form(key: _formKey, child: formColumn)),
            ),
            Positioned(
              bottom: -25,
              left: MediaQuery
                  .of(context)
                  .size
                  .width * 3 / 4 / 4,
              child: buildAccountButton(context, () => _login()),
            ),
          ],
        ),
      ],
    );
  }

  @override
  void onLoginSuccess(User result) {
    Log.d(tag, "onLoginSuccess > result:" + result.toString());
    _startMainActivity();
  }

  @override
  void onError(String error) {
    Log.d(tag, error);
    Toast.show(error, context);
  }

  /// 跳转首页
  Future<void> _startMainActivity() async {
    _platform.invokeMethod('startMainActivity').then((value) {
      Log.d(tag, "_startMainActivity > value:$value");
    }).catchError((e) {
      Log.d(tag, e.message);
    });
  }

  /// 登录
  Future<void> _loginNative() async {
    /// 表单校验
    if (_formKey.currentState.validate()) {
      _formKey.currentState.save();
      _platform.invokeMethod('login', {
        "username": "${_accountData.name}",
        "password": "${_accountData.password}"
      }).then((value) {
        Log.d(tag, "_loginNative > value:$value");
      }).catchError((e) {
        Log.d(tag, e.message);
      });
    }
  }

  /// 用户名校验
  String _validateName(String value) {
    Log.d(tag, "_validateName > value:$value");
    if (value.isEmpty) return '请输入用户名';
    final RegExp nameExp = RegExp(r'^[A-Za-z0-9]+$');
    if (!nameExp.hasMatch(value)) return '请输入[A-Za-z0-9]之内的字符';
    return null;
  }

  /// 密码校验
  String _validatePassword(String value) {
    Log.d(tag, "_validatePassword > value:$value");
    if (value == null || value.isEmpty) return "请输入密码";
    return null;
  }

  /// 登录
  void _login() {
    Log.d(tag, "login");
    /// 表单校验
    if (_formKey.currentState.validate()) {
      _formKey.currentState.save();

      /// Flutter方式进行登录
      // _accountModel.login(_accountData.name, _accountData.password);
      /// 使用原生的方式登录，便于Cookie统一管理
      _loginNative();
    }
  }
}
