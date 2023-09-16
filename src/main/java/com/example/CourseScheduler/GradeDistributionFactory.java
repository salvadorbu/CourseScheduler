package com.example.CourseScheduler;

import com.example.CourseScheduler.model.GradeDistributionItem;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GradeDistributionFactory {
    private static Map<String, List<GradeDistributionItem>> generateGDIMapping() throws IOException {
        String[] courses = new String[] {"AOE","ALCE","AAEC","ALS","AINS","APSC","APS","AHRM","ARBC","ARCH","AAD","ART","BDS","BCHM","BIOL","BSE","BMVS","BMSP","BMES","BC","BUS","BIT","EDCT","CHE","CHEM","CHN","CINE","CEE","CLA","COS","COMM","CMST","CMDA","CS","CEM","CONS","CEP","CRIM","CSES","DASC","DANC","ECON","EDCO","EDCI","EDEP","ECE","ENGR","ENGE","ESM","ENGL","ENT","ENSC","FCS","FMD","FIN","FNAD","FA","FIW","FST","FL","FREC","VT","FR","GEOG","GEOS","GER","GR","HEB","HIST","HORT","HTM","HD","HNFE","HUM","ISE","IDS","EDIT","ISC","ITDS","IS","ITAL","JPN","JMC","JUD","KOR","LAR","LAT","LDRS","LAHS","MGT","MKTG","MSE","MATH","ME","MTRG","MN","AROTC","MS","AS","MINE","MUS","NANO","NR","NEUR","NSEG","PSVP","PHIL","PPE","PHYS","PPWS","PSCI","PHS","PORT","PM","PSYC","PR","REAL","RLCL","RED","RUS","SPES","SPIA","STS","STL","SOC","SPAN","STAT","SUMA","SBIO","SYSB","EDTE","TA","TBMH","UNIV","UH","REG","UAP","WATR","WGS"};
        RegistrationTimetable rt = new RegistrationTimetable("2023");
        Map<String, List<GradeDistributionItem>> gdiMapping = new HashMap<>();

        for (String course : courses) {
            try {
                gdiMapping.put(course, rt.getCourses(course));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return gdiMapping;
    }

    public static Map<String, List<GradeDistributionItem>> insertGPA(){
        try {
            Map<String, List<GradeDistributionItem>> gdiMapping = GradeDistributionFactory.generateGDIMapping();
            double gpa;

            for (Map.Entry<String, List<GradeDistributionItem>> entry : gdiMapping.entrySet()) {
                List<GradeDistributionItem> gradeDistributionItems = entry.getValue();
                for(GradeDistributionItem item : gradeDistributionItems){
                    System.out.println(item.toString());
                    gpa = searchGPA(item.getCourseNo(), item.getInstructor().substring(item.getInstructor().lastIndexOf(" ") + 1));
                    item.setGPA(gpa);
                }
            }
            return gdiMapping;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static double searchGPA(String courseNo, String professor) throws IOException {
        //Replace with your local file path to updated.csv data dump
        BufferedReader br = new BufferedReader(new FileReader("C:\\Code\\grade_distro_formatter\\updated.csv"));
        String line;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            if(values[1].equalsIgnoreCase(courseNo) && values[2].equalsIgnoreCase(professor)) {
                br.close();
                return Double.parseDouble(values[3]);
            }
        }
        br.close();
        return 0.;
    }

    public static void main(String[] args) throws IOException {
        System.out.print(GradeDistributionFactory.generateGDIMapping());
    }
}
