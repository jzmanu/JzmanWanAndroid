
import 'package:flutter/material.dart';

class Common{
  static final baseUrl = 'https://www.wanandroid.com/';
  static final expires = 'expires';
}


/// 统一的错误监听器
abstract class OnErrorListener{
  void onError(String error);
}