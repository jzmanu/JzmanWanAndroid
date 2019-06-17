package com.manu.wanandroid.http.rx;

import java.util.List;

/**
 * @Desc: BasePageBean
 * @Author: jzman
 * @Date: 2019/5/15 0015 17:00
 */
public class BasePageBean<T> {

    /**
     * curPage : 2
     * datas : [{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8416,"link":"https://mp.weixin.qq.com/s/FiQEa0M93eSi1i4PzW_6Nw","niceDate":"2019-05-10","origin":"","prefix":"","projectLink":"","publishTime":1557417600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"面试加分项：RecyclerView全面的源码解析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" W_BinaryTree","chapterId":77,"chapterName":"响应式编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8386,"link":"https://juejin.im/post/5908572844d904006939b6eb","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557411491000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"拥抱RxJava（番外篇）：关于RxJava的Tips &amp; Tricks","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" W_BinaryTree","chapterId":77,"chapterName":"响应式编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8385,"link":"https://juejin.im/post/5a2549576fb9a04519696d45","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557410732000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"一篇不太一样的RxJava介绍","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"W_BinaryTree","chapterId":77,"chapterName":"响应式编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8384,"link":"https://juejin.im/post/5cd04b6e51882540e53fdfa2","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557410336000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"我为什么不再推荐RxJava","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" 欢子","chapterId":453,"chapterName":"6.0 ( M )","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8383,"link":"https://juejin.im/post/5cd38f7de51d45368a619a7b","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557410263000,"superChapterId":453,"superChapterName":"版本适配","tags":[],"title":"【源码阅读】AndPermission源码阅读","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"梁山boy","chapterId":308,"chapterName":"多线程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8382,"link":"https://juejin.im/post/5cd1d1876fb9a031eb58ae4d","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557410170000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"解读 JUC &mdash;&mdash; AQS 独占模式","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Android丶SE开发","chapterId":99,"chapterName":"具体案例","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8381,"link":"https://juejin.im/post/5cd3e92de51d4514df420749","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557410114000,"superChapterId":94,"superChapterName":"自定义控件","tags":[],"title":"Android进阶：十二、最简单的方式实现自定义阴影效果","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" 看书的小蜗牛","chapterId":171,"chapterName":"binder","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8380,"link":"https://juejin.im/post/5cd239116fb9a0321d73c127","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557410082000,"superChapterId":173,"superChapterName":"framework","tags":[],"title":"Android中mmap原理及应用简析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" 张风捷特烈","chapterId":171,"chapterName":"binder","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8379,"link":"https://juejin.im/post/5cd2971f51882541c90ee112","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557410051000,"superChapterId":173,"superChapterName":"framework","tags":[],"title":"Android点将台：你敢摸我猫 [- IPC -]","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Listen ","chapterId":77,"chapterName":"响应式编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8378,"link":"https://listenzz.github.io/android-lifecyle-works-perfectly-with-rxjava.html","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557409925000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"Android 生命周期架构组件与 RxJava 完美协作","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8408,"link":"https://mp.weixin.qq.com/s/_8p5R4kKxEfiBeqeAIPTeA","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557331200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"清洁和新 Android 架构的认知诉求","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8415,"link":"https://mp.weixin.qq.com/s/-gLXNbYipppRfkHY0DQxcA","niceDate":"2019-05-09","origin":"","prefix":"","projectLink":"","publishTime":1557331200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"今天我们深入探讨一下如何写好异步代码","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"玉刚说","chapterId":410,"chapterName":"玉刚说","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8402,"link":"https://mp.weixin.qq.com/s/vfN6oir3vHUvKpKzvMbihw","niceDate":"2019-05-08","origin":"","prefix":"","projectLink":"","publishTime":1557244800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/410/1"}],"title":"深入 Java 虚拟机之面试总结篇","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8407,"link":"https://mp.weixin.qq.com/s/6_4qoGpVaae5dHC_UKtNqw","niceDate":"2019-05-08","origin":"","prefix":"","projectLink":"","publishTime":1557244800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android 开发中关于摄像头方向的理解","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8410,"link":"https://mp.weixin.qq.com/s/KgUbnQpgTmJjmO1ibQm5SA","niceDate":"2019-05-08","origin":"","prefix":"","projectLink":"","publishTime":1557244800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"开源一个 Android 图片压缩框架","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"奇葩AnJoiner","chapterId":77,"chapterName":"响应式编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8376,"link":"https://www.jianshu.com/p/79cb4e1c9771","niceDate":"2019-05-07","origin":"","prefix":"","projectLink":"","publishTime":1557236172000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"Rx系列之Rxjava操作符进阶-使用场景","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8369,"link":"https://mp.weixin.qq.com/s/kfdUfPj1XDpoajqRwqSpzQ","niceDate":"2019-05-07","origin":"","prefix":"","projectLink":"","publishTime":1557158400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android 适配之版本适配","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8372,"link":"https://mp.weixin.qq.com/s/sBmDQXbs1AwNTtTEaf3F-g","niceDate":"2019-05-07","origin":"","prefix":"","projectLink":"","publishTime":1557158400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android 这些内容你应该知道 | 3 期","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8374,"link":"https://mp.weixin.qq.com/s/3Q3aJ0mYUWU5bo8rZOhDcA","niceDate":"2019-05-07","origin":"","prefix":"","projectLink":"","publishTime":1557158400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"Jetpack核心组件，ViewModel的使用及原理解析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"wuyr","chapterId":363,"chapterName":"创意汇","collect":false,"courseId":13,"desc":"Base64刚好对应64卦象，编码时先将目标字符串转成标准的Base64，然后拆分每一个字符，从映射表中取出对应的卦象。解码同理，先把卦象码通过映射表得到Base64，然后解码Base64。\r\n","envelopePic":"https://wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":false,"id":8365,"link":"http://www.wanandroid.com/blog/show/2560","niceDate":"2019-05-06","origin":"","prefix":"","projectLink":"https://github.com/wuyr/HexagramDecoder","publishTime":1557157262000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=363"}],"title":"易经64卦编解码（HexagramDecoder）","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 20
     * over : false
     * pageCount : 324
     * size : 20
     * total : 6480
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<T> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

}
