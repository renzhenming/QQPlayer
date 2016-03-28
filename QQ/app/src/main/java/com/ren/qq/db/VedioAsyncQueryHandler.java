package com.ren.qq.db;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;

import com.ren.qq.adapter.VedioAdapter;

/**
 * Created by Administrator on 2016/3/26.
 */
public class VedioAsyncQueryHandler extends AsyncQueryHandler {
    public VedioAsyncQueryHandler(ContentResolver cr) {
        super(cr);
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        super.onQueryComplete(token, cookie, cursor);

        if (cookie != null && cookie instanceof VedioAdapter){
            ((VedioAdapter) cookie).swapCursor(cursor);
        }
    }
}
