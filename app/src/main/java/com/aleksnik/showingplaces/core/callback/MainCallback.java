package com.aleksnik.showingplaces.core.callback;


import com.aleksnik.showingplaces.model.ModelDetailPlace;
import com.aleksnik.showingplaces.model.NearestPlace;

import java.util.List;

public interface MainCallback {

     void onSuccess();

     void onError(String error);

     void onSuccessGetNearestPlaces(List<NearestPlace> nearestPlaces);

     void onSuccessGetPlace(ModelDetailPlace modelDetailPlace);


}
