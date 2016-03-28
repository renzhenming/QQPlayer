package com.ren.qq.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;
import android.provider.MediaStore.Audio.Media;

import com.ren.qq.R;
import com.ren.qq.adapter.MusicAdapter;
import com.ren.qq.db.MusicAsyncQueryHandler;

/**
 * Created by Administrator on 2016/3/25.

*/
public class MusicFragment extends BaseFragment {

    private static final int MUSIC_TOKEN = 2;
    private ListView musicListview;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initView(View view) {
        musicListview = (ListView) view.findViewById(R.id.simple_listview);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        MusicAdapter adapter = new MusicAdapter(getActivity(), null);
        musicListview.setAdapter(adapter);


        ContentResolver resolver = getActivity().getContentResolver();
        MusicAsyncQueryHandler musicAsyncHandler = new MusicAsyncQueryHandler(resolver);
        musicAsyncHandler.startQuery(MUSIC_TOKEN,adapter,Media.EXTERNAL_CONTENT_URI,new String[]{Media._ID,Media.TITLE, Media.DATA,Media.DURATION, Media.SIZE},null,null,null);

    }

    @Override
    protected void processClick(View v) {

    }


}