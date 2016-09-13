package com.aleksnik.showingplaces.ui.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aleksnik.showingplaces.core.SPApplication;
import com.aleksnik.showingplaces.net.GooglePlace.GoogleFacade;


public abstract class GenericActivity extends AppCompatActivity {
    protected GoogleFacade mGoogleFacade;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoogleFacade = getSPApplication().getGoogleFacade();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public final SPApplication getSPApplication() {
        return (SPApplication) getApplication();
    }


    protected void showShortToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

}
