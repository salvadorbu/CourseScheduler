package com.example.CourseScheduler;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


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
        // test
        return;
    }
}
