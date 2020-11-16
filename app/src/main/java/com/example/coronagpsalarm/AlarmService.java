package com.example.coronagpsalarm;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.coronagpsalarm.gpsLibrary.GPSPermission;
import com.example.coronagpsalarm.gpsLibrary.GPSTracker;

public class AlarmService extends Thread {
    private Handler handler;
    private Runnable runnable;

    private GPSPermission gpsPermission;
    private GPSTracker gpsTracker;

    private Location location;
    private Location location2;

    private boolean isRun = true;

    Context context;

    public AlarmService(Context context) {
        this.context = context;
    }

    private void gpsUpdate() {
        gpsPermission = new GPSPermission(context, new MainActivity());
        gpsPermission.PermissionSetting();
        gpsTracker = new GPSTracker(context);

        // 현재 위치
        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        location = new Location("point A");
        location2 = new Location("point B");

        location.setLatitude(latitude);
        location.setLongitude(longitude);

        location2.setLatitude(36.320686);
        location2.setLongitude(127.408690);
    }

    private void sendNotification() {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String NOTIFICATION_ID = "Channel_01";
        String NOTIFICATION_NAME = "MyChannel01";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_ID)
                .setContentTitle("Service Test") //타이틀 TEXT
                .setContentText("확진자 방문 지역 접근")
                .setAutoCancel(true)
                .setSmallIcon (R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        // 채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_ID, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, builder.build());
    }
}
