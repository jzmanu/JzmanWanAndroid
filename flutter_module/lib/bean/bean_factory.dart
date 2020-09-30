
import 'package:flutter_module/bean//banner_bean.dart';
import 'package:flutter_module/bean/user.dart';

String tag = "BeanFactory";

class BeanFactory{

  static T getBean<T>(json){
    if(json == null) return null;
    if(T.toString() == "User"){
      return User.fromJson(json) as T;
    }else if(T.toString() == "BannerBean"){
      return BannerBean.fromJson(json) as T;
    }else{
      return null;
    }
  }
}