package com.example.CourseScheduler;

import com.example.CourseScheduler.model.GradeDistributionItem;
import com.example.CourseScheduler.repository.ItemRepository;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;



public class CourseRanker {
    private final ItemRepository itemRepository;
    private List<List<GradeDistributionItem>> availableCourses;

    public CourseRanker(ItemRepository itemRepository, List<CourseRequest> courses) {
        this.itemRepository = itemRepository;
        this.availableCourses = new ArrayList<>();

        for (CourseRequest courseRequest : courses) {
            availableCourses.add(itemRepository.findAll(courseRequest.getSubject(), courseRequest.getSubjectID()));
        }
    }

    public List<List<GradeDistributionItem>> generateSchedule() {
        List<List<GradeDistributionItem>> sortedCourses = new ArrayList<>();

        for (int i = 0; i < availableCourses.size(); i++) {
            List<GradeDistributionItem> courses = availableCourses.get(i);
            List<GradeDistributionItem> sorted = new ArrayList<>();

            if (courses.size() <= 3) {
                for (GradeDistributionItem course : courses) {
                    sorted.add(course);
                }
            } else {
                Comparator<GradeDistributionItem> customComparator = Collections.reverseOrder();
                Collections.sort(courses, customComparator);
                sorted.add(courses.get(0));
                sorted.add(courses.get(1));
                sorted.add(courses.get(2));
            }

            sortedCourses.add(sorted);
        }
        return sortedCourses;
    }


    public List<List<GradeDistributionItem>> generateAccessibilitySchedule() {

        List<List<GradeDistributionItem>> sortedByGPA = generateSchedule();

        ArrayList<String[]> classData = new ArrayList<>();
        Set<String> buildings = new HashSet<>();
        HashMap<String, Double> distanceData = new HashMap<>();

        for (List<GradeDistributionItem> innerList : sortedByGPA) {
            for (GradeDistributionItem item : innerList) {
                String[] temp_ar = {String.valueOf(item.getGPA()), item.getBuilding(), item.getStartTime(), item.getEndTime(), item.getSubject(), item.getCourseNo(), item.getCRN()};
                classData.add(temp_ar);

                buildings.add(item.getBuilding());
            }
        }

        // Calculate and store distances between buildings
        Map<String, Map<String, Double>> distanceMap = new HashMap<>();

        for (String build1 : buildings) {
                Map<String, Double> innerDistanceMap = new HashMap<>();
                for (String build2 : buildings) {
                    if (!build1.equals(build2)) {
                        double dist = FlatEarthDist.distance(build1, build2);
                        innerDistanceMap.put(build2, dist);
                    }
                }
                distanceMap.put(build1, innerDistanceMap);
        }
        String prompt = "I have a list of potential classes and I am trying to make a schedule. classData has all these" +
                " classes in the format [GPA, Building, Start Time, End Time, Subject, course no, CRN]. Additionally, " +
                "distanceMap has the distance between every single building in the format {Building A={Building B=distance between A and B, Building C=distance between A and C}, Building B={Building A=distance between B and A,... " +
                "\nTaking into account both the GPA and the Distance between the building where a higher GPA is better and a lower distance is better," +
                " please return a string formatted like \"[CRN, CRN, CRN, ...]\" that includes one of every single class. The classes can be identified by their Subject and course no." +
                " Lastly, take into account the start and end time when creating this list to make sure no two classes overlap. When you answer, please only output the list and nothing more. The classData = " + classData + "\n and the " +
                "distanceMap = ";
        System.out.println(prompt);
        System.out.println(chatGPT(prompt));

        return sortedByGPA;
//        chatGPT("");
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
            String body = "{\"model\": \"" + model + "\", \"max_tokens\": 150, \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
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
