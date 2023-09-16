package com.example.CourseScheduler.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("gradedistributionitems")
public class GradeDistributionItem {


    @Id
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    private String subject;

    private String courseNo;

    private String CRN;
    public String getCRN() { return CRN; }

    private String instructor;

    private String startTime;

    private String endTime;
    private double GPA;

    private String building;

    private String room;
    public String getRoom() { return room; }



    public GradeDistributionItem(String crn, String subject, String courseNo, String instructor, double GPA, String room, String building, String startTime, String endTime){
        super();
        this.CRN = crn;
        this.subject = subject;
        this.courseNo = courseNo;
        this.instructor = instructor;
        this.GPA = GPA;
        this.room = room;
        this.building = building;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "crn=" + CRN + ",subject=" + subject +
                ",courseNo=" + courseNo + ",instructor=" + instructor + ",room=" + room + ",building=" + building;
    }

    public GradeDistributionItem(){

    }
}
