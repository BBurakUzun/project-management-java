package org.example.pmanagementapp;

import java.util.LinkedList;
import java.util.Queue;

public class ProjectHandler {
static LinkedList<Project> toDoProjects=new LinkedList<>();;
static LinkedList<Project> onGoingProjects=new LinkedList<>();;
static LinkedList<Project> finishedProjects=new LinkedList<>();;
static LinkedList<Project> abandonedProjects=new LinkedList<>();

public void addProject(Project project)
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
public void editProject(Double stage)
{}
public void setOnGoing(Project project)
{   if(toDoProjects.remove(project))
    onGoingProjects.add(project);}

public void setFinished(Project project)
    {if(onGoingProjects.remove(project))
     finishedProjects.add(project);}
public void setAbandoned(Project project)
    {}

}
