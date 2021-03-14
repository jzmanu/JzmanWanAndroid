import 'package:shared_preferences/shared_preferences.dart';

import 'log_util.dart';

class Sp{

  static putString(String key, String value) async{
    var prefs = await SharedPreferences.getInstance();
    await prefs.setString(key, value);
  }

  static putInt(String key, int value) async{
    var prefs = await SharedPreferences.getInstance();
    await prefs.setInt(key, value);
  }

  static getString(String key,Function callback) async{
    SharedPreferences.getInstance().then((prefs) {
      callback(prefs.getString(key));
    });
  }

  static getInt(String key,Function callback) async{
    SharedPreferences.getInstance().then((prefs) {
      callback(prefs.getInt(key));
    });
  }
}