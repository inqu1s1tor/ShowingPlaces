package com.aleksnik.showingplaces.net.GooglePlace;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.aleksnik.showingplaces.core.callback.MainCallback;
import com.aleksnik.showingplaces.util.ModelMapper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

class GoogleMapManager {

    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = GoogleMapManager.class.getSimpleName();

    public void setClient(GoogleApiClient client) {
        mGoogleApiClient = client;
    }


    Location getCurrentLocation(Activity activity) {

        if (ActivityCompat.checkSelfPermission(mGoogleApiClient.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mGoogleApiClient.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            if(isMarshmallowPlusDevice()) {
                ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
            }
            Log.d("TAG", "Have no permissions");
            return  null;
        }

            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (lastLocation != null) {
                Log.d("!!!!! - all good - ", "" + lastLocation);
                return lastLocation;
            } else {
                lastLocation = new Location(LocationManager.GPS_PROVIDER);
                Log.d("!!!!! - error - ", "" + lastLocation);
                lastLocation.setLatitude(2.0);
                lastLocation.setLongitude(1.0);
                return lastLocation;
            }
        }


    public boolean isMarshmallowPlusDevice() {
        Log.d(TAG, "isMarshmallowPlusDevice: ");
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    void getNearestPlaceById(String placeId, final MainCallback mainCallback) {

        Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            final Place myPlace = places.get(0);
                            ModelMapper.convertGooglePlaceToModel(myPlace);

                            mainCallback.onSuccessGetPlace(ModelMapper.convertGooglePlaceToModel(myPlace));

                            Log.d("TAG", "Place found: " + "  " + myPlace.getAddress() + " "
                                    + myPlace.getPhoneNumber() + "  " + myPlace.getAddress());
                        } else {
                            Log.e("TAG", "Place not found");
                        }
                        places.release();
                    }
                });

    }


}
