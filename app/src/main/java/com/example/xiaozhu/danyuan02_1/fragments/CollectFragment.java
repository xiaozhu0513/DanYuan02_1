package com.example.xiaozhu.danyuan02_1.fragments;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.xiaozhu.danyuan02_1.BaseApp;
import com.example.xiaozhu.danyuan02_1.R;
import com.example.xiaozhu.danyuan02_1.adapters.CollectRvAdapter;
import com.example.xiaozhu.danyuan02_1.adapters.MeRvAdapter;
import com.example.xiaozhu.danyuan02_1.adapters.UtilAdapter;
import com.example.xiaozhu.danyuan02_1.beans.Bean;
import com.example.xiaozhu.danyuan02_1.beans.RecentBean;
import com.example.xiaozhu.danyuan02_1.presenter.MyPresenter;
import com.example.xiaozhu.danyuan02_1.view.MyView;
import com.example.xts.greendaodemo.db.RecentBeanDao;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment implements MyView{

    private View view;
    private RecyclerView mRecyclerView;
    private ArrayList<RecentBean> lists;
    private CollectRvAdapter adapter;
    private MyPresenter myPresenter;
    private RecentBeanDao recentBeanDao;

    public static CollectFragment newInstance(){
        CollectFragment collectFragment = new CollectFragment();
        return collectFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, null);
        myPresenter = new MyPresenter(this);
        recentBeanDao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        registerForContextMenu(mRecyclerView);
        lists = new ArrayList<>();
        adapter = new CollectRvAdapter(getContext(),lists);
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        myPresenter.getDataSql();
    }

    @Override
    public void setData(Bean bean) {

    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public void setData(final List<RecentBean> recent) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.addData(recent);
            }
        });
    }

    public void query() {
        adapter.lists.clear();
        initData();
    }
}
