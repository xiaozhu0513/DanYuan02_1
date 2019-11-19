package com.example.xiaozhu.danyuan02_1.presenter;


import com.example.xiaozhu.danyuan02_1.beans.Bean;
import com.example.xiaozhu.danyuan02_1.beans.RecentBean;
import com.example.xiaozhu.danyuan02_1.model.MyModel;
import com.example.xiaozhu.danyuan02_1.net.ResultCallBask;
import com.example.xiaozhu.danyuan02_1.view.MyView;

import java.util.List;

/**
 * Created by xiaozhu on 2019/11/19.
 */

public class MyPresenter {
    private final MyModel myModel;
    private MyView view;

    public MyPresenter(MyView view) {
        myModel = new MyModel();
        this.view = view;
    }

    public void getData() {
        myModel.getData(new ResultCallBask<Bean>() {
            @Override
            public void succeed(Bean bean) {
                if (bean != null && view != null) {
                    view.setData(bean);
                }
            }

            @Override
            public void loser(String toast) {
                view.showToast(toast);
            }

            @Override
            public void sqlSucceed(List<RecentBean> recent) {

            }
        });
    }

    public void getDataSql() {
        myModel.getDataSql(new ResultCallBask<List<Bean>>() {
            @Override
            public void succeed(List<Bean> bean) {

            }

            @Override
            public void loser(String toast) {

            }

            @Override
            public void sqlSucceed(List<RecentBean> recent) {
                if (recent != null && view != null) {
                    view.setData(recent);
                }
            }
        });
    }
}
