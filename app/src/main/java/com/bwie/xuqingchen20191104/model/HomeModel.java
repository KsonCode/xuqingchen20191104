package com.bwie.xuqingchen20191104.model;

import com.bwie.xuqingchen20191104.contract.IHomeContract;
import com.bwie.xuqingchen20191104.net.NetCallback;
import com.bwie.xuqingchen20191104.net.OkhttpUtils;

public class HomeModel implements IHomeContract.IHomeModel {
    @Override
    public void getHomeData(String url, NetCallback netCallback) {

        OkhttpUtils.getInstance().doGet(url,netCallback);

    }
}
