package com.ren.qq.db;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;

import com.ren.qq.adapter.MusicAdapter;

/**
 * Created by Administrator on 2016/3/27.
 */
public class MusicAsyncQueryHandler extends AsyncQueryHandler {
    public MusicAsyncQueryHandler(ContentResolver cr) {
        super(cr);
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        super.onQueryComplete(token, cookie, cursor);
        if (cookie instanceof MusicAdapter){
            ((MusicAdapter) cookie).swapCursor(cursor);
        }
    }
}
