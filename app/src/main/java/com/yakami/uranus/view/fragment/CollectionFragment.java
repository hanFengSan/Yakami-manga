package com.yakami.uranus.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yakami.uranus.R;
import com.yakami.uranus.base.BaseFragment;
import com.yakami.uranus.utils.Tools;
import com.yakami.uranus.utils.smartimageview.SmartImageView;
import com.yakami.uranus.view.activity.MangaInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yakami on 2015/11/28.
 */
public class CollectionFragment extends BaseFragment {

    private List<CollectionItem> mDatas;
    private RecyclerView mRecyclerView;
    private CollectionAdapter mAdapter;
    GridLayoutManager mGridLayoutManager;

   @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       Log.e(TAG, "create CollectionFragment");
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        Log.e(TAG, "create CollectionFragment view");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        initList();
        return view;
    }

    void initList() {
        mDatas = new ArrayList<>();
        ReadAllCollection();
        mGridLayoutManager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter = new CollectionAdapter());
    }

    void ReadAllCollection() {
        for (int i = 0; i < 200; i++) {
            String result = readCollection("collection" + i);
            if (Tools.isEmpty(result))
                break;
            mDatas.add(new CollectionItem(result));
        }
    }

    public String readCollection(String name) {
        SharedPreferences read = getActivity().getSharedPreferences("lock", getActivity().MODE_WORLD_READABLE);
        return read.getString(name, "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "destroy homeFragment view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "destroy homeFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "pause homeFragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "stop homeFragment");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "start homeFragment");
    }

    class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_collection, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.name.setText(mDatas.get(position).name);
            holder.cover.setImageUrl(mDatas.get(position).imgUrl);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name;
            SmartImageView cover;

            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.collection_name);
                cover = (SmartImageView) view.findViewById(R.id.collection_cover);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), MangaInfoActivity.class);
                        Bundle data = new Bundle();
                        data.putString("url", mDatas.get(getAdapterPosition()).url);
                        intent.putExtra("data", data);
                        getContext().startActivity(intent);
                    }
                });
            }
        }
    }

    class CollectionItem {
        String name;
        String url;
        String imgUrl;

        public CollectionItem(String str) {
            String[] array = str.split(";");
            name = array[0];
            url = array[1];
            imgUrl = array[2];
        }
    }

}
