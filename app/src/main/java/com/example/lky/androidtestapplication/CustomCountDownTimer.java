package com.example.lky.androidtestapplication;


import android.os.Handler;

public class CustomCountDownTimer implements Runnable{


    private int time,currentTime;
    private ICountDownListener iCountDownListener;
    private Handler handler;

    private boolean isRun = false;


    public CustomCountDownTimer(int time, ICountDownListener iCountDownListener) {
        this.time = time;
        this.currentTime = time;
        this.iCountDownListener = iCountDownListener;
        handler = new Handler();
    }

    @Override
    public void run() {
        if(isRun){
            if(iCountDownListener != null){
                iCountDownListener.onticker(currentTime);
            }
            if(currentTime == 0){
                cancel();
                if(iCountDownListener!=null)iCountDownListener.onfinish();
            }else{
                currentTime = time--;
                handler.postDelayed(this,1000);
            }
        }
    }


    public void start(){
        isRun = true;
        handler.post(this);
    }

    public void cancel(){
        isRun = false;
        handler.removeCallbacks(this);
    }

    /**
     * 计时回调接口
     */
    public interface ICountDownListener{

        /**
         * 倒计时回调
         *
         */
        void onticker(int time);
        /**
         * 结束回调
         */
        void onfinish();
    }
}
