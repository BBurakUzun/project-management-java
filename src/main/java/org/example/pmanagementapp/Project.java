package org.example.pmanagementapp;

public class Project {
    private String projectTitle;
    private String projectFirstDate;
    private String projectLastDate;
    private double projectStage;
    private String description;

    public Project(String projectTitle, String projectFirstDate, String projectLastDate, double projectStage, String description) {
        this.projectTitle = projectTitle;
        this.projectFirstDate = projectFirstDate;
        this.projectLastDate = projectLastDate;
        this.projectStage = projectStage;
        this.description = description;
    }


    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%s", projectTitle, projectFirstDate, projectLastDate, projectStage, description);
    }

    public static Project fromCSV(String csv) {
        String[] fields = csv.split(",");
        return new Project(fields[0], fields[1], fields[2], Double.parseDouble(fields[3]), fields[4]);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectLastDate() {
        return projectLastDate;
    }

    public void setProjectLastDate(String projectLastDate) {
        this.projectLastDate = projectLastDate;
    }

    public double getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(double projectStage) {
        this.projectStage = projectStage;
    }

    public String getProjectFirstDate() {
        return projectFirstDate;
    }

    public void setProjectFirstDate(String projectFirstDate) {
        this.projectFirstDate = projectFirstDate;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
}
