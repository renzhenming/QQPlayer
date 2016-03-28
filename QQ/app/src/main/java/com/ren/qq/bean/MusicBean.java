package com.ren.qq.bean;

import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Video.Media;

/**
 * Created by Administrator on 2016/3/27.
 */
public class MusicBean {

    private String title,data;
    private int duration,size;

    public static MusicBean instanceMusicBean(Cursor cursor){
        MusicBean bean = new MusicBean();
        bean.title = cursor.getString(cursor.getColumnIndex(Media.TITLE));
        bean.data = cursor.getString(cursor.getColumnIndex(Media.DATA));
        bean.duration = cursor.getInt(cursor.getColumnIndex(Media.DURATION));
        bean.size = cursor.getInt(cursor.getColumnIndex(Media.SIZE));
        return bean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
