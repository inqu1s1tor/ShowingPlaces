package com.aleksnik.showingplaces.model;


import com.google.android.gms.maps.model.LatLng;

public class ModelDetailPlace  {

    private String DetailName;
    private String DetailAddress;
    private String DetailPhoneNumber;
    private LatLng latLng;


    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }


    public String getDetailName() {
        return DetailName;
    }

    public void setDetailName(String detailName) {
        DetailName = detailName;
    }

    public String getDetailAddress() {
        return DetailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        DetailAddress = detailAddress;
    }

    public String getDetailPhoneNumber() {
        return DetailPhoneNumber;
    }

    public void setDetailPhoneNumber(String detailPhoneNumber) {
        DetailPhoneNumber = detailPhoneNumber;
    }





}
