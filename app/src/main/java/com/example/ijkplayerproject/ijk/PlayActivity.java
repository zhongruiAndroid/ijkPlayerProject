package com.example.ijkplayerproject.ijk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ijkplayerproject.R;
import com.github.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayActivity extends AppCompatActivity {
    IjkVideoView   ijkVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IjkMediaPlayer.loadLibrariesOnce(null);
        setContentView(R.layout.activity_play);
        initView();
    }

    private void initView() {
        String url="http://dy-frontend.video.ums.uc.cn/video/wemedia/b8d8ca9a565a4f5cb57e094eac061f46/7ed8520a98cd9c9a709f088dd1356865-3815174574-2-0-4-h264.mp4?auth_key=1609585399-237329d82ce54803a5c8b200723c2cb1-0-c4ce1e466345107b6469bc2c6c5190a4";
        ijkVideo=findViewById(R.id.ijkVideo);
        ijkVideo.setVideoPath(url);
        ijkVideo.start();
    }
}