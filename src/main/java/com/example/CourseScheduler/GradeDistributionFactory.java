package com.example.CourseScheduler;

import com.example.CourseScheduler.model.GradeDistributionItem;

import java.io.IOException;
import java.util.*;
import com.example.CourseScheduler.repository.ItemRepository;
public class GradeDistributionFactory {
    public static Map<String, List<GradeDistributionItem>> generateGDIMapping() throws IOException {
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

    public static void main(String[] args) throws IOException {
        Map<String, List<GradeDistributionItem>> map = GradeDistributionFactory.generateGDIMapping();
        for (List<GradeDistributionItem> list : map.values()) {
            for (GradeDistributionItem gd : list) {
                System.out.println(gd.toString());
            }
        }
    }
}
