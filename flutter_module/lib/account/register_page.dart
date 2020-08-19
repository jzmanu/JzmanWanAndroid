import 'package:flutter/material.dart';

/// 注册
class RegisterWidget extends StatefulWidget {
  @override
  _RegisterWidgetState createState() => _RegisterWidgetState();
}

class _RegisterWidgetState extends State<RegisterWidget> {
  /// 密码是否可见
  bool isPasswordVisible = false;
  bool isRePasswordVisible = false;
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Stack(
          overflow: Overflow.visible,
          children: <Widget>[
            Container(
              width: MediaQuery.of(context).size.width * 3 / 4,
              height: 280,
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(10),
              ),
              child: Padding(
                padding: EdgeInsets.all(12),
                child: Form(
                  child: Column(
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
                        obscureText: !isPasswordVisible,
                        decoration: InputDecoration(
                          hintText: '密码',
                          icon: Icon(Icons.lock, color: Colors.black87,),
                          border: InputBorder.none,
                          suffixIcon: IconButton(
                            icon: Icon(
                              //根据isPasswordVisible状态显示不同的图标
                              isPasswordVisible ? Icons.visibility : Icons.visibility_off,
                              color: Theme.of(context).primaryColorDark,
                            ),
                            onPressed: (){
                              setState(() {
                                isPasswordVisible = !isPasswordVisible;
                              });
                            },
                          ),
                        ),
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
                        obscureText: !isPasswordVisible,
                        decoration: InputDecoration(
                          hintText: '重复密码',
                          icon: Icon(Icons.lock, color: Colors.black87,),
                          border: InputBorder.none,
                          suffixIcon: IconButton(
                            icon: Icon(
                              //根据isRePasswordVisible状态显示不同的图标
                              isRePasswordVisible ? Icons.visibility : Icons.visibility_off,
                              color: Theme.of(context).primaryColorDark,
                            ),
                            onPressed: (){
                              setState(() {
                                isRePasswordVisible = !isRePasswordVisible;
                              });
                            },
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            Positioned(
                bottom: -25,
                left: MediaQuery.of(context).size.width * 3 / 4 / 4,
                child: Container(
                  width: MediaQuery.of(context).size.width * 3 / 4 / 2,
                  height: 50,
                  child: RaisedButton(
                    onPressed: () {},
                    textColor: Colors.white,
                    elevation: 20,
                    color: Color(0xFFED7D0D),
                    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
                    child: const Text('注册', style: TextStyle(fontSize: 20)),
                  ),
                )
            ),
          ],
        )
      ],
    );
  }
}