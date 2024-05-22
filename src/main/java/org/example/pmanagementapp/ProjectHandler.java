package org.example.pmanagementapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class ProjectHandler {
static Linkedlist toDoProjects=new Linkedlist();;
static Linkedlist onGoingProjects=new Linkedlist();;
static Linkedlist finishedProjects=new Linkedlist();;
static Linkedlist abandonedProjects=new Linkedlist();

public static void addProject(Project project)
{toDoProjects.add(project);}
    public void deleteProject(Project project, String listType) {
        switch (listType) {
            case "toDo":
                toDoProjects.remove(project);
                break;
            case "onGoing":
                onGoingProjects.remove(project);
                break;
            case "finished":
                finishedProjects.remove(project);
                break;
            case "abandoned":
                abandonedProjects.remove(project);
                break;
            default:
                System.out.println("Geçersiz liste türü: " + listType);
                break;
        }
    }
public static void editProject(Double stage)
{}
public static void setOnGoing(Project project)
{   if(toDoProjects.remove(project))
    onGoingProjects.add(project);}

public static void setFinished(Project project)
    {if(onGoingProjects.remove(project))
     finishedProjects.add(project);}
public static void setAbandoned(Project project,String listType) {
    switch (listType) {
        case "toDo":
            if(toDoProjects.remove(project))
                abandonedProjects.add(project);
            break;
        case "onGoing":
            if(onGoingProjects.remove(project))
                abandonedProjects.add(project);
            break;
        default:
            break;
    }
}

    public static void saveProjects() {
        try (FileWriter writer = new FileWriter("projects.csv")) {
            for (Project project : toDoProjects) {
                writer.write("toDo," + project.toCSV() + "\n");
            }
            for (Project project : onGoingProjects) {
                writer.write("onGoing," + project.toCSV() + "\n");
            }
            for (Project project : finishedProjects) {
                writer.write("finished," + project.toCSV() + "\n");
            }
            for (Project project : abandonedProjects) {
                writer.write("abandoned," + project.toCSV() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadProjects() {
        try (BufferedReader reader = new BufferedReader(new FileReader("projects.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                String listType = parts[0];
                Project project = Project.fromCSV(parts[1]);
                switch (listType) {
                    case "toDo":
                        toDoProjects.add(project);
                        break;
                    case "onGoing":
                        onGoingProjects.add(project);
                        break;
                    case "finished":
                        finishedProjects.add(project);
                        break;
                    case "abandoned":
                        abandonedProjects.add(project);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProjectFromCSV(Project project) {

        try (BufferedReader reader = new BufferedReader(new FileReader("projects.csv"))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            try (FileWriter writer = new FileWriter("projects.csv")) {
                for (String line : lines) {
                    if (!line.contains(project.getProjectTitle())) {
                        writer.write(line + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
