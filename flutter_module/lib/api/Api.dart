class Api{

  /// 登录
  /// 参数：username,password,repassword
  /// 请求方式：POST
  static final String apiRegister = "user/register";

  /// 登录
  /// 参数：username，password
  /// 请求方式：POST
  static final String apiLogin = "user/login";

  /// 退出登录
  /// 请求方式：GET
  static final String apiLoginOut = "user/logout/json";

  /// 获取首页文章
  static final String apiHomeArticle = "article/list/0/json";

  /// 获取banner列表
  static final String apiBanner = "banner/json";

}