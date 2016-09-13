package com.yakami.uranus.view.activity;

import android.content.SharedPreferences;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.yakami.uranus.utils.MangaInfoParser;
import com.yakami.uranus.utils.Tools;

/**
 * Created by Yakami on 2015/12/10.
 */
public class MangaInfoController {

    private MangaInfoActivity mActivity;

    public MangaInfoController(MangaInfoActivity activity) {
        mActivity = activity;
    }

    public void initMangaInfo(String url) {
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            s = new String(s.getBytes("iso-8859-1"),"utf-8");
                            MangaInfoParser parser = new MangaInfoParser();
                            parser.parse(s);
                            mActivity.initView(parser.mImgUrl, parser.mName, parser.mAuthor,
                                    parser.mState, parser.mChapterNum, parser.mUpdateTime, parser.mIntro,
                                    parser.mChapterList);
                        } catch (Exception e) {
                            Tools.toast(mActivity.getContext(), "转码错误");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Tools.toast(mActivity.getContext(), volleyError.toString());
                        mActivity.showErrorText();
                    }
                });
        mActivity.mQueue.add(request);
    }

    public void saveCollection(String name, String url, String imgUrl) {
        SharedPreferences.Editor editor = mActivity.getSharedPreferences("lock", mActivity.MODE_WORLD_WRITEABLE).edit();
        editor.putString(getAviableName(), String.format("%s;%s;%s", name, url, imgUrl));
    }

    public String readCollection(String name) {
        SharedPreferences read = mActivity.getSharedPreferences("lock", mActivity.MODE_WORLD_READABLE);
        return read.getString(name, "");
    }

    public String getAviableName() {
        for (int i = 0; i < 1000; i++) {
            if (Tools.isEmpty(readCollection("collection" + i)))
                return "collection" + i;
        }
        return "collection10001";
    }
}
