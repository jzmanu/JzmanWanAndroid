
/// 日志工具类
class Log{

  static bool debug = false;

  static d(String tag, String message){
    if(debug){
      i(tag, message);
    }
  }

  static i(String tag, String message){
      print("$tag > $message");
  }
}