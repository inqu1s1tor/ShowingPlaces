package com.aleksnik.showingplaces.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.aleksnik.showingplaces.R;
import com.aleksnik.showingplaces.ui.fragments.CategoryOfPlacesFragment;
import com.aleksnik.showingplaces.ui.fragments.GenericFragment;
import com.aleksnik.showingplaces.ui.fragments.MapFragment;
import com.aleksnik.showingplaces.ui.fragments.NearestPlacesFragment;
import com.aleksnik.showingplaces.ui.fragments.PlaceDetailsInformationFragment;
import com.aleksnik.showingplaces.ui.fragments.SettingsFragment;
import com.google.android.gms.maps.model.LatLng;


public class FragmentLauncher {

    private final FragmentManager manager;

    public FragmentLauncher(FragmentManager manager) {
        this.manager = manager;
    }


    private void launch(GenericFragment fragment, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();

        if(!TextUtils.isEmpty(tag)) {
            transaction.addToBackStack(tag);
        }
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commitAllowingStateLoss();

    }


    public void launchCategoryOfPlacesFragment() {
        GenericFragment categoryOfPlacesFragment = new CategoryOfPlacesFragment();
        launch(categoryOfPlacesFragment, null);
    }

    public void launchNearestPlacesFragment(String selectedCategory){
        GenericFragment nearestPlacesFragment = new NearestPlacesFragment();
        nearestPlacesFragment.setArguments(NearestPlacesFragment.buildArguments(selectedCategory));
        launch(nearestPlacesFragment, NearestPlacesFragment.class.getCanonicalName());
    }

    public void launchSettingsFragment() {
        GenericFragment settingsFragment = new SettingsFragment();
        launch(settingsFragment, SettingsFragment.class.getCanonicalName());
    }

    public void launchPlaceDetailsInformationFragment(String selectedPlaceId) {
        GenericFragment placeDetailsInformationFragment = new PlaceDetailsInformationFragment();
        placeDetailsInformationFragment.setArguments(PlaceDetailsInformationFragment.buildArguments(selectedPlaceId));
        launch(placeDetailsInformationFragment, PlaceDetailsInformationFragment.class.getCanonicalName());
    }

    public void launchMapFragment(LatLng latLng) {
        GenericFragment mapFragment = new MapFragment(latLng);
        launch(mapFragment, MapFragment.class.getCanonicalName());
    }

}
