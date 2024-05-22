package org.example.pmanagementapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public  class ProjectController extends  AbstractController{
    @FXML
    public Button deleteButton;

    @FXML
    public TextArea description;

    @FXML
    public TextField projectTitle;

    @FXML
    public DatePicker firstDate;

    @FXML
    public DatePicker lastDate;

    @FXML
    public Slider projectStage;

    @FXML
    private ImageView saveButton;

    private static MainController mainController;
    private static AnchorPane currentAnchorProject;
    private static Project currentProject;
    private VBox Vbox;


    @FXML
    public void initialize() {
        if (mainController == null) {
            System.out.println("hay aq");
        }

    }

    public void setEvents() {
        System.out.println("ENTER HERE 1");
        deleteButton.setOnAction(e -> {
            System.out.println("ENTER HERE 2");
            Vbox.getChildren().remove(currentAnchorProject);
        });
    }

    public void setProject(AnchorPane anchhorProject, Project project, VBox vbox) {
        ProjectController.currentAnchorProject = anchhorProject;
        ProjectController.currentProject = project;
        this.Vbox = vbox;


    }

    public void setMainController(MainController mainController) {
        ProjectController.mainController = mainController;
    }

    @FXML
    void onSave(MouseEvent event) {
        String name = projectTitle.getText();
        String first = firstDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String last = lastDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        double stage = projectStage.getValue();
        String desc = description.getText();
        Project newProject = new Project(name, first, last, Math.round(stage), desc);

        if (currentProject != null && currentProject.getProjectTitle().equals(name)) {
            Vbox.getChildren().remove(currentAnchorProject);
            ProjectHandler.abandonedProjects.add(currentProject);
        }

        ProjectHandler.addProject(newProject);

        ProjectController.mainController.addNewAnchorPane(newProject);

        Stage currentStage = (Stage) projectTitle.getScene().getWindow();
        currentStage.close();



    }


    void editProject() {

    }
}
