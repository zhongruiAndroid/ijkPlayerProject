package com.example.ijkplayerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

import com.example.ijkplayerproject.ijk.PlayActivity;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btIjk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btIjk = findViewById(R.id.btIjk);
        btIjk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btIjk:
                startAct(PlayActivity.class);
                break;
        }
    }

    public void startAct(Class clazz) {
        startActivity(new Intent(this, clazz));
    }
}