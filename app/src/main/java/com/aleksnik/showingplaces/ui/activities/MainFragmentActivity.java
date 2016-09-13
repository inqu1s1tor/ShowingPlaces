package com.aleksnik.showingplaces.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.aleksnik.showingplaces.R;
import com.aleksnik.showingplaces.core.bridge.ActivityBridge;
import com.aleksnik.showingplaces.ui.FragmentLauncher;


public class MainFragmentActivity extends GenericActivity implements ActivityBridge {

    private FragmentLauncher fragmentLauncher;


    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainFragmentActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_activity);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
        }

        fragmentLauncher = new FragmentLauncher(getSupportFragmentManager());
        fragmentLauncher.launchCategoryOfPlacesFragment();
    }


    @Override
    public FragmentLauncher getFragmentLauncher() {
        return fragmentLauncher;
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }


}


