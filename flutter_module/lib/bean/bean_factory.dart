
import 'package:flutter_module/bean/user.dart';

String tag = "BeanFactory";

class BeanFactory{

  static T getBean<T>(json){
    if(json == null) return null;
    if(T.toString() == "User"){
      return User.fromJson(json) as T;
    }else{
      return null;
    }
  }
}