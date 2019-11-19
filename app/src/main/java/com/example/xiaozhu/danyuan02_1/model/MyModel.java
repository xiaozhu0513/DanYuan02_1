package com.example.xiaozhu.danyuan02_1.model;

import com.example.xiaozhu.danyuan02_1.BaseApp;
import com.example.xiaozhu.danyuan02_1.beans.Bean;
import com.example.xiaozhu.danyuan02_1.beans.RecentBean;
import com.example.xiaozhu.danyuan02_1.net.ApiService;
import com.example.xiaozhu.danyuan02_1.net.ResultCallBask;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaozhu on 2019/11/19.
 */

public class MyModel {
    private List<Bean> recent;

    public void getData(final ResultCallBask<Bean> callBask) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.meUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        callBask.succeed(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBask.loser(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDataSql(final ResultCallBask<List<Bean>> callBask) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<RecentBean> recent = BaseApp.getInstance().getDaoSession().getRecentBeanDao().loadAll();
                callBask.sqlSucceed(recent);
            }
        }).start();
    }

}
