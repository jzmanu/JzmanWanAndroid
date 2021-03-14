import 'package:flutter/material.dart';

/// 通用的密码输入装饰器
InputDecoration buildPassInputDecoration(
    BuildContext context, bool isPassVisible, VoidCallback onPressed) {
  return InputDecoration(
    hintText: '密码',
    icon: Icon(
      Icons.lock,
      color: Colors.black87,
    ),
    border: InputBorder.none,
    suffixIcon: IconButton(
      icon: Icon(
        // 根据isPasswordVisible状态显示不同的图标
        isPassVisible ? Icons.visibility : Icons.visibility_off,
        color: Theme.of(context).primaryColorDark,
      ),
      onPressed: onPressed,
    ),
  );
}

/// 账号注册、登录按钮
Container buildAccountButton(BuildContext context,VoidCallback onPressed){
  return Container(
    width: MediaQuery.of(context).size.width * 3 / 4 / 2,
    height: 50,
    child: RaisedButton(
      onPressed: onPressed,
      textColor: Colors.white,
      elevation: 20,
      color: Color(0xFFED7D0D),
      shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8)),
      child: const Text('登录', style: TextStyle(fontSize: 20)),
    ),
  );
}

