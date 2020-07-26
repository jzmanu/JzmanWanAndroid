/// 服务端返回基础类封装
class BaseResultBean<T> {
  num errorCode;
  String errorMsg;
  T data;
}
