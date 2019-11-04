package com.bwie.xuqingchen20191104;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bwie.xuqingchen20191104.api.Api;
import com.bwie.xuqingchen20191104.bean.HomeBean;
import com.bwie.xuqingchen20191104.contract.IHomeContract;
import com.bwie.xuqingchen20191104.presenter.HomePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IHomeContract.IHomeView {

    @BindView(R.id.rv_home)
    RecyclerView recyclerView;
    @BindView(R.id.rv_mlsh)
    RecyclerView rvMlsh;

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initData() {

        homePresenter = new HomePresenter(this);
        homePresenter.getHomeData(Api.HOME_URL);

    }

    private void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));


    }


    @Override
    public void success(HomeBean homeBean) {
        if (homeBean!=null){
            Toast.makeText(this, ""+homeBean.message, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void failure(String error) {

    }
}
