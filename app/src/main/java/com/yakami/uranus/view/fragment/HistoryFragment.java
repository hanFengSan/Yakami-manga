package com.yakami.uranus.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yakami.uranus.R;
import com.yakami.uranus.base.BaseFragment;

/**
 * Created by Yakami on 2015/11/28.
 */
public class HistoryFragment extends BaseFragment {

   @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       Log.e(TAG, "create recommendFragment");
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        Log.e(TAG, "create recommendFragment view");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "destroy recommendFragment view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "destroy recommendFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "resume recommendFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "pause recommendFragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "stop recommendFragment");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "start recommendFragment");
    }
}
