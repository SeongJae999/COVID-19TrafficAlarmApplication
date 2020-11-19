package com.example.coronagpsalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.MenuItem;

import com.example.coronagpsalarm.gpsLibrary.GPSPermission;
import com.example.coronagpsalarm.models.CoronaLocation;
import com.example.coronagpsalarm.models.PatientInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CoronaLocation> locationArrayList;
    private ArrayList<PatientInfo> patientInfoArrayList;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference locationRef;
    private DatabaseReference patientInfoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
    }

    private void Init() {
        firebaseCall(); // 파이어베이스 호출

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_main);
        // BottomNavigation Item Click Listener 설정

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMain, new HomeActivity(patientInfoArrayList)).commitAllowingStateLoss();
                        break;
                    case R.id.menu_googleMap:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMain,new GoogleMapsActivity(locationArrayList)).commitAllowingStateLoss();
                        break;
                    case R.id.menu_news:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMain, new NewsActivity()).commitAllowingStateLoss();
                }
                return false;
            }
        });
    }

    private void firebaseCall() {
        locationArrayList = new ArrayList<>(); // CoronaLocation 객체를 담을 ArrayList
        patientInfoArrayList = new ArrayList<>(); // PatientInfo 객체를 담을 ArrayList
        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference(); // 데이터베이스 테이블 연결

        // 확진자 수 현황
        patientInfoRef = databaseReference.child("Info");
        patientInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출
                    PatientInfo patientInfo = snapshot.getValue(PatientInfo.class); // Location 객체에 데이터를 저장
                    patientInfoArrayList.add(patientInfo);
                }
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentMain, new HomeActivity(patientInfoArrayList)).commitAllowingStateLoss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 데이터베이스를 가져오던중 에러 발생 시
                Log.e("MainActivity", String.valueOf(error.toException())); // 에러문 출력
            }
        });
        // 확진자 방문 위치
        locationRef = databaseReference.child("daejeon");
        locationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CoronaLocation location = snapshot.getValue(CoronaLocation.class);
                    locationArrayList.add(location);
                }

                // 절전 모드를 해체하는 권한을 얻는 코드
                PowerManager pm = (PowerManager) getApplicationContext().getSystemService(POWER_SERVICE);
                boolean isWhiteListing = false;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    isWhiteListing = pm.isIgnoringBatteryOptimizations(getApplicationContext().getPackageName());
                }
                if (!isWhiteListing) {
                    Intent intent = new Intent();
                    intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                    intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                    startActivity(intent);
                }

                GPSPermission gpsPermission = new GPSPermission(getApplicationContext(), MainActivity.this);
                gpsPermission.PermissionSetting();

                ServiceThread serviceThread = new ServiceThread(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, AlarmService.class);
                startService(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });
    }
}