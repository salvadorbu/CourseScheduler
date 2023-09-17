package com.example.CourseScheduler;

import com.example.CourseScheduler.model.GradeDistributionItem;
import com.example.CourseScheduler.repository.ItemRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseRequestController {
    private final ItemRepository itemRepository;
    public CourseRequestController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping("/submit")
    List<List<GradeDistributionItem>> processCourseRequest(@RequestBody List<CourseRequest> courses) {
        CourseRanker ranker = new CourseRanker(itemRepository, courses);
        if (courses.get(0).getAccessibleRouting()) {
            return ranker.generateAccessibilitySchedule();
        }
        return ranker.generateSchedule();
    }
}
