package com.yakami.uranus.view.activity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.yakami.uranus.utils.MangaItem;
import com.yakami.uranus.utils.SearchList;
import com.yakami.uranus.utils.Tools;

import java.net.URLEncoder;
import java.util.ArrayList;

public class SearchController {

    SearchActivity mActivity;
    ArrayList<MangaItem> mResultList;

    public SearchController(SearchActivity activity) {
        mActivity = activity;
    }

    public void getSearchResult(String keyword) {
        try {
            String url = "http://so.77mh.com/k.php?word=" + URLEncoder.encode(keyword, "UTF-8") + "&size=40&field=Title,SubTitle,Author&ma=0";

            StringRequest request = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String html) {
//                        mActivity.mTextView.setText(html);
                            mResultList = new SearchList("", 1, html).getResult();
                            mActivity.initRecyclerView(mResultList);
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
        } catch (Exception e) {

        }
    }


}
