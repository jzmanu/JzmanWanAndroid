
import 'package:flutter_module/bean/bean_factory.dart';

/// 服务端返回基础类封装
class BaseResultBean<Data> {
  Data data;
  int errorCode;
  String errorMsg;

  Map<String, dynamic> resultObj;
  List<dynamic> resultArray;

  BaseResultBean({this.data, this.errorCode, this.errorMsg});

  /// 处理对象
  BaseResultBean.fromJson(Map<String, dynamic> map) {
    resultObj = map['data'];
    errorCode = map['errorCode'];
    errorMsg = map['errorMsg'];
  }

  /// 处理数组
  BaseResultBean.fromJsonArray(Map<String, dynamic> map) {
    errorCode = map['errorCode'];
    errorMsg = map['errorMsg'];
    resultArray = map['data'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['data'] = this.data;
    data['errorCode'] = this.errorCode;
    data['errorMsg'] = this.errorMsg;
    return data;
  }

  T getObj<T>(){
    return BeanFactory.getBean(resultObj);
  }

  List<T> getList<T>(){
    var result = List<T>();
    if (resultArray != null) {
      resultArray.forEach((element) {
        result.add(BeanFactory.getBean(element));
      });
    }
    return result;
  }
}
