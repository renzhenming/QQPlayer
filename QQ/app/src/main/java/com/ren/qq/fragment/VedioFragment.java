package com.ren.qq.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore.Video.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ren.qq.R;
import com.ren.qq.activity.VedioPlayActivity;
import com.ren.qq.adapter.VedioAdapter;
import com.ren.qq.bean.VedioBean;
import com.ren.qq.db.VedioAsyncQueryHandler;

/**
 * Created by Administrator on 2016/3/25.
 */
public class VedioFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final int VEDIO_TOKEN = 1;
    private ListView listview;

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_vedio;

    }

    @Override
    protected void initView(View view) {
        listview = (ListView) view.findViewById(R.id.simple_listview);

        listview.setOnItemClickListener(this);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        //主线程中查询
//        ContentResolver resolver = getActivity().getContentResolver();
//        Cursor cursor = resolver.query(Media.EXTERNAL_CONTENT_URI, new String[]{Media._ID,Media.TITLE, Media.SIZE, Media.DURATION, Media.DATA}, null, null, null);
        //
        VedioAdapter vAdapter = new VedioAdapter(getActivity(), null);
        listview.setAdapter(vAdapter);

        //vAdapter.swapCursor(cursor);
        //异步查询
        VedioAsyncQueryHandler handler = new VedioAsyncQueryHandler(getActivity().getContentResolver());
        handler.startQuery(VEDIO_TOKEN,vAdapter,Media.EXTERNAL_CONTENT_URI, new String[]{Media._ID,Media.TITLE, Media.SIZE, Media.DURATION, Media.DATA}, null, null, null);


    }

    @Override
    protected void processClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) parent.getItemAtPosition(position);
        VedioBean bean = VedioBean.instanceWithCursor(cursor);

        Intent intent = new Intent(getActivity(),VedioPlayActivity.class);
        intent.putExtra("vediobean",bean);
        startActivity(intent);
    }
}
