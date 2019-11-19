package com.example.xiaozhu.danyuan02_1.net;

import com.example.xiaozhu.danyuan02_1.beans.RecentBean;

import java.util.List;

/**
 * Created by xiaozhu on 2019/11/19.
 */

public interface ResultCallBask<T> {
    void succeed(T bean);
    void sqlSucceed(List<RecentBean> recent);
    void loser(String toast);
}
