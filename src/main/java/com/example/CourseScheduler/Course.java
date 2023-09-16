package com.example.CourseScheduler;

public class Course {
    private String professor;
    private int CRN;
    private double longitude;
    private double latitude;
    private double averageGPA;
    String classTime; // Format [DAYS],[HOUR] (i.e. MWF,4:00pm-5:00pm)


    public Course(String prof, int crn, double lon, double lat, double gpa, String time) {
        this.professor = prof;
        this.CRN = crn;
        this.longitude = lon;
        this.latitude = lat;
        this.averageGPA = gpa;
        this.classTime = time;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getProfessor() {
        return professor;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getCRN() {
        return CRN;
    }

    public double getAverageGPA() {
        return averageGPA;
    }

    @Override
    public String toString() {
        return String.format("Course{CRN=%d, professor=%s, GPA=%f, Longitude=%f, Latitude=%f}",
                CRN, professor, averageGPA, longitude, latitude);
    }
}
