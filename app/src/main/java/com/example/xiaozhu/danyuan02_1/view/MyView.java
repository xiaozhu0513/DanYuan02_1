package com.example.xiaozhu.danyuan02_1.view;

import com.example.xiaozhu.danyuan02_1.beans.Bean;
import com.example.xiaozhu.danyuan02_1.beans.RecentBean;

import java.util.List;

/**
 * Created by xiaozhu on 2019/11/19.
 */

public interface MyView {
    void setData(Bean bean);
    void showToast(String toast);
    void setData(List<RecentBean> recent);
}
