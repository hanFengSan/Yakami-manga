package com.yakami.uranus.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.yakami.uranus.R;
import com.yakami.uranus.base.BaseActivity;
import com.yakami.uranus.utils.smartimageview.SmartImageView;
import com.yakami.uranus.utils.smartimageview.WebImage;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChapterActivity extends BaseActivity {

    @Bind(R.id.chapter_toolbar) Toolbar mToolbar;
    @Bind(R.id.chapter_loading) TextView mLoadingText;
    @Bind(R.id.chapter_progressBar) ProgressBar mProgressBar;
    @Bind(R.id.img_list) RecyclerView mRecyclerView;

    private ChapterController mController;
    RequestQueue mQueue;
    String mChapterName;
    String mChapterUrl;
    ArrayList<String> mDatas;
    GridLayoutManager mGridLayoutManager;
    private PageAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        ButterKnife.bind(this);

        mToolbar.setTitle(getIntent().getBundleExtra("data").getString("name"));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getBundleExtra("data");
        mChapterUrl = bundle.getString("url");
        mChapterName = bundle.getString("name");

        mQueue = Volley.newRequestQueue(getContext());
        mController = new ChapterController(this);
        mController.initScript(mChapterUrl);
    }

    public void setText(String str) {
        mLoadingText.setText(str);
    }

    public void hideText() {
        mProgressBar.setVisibility(View.GONE);
        mLoadingText.setVisibility(View.GONE);
    }

    public void showErrorText() {
        mProgressBar.setVisibility(View.GONE);
        mLoadingText.setText("加载失败！点击重试");
        mLoadingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mLoadingText.setText("加载中...");
                mLoadingText.setOnClickListener(null);
                String url = getIntent().getBundleExtra("data").getString("url");
                mController.initScript(mChapterUrl);
            }
        });
    }

    public void initList(ArrayList<String> list) {
        mDatas = list;
        loadingImage();
        hideText();
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter = new PageAdapter());
    }

    public void loadingImage() {

    }

    class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_page, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            WebImage.setRefer(mChapterUrl);
            holder.imgPage.setImageUrl(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            SmartImageView imgPage;

            public MyViewHolder(View view) {
                super(view);
                imgPage = (SmartImageView) view.findViewById(R.id.img_page);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), BrowserActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("urlList", mDatas);
                        bundle.putInt("position", getAdapterPosition());
                        intent.putExtra("data", bundle);
                        getContext().startActivity(intent);
                    }
                });
            }
        }
    }

    class Page {
        String url;
        String num;
    }

}
