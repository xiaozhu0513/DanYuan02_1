package com.example.xiaozhu.danyuan02_1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiaozhu on 2019/11/10.
 */

public abstract class UtilAdapter extends RecyclerView.Adapter {

    public OnItemClickListener onItemClickListener;
    public OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int a);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener {
        void onItemClickLong(View v, int a);
    }
}
