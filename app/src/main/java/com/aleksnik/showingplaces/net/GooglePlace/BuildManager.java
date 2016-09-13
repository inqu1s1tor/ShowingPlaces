package com.aleksnik.showingplaces.net.GooglePlace;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;


class BuildManager {

    private GoogleApiClient mClient;
    private boolean mResolvingError = false;

    GoogleApiClient buildGoogleApiClient(final FragmentActivity fragmentActivity, GoogleApiClient.ConnectionCallbacks googleConnectionCallback) {

        mClient = new GoogleApiClient
                .Builder(fragmentActivity)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(googleConnectionCallback)
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d("TAG", "onConnectionFailed: "+connectionResult.getErrorMessage());
                    }
                })
                .build();

        Log.d("Log", "mClient"+ mClient);

        mClient.connect();


        return mClient;
    }

    void tryConnectClient(GoogleApiClient client, int resultCode) {
        mResolvingError = false;
        if (resultCode == Activity.RESULT_OK) {
            if (!client.isConnecting() &&
                    !client.isConnected()) {
                client.connect();
            }
        }
    }

    public GoogleApiClient getmClient() {
        return mClient;
    }

    public void onDialogDismissed() {
        mResolvingError = false;
    }
}
