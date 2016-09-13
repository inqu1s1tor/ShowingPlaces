package com.aleksnik.showingplaces.core;

import android.app.Application;

import com.aleksnik.showingplaces.net.GooglePlace.GoogleFacade;
import com.aleksnik.showingplaces.storage.SharedDataManager;


public class SPApplication extends Application {
    private GoogleFacade googleFacade;
    private SharedDataManager sharedDataManager;


    @Override
    public void onCreate() {
        super.onCreate();
        googleFacade = new GoogleFacade();
        sharedDataManager = new SharedDataManager(this);
    }

    public GoogleFacade getGoogleFacade() {
        return googleFacade;
    }

    public SharedDataManager getSharedDataManager() {
        return sharedDataManager;
    }
}
