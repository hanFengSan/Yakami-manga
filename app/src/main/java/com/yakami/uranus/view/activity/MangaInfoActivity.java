package com.yakami.uranus.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.yakami.uranus.R;
import com.yakami.uranus.base.BaseActivity;
import com.yakami.uranus.utils.ChapterInfo;
import com.yakami.uranus.utils.Tools;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MangaInfoActivity extends BaseActivity {

    @Bind(R.id.toolbar_mangaInfo) Toolbar mToolbar;
    @Bind(R.id.mangaInfo_chapterList) RecyclerView mRecyclerView;
    @Bind(R.id.mangaInfo_cover) ImageView mImageView;
    @Bind(R.id.mangaInfo_name) TextView mName;
    @Bind(R.id.mangaInfo_author) TextView mAuthor;
    @Bind(R.id.mangaInfo_state) TextView mState;
    @Bind(R.id.mangaInfo_numOfChapter) TextView mNum;
    @Bind(R.id.mangaInfo_updateTime) TextView mUpdateTime;
    @Bind(R.id.mangaInfo_intro) TextView mIntro;
    @Bind(R.id.mangaInfo_loading) TextView mLoadingText;
    @Bind(R.id.mangaInfo_progressBar) ProgressBar mProgressBar;
    @Bind(R.id.mangaInfo_contentLayout) LinearLayout mContentLayout;

    ArrayList<ChapterInfo> mDatas = new ArrayList<>();
    GridLayoutManager mGridLayoutManager;
    private ChapterAdapter mAdapter;
    MangaInfoController mController;
    public RequestQueue mQueue;
    private String mImgUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangainfo);
        ButterKnife.bind(this);

        mToolbar.setTitle("mangaInfo");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mQueue = Volley.newRequestQueue(getContext());
        String url = getIntent().getBundleExtra("data").getString("url");
        mController = new MangaInfoController(this);
        mController.initMangaInfo(url);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mangainfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_collection) {
            mController.saveCollection(mName.getText().toString(), getIntent().getBundleExtra("data").getString("url"), mImgUrl);
            Tools.toast(getContext(), "收藏成功");
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView(String imgUrl, String name, String author, String state, String num,
                         String time, String intro, ArrayList<ChapterInfo> chapterList) {
        mImgUrl = imgUrl;
        loadImage(imgUrl);
        mName.setText(name);
        mAuthor.setText("作者: " + author);
        mState.setText("状态: " + state);
        mNum.setText("话数: " + num);
        mUpdateTime.setText("更新: " + time);
        mIntro.setText("简介: " + intro);
        mDatas = chapterList;
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter = new ChapterAdapter());
        hideText();
        mContentLayout.setVisibility(View.VISIBLE);
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
                mController.initMangaInfo(url);
            }
        });
    }

    public void loadImage(String url) {
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Tools.toast(getContext(), volleyError.toString());
            }
        });
        mQueue.add(request);
    }

    class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_chapter, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
//            holder.name.setText(mDatas.get(position).getTitle());
            holder.chapterNum.setText(mDatas.get(position).chapterName);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView chapterNum;

            public MyViewHolder(View view) {
                super(view);
                chapterNum = (TextView) view.findViewById(R.id.chapterNum);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ChapterActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url", mDatas.get(getAdapterPosition()).chapterUrl);
                        bundle.putString("name", mDatas.get(getAdapterPosition()).chapterName);
                        intent.putExtra("data", bundle);
                        getContext().startActivity(intent);
                    }
                });
            }
        }
    }
}
