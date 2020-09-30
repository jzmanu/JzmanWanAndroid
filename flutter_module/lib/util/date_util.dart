
class DateUtil{

  /// 毫秒数转DateTime
  static DateTime millsToDateTime(mills){
    return DateTime.fromMillisecondsSinceEpoch(mills);
  }

  /// 毫秒数转指定格式
  static String millsToFormatString(mills){
    DateTime dateTime = DateTime.fromMillisecondsSinceEpoch(mills);
    String year = dateTime.year.toString();
    String month = dateTime.month.toString().padLeft(2,'0');
    String day = dateTime.day.toString().padLeft(2,'0');
    String hour = dateTime.hour.toString().padLeft(2,'0');
    String minute = dateTime.minute.toString().padLeft(2,'0');
    String second = dateTime.second.toString().padLeft(2,'0');
    return "$year-$month-$day $hour-$minute-$second";
  }
}