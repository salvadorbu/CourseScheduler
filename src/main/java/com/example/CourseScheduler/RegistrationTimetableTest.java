package com.example.CourseScheduler;



import com.example.CourseScheduler.model.GradeDistributionItem;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
        List<GradeDistributionItem> list = (ArrayList)rt.getCourses("CS");
        for (GradeDistributionItem gdi : list) {
            System.out.println(gdi.toString());
        }
    }
}
