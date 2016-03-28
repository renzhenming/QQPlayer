package com.ren.qq.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ren.qq.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/25.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> list;

    public FragmentAdapter(FragmentManager fm, ArrayList<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
