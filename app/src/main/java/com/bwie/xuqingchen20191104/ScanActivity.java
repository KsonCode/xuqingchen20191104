package com.bwie.xuqingchen20191104;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bwie.xuqingchen20191104.bean.HomeBean;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanActivity extends AppCompatActivity {

    @BindView(R.id.scan)
    Button scan;
    @BindView(R.id.generateCode)
    Button generateCode;
    @BindView(R.id.codeImg)
    ImageView codeImg;
    private String code = "";
    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
    }

    //线程主线程，支持粘性
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(HomeBean.ResultBean.RxxpBean.ListBean bean) {

        code = bean.price;

    }

    /**
     *
     * @param view 必须有，代表当前点击的控件
     */
    @OnClick(R.id.scan)
    public void toScan(View view){
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * 生成二维码
     * @param view
     */
    @OnClick(R.id.generateCode)
    public void setGenerateCode(View view){
       Bitmap mBitmap = CodeUtils.createImage(code, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        codeImg.setImageBitmap(mBitmap);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
