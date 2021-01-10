package com.example.ijkplayerproject.ijk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
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

    private RadioButton rbFileTypeUrl;
    private RadioButton rbFileTypeAssets;
    private RadioButton rbFileTypeRaw;


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
        btSpeed1.setOnClickListener(this);

        btSpeed15 = findViewById(R.id.btSpeed15);
        btSpeed15.setOnClickListener(this);

        btSpeed2 = findViewById(R.id.btSpeed2);
        btSpeed2.setOnClickListener(this);


        sbVolume = findViewById(R.id.sbVolume);
        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("=====", "=====onProgressChanged" + fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                Log.i("=====", "=====onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("=====", "=====onStopTrackingTouch");

            }
        });

        btPast = findViewById(R.id.btPast);
        btPast.setOnClickListener(this);

        tvOtherUrl = findViewById(R.id.tvOtherUrl);
        tvOtherUrl.setText(url);

        rbFileTypeUrl = findViewById(R.id.rbFileTypeUrl);
        rbFileTypeAssets = findViewById(R.id.rbFileTypeAssets);
        rbFileTypeRaw = findViewById(R.id.rbFileTypeRaw);

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
        switch (v.getId()) {
            case R.id.btPlay:
                if (rbFileTypeUrl.isChecked()) {
                    ijkVideo.setVideoPath(tvOtherUrl.getText().toString());
                } else if (rbFileTypeAssets.isChecked()) {
                    ijkVideo.setVideoAssetsPath("test/car.mp4");
                } else if (rbFileTypeRaw.isChecked()) {
                    ijkVideo.setVideoResId(R.raw.test);
                } else {
                    ijkVideo.setVideoPath(tvOtherUrl.getText().toString());
                }
                ijkVideo.start();
                break;
            case R.id.btPause:
                if (ijkVideo.isPlaying()) {
                    ijkVideo.pause();
                } else {
                    ijkVideo.start();
                }
                break;
            case R.id.btPast:
                sbVolume.setProgress(30);
                break;
            case R.id.btSpeed075:
                ijkVideo.getMediaPlayer().setSpeed(0.75f);
                break;
            case R.id.btSpeed1:
                ijkVideo.getMediaPlayer().setSpeed(1f);
                break;
            case R.id.btSpeed15:
                ijkVideo.getMediaPlayer().setSpeed(1.5f);
                break;
            case R.id.btSpeed2:
                ijkVideo.getMediaPlayer().setSpeed(2f);
                break;
        }
    }
}