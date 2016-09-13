package com.aleksnik.showingplaces.net.GooglePlace;

import android.app.Activity;
import android.location.Location;
import android.support.v4.app.FragmentActivity;

import com.aleksnik.showingplaces.core.callback.MainCallback;
import com.google.android.gms.common.api.GoogleApiClient;



public class GoogleFacade {
    private GoogleMapManager mGoogleMapManager;
    private GoogleApiClient mClient;
    private final BuildManager mBuildManager;


    public GoogleFacade() {
        mBuildManager = new BuildManager();
        mGoogleMapManager = new GoogleMapManager();
    }

    public void buildGoogleApiClient(
            FragmentActivity activity,
            GoogleApiClient.ConnectionCallbacks googleConnectionCallback) {

        mClient = mBuildManager.buildGoogleApiClient(activity, googleConnectionCallback);
        mGoogleMapManager.setClient(mClient);

    }

    public GoogleApiClient getmClient() {
        return mBuildManager.getmClient();
    }

    public void tryConnectClient(int resultCode) {
        mBuildManager.tryConnectClient(mClient, resultCode);
    }

    public void onDialogDismissed() {
        mBuildManager.onDialogDismissed();
    }

    public Location getCurrentLocation(Activity activity) {
        return mGoogleMapManager.getCurrentLocation(activity);
    }

    public void getNearestPlaceById(String placeId, MainCallback simpleMainCallback) {
        mGoogleMapManager.getNearestPlaceById(placeId, simpleMainCallback );
    }
}
