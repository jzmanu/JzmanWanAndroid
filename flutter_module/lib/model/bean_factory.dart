
import 'package:flutter_module/model/banner_bean.dart';
import 'package:flutter_module/model/user.dart';

class BeanFactory{

  static T getBean<T>(json){
    print('getBean:$json');
    if(T.toString() == "User"){
      return User.fromJson(json) as T;
    }else if(T.toString() == "BannerBean"){
      return BannerBean.fromJson(json) as T;
    }else{
      return null;
    }
  }
}