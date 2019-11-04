package com.bwie.xuqingchen20191104.contract;

import com.bwie.xuqingchen20191104.bean.HomeBean;
import com.bwie.xuqingchen20191104.net.NetCallback;

/**
 * 契约类：首页契约类
 */
public interface IHomeContract {

    interface IHomeModel{

        void getHomeData(String url, NetCallback netCallback);

    }
    interface IHomeView{

        void success(HomeBean homeBean);
        void failure(String error);

    }
    interface IHomePresenter{

        void getHomeData(String url);
    }
}
