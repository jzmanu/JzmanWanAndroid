import 'package:dio/dio.dart';

/// Http工具类
class Http{

  Http._();

  static final _instance = Http._();

  factory Http.getInstance() => _instance;

  static void get(String url) async{
    try{
      Response response = await Dio().get(url);
      if(response.statusCode == 200){

      }
    } on DioError catch(e){

    }

  }
}