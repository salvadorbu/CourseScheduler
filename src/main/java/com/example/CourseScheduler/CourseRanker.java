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

        for (GradeDistributionItem gdi : availableCourses.get(2)) {
            System.out.println(gdi.toString());
        }
        return sortedCourses;
    }
}
