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
    private TextArea description;

    @FXML
    private TextField projectTitle;

    @FXML
    private DatePicker firstDate;

    @FXML
    private DatePicker lastDate;

    @FXML
    private Slider projectStage;

    @FXML
    private ImageView saveButton;

    private static MainController mainController;
    private static AnchorPane currentAnchorProject;
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
            Vbox.getChildren().remove(ProjectController.currentAnchorProject);
        });
    }

    public void setProject(AnchorPane project, VBox vbox) {
        ProjectController.currentAnchorProject = project;
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

        ProjectHandler.addProject(newProject);


        ProjectController.mainController.addNewAnchorPane(newProject);

        Stage currentStage = (Stage) projectTitle.getScene().getWindow();
        currentStage.close();



    }


    void editProject() {

    }
}
