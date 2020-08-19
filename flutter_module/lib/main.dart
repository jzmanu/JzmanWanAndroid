import 'package:bubble_tab_indicator/bubble_tab_indicator.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';

import 'account/login_page.dart';
import 'account/register_page.dart';

void main() => runApp(AccountApp());

class AccountApp extends StatelessWidget {
  static final String routeName = "/login";

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Account",
      theme: ThemeData.light(),
      home: Scaffold(
          drawerDragStartBehavior: DragStartBehavior.down,
          body: Container(
            child: AccountPage(),
            constraints: BoxConstraints.expand(),
            decoration: BoxDecoration(
                image: DecorationImage(
                    image: AssetImage('image/bg_src_tianjin.jpg'),
                    fit: BoxFit.cover)),
          )
    ));
  }
}

/// AccountPage
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
    LoginWidget(),
    RegisterWidget()
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
    return SingleChildScrollView(
      child: Column(
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
            constraints: BoxConstraints(maxHeight: 400,minHeight: 200),
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
      ),
    );
  }
}

class AccountData {
  String name = '';
  String password = '';
  String rePassword = '';
}


