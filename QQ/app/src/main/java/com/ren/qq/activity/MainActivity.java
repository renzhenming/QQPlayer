package com.ren.qq.activity;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.ren.qq.R;
import com.ren.qq.adapter.FragmentAdapter;
import com.ren.qq.fragment.BaseFragment;
import com.ren.qq.fragment.MusicFragment;
import com.ren.qq.fragment.VedioFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager viewpager;
    private View line;
    private ArrayList<BaseFragment> list;
    private TextView vedio;
    private TextView music;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void initListener() {

    }

    @SuppressLint("NewApi")
    @Override
    public void initData() {
        Point point = new Point();
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        defaultDisplay.getSize(point);

        int x = point.x / 2;
        line.getLayoutParams().width = x;
        line.requestLayout();


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                System.out.println("Position"+position);
//                System.out.println("positionOffset"+positionOffset);
//                System.out.println("positionOffsetPixels"+positionOffsetPixels);
                //LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) line.getLayoutParams();
                // indicater.setTranslationX(startPosition+distance);

                int width = getWindowManager().getDefaultDisplay().getWidth();
                int startPosition = width * position / list.size();
                int offset = (int) (width * positionOffset / list.size());
                line.setTranslationX(startPosition + position + offset);
                // line.offsetLeftAndRight(startPosition+position+offset);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    vedio.animate().scaleX(1.2f);
                    vedio.animate().scaleY(1.2f);

                    music.animate().scaleX(1.0f);
                    music.animate().scaleY(1.0f);

                    vedio.setTextColor(getResources().getColor(R.color.white));
                    music.setTextColor(getResources().getColor(R.color.green));
                } else if (position == 1) {
                    vedio.animate().scaleX(1.0f);
                    vedio.animate().scaleY(1.0f);

                    music.animate().scaleX(1.2f);
                    music.animate().scaleY(1.2f);

                    vedio.setTextColor(getResources().getColor(R.color.green));
                    music.setTextColor(getResources().getColor(R.color.white));
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        line = findViewById(R.id.line);
        vedio = (TextView) findViewById(R.id.vedioText);
        music = (TextView) findViewById(R.id.musicText);

        vedio.setOnClickListener(this);
        music.setOnClickListener(this);

        list = new ArrayList<>();
        list.add(new VedioFragment());
        list.add(new MusicFragment());


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewpager, new VedioFragment());
        fragmentTransaction.replace(R.id.viewpager, new MusicFragment());
        viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void processOtherButton(View v) {
        switch (v.getId()) {
            case R.id.vedioText:

                viewpager.setCurrentItem(0);

                break;
            case R.id.musicText:

                viewpager.setCurrentItem(1);
                break;
        }
    }


}
