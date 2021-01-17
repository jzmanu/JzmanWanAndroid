package com.manu.wanandroid.bean;

import java.util.List;

/**
 * @Desc: ArticleBean
 * @Author: jzman
 * @Date: 2019/5/8 0008 11:16
 */
public class ArticleBean {

    /**
     * apkLink :
     * author : TeaOf
     * chapterId : 99
     * chapterName : 具体案例
     * collect : false
     * courseId : 13
     * desc :
     * envelopePic :
     * fresh : false
     * id : 8356
     * link : https://www.jianshu.com/p/f144d6645877
     * niceDate : 2019-05-04
     * origin :
     * prefix :
     * projectLink :
     * publishTime : 1556976665000
     * superChapterId : 94
     * superChapterName : 自定义控件
     * tags : []
     * title : 仿写一个QQ空间图片预览Dialog
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */

    private String apkLink;
    private String author;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    private int id;
    private String link;
    private String niceDate;
    private String origin;
    private String prefix;
    private String projectLink;
    private long publishTime;
    private int superChapterId;
    private String superChapterName;
    private String title;
    private String shareUser;
    private int type;
    private int userId;
    private int visible;
    private int zan;
    private List<?> tags;

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "apkLink='" + apkLink + '\'' +
                ", author='" + author + '\'' +
                ", chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", collect=" + collect +
                ", courseId=" + courseId +
                ", desc='" + desc + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                ", fresh=" + fresh +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", niceDate='" + niceDate + '\'' +
                ", origin='" + origin + '\'' +
                ", prefix='" + prefix + '\'' +
                ", projectLink='" + projectLink + '\'' +
                ", publishTime=" + publishTime +
                ", superChapterId=" + superChapterId +
                ", superChapterName='" + superChapterName + '\'' +
                ", title='" + title + '\'' +
                ", shareUser='" + shareUser + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", visible=" + visible +
                ", zan=" + zan +
                ", tags=" + tags +
                '}';
    }
}