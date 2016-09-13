package com.aleksnik.showingplaces.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksnik.showingplaces.R;
import com.aleksnik.showingplaces.core.callback.SimpleMainCallback;
import com.aleksnik.showingplaces.model.ModelDetailPlace;
import com.google.android.gms.maps.model.LatLng;

public class PlaceDetailsInformationFragment extends GenericFragment {

    private TextView detailsNamePlaceTv;
    private TextView detailsAddressPlaceTv;
    private TextView detailsPhoneNumberPlaceTv;
    private static final String SELECTED_PLACE_ID = "selected_place_id";
    private LatLng latLng;
    public static final String TAG = PlaceDetailsInformationFragment.class.getSimpleName();
    private String selectedPlaceId;


    public static Bundle buildArguments(String selectedPlaceId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_PLACE_ID, selectedPlaceId);
        return bundle;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_datails_information_fragment, container, false);

        detailsNamePlaceTv = (TextView) view.findViewById(R.id.details_name_place);
        detailsAddressPlaceTv = (TextView) view.findViewById(R.id.details_address_place);
        detailsPhoneNumberPlaceTv = (TextView) view.findViewById(R.id.details_number_phone_place);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedPlaceId = (String) bundle.getSerializable(SELECTED_PLACE_ID);
        }

        mActivityBridge.getSPApplication().getGoogleFacade().getNearestPlaceById(selectedPlaceId, new NewCallback());

        view.findViewById(R.id.show_on_the_map).setOnClickListener(new Clicker());

    }

    private void initDetailPlaceInformation(ModelDetailPlace modelDetailPlace) {
        detailsNamePlaceTv.setText(modelDetailPlace.getDetailName());
        detailsAddressPlaceTv.setText(modelDetailPlace.getDetailAddress());
        detailsPhoneNumberPlaceTv.setText(modelDetailPlace.getDetailPhoneNumber());


    }

    private LatLng getLatLongtitude(ModelDetailPlace modelDetailPlace) {
        this.latLng = modelDetailPlace.getLatLng();
        Log.d(TAG, "getLatLongtitude: "+latLng);
        return latLng;
    }


    private final class NewCallback extends SimpleMainCallback {

        @Override
        public void onSuccessGetPlace(ModelDetailPlace modelDetailPlace) {
            Log.d("TAG", "onSuccessGetPlace: " + modelDetailPlace.getDetailName());
            initDetailPlaceInformation(modelDetailPlace);
            getLatLongtitude(modelDetailPlace);

            hideProgressDialog();
        }
    }

    private final class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.show_on_the_map:
                    Log.d(TAG, "onClick: "+latLng);
                    mActivityBridge.getFragmentLauncher().launchMapFragment(latLng);
                    break;
            }
        }
    }
}
