package com.example.ijkplayerproject.ijk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ijkplayerproject.R;
import com.github.media.IjkVideoView;
import com.github.media.helper.IjkHelper;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayActivity extends AppCompatActivity {
    IjkVideoView   ijkVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IjkHelper.init(null);
        IjkHelper.native_profileBegin();
        setContentView(R.layout.activity_play);
        initView();
    }

    private void initView() {
        String url="https://dy-frontend.video.ums.uc.cn/video/wemedia/421795e4cb6241ea8fee95501b755de7/0aabb87d9be7be1ffba0048260b9a724-3744068669-2-0-4-h264.mp4?auth_key=1609660970-1bb32f80934248eba9b4c9f80cb75792-0-ce144437e7fad68f4dacb6884c187a0a";
        ijkVideo=findViewById(R.id.ijkVideo);
        ijkVideo.setVideoPath(url);
        ijkVideo.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IjkHelper.native_profileEnd();
    }
}