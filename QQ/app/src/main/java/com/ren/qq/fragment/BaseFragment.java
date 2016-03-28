package com.ren.qq.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ren.qq.R;
import com.ren.qq.utils.LogUtils;

/**
 * Created by Administrator on 2016/3/25.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(),null);
        initView(view);
        registerCommonButton(view);
        initListener();
        initData();
        return view;
    }

    /** 返回Activity使用的布局id */
    protected abstract int getLayoutResId();

    /** 执行findViewById操作
     * @param view */
    protected abstract void initView(View view);

    /** 执行注册监听器和适配器的操作 */
    protected abstract void initListener();

    /** 获取界面上要使用的数据，执行初始化操作 */
    protected abstract void initData();

    /** 处理在BaseActivity未处理的点击事件 */
    protected abstract void processClick(View v);

    @Override
    public void onClick(View v) {
        // 将在多数界面都存在的点击事件统一处理
        switch (v.getId()) {
            case R.id.back:
                getFragmentManager().popBackStack();
                break;

            default:
                // 处理在BaseActivity未处理的点击事件
                processClick(v);
                break;
        }
    }

    /** 直接注册在多个界面都有的按钮的点击事件
     * @param  */
    private void registerCommonButton(View root) {
        // 返回按钮
        View view = root.findViewById(R.id.back);
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    /** 显示一个Toast */
    protected void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /** 打印一个Error级的log */
    protected void logE(String msg) {
        LogUtils.e(this.getClass(), msg);
    }
}
