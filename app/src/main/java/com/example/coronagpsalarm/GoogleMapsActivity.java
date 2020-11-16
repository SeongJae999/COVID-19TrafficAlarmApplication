package com.example.coronagpsalarm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coronagpsalarm.models.CoronaLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class GoogleMapsActivity extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private ArrayList<CoronaLocation> arrayList;

    public GoogleMapsActivity(ArrayList<CoronaLocation> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    // Fragment 와 Layout 을 연결시켜주는 부분
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_googlemap, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }
    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mapView != null)
            mapView.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        for(int i = 1; i < arrayList.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(arrayList.get(i).getLatitude(), arrayList.get(i).getLongitude()));
            googleMap.addMarker(markerOptions);
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.339878, 127.394001), 12));
    }
}
