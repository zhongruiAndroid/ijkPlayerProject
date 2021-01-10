package com.example.ijkplayerproject.ijk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ijkplayerproject.R;
import com.github.media.IjkVideoView;
import com.github.media.helper.IjkHelper;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    String url = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";



    private IjkVideoView ijkVideo;
    private TextView tvProgressTipsStart;
    private AppCompatSeekBar sbProgress;
    private TextView tvProgressTipsEnd;
    private Button btPlay;
    private Button btPause;
    private Button btSpeed075;
    private Button btSpeed1;
    private Button btSpeed15;
    private Button btSpeed2;
    private AppCompatSeekBar sbVolume;
    private Button btPast;
    private TextView tvOtherUrl;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IjkHelper.init(null);
//        IjkHelper.native_profileBegin();
        setContentView(R.layout.activity_play);
        initView();
        initData();
    }



    private void initView() {
        ijkVideo = findViewById(R.id.ijkVideo);



        ijkVideo = findViewById(R.id.ijkVideo);
        tvProgressTipsStart = findViewById(R.id.tvProgressTipsStart);
        sbProgress = findViewById(R.id.sbProgress);
        tvProgressTipsEnd = findViewById(R.id.tvProgressTipsEnd);
        btPlay = findViewById(R.id.btPlay);
        btPlay.setOnClickListener(this);
        btPause = findViewById(R.id.btPause);
        btPause.setOnClickListener(this);

        btSpeed075 = findViewById(R.id.btSpeed075);
        btSpeed075.setOnClickListener(this);

        btSpeed1 = findViewById(R.id.btSpeed1);
        btSpeed15 = findViewById(R.id.btSpeed15);
        btSpeed2 = findViewById(R.id.btSpeed2);

        sbVolume = findViewById(R.id.sbVolume);
        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("=====","=====onProgressChanged"+fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                Log.i("=====","=====onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("=====","=====onStopTrackingTouch");

            }
        });

        btPast = findViewById(R.id.btPast);
        btPast.setOnClickListener(this);

        tvOtherUrl = findViewById(R.id.tvOtherUrl);
        tvOtherUrl.setText(url);


    }
    private void initData() {

        ijkVideo.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                return false;
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkVideo.release();
//        ijkVideo.releaseWithoutStop();
        IjkHelper.native_profileEnd();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btPlay:
//                ijkVideo.setVideoPath(tvOtherUrl.getText().toString());
//                ijkVideo.setVideoResId(R.raw.test);
                ijkVideo.setVideoAssetsPath("test/car.mp4");
                ijkVideo.start();
            break;
            case R.id.btPause:
                if (ijkVideo.isPlaying()) {
                    ijkVideo.pause();
                }else{
                    ijkVideo.start();
                }
            break;
            case R.id.btPast:
                // TODO: 2021/1/3
                sbVolume.setProgress(30);
            break;
            case R.id.btSpeed075:
               IjkVideoView ijk=new IjkVideoView(this);

            break;
        }
    }
}