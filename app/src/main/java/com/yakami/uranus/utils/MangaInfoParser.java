package com.yakami.uranus.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MangaInfoParser {

    public String mImgUrl;
    public String mName;
    public String mAuthor;
    public String mState;
    public String mChapterNum;
    public String mUpdateTime;
    public String mIntro;
    public ArrayList<ChapterInfo> mChapterList = new ArrayList<>();

    public void parse(String htmlStr) {
        Document html = Jsoup.parse(htmlStr);
        Element content = html.getElementsByAttributeValue("class", "ar_list_coc").get(0);
        mImgUrl = content.getElementsByTag("img").get(0).attr("src");
        Element introContainer = content.getElementsByTag("ul").get(0);
        mName = introContainer.getElementsByTag("h1").get(0).text();
        mAuthor = introContainer.getElementsByTag("a").get(0).text();
        mState = introContainer.getElementsByTag("a").get(1).text();
        mChapterNum = introContainer.getElementsByTag("li").get(3).text().replace("章数", "").replace("\"", "");
        mUpdateTime = introContainer.getElementsByTag("li").get(4).text().replace("更新", "").replace("\"", "");
        mIntro = introContainer.getElementById("det").text();
        Elements chapters = content.getElementsByTag("ul").get(2).getElementsByTag("li");
        for (Element element : chapters) {
            Element tmp = element.getElementsByTag("a").get(0);
            mChapterList.add(new ChapterInfo(tmp.attr("title"), "http://www.77mh.com"+ tmp.attr("href")));
        }
    }

}
