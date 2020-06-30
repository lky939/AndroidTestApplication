package com.example.lky.androidtestapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements CustomCountDownTimer.ICountDownListener {

    @BindView(R.id.splash_play)
    VideoView splashPlay;
    @BindView(R.id.splash_count)
    TextView splashCount;
    private CustomCountDownTimer timer;

//    FullScreenVideoView splashPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        //设置播放路径
        splashPlay.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        timer = new CustomCountDownTimer(5, this);
        initListener();
        timer.start();

    }

    /**
     * 初始化监听
     */
    private void initListener() {
        splashPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                splashPlay.start();
            }
        });

        splashPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                splashPlay.start();
            }
        });

        splashCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void onticker(int time) {
        splashCount.setText(time+"秒");
    }

    @Override
    public void onfinish() {
        splashCount.setText("跳过");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }


}
