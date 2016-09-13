package com.aleksnik.showingplaces.net;



public final class RequestFactory {
    private static final String googleAPIKey = "AIzaSyAoITHNMCBjG6MGaj3olOSoYmfLeA8Rqxw";


    public static String getUrlRequest(String category, double latitude, double longitude, int radius) {
        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=" + radius + "&types=" +category +"&key=" + googleAPIKey;
    }

}
