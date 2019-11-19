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
import com.example.xiaozhu.danyuan02_1.beans.RecentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2019/11/19.
 */

public class CollectRvAdapter extends UtilAdapter{

    private Context context;
    public ArrayList<RecentBean> lists;

    public CollectRvAdapter(Context context, ArrayList<RecentBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            View view = LayoutInflater.from(context).inflate(R.layout.item1_layout, parent, false);
            ViewHolder1 viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item2_layout, parent, false);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type==1){
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            Glide.with(context).load(lists.get(position).getThumbnail()).into(viewHolder1.img1);
            viewHolder1.news_id1.setText(lists.get(position).getNews_id()+"");
            viewHolder1.title1.setText(lists.get(position).getTitle());
        }else {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            Glide.with(context).load(lists.get(position).getThumbnail()).into(viewHolder2.img2);
            viewHolder2.news_id2.setText(lists.get(position).getNews_id()+"");
            viewHolder2.title2.setText(lists.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==1){
            return 1;
        }else {
            return 2;
        }
    }

    public void addData(List<RecentBean> recent) {
        lists.addAll(recent);
        notifyDataSetChanged();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        private ImageView img1;
        private TextView news_id1;
        private TextView title1;
        public ViewHolder1(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            news_id1 = itemView.findViewById(R.id.news_id1);
            title1 = itemView.findViewById(R.id.title1);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{
        private ImageView img2;
        private TextView news_id2;
        private TextView title2;
        public ViewHolder2(View itemView) {
            super(itemView);
            img2 = itemView.findViewById(R.id.img2);
            news_id2 = itemView.findViewById(R.id.news_id2);
            title2 = itemView.findViewById(R.id.title2);
        }
    }
}
