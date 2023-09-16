package com.example.CourseScheduler;

import com.example.CourseScheduler.model.GradeDistributionItem;
import com.example.CourseScheduler.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseRequestController {
    public CourseRequestController() {}
    @Autowired
    ItemRepository gradeDistributionItemRepo;

    @PostMapping("/submit")
    void processCourseRequest(@RequestBody CourseRequest newCourseRequest) {
        getItemsByInstructor(newCourseRequest);
    }

    public void getItemsByInstructor(CourseRequest request) {
        List<GradeDistributionItem> list = gradeDistributionItemRepo.findAll(request.getSubject(), request.getSubjectID());
        list.forEach(item -> System.out.println(item.toString()));
    }
}
