package com.example.xiaozhu.danyuan02_1.net;

import com.example.xiaozhu.danyuan02_1.beans.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by xiaozhu on 2019/11/19.
 */

public interface ApiService {
    String meUrl = "http://news-at.zhihu.com/";
    @GET("api/4/news/hot")
    Observable<Bean> getData();
}
