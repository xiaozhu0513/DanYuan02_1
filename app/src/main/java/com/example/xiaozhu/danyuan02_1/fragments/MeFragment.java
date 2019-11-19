package com.example.xiaozhu.danyuan02_1.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.xiaozhu.danyuan02_1.BaseApp;
import com.example.xiaozhu.danyuan02_1.R;
import com.example.xiaozhu.danyuan02_1.adapters.MeRvAdapter;
import com.example.xiaozhu.danyuan02_1.adapters.UtilAdapter;
import com.example.xiaozhu.danyuan02_1.beans.Bean;
import com.example.xiaozhu.danyuan02_1.beans.RecentBean;
import com.example.xiaozhu.danyuan02_1.presenter.MyPresenter;
import com.example.xiaozhu.danyuan02_1.view.MyView;
import com.example.xts.greendaodemo.db.RecentBeanDao;

import java.util.ArrayList;
import java.util.List;

public class MeFragment extends Fragment implements MyView{

    private View view;
    private RecyclerView mRecyclerView;
    private ArrayList<RecentBean> lists;
    private MeRvAdapter adapter;
    private MyPresenter myPresenter;
    private int i = 0;
    private RecentBeanDao recentBeanDao;

    public static MeFragment newInstance() {
        MeFragment meFragment = new MeFragment();
        return meFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        myPresenter = new MyPresenter(this);
        recentBeanDao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        registerForContextMenu(mRecyclerView);
        lists = new ArrayList<>();
        adapter = new MeRvAdapter(getContext(),lists);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemLongClickListener(new UtilAdapter.OnItemLongClickListener() {
            @Override
            public void onItemClickLong(View v, int a) {
                i = a;
            }
        });
        initData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,0,0,"插入");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                 new AlertDialog.Builder(getContext())
                                .setTitle("是否添加到数据库？")
                                .setNegativeButton("否",null)
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        recentBeanDao.insertOrReplace(lists.get(i));
                                    }
                                })
                                .show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initData() {
        myPresenter.getData();
    }

    @Override
    public void setData(Bean bean) {
        adapter.addData(bean);
    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public void setData(List<RecentBean> recent) {

    }

}
