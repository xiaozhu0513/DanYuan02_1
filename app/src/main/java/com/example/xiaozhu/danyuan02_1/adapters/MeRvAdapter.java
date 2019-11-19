package com.example.xiaozhu.danyuan02_1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiaozhu.danyuan02_1.R;
import com.example.xiaozhu.danyuan02_1.beans.Bean;
import com.example.xiaozhu.danyuan02_1.beans.RecentBean;

import java.util.ArrayList;

/**
 * Created by xiaozhu on 2019/11/19.
 */

public class MeRvAdapter extends UtilAdapter{

    private Context context;
    public ArrayList<RecentBean> lists;

    public MeRvAdapter(Context context, ArrayList<RecentBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(context).load(lists.get(position).getThumbnail()).into(viewHolder.img);
        viewHolder.news_id.setText(lists.get(position).getNews_id()+"");
        viewHolder.title.setText(lists.get(position).getTitle());
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListener!=null){
                    onItemLongClickListener.onItemClickLong(v,position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void addData(Bean bean) {
        lists.addAll(bean.getRecent());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView news_id;
        private TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            news_id = itemView.findViewById(R.id.news_id);
            title = itemView.findViewById(R.id.title);
        }
    }

}
