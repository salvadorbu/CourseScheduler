package com.example.CourseScheduler;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.CourseScheduler.model.GradeDistributionItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


public class schedulePicker {

    public static void main(String[] args) {

        String jsonFilePath = "src/main/java/com/example/CourseScheduler/database/location_formatted.json";

        try {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, double[]>>() {}.getType();

            HashMap<String, double[]> locationDict = gson.fromJson(new FileReader(jsonFilePath), type);

            FlatEarthDist distCalc = new FlatEarthDist();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(chatGPT("How to combat baldness and poor posture? Additionally, tell me all about soil competitions."));
    }

    public static List<List<GradeDistributionItem>> getCourseSchedule(List<List<GradeDistributionItem>> availableCourses){
        String jsonFilePath = "src/main/java/com/example/CourseScheduler/database/location_formatted.json";

        try {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, double[]>>() {}.getType();

            HashMap<String, double[]> locationDict = gson.fromJson(new FileReader(jsonFilePath), type);

            FlatEarthDist distCalc = new FlatEarthDist();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"max_tokens\": 30, \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            System.out.println(body);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }

}
