import 'package:dio/dio.dart';

/// Http工具类
class Http{
  static void get(String url) async{
    try{
      Response response = await Dio().get(url);
      if(response.statusCode == 200){
//        response.data
      }
    } on DioError catch(e){

    }

  }
}