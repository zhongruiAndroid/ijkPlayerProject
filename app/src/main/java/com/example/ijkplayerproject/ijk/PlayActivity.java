package com.example.ijkplayerproject.ijk;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ijkplayerproject.R;
import com.github.audiomanager.AudioListener;
import com.github.audiomanager.AudioTools;
import com.github.media.IjkVideoView;
import com.github.media.helper.IjkHelper;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    String url = "http://jdvodluwytr3t.vod.126.net/jdvodluwytr3t/nos/ept/hls/2019/03/20/1214457506_fb09ce1669704168a6b56878724ab838_esd.m3u8";//?ak=7909bff134372bffca53cdc2c17adc27a4c38c6336120510aea1ae1790819de89bd87aa1825daf647a5c0b714b8eed29564993ff2eb0e995658d1051945a9adf4eb900f4ba643bf70c7dc9d2201c4f1bdc2c820d02e05f097dce22a2d269d51b30d7326413f2c325d90c0e56061a553ee6fc2ae2e15a00d5fd833ea365cf99f40fadfe637d1a6500ff2f63b9e5380ed8";
//    String url = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";


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

    private CheckBox cbUrlUseCache;
    private CheckBox cbLoopPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioTools.get().init(this);
        IjkHelper.init(null);
//        IjkHelper.native_profileBegin();
        setContentView(R.layout.activity_play);
        initView();
        initData();

        AudioTools.get().addListener(this, new AudioListener(){
            @Override
            public void onMusicVolumeChange(int preVolume, int volume) {
                int maxVolume = AudioTools.get().getMaxVolume(AudioManager.STREAM_MUSIC);
                int i = sbVolume.getMax() * volume / maxVolume;
                sbVolume.setProgress(i);
                ijkVideo.setVolume(volume);
            }
        });
    }
    private int getScreenWidth(){
        return getResources().getDisplayMetrics().widthPixels;
    }

    private void initView() {


        ijkVideo = findViewById(R.id.ijkVideo);
        ijkVideo.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                int videoWidth = mp.getVideoWidth();
                int videoHeight = mp.getVideoHeight();
                int h = getScreenWidth() * videoHeight / videoWidth;
                ViewGroup.LayoutParams layoutParams = ijkVideo.getLayoutParams();
                layoutParams.width=getScreenWidth();
                layoutParams.height=h;
                ijkVideo.setLayoutParams(layoutParams);
            }
        });


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
        int maxVolume = AudioTools.get().getMaxVolume(AudioManager.STREAM_MUSIC);
        int musicVolume = AudioTools.get().getMusicVolume();
        int i = sbVolume.getMax() * musicVolume / maxVolume;
        sbVolume.setProgress(i);
        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(!fromUser){
                    return;
                }
                int maxVolume = AudioTools.get().getMaxVolume(AudioManager.STREAM_MUSIC);
                int i = progress * maxVolume / sbVolume.getMax();
                AudioTools.get().setMusicVolume(i);
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
        cbUrlUseCache = findViewById(R.id.cbUrlUseCache);
        cbLoopPlay = findViewById(R.id.cbLoopPlay);

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
        AudioTools.get().unInit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btPlay:
                ijkVideo.getConfig().setUseCache(cbUrlUseCache.isChecked());
                if (rbFileTypeUrl.isChecked()) {
                    ijkVideo.setVideoPath(tvOtherUrl.getText().toString());
                } else if (rbFileTypeAssets.isChecked()) {
                    ijkVideo.setVideoAssetsPath("test/car.mp4");
                } else if (rbFileTypeRaw.isChecked()) {
                    ijkVideo.setVideoResId(R.raw.test);
                } else {
                    ijkVideo.setVideoPath(tvOtherUrl.getText().toString());
                }
//                File test = getExternalFilesDir("test");
//                ijkVideo.setVideoPath(test.getAbsolutePath()+"/file.mp4");
                ijkVideo.getMediaPlayer().setLooping(cbLoopPlay.isChecked());
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