package org.example.pmanagementapp;

public class Project {
    private String projectTitle;
    private String projectDate;
    private String projectSendDate;
    private double projectStage;
    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectSendDate() {
        return projectSendDate;
    }

    public void setProjectSendDate(String projectSendDate) {
        this.projectSendDate = projectSendDate;
    }

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
