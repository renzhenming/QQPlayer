package com.ren.qq.utils;

import android.database.Cursor;
import android.util.Log;

/**
 * Created by Administrator on 2016/3/25.
 */
public class LogUtils {

    public static final boolean ENABLED = true;

    public static void d(String tag,String msg){
        if (ENABLED){
            Log.d(tag,msg);
        }
    }

    public static void e(String tag,String msg){
        if (ENABLED){
            Log.e(tag, msg);
        }
    }

    public static void e(Class <?> cls , String msg){
        if (ENABLED){
            Log.e("ren_"+cls.getSimpleName(),msg);
        }
    }
    private static void cursorUtils(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        boolean isMove = cursor.moveToFirst();
        Log.e("VedioFragment.initData", "VedioFragment.initData.isMove" + isMove);
        while (cursor.moveToNext()) {
            Log.e("VedioFragment.initData", "VedioFragment.initData.资源的名字你出来");
            for (int i = 0; i < cursor.getColumnCount(); i++) {

                Log.e("VedioFragment.initData", "VedioFragment.initData.获取到的资源为" + cursor.getColumnName(i));

            }
        }
        cursor.close();
    }
}
