package com.yakami.uranus.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yakami on 2015/12/9.
 */
public class SearchList{
    int TabCount = 0;
    ArrayList<MangaItem> mResult = new ArrayList<>();


    public SearchList(String keyword,int page, String htmlresult){
        //http://so.77mh.com/k.php?word=%E5%B0%91%E5%B9%B4&size=40&field=&ma=0
        String str = "http://so.77mh.com/k.php?word="+keyword+"&size=40&field=Title,SubTitle,Author&ma=0";
        try {
            Pattern pNoResult = Pattern.compile("so_head\">(.+<b>0</b>)"); //搜索结果为0；
            Matcher mNoResult = pNoResult.matcher(htmlresult);
            if(mNoResult.find()) {
//	        	togetOtherSearchWeb();						//去其他搜索页试试？
//                result=null;									//搜索结果为空，返回NULL
            }

            Document doc = Jsoup.parse(htmlresult);

            pNoResult = Pattern.compile("so_head\">(.{3}<b>(\\d+?)</b>)");
            mNoResult = pNoResult.matcher(htmlresult);
            if(mNoResult.find())
                str= mNoResult.group(2);

            TabCount = Integer.parseInt(str);
            System.out.println(TabCount);

            Elements count = doc.getElementsByTag("dl");
            //用作找到的搜索结果的数目
            for(int i=0;i<count.size();i++){
                MangaItem result = new MangaItem();
                Element ListDL = doc.getElementsByTag("dl").get(i);
                String Title = ListDL.getElementsByTag("a").get(1).text().trim();
                result.setTitle(Title);
                String URL = ListDL.getElementsByTag("a").attr("href");
                result.setUrl(URL);
//                new MangaInfo().setUrl(URL);
                String imgURL = ListDL.getElementsByTag("a").first().select("img[src~=]").attr("src");
                result.setImgUrl(imgURL);
                String updateState = ListDL.getElementsByTag("i").get(1).getElementsByTag("a").first().text();
                result.setUpdateState(updateState);
                String author =ListDL.getElementsByTag("i").get(1).getElementsByTag("a").get(1).text().trim();
                result.setAuthor(author);
                String style =ListDL.getElementsByTag("i").get(2).getElementsByTag("span").first().text().trim();
                result.setStyle(style);
                String intro = ListDL.getElementsByTag("i").get(3).getElementsByClass("info").text().trim();
                result.setIntro(intro);
                mResult.add(result);
//			System.out.println(URL+"\t"+imgURL+"\t"+Title+"\t"+updateState+"\t"+author+"\t"+style+"\t"+intro);
            }




        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public SearchList(String keyword,int page ,boolean  pagesize,boolean Title,boolean SubTitle,boolean Author,boolean presear){
        this(keyword,page,"");


    }


    /**
     * @return the result
     */
    public ArrayList getResult() {
        return mResult;
    }


    /**
     * @param result the result to set
     */
//    public void setResult(MangaItem result) {
//        this.result = result;
//    }

}