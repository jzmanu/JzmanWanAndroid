import 'package:flutter/material.dart';
import 'package:flutter_module/bean/user.dart';
import 'package:flutter_module/common/widget/common_widget.dart';
import 'package:flutter_module/main.dart';
import 'package:flutter_module/model/account_model.dart';
import 'package:flutter_module/util/log_util.dart';
import 'package:toast/toast.dart';

import 'login_page.dart';

/// 注册
class RegisterWidget extends StatefulWidget {
  @override
  _RegisterWidgetState createState() => _RegisterWidgetState();
}

class _RegisterWidgetState extends State<RegisterWidget> implements OnRegisterListener{
  /// 密码是否可见
  bool isPasswordVisible = false;
  bool isRePasswordVisible = false;

  final GlobalKey<FormState> _formKey = new GlobalKey<FormState>();
  final GlobalKey<FormFieldState<String>> _passwordKey = new GlobalKey<FormFieldState<String>>();

  /// 注册账号数据
  AccountData _accountData;
  AccountModel _accountModel;

  @override
  void initState() {
    super.initState();
    _accountData = AccountData();
    _accountModel = AccountModel(registerListener: this);
  }

  @override
  Widget build(BuildContext context) {
    Widget formColumn = Column(
      children: <Widget>[
        TextFormField(
          keyboardType: TextInputType.text,
          textInputAction: TextInputAction.next,
          textCapitalization: TextCapitalization.words,
          maxLines: 1,
          decoration: InputDecoration(
              hintText: '用户名',
              icon: Icon(Icons.person, color: Colors.black87,),
              border: InputBorder.none
          ),
          onSaved: (value){
            Log.d(tag,"name:$value");
            _accountData.name = value;
          },
          validator: _validateName,
        ),
        Container(
          height: 1,
          color: Colors.grey[350],
          margin: const EdgeInsets.only(bottom: 16,top: 16),
        ),
        TextFormField(
          key: _passwordKey,
          textCapitalization: TextCapitalization.words,
          textInputAction: TextInputAction.done,
          keyboardType: TextInputType.visiblePassword,
          maxLines: 1,
          // 是否是密码
          obscureText: !isPasswordVisible,
          decoration: buildPassInputDecoration(context, isPasswordVisible, (){
            setState(() {
              isPasswordVisible = !isPasswordVisible;
            });
          }),
          onSaved: (value){
            _accountData.password = value;
          },
          validator: _validatePassword,
        ),
        Container(
          height: 1,
          color: Colors.grey[350],
          margin: const EdgeInsets.only(bottom: 16,top: 16),
        ),
        TextFormField(
          textCapitalization: TextCapitalization.words,
          textInputAction: TextInputAction.done,
          keyboardType: TextInputType.visiblePassword,
          maxLines: 1,
          // 是否是密码
          obscureText: !isRePasswordVisible,
          decoration: buildPassInputDecoration(context, isRePasswordVisible, (){
            setState(() {
              isRePasswordVisible = !isRePasswordVisible;
            });
          }),
          onSaved: (value){
            _accountData.rePassword = value;
          },
          validator: _validateRepeatPassword,
        ),
      ],
    );

    return Column(
      children: <Widget>[
        Stack(
          overflow: Overflow.visible,
          children: <Widget>[
            Container(
              width: MediaQuery.of(context).size.width * 3 / 4,
              height: 330,
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(10),
              ),
              child: Padding(
                padding: EdgeInsets.all(12),
                child: Form(
                  key: _formKey,
                  child: formColumn
                ),
              ),
            ),
            Positioned(
                bottom: -25,
                left: MediaQuery.of(context).size.width * 3 / 4 / 4,
                child: buildAccountButton(context, () => _register()),
            ),
          ],
        )
      ],
    );
  }

  @override
  void onRegisterSuccess(User result) {
    Log.d(tag, "onRegisterSuccess > result:"+result.toString());
    Toast.show("${result.username},请登录", context);
    AccountPageState.instance.animateToPage(0);

  }
  @override
  void onError(String error) {
    Log.d(tag, "onError > error:"+error);
    Toast.show(error, context);
  }

  /// 用户名校验
  String _validateName(String value) {
    Log.d(tag, "_validateName > value:$value");
    if (value.isEmpty) return '请输入密码';
    final RegExp nameExp = RegExp(r'^[A-Za-z0-9]+$');
    if (!nameExp.hasMatch(value))
      return '请输入[A-Za-z0-9]之内的字符';
    return null;
  }

  /// 密码校验
  String _validatePassword(String value) {
    Log.d(tag, "_validatePassword > value:$value");
    if(value == null || value.isEmpty) return "请输入密码";
    return null;
  }

  /// 重复密码校验
  String _validateRepeatPassword(String value) {
    Log.d(tag, "_validateRepeatPassword > value:$value");
    final FormFieldState<String> passwordField = _passwordKey.currentState;
    if (passwordField.value == null || passwordField.value.isEmpty)
      return '请输入密码';
    if(value == null || passwordField.value.isEmpty)
      return "请重复输入密码";
    if (passwordField.value != value)
      return "两次密码输入不一致";
    return null;
  }

  /// 注册
  void _register(){
    /// 表单校验
    if( _formKey.currentState.validate()){
      _formKey.currentState.save();
      _accountModel.register(_accountData.name,_accountData.password,_accountData.rePassword);
    }
  }
}
