package com.example.ijkplayerproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ijkplayerproject.ijk.PlayActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btIjk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btIjk = findViewById(R.id.btIjk);
        btIjk.setOnClickListener(this);

        int i = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int i2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(i== PackageManager.PERMISSION_DENIED||i2== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
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