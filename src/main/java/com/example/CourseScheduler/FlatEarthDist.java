package com.example.CourseScheduler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class FlatEarthDist {
    public static double distance(String build1, String build2){
        String jsonFilePath = "src/main/java/com/example/CourseScheduler/database/location_formatted.json";

        try {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, double[]>>() {}.getType();
            HashMap<String, double[]> locationDict = gson.fromJson(new FileReader(jsonFilePath), type);

            double lat1 = locationDict.get(build1)[0];
            double lng1 = locationDict.get(build1)[1];
            double lat2 = locationDict.get(build2)[0];
            double lng2 = locationDict.get(build2)[1];

            double a = (lat1-lat2)*FlatEarthDist.distPerLat(lat1);
            double b = (lng1-lng2)*FlatEarthDist.distPerLng(lat1);
            return Math.sqrt(a*a+b*b);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.;
    }

    private static double distPerLng(double lat){
        return 0.0003121092*Math.pow(lat, 4)
                +0.0101182384*Math.pow(lat, 3)
                -17.2385140059*lat*lat
                +5.5485277537*lat+111301.967182595;
    }

    private static double distPerLat(double lat){
        return -0.000000487305676*Math.pow(lat, 4)
                -0.0033668574*Math.pow(lat, 3)
                +0.4601181791*lat*lat
                -1.4558127346*lat+110579.25662316;
    }
}
