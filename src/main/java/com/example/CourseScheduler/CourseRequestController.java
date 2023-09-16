package com.example.CourseScheduler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseRequestController {
    public CourseRequestController() {}

    @PostMapping("/submit")
    Course processCourseRequest(@RequestBody CourseRequest newCourseRequest) {
        //process newcourserequest
        return new Course("Burleson", 8000, 0.0000, 0.0000, 4.0, "time");
    }
}
