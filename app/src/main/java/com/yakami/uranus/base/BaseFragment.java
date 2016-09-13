package com.yakami.uranus.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.yakami.uranus.R;
import com.yakami.uranus.utils.Tools;

/**
 * Created by Yakami on 2015/11/28.
 */
public class BaseFragment extends Fragment {

    public Context mContext;
    public LayoutInflater mInflater;
    public Resources resources;
    public Toolbar mToolbar;
    public TextView mTitle;
    public int mCatalog;
    public static final String TAG = "BASE";
    public static final int BUNDLE_CATALOG_SEARCH_PAGER = 1;
    public static final int BUNDLE_CATALOG_SEARCH_RESULT = 2;
    public static final int BUNDLE_CATALOG_HOT = 3;
    public static final int BUNDLE_CATALOG_COLLECT = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mContext = getActivity();
        resources = mContext.getResources();
        mInflater = getLayoutInflater(savedInstanceState);
        Log.e("base", " create baseFragment");
    }

    /*由于setRetainInstance(true)，重建时fragment的create会比activity快，所以在这绑定控件*/
    @Override
    public void onResume() {
        super.onResume();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
    }


    public void toast(String str) {
        Tools.toast(mContext, str);
    }
}
