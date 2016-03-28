package com.ren.qq.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.ren.qq.R;
import com.ren.qq.utils.LogUtils;

/**
 * Created by Administrator on 2016/3/25.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private View id;
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("111111111111111111111111");
        setContentView(getLayoutResId());
        mActivity = this;
        initView();
        initData();
        initListener();
        registerCommonButton();
    }


    //    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        System.out.println("111111111111111111111111");
//        setContentView(getLayoutResId());
//        mActivity = this;
//        initView();
//        initData();
//        initListener();
//        registerCommonButton();
//    }
    /** 初始化每个页面都有的按钮的点击事件*/
    private void registerCommonButton() {
        //退出按钮
        id = findViewById(R.id.back);
        if (id != null){
            id.setOnClickListener(this);
        }
    }

    /** 初始化监听接口*/
    public abstract void initListener();

    /** 初始化数据*/
    public abstract void initData() ;
    /** 初始化view*/
    public abstract void initView() ;

    /** 获取布局的id */
    public abstract int getLayoutResId() ;
    /**打印一个Error及的log*/
    public void LogE(String msg){
        LogUtils.e(this.getClass(),msg);
    }
    /**显示一个Toast*/
    public void toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
    /**处理子类特有的点击事件*/
    public abstract void processOtherButton(View v) ;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;

            default:
                processOtherButton(v);
                break;
        }
    }

}
