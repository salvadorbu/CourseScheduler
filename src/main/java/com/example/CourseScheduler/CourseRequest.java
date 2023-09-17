package com.example.CourseScheduler;


public class CourseRequest {
    private String subject; // i.e. MATH, CS, GEOG
    private String subjectID; // i.e. 1114, 2114, 3134
    private boolean accessibleRouting;

    public CourseRequest(String subject, String subjectID, boolean accessibleRouting) {
        this.accessibleRouting = accessibleRouting;
        this.subject = subject;
        this.subjectID = subjectID;
    }

    public String getSubject() {
        return subject;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public boolean getAccessibleRouting() {
        return accessibleRouting;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public void setAccessibleRouting(boolean accessibleRouting) {
        this.accessibleRouting = accessibleRouting;
    }

    @Override
    public String toString() {
        return "CourseRequest{" + "subject=" + this.subject + ", subjectID=" + this.subjectID + ", accessibleRouting=" + "}";
    }
}