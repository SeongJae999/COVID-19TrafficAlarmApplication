package com.example.coronagpsalarm;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.util.Log;

import com.example.coronagpsalarm.gpsLibrary.GPSTracker;

public class ServiceThread extends Thread{
    private Handler handler;
    private boolean isRun = true;

    private GPSTracker gpsTracker;
    private Location location;
    private Location location2;

    private Context context;

    public ServiceThread(Handler handler){
        this.handler = handler;
    }

    public ServiceThread(Context context) {
        this.context = context;
    }

    public void stopForever(){
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run(){
        //반복적으로 수행할 작업을 한다.
        while(isRun){
            if(gpsUpdate() <= 1000)
                handler.sendEmptyMessage(0); //쓰레드에 있는 핸들러에게 메세지를 보냄
            try{
                Thread.sleep(10000); //10초씩 쉰다.
            }catch (Exception e) {}
        }
    }

    private double gpsUpdate() {
        gpsTracker = new GPSTracker(context);

        // 현재 위치
        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        location = new Location("point A");
        location2 = new Location("point B");

        Log.d("fuckingsibal",""+latitude +","+longitude);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        location2.setLatitude(36.320686);
        location2.setLongitude(127.408690);

        Log.d("tlqkf",""+location.distanceTo(location2));
        return location.distanceTo(location2);
    }
}