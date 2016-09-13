package com.yakami.uranus.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.yakami.uranus.R;
import com.yakami.uranus.base.BaseActivity;
import com.yakami.uranus.utils.MangaItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yakami on 2015/11/28.
 */
public class SearchActivity extends BaseActivity {

    @Bind(R.id.toolbar_search) Toolbar mToolbar;
    @Bind(R.id.search_list) RecyclerView mRecyclerView;
    @Bind(R.id.search_text) TextView mSearchText;
    @Bind(R.id.search_progressBar) ProgressBar mProgressBar;
    LinearLayoutManager mLayoutManager;
    SearchController mController;
    public RequestQueue mQueue;
    private ArrayList<MangaItem> mDatas;
    private HomeAdapter mAdapter;
    private String mKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");

        mToolbar.setTitle("搜索结果");
        /*显示toolbar并激活返回按钮*/
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
        mController = new SearchController(this);
        mKeyword = bundle.getString("searchString");
        mController.getSearchResult(mKeyword);
    }

    public void initRecyclerView(ArrayList<MangaItem> list) {
        hideText();
        mDatas = list;
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, mLayoutManager.getOrientation()));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());

    }

    public void hideText() {
        mProgressBar.setVisibility(View.GONE);
        mSearchText.setVisibility(View.GONE);
    }

    public void showErrorText() {
        mProgressBar.setVisibility(View.GONE);
        mSearchText.setText("加载失败！点击重试");
        mSearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mSearchText.setText("加载中...");
                mSearchText.setOnClickListener(null);
                mController.getSearchResult(mKeyword);
            }
        });
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_search, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.name.setText(mDatas.get(position).getTitle());
            holder.author.setText("作者: " + mDatas.get(position).getAuthor());
            holder.intro.setText(mDatas.get(position).getIntro());
            holder.state.setText(mDatas.get(position).getUpdateState());
            holder.imgButton.setImageResource(R.drawable.loading);
            ImageRequest imageRequest = new ImageRequest(
                    mDatas.get(position).getImgUrl(),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.imgButton.setImageBitmap(bitmap);
                        }
                    }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    holder.imgButton.setImageResource(R.drawable.loading_error);
                }
            }
            );
            mQueue.add(imageRequest);

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView author;
            TextView intro;
            TextView state;
            ImageButton imgButton;
            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.item_name);
                author = (TextView) view.findViewById(R.id.item_author);
                intro = (TextView) view.findViewById(R.id.item_intro);
                state = (TextView) view.findViewById(R.id.item_state);
                imgButton = (ImageButton) view.findViewById(R.id.item_imgButton);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Tools.toast(getContext(), "点击位置：" + getAdapterPosition());
                        Intent intent = new Intent(getContext(), MangaInfoActivity.class);
                        Bundle data = new Bundle();
                        data.putString("url", mDatas.get(getAdapterPosition()).getUrl());
                        intent.putExtra("data", data);
                        getContext().startActivity(intent);
                    }
                });
            }
        }
    }
}
