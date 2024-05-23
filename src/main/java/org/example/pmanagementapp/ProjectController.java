package org.example.pmanagementapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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
            System.out.println("maincontroller null başladı");
        }

    }

    public void setEvents() {
        deleteButton.setOnAction(e -> {
            Vbox.getChildren().remove(currentAnchorProject);
            addNewAnchorPane(currentProject);
            ProjectHandler.deleteProjectFromCSV(currentProject);
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
            mainController.abandonedVBox.getChildren().add(currentAnchorProject);
        }

        ProjectHandler.addProject(newProject);

        ProjectController.mainController.addNewAnchorPane(newProject);

        Stage currentStage = (Stage) projectTitle.getScene().getWindow();
        currentStage.close();



    }


    void addNewAnchorPane(Project project) {

        VBox projectVBox = null;
        AnchorPane newAnchorPane = new AnchorPane();
        newAnchorPane.setPrefSize(206.0, 89.0);
        newAnchorPane.setStyle("-fx-background-color: #ffffee; -fx-background-radius: 20px;");

        Rectangle rectangle = new Rectangle(13.0, 94.0);
        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);

        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeWidth(0.0);

        Label projectLabel = new Label(project.getProjectTitle());
        projectLabel.setLayoutX(25.0);
        projectLabel.setLayoutY(5.0);
        projectLabel.setStyle("-fx-font-family: 'Berlin Sans FB'; -fx-font-size: 15.0;");

        Label dateLabel1 = new Label(project.getProjectLastDate());
        dateLabel1.setLayoutX(130.0);
        dateLabel1.setLayoutY(69.0);

        ImageView editButton = new ImageView(new Image(MainController.class.getResourceAsStream("free-edit-2653317-2202989.png")));
        editButton.setFitHeight(32.0);
        editButton.setFitWidth(32.0);
        editButton.setLayoutX(174.0);
        editButton.setLayoutY(6.0);


        Label dateLabel2 = new Label(project.getProjectFirstDate());
        dateLabel2.setLayoutX(25.0);
        dateLabel2.setLayoutY(69.0);

        double stage = project.getProjectStage();
        Label progressLabel = new Label("%" + String.valueOf(Math.round(stage)));

            rectangle.setFill(javafx.scene.paint.Color.web("red"));
            projectVBox = mainController.abandonedVBox;


        progressLabel.setLayoutX(25.0);
        progressLabel.setLayoutY(29.0);
        progressLabel.setStyle("-fx-font-family: 'Berlin Sans FB'; -fx-font-size: 15.0;");


        newAnchorPane.getChildren().addAll(rectangle, projectLabel, dateLabel1, editButton, progressLabel, dateLabel2);

        VBox finalProjectVBox = projectVBox;


        projectVBox.getChildren().add(newAnchorPane);

    }

}
