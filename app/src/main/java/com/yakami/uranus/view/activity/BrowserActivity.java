package com.yakami.uranus.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yakami.uranus.R;
import com.yakami.uranus.base.BaseActivity;
import com.yakami.uranus.utils.smartimageview.SmartImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BrowserActivity extends BaseActivity {

    @Bind(R.id.browser_recyclerview) RecyclerView mRecyclerView;
    ArrayList<String> mDatas;
    LinearLayoutManager mLinearLayoutManager;
    private BrowserAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
        mDatas = (ArrayList<String>) getIntent().getBundleExtra("data").getSerializable("urlList");
        initList();
        mLinearLayoutManager.scrollToPosition(getIntent().getBundleExtra("data").getInt("position"));
    }


    public void initList() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter = new BrowserAdapter());
    }

    class BrowserAdapter extends RecyclerView.Adapter<BrowserAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_browser, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.image.setImageUrl(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            SmartImageView image;

            public MyViewHolder(View view) {
                super(view);
                image = (SmartImageView) view.findViewById(R.id.browser_item_img);
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Tools.toast(getContext(), "点击位置：" + getAdapterPosition());
//                        Intent intent = new Intent(getContext(), MangaInfoActivity.class);
//                        Bundle data = new Bundle();
//                        data.putString("url", mDatas.get(getAdapterPosition()).getUrl());
//                        intent.putExtra("data", data);
//                        getContext().startActivity(intent);
//                    }
//                });
            }
        }
    }
}
