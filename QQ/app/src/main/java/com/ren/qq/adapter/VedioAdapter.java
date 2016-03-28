package com.ren.qq.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ren.qq.R;
import com.ren.qq.bean.VedioBean;
import com.ren.qq.utils.TimeUtils;

/**
 * Created by Administrator on 2016/3/26.
 */
public class VedioAdapter extends CursorAdapter {
    public VedioAdapter(Context context, Cursor c) {
        super(context, c);
    }

    public VedioAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

//    public VedioAdapter(Context context, Cursor c, int flags) {
//        super(context, c, flags);
//    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_vedio, null);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        VedioBean vedioBean = VedioBean.instanceWithCursor(cursor);
        holder.name.setText(vedioBean.getTitle());
        holder.duration.setText(TimeUtils.formatDuration(vedioBean.getDuration()));
        holder.size.setText(Formatter.formatFileSize(context, vedioBean.getSize()) + "");

    }

    static class ViewHolder {
        TextView name, duration, size;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            duration = (TextView) view.findViewById(R.id.duration);
            size = (TextView) view.findViewById(R.id.size);
        }

    }
}
