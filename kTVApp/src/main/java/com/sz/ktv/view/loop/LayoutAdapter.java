/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sz.ktv.view.loop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sz.ktv.R;
import com.sz.ktv.db.Song;
import com.sz.ktv.ui.fragment.adapter.GemingAdapter;
import com.sz.ktv.ui.inter.GemingClickListener;

import java.util.List;


public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {
    private static final int DEFAULT_ITEM_COUNT = 10;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private final List<Integer> mItems = null;
    private int mCurrentItemId = 0;
    private  List<List<Song>> songList;
    private GemingClickListener mListener;


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final GridView gridView;

        public SimpleViewHolder(View view) {
            super(view);
            gridView = (GridView) view.findViewById(R.id.grid_view);
        }
    }

    public LayoutAdapter(Context context, RecyclerView recyclerView, List<List<Song>> mSongList, GemingClickListener listener) {
//       this(context, recyclerView, DEFAULT_ITEM_COUNT);
        mRecyclerView = recyclerView;
        this.songList = mSongList;
        mContext = context;
        mListener = listener;
    }

//    public LayoutAdapter(Context context, RecyclerView recyclerView, int itemCount) {
//        mContext = context;
//        mItems = new ArrayList<Integer>(itemCount);
//        for (int i = 0; i < itemCount; i++) {
//            addItem(i);
//        }
//
//        mRecyclerView = recyclerView;
//    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void addItem() {
        final int id = mCurrentItemId++;
        mItems.add(id, id);
        notifyItemInserted(id);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.page_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
//        holder.title.setText(mItems.get(position).toString());
         List<Song> list = songList.get(position);
        Log.i("TAG_当前位置:", ""+position);
        holder.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        GemingAdapter adapter = new GemingAdapter(list, mContext);
        adapter.setGemingClickListener(mListener);
        holder.gridView.setAdapter(adapter);
        updatePos(position);
    }

    public void updatePos(int pos)
    {

    }
    @Override
    public int getItemCount() {
        return songList.size();
    }
}
