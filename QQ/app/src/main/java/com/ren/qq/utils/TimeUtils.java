package com.ren.qq.utils;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/26.
 */
public class TimeUtils {
    public static final int HOUR = 1000*60*60;
    public static final int MIN = 1000*60;
    public static final int SEC = 1000;


    public static String formatDuration(int duration){
        //小时
        int hour = duration / HOUR;
        int remainTime = duration % HOUR;
        //分钟
        int min = remainTime / MIN;
        remainTime = remainTime % MIN;
        //秒
        int sec = remainTime / SEC;

        String str = null;
        if (hour > 0){
            str = String.format("%02d:%02d:%02d",hour,min,sec);

        }else{
            str = String.format("%02d:%02d",min,sec);
        }

        return str;

    }

    public static String formatSystemTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
