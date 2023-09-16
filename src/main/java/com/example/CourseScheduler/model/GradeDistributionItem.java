package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("gradedistributionitems")
public class GradeDistributionItem {
    @Id
    private String id;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
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
    private int courseNo;
    private String instructor;
    private double GPA;

    public GradeDistributionItem(String id, String subject, int courseNo, String instructor, double GPA){
        super();
        this.id = id;
        this.subject = subject;
        this.courseNo = courseNo;
        this.instructor = instructor;
        this.GPA = GPA;
    }
}
