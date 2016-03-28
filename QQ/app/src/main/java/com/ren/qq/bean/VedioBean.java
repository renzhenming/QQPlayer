package com.ren.qq.bean;

import android.database.Cursor;
import android.provider.MediaStore.Video.Media;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/26.
 */
public class VedioBean implements Serializable{

    private String title;
    private int duration;
    private int size;
    private String data;

    public static VedioBean instanceWithCursor(Cursor cursor){
        VedioBean bean = new VedioBean();
        bean.title = cursor.getString(cursor.getColumnIndex(Media.TITLE));
        bean.duration = cursor.getInt(cursor.getColumnIndex(Media.DURATION));
        bean.size = cursor.getInt(cursor.getColumnIndex(Media.SIZE));
        bean.data = cursor.getString(cursor.getColumnIndex(Media.DATA));

        return bean;
    }

    @Override
    public String toString() {
        return "VedioBean{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
