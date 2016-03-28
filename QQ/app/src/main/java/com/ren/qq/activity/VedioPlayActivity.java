package com.ren.qq.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.ren.qq.R;
import com.ren.qq.bean.VedioBean;
import com.ren.qq.utils.TimeUtils;

public class VedioPlayActivity extends BaseActivity implements MediaPlayer.OnPreparedListener, SeekBar.OnSeekBarChangeListener {

    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    updateCurrentTime();
                    break;
            }
        }
    };
    private VideoView videoView;
    private static TextView time;
    private ImageView electricCharge;
    private TextView fileName;
    int lastVolumn;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            updateSystemCharge(level);
        }
    };
    private SeekBar volumn;
    private AudioManager manager;
    private ImageView mute;


    private void updateSystemCharge(int level) {
        if (level < 10) {
            electricCharge.setImageResource(R.drawable.ic_battery_0);
        } else if (level < 20) {
            electricCharge.setImageResource(R.drawable.ic_battery_10);
        } else if (level < 30) {
            electricCharge.setImageResource(R.drawable.ic_battery_20);
        } else if (level < 50) {
            electricCharge.setImageResource(R.drawable.ic_battery_40);
        } else if (level < 70) {
            electricCharge.setImageResource(R.drawable.ic_battery_60);
        } else if (level < 90) {
            electricCharge.setImageResource(R.drawable.ic_battery_80);
        } else if (level <= 100) {
            electricCharge.setImageResource(R.drawable.ic_battery_100);
        }
    }

    @Override
    public void initListener() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);

        volumn.setOnSeekBarChangeListener(this);
    }

    @Override
    public void initData() {

        VedioBean vediobean = (VedioBean) getIntent().getSerializableExtra("vediobean");
        Log.e("VedioPlayActivity.initData", "VedioPlayActivity.initData.vediobean" + vediobean);
        Uri uri = Uri.parse(vediobean.getData());

        updateCurrentTime();

        fileName.setText(vediobean.getTitle());


        videoView.setVideoURI(uri);

    }

    private static void updateCurrentTime() {
        System.out.println("333333333333333333");
        time.setText(TimeUtils.formatSystemTime());
        handler.sendEmptyMessageDelayed(100, 500);
    }

    @Override
    public void initView() {


        videoView = (VideoView) findViewById(R.id.videoview);
        time = (TextView) findViewById(R.id.current_time);
        electricCharge = (ImageView) findViewById(R.id.electric_charge);
        fileName = (TextView) findViewById(R.id.file_name);
        volumn = (SeekBar) findViewById(R.id.volumn);
        mute = (ImageView) findViewById(R.id.mute);

        mute.setOnClickListener(this);
        videoView.setOnPreparedListener(this);
        initSeekBarVolumn();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.vedio_layout;
    }

    @Override
    public void processOtherButton(View v) {

        switch (v.getId()){

            case R.id.mute:
                switchMuteState();
                break;
        }
    }

    private void switchMuteState() {
        if (getCurrentVolumn() == 0){
            setCurrentVolumn(lastVolumn);
            volumn.setProgress(lastVolumn);
        }else{
            lastVolumn = getCurrentVolumn();
            setCurrentVolumn(0);
            volumn.setProgress(0);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        handler.removeCallbacksAndMessages(null);
    }
    /**坚听seekbar的更新状态*/
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e("VedioPlayActivity.onProgressChanged", "VedioPlayActivity.onProgressChanged.progress" + progress + "fromuser" + fromUser);
        if (!fromUser){
            return;
        }
        setCurrentVolumn(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //初始化音量
    public void initSeekBarVolumn(){
        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int vulumn = manager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumn.setMax(maxVolume);
        volumn.setProgress(vulumn);

    }
    //获取当前音量
    public int getCurrentVolumn(){
       return manager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }
    //设置当前音量
    public void setCurrentVolumn(int progress){
        manager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
    }
}
