package com.yakami.uranus.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yakami.uranus.R;
import com.yakami.uranus.base.BaseFragment;
import com.yakami.uranus.utils.Tools;
import com.yakami.uranus.view.activity.SearchActivity;

/**
 * Created by Yakami on 2015/11/28.
 */
public class SearchFragment extends BaseFragment {

    Button mSearchButton;
    EditText mEditText;

   @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mSearchButton = (Button) view.findViewById(R.id.btn_search);
        mEditText = (EditText) view.findViewById(R.id.search_string);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                Bundle data = new Bundle();
                data.putString("searchString", mEditText.getText().toString());
                intent.putExtra("data", data);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    public void saveSearch(String keyword) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("lock", getActivity().MODE_WORLD_WRITEABLE).edit();
        //步骤2-2：将获取过来的值放入文件
        editor.putString(getAviableName(), keyword);
        //步骤3：提交
        editor.commit();
        Tools.toast(getContext(), readSearch("search0"));
    }

    public String readSearch(String name) {
        SharedPreferences read = getActivity().getSharedPreferences("lock", getActivity().MODE_WORLD_READABLE);
        return read.getString(name, "");
    }

    public String getAviableName() {
        for (int i = 0; i < 1000; i++) {
            if (Tools.isEmpty(readSearch("search" + i)))
                return "search" + i;
        }
        return "search10001";
    }
}
