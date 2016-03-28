package com.ren.qq.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.ren.qq.R;


public class SplashActivity extends BaseActivity {


    @Override
    public void initView() {


    }

    @Override
    public int getLayoutResId() {
        System.out.println("222222222222222222222");
        return R.layout.activity_splash;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mActivity,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }

    @Override
    public void processOtherButton(View v) {

    }
}
