package com.imchen.demo;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.imchen.demo.service.FloatBallService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();
    }

    public void startFloatBall(View view){
        Toast.makeText(getApplicationContext(),"start float ball ",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this, FloatBallService.class);
        startService(intent);
    }

    public void stopFloatBall(View view){
        Toast.makeText(getApplicationContext(),"stop float ball ",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,FloatBallService.class);
        stopService(intent);
    }

    public void getPermission(){
        if (Build.VERSION.SDK_INT>=23){
            if(!Settings.canDrawOverlays(this)){
                Intent intent=new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }
        }
    }
}
