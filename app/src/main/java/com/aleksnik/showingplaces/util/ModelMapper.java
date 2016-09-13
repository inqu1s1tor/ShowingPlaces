package com.aleksnik.showingplaces.util;


import com.aleksnik.showingplaces.model.ModelDetailPlace;
import com.aleksnik.showingplaces.model.NearestPlace;
import com.google.android.gms.location.places.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class ModelMapper {


    public static List<NearestPlace> convertJsonToModel(JSONArray jsonArrayPlaces) throws JSONException {
        List<NearestPlace> nearestPlaces = new ArrayList<>(jsonArrayPlaces.length());

        for (int i = 0; i < jsonArrayPlaces.length(); i++) {
            JSONObject jsonObject = jsonArrayPlaces.getJSONObject(i);

            NearestPlace nearestPlace = new NearestPlace();
            nearestPlace.setPlaceId(jsonObject.getString("place_id"));
            nearestPlace.setNamePlace(jsonObject.getString("name"));
            nearestPlace.setAddressPlace(jsonObject.getString("vicinity"));

            nearestPlaces.add(nearestPlace);

        }

        return nearestPlaces;

    }

    public static ModelDetailPlace convertGooglePlaceToModel (Place myPlace) {
        ModelDetailPlace modelDetailPlace = new ModelDetailPlace();

        modelDetailPlace.setDetailName(myPlace.getName().toString());
        modelDetailPlace.setDetailAddress(myPlace.getAddress().toString());
        modelDetailPlace.setDetailPhoneNumber(myPlace.getPhoneNumber().toString());
        modelDetailPlace.setLatLng(myPlace.getLatLng());
        return modelDetailPlace;

    }

}

