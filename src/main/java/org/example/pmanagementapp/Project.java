package org.example.pmanagementapp;

public class Project {
    private String projectTitle;
    private String projectDate;
    private double projectStage;

    public double getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(double projectStage) {
        this.projectStage = projectStage;
    }

    public String getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(String projectDate) {
        this.projectDate = projectDate;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
}
