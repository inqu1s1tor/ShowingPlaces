package com.aleksnik.showingplaces.net;

import android.util.Log;

import com.aleksnik.showingplaces.core.callback.MainCallback;
import com.aleksnik.showingplaces.core.callback.SimpleMainCallback;
import com.aleksnik.showingplaces.model.NearestPlace;
import com.aleksnik.showingplaces.util.ModelMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class HttpRequest {

    public static final String TAG = HttpRequest.class.getSimpleName();
    private List<NearestPlace> nearestPlaces = new ArrayList<>();

    private BufferedReader br;



    public void callLocation(String URL_ADDRESS, final SimpleMainCallback mainCallback) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(URL_ADDRESS);
            Log.d(TAG, "createHttpRequestPlaces: " + url.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);

            connection.setDoOutput(true);
            connection.connect();
            Log.d(TAG, "createHttpRequestPlaces: " + connection.getContent().toString());

            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String jsonString = new String();

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            jsonString = sb.toString();
            Log.d(TAG, "createHttpRequestPlaces: " + jsonString);
            System.out.println("JSON: " + jsonString);
            parseJson(jsonString,mainCallback);

        } catch (Exception e) {
            Log.d(TAG, "createHttpRequestPlaces: " + e.getMessage());
        }
        finally {

            try {
                connection.disconnect();
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseJson(String json, MainCallback mainCallback) {
        JSONObject dataJsonObj = null;
        try {
            dataJsonObj = new JSONObject(json);
            JSONArray jsonArray = dataJsonObj.getJSONArray("results");

            nearestPlaces = ModelMapper.convertJsonToModel(jsonArray);
            mainCallback.onSuccessGetNearestPlaces(nearestPlaces);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<NearestPlace> getNearestPlaces() {
        return nearestPlaces;
    }

}



