本项目使用 [玩安卓](https://www.wanandroid.com/) 提供的 API 开发，采用 `MVP` 架构，项目中使用 `Flutter`和原生混合开发的方式实现了部分功能，如注册登录功能，使用到的优秀开源框架有 `Dagger2`、`Retrofit`、`RxJava2` 等，项目已经全面转换到 `androidx`，由于日常工作很少接触常用 App 的开发，故作为练手项目利用业务时间开发，如果你正好需要一个完整项目练手，那个这个绝对适合你。

由于该项目搭建的早，其中像依赖注入框架 `Dagger2` 还未替换为更好用的 `Hilt`，`Hilt` 以及最近很火的是 声明式 UI 写作方式 `Compose` 也是 `Jetpack` 系列的组件，考虑到替换工作量大，后续会以新建分支的方式不断迭代进去，且看后续更新，项目地址如下：

- https://github.com/jzmanu/JzmanWanAndroid


### 实现功能

- 登录注册
- 首页文章
- 项目分类
- 知识体系
- 文章详情
- 收藏功能
- 积分信息
- 我的分享
- App升级
- 未完待续

欢迎在[issue](https://github.com/jzmanu/JzmanWanAndroid/issues)上反应 bug 以便及时修复。

### 项目效果 

如果效果图不能清晰展示建议查看：[玩安卓开源客户端介绍](https://www.yuque.com/docs/share/9bbf8265-56a3-42a2-81cd-c470beb0de8c?#)。

| ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735084709-e9a6f8b1-dd5f-45e3-a0fe-bf2115e04c01.gif) | ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735087134-53209091-f823-4e15-9a53-c6fa7d32dbcf.gif) | ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735065536-b09fb3b7-c018-4456-b83b-472761390494.gif) |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735075666-69b6ba3b-b6d2-4cc1-b651-14080f5cd3cf.gif) | ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735089875-04bfe6bf-41a7-4562-9637-4aada7288c79.gif) | ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735082254-331a0765-3fc9-4ad2-9aa0-f1800984e1ce.gif) |
| ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735071598-9378a1fe-e660-4686-b509-20a3aa236877.gif) | ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735080167-b47770fe-2c43-4cab-83ce-c55185295dde.gif) | ![](https://cdn.nlark.com/yuque/0/2021/gif/644330/1615735096755-7f28f940-e6d7-4d55-ac17-3475178311b7.gif) |


### apk下载体验

下载地址：[JzmanWanAndroid-1.0-release.apk](https://github.com/jzmanu/JzmanWanAndroid/releases/download/v1.0/JzmanWanAndroid-v1.0-release.apk)

![jzmanwanandroid](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/00fcae9d8d2749dda60eaf379a5a2975~tplv-k3u1fbpfcp-watermark.image)


### 后续计划

首先作为练手项目当然要引入新的技术，**时刻保持技术更新**，并体现在项目上，其次继续添加新模块，优化显示细节，比如目前的版本中优化了简书文章的显示等，还有其他小众站点的文章显示待优化，后续会优先不定期完成如下模块：

- 文章搜索
- 面试专题
- 每日一问
- 阅读历史
- 深色主题
- 继续补充中...

### 如何运行项目

某个网友微信公众号反馈项目不能运行，由于时间过了几天不能够主动回复消息，这个补充到 README 中，如果自己环境不能直接运行：

1. 先运行 Android 子项目 JzmanWanAndroid\flutter_module\.android 能够远行无报错，如有报错多半是环境问题，按照提示处理。
2. 再运行 JzmanWanAndroid 原项目则可正常运行。

亲测没问题，如还有问题提个 [issue](https://github.com/jzmanu/JzmanWanAndroid/issues) 。

### 联系作者

个人微信公众号：**躬行之** 。

![](https://img-blog.csdnimg.cn/img_convert/330428d616042c434da017550213df07.png)
