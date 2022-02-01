package com.example.googledirectionsapp;

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.HashMap;

public class GetDirectionsData extends AsyncTask<Object, String, String> {

    private GoogleMap mMap;
    private String url;
    private String googleDirectionsData;
    String duration, distance;
    LatLng latLng;

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];
        latLng = (LatLng) objects[2];

        DownloadUrl downloadUrl = new DownloadUrl();

        try {
            googleDirectionsData = downloadUrl.readUrl(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {
        HashMap<String, String> directionLisst = null;
        String directionPoints[];
        DataParser dataParser = new DataParser();
        directionLisst = dataParser.parseDirections(s);

        mMap.clear();

        duration = directionLisst.get("duration");
        distance = directionLisst.get("distance");

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Duration = "+duration);
        markerOptions.snippet("Distance = "+distance);

        mMap.addMarker(markerOptions);

        directionPoints = dataParser.getDirectionsPoints(s);
        displayDirectionPoints(directionPoints);

    }

    void displayDirectionPoints(String[] directionPoints){
        int size = directionPoints.length;
        for (int i=0; i<size; i++){
            PolylineOptions options = new PolylineOptions();
            options.color(Color.RED);
            options.width(10);
            options.addAll(PolyUtil.decode(directionPoints[i]));

            mMap.addPolyline(options);
        }
    }
}
