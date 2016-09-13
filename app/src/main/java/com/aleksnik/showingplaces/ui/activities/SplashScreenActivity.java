package com.aleksnik.showingplaces.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.aleksnik.showingplaces.R;
import com.aleksnik.showingplaces.util.ConnectivityChecker;
import com.google.android.gms.common.api.GoogleApiClient;



public class SplashScreenActivity extends GenericActivity {

    public static final String TAG = SplashScreenActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);


        if (checkConnectivity()) {
            mGoogleFacade.buildGoogleApiClient(this, new GoogleConnectionCallback());
        } else {
            finish();
        }

    }

    private class GoogleConnectionCallback implements GoogleApiClient.ConnectionCallbacks {
        @Override
        public void onConnected(@Nullable Bundle bundle) {

            Log.d(TAG, "onConnected: ");
            MainFragmentActivity.start(SplashScreenActivity.this);
            finish();
        }

        @Override
        public void onConnectionSuspended(int i) {
        }
    }

    private boolean checkConnectivity() {

        if (!ConnectivityChecker.isPlayServiceArePresents(this)) {
            showLongToast("Play Services are missed");
            return false;
        }

        if (!ConnectivityChecker.isNetworkAvailable(this)) {
            showLongToast("Wi-Fi/Internet is not active.");
            return false;
        }

        return true;
    }


}
