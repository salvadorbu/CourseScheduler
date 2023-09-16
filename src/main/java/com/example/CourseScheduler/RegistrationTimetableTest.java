package com.example.CourseScheduler;

<<<<<<< HEAD
import org.testng.annotations.Test;
=======
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

>>>>>>> 9c99b31a7ff7678fa32954087c97940b93cf8084

public class RegistrationTimetableTest {
    private RegistrationTimetable rt;
    @Before
    public void setUp() {
        try {
            rt = new RegistrationTimetable("202309");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSampleRequest() throws IOException {
        rt.getCourses("MATH", "2114");
    }
}
