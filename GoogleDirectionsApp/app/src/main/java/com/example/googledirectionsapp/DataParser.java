package com.example.googledirectionsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DataParser {

    private static final String TAG = "DataParser";

    public HashMap<String, String> parseDirections(String jsonData){
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getDuration(jsonArray);
    }

    public String[] getDirectionsPoints(String jsonData){
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getPaths(jsonArray);
    }

    public String[] getPaths(JSONArray googleStepsJson){
        int size = googleStepsJson.length();
        String[] polylines = new String[size];

        for (int i=0; i<size; i++){
            try {
                polylines[i] = getPath(googleStepsJson.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return polylines;
    }

    public String getPath(JSONObject googlePathJson){
        String polyline = "";
        try {
            polyline = googlePathJson.getJSONObject("polyline").getString("points");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return polyline;
    }

    private HashMap<String, String> getDuration(JSONArray googleDirectionJson) {
        HashMap<String, String> googleDirectionsMap = new HashMap<>();

        String duration = "";
        String distance = "";

        Log.d("json response: ",googleDirectionJson.toString());

        try {
            duration = googleDirectionJson.getJSONObject(0).getJSONObject("duration").getString("text");
            distance = googleDirectionJson.getJSONObject(0).getJSONObject("distance").getString("text");

            googleDirectionsMap.put("duration", duration);
            googleDirectionsMap.put("distance", distance);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return googleDirectionsMap;
    }
}
