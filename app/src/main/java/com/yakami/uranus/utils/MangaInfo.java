package com.yakami.uranus.utils;

import java.util.Map;

public class MangaInfo{

    String mangaName;
    String author;
    String state;
    String numOfChapter;
    String lastUpdateTime;
    String intro;
    String imgUrl;
    Map<String, String> chapterMap;

    public MangaInfo(String mangaName,String author,String state,String numOfChapter,
                     String lastUpdateTime,String intro,String imgUrl, Map<String, String> chapterMap) {
        this.mangaName = mangaName;
        this.author = author;
        this.state = state;
        this.numOfChapter = numOfChapter;
        this.lastUpdateTime = lastUpdateTime;
        this.intro = intro;
        this.imgUrl = imgUrl;
        this.chapterMap = chapterMap;
    }
}