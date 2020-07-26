import 'package:bubble_tab_indicator/bubble_tab_indicator.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:toast/toast.dart';

void main() => runApp(LoginApp());

class LoginApp extends StatelessWidget {
  static final String routeName = "/login";

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Login",
      theme: ThemeData.light(),
      home: Scaffold(
          body: Column(
            children: <Widget>[
              Expanded(
                flex: 1,
                child: Container(
                  width: double.infinity,
                  child: AccountPage(),
                  decoration: BoxDecoration(
                      image: DecorationImage(
                          image: AssetImage('image/bg_src_tianjin.jpg'),
                          fit: BoxFit.cover)),
                ))
          ],
      )));
  }
}

/// LoginPage
class AccountPage extends StatefulWidget {
  @override
  State createState() => _AccountPageState();
}

class _AccountPageState extends State<AccountPage>
    with SingleTickerProviderStateMixin {
  /// Tabs
  var tabs = <Tab>[
    Tab(text: "登录",),
    Tab(text: "注册",),
  ];

  /// Widgets
  var widgets = <Widget>[
    _LoginWidget(),
    _RegisterWidget()
  ];

  TabController tabController ;
  PageController pageController;
  double radius = 40;
  double height = 46;

  @override
  void initState() {
    super.initState();
    tabController = TabController(length: 2, vsync: this, initialIndex: 0);
    pageController = PageController(initialPage: 0);
    tabController.addListener((){
      var index = tabController.index;
      print("index:$index");
      var height = MediaQuery.of(context).size.height;
      print("height:$height");
      print("index:$index");
      pageController.animateToPage(
          tabController.index,
          duration: Duration(milliseconds: 300),
          curve: Curves.linear);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Container(
          width: MediaQuery.of(context).size.width * 3 / 4,
          height: height,
          margin: const EdgeInsets.only(top: 150),
          decoration: BoxDecoration(
            /// Tab背景颜色
              color: Color.fromARGB(255,172, 108, 96),
              borderRadius: BorderRadius.circular(radius)
          ),
          child: Theme(
            data: ThemeData(
                highlightColor: Colors.transparent,
                splashColor: Colors.transparent
            ),
            child: TabBar(
              tabs: tabs,
              controller: tabController,
              labelColor: Colors.black87,
              labelStyle: TextStyle(fontSize: 18),
              unselectedLabelColor: Colors.black87,
              indicator: BubbleTabIndicator(
                indicatorColor: Colors.white,
                indicatorRadius: radius,
                indicatorHeight: height,
                padding: EdgeInsets.zero,
                insets: EdgeInsets.zero,
              ),
            ),
          ),
        ),
        Container(
          constraints: BoxConstraints(maxHeight: 400,minHeight: 300),
          margin: const EdgeInsets.only(top: 20),
          padding: const EdgeInsets.all(10),
          child: PageView.builder(
            itemCount: tabs.length,
            itemBuilder: (BuildContext context, int index){
              return widgets[index];
            },
            controller: pageController,
            onPageChanged: (index){
              tabController.animateTo(
                  index,
                  duration: Duration(milliseconds: 300),
                  curve: Curves.linear);
            },
          ),
        )
      ],
    );
  }
}

/// 登录
class _LoginWidget extends StatefulWidget {
  @override
  _LoginWidgetState createState() => _LoginWidgetState();
}

class _LoginWidgetState extends State<_LoginWidget> {
  /// 密码是否可见
  bool isPasswordVisible = false;
  static  MethodChannel platform  ;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    //android 向 flutter 通信
    platform = const MethodChannel('com.manu.startMainActivity');
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
                    child: Column(
                      children: <Widget>[
                        TextFormField(
                          keyboardType: TextInputType.text,
                          textInputAction: TextInputAction.next,
                          textCapitalization: TextCapitalization.words,
                          maxLines: 1,
                          decoration: InputDecoration(
                              hintText: '账号',
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
                                // 根据isPasswordVisible状态显示不同的图标
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

                      ],
                    ),
                  )
              ),
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
//                    _startMainActivity();
                    platform.invokeMethod('startMainActivity').then((value) {
                      print("value:startMainActivity");
                    }).catchError((e) {
                      print(e.message);
                    });
                  },
                  textColor: Colors.white,
                  elevation: 20,
                  color: Color(0xFFED7D0D),
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
                  child: const Text('登录', style: TextStyle(fontSize: 20)),
                ),
              )
            ),
          ],
        ),
      ],
    );
  }

  Future<void> _startMainActivity() async {
    try {
      await platform.invokeMethod('startMainActivity');
    } on PlatformException catch (e) {
      print("_startMainActivity:${e.message}");
    }
    setState(() {

    });
  }

}


/// 注册
class _RegisterWidget extends StatefulWidget {
  @override
  _RegisterWidgetState createState() => _RegisterWidgetState();
}

class _RegisterWidgetState extends State<_RegisterWidget> {
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



