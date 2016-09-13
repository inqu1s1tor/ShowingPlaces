package com.aleksnik.showingplaces.ui.fragments;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksnik.showingplaces.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends GenericFragment implements OnMapReadyCallback {

    public static final String TAG = MapFragment.class.getSimpleName();

    private LatLng latLng;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.map_fragment, container, false);
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm
                .findFragmentById(R.id.map);
        Log.d(TAG, "onCreateView: " + mapFragment);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: " + googleMap.getMapType());
        //LatLng place = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: sraerttmap");
        super.onStart();
    }

    public MapFragment() {

    }

    public MapFragment(LatLng latLng) {
        this.latLng = latLng;
    }


}
