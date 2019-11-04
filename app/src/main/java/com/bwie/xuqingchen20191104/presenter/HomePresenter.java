package com.bwie.xuqingchen20191104.presenter;

import com.bwie.xuqingchen20191104.bean.HomeBean;
import com.bwie.xuqingchen20191104.contract.IHomeContract;
import com.bwie.xuqingchen20191104.model.HomeModel;
import com.bwie.xuqingchen20191104.net.NetCallback;
import com.google.gson.Gson;

public class HomePresenter implements IHomeContract.IHomePresenter {

    private HomeModel homeModel;
    private IHomeContract.IHomeView iHomeView;

    public HomePresenter(IHomeContract.IHomeView iHomeView){
        this.iHomeView = iHomeView;
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData(String url) {


        homeModel.getHomeData(url, new NetCallback() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(result,HomeBean.class);
                if (iHomeView!=null){
                    iHomeView.success(homeBean);
                }
            }

            @Override
            public void failure(String error) {

                iHomeView.failure(error);
            }
        });

    }
}
