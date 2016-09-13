package com.yakami.uranus.utils;

/**
 * Created by Yakami on 2015/12/9.
 */
public class MangaItem {
    public String title;
    public String url;
    public String author;
    public String style;
    public String updateState;
    public String intro;
    public String imgUrl;

    public MangaItem() {
    }

    public MangaItem(MangaItem i) {
        this.title = i.title;
        this.url = i.url;
        this.author = i.author;
        this.style = i.style;
        this.imgUrl = i.imgUrl;
        this.intro = i.intro;
        this.updateState = i.updateState;
    }

    public MangaItem(String title, String url, String author, String style, String updateState,
                     String lastUpdate, String intro, String imgUrl) {
        super();
        this.title = title;
        this.url = url;
        this.author = author;
        this.style = style;
        this.updateState = updateState;
        this.intro = intro;
        this.imgUrl = imgUrl;
    }


    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getUpdateState() {
        return updateState;
    }


    public void setUpdateState(String updateState) {
        this.updateState = updateState;
    }


    public String getIntro() {
        return intro;
    }


    public void setIntro(String intro) {
        this.intro = intro;
        if (intro.equals("")) {
            this.intro = "简介: 无";
        }

    }


    public String getImgUrl() {
        return imgUrl;
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "标题： " + title + "\n"
                + "图片地址：" + imgUrl + "\n"
                + "首链接：" + url + "\n"
                + "作者：" + author + "\n"
                + "更新状态：" + updateState + "\n"
                + "类型：" + style + "\n"
                + "描述：" + intro + "\n";


    }


}