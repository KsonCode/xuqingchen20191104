package com.bwie.xuqingchen20191104.net;

/**
 * 网络数据回调
 */
public interface NetCallback {

    void success(String result);
    void failure(String error);
}
