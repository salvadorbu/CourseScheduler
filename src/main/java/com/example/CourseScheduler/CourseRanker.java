package com.example.CourseScheduler;

import com.example.CourseScheduler.model.GradeDistributionItem;
import com.example.CourseScheduler.repository.ItemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
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
}