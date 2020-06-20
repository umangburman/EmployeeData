package com.mvvm.room.employeedata.services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.mvvm.room.employeedata.view.SecondActivity;

public class CountTime extends Service {

    private CountDownTimer timer;

    Intent i;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        timer = new CountDownTimer(6000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Intent broadCastIntent = new Intent();
                broadCastIntent.setAction(SecondActivity.broadcastAction);
                broadCastIntent.putExtra("count", "" + millisUntilFinished / 1000);
                sendBroadcast(broadCastIntent);
            }

            @Override
            public void onFinish() {
                try{
                    stopSelf();
                }catch(Exception e){
                    Log.e("Error", "Error: " + e.toString());
                }
            }
        }.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }

}
