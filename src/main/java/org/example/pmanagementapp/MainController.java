package org.example.pmanagementapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainController extends AbstractController {


    @FXML
    public VBox abandonedVBox;

    @FXML
    private ImageView addButton;

    @FXML
    private VBox finishedVBox;

    @FXML
    private VBox onGoingVBox;

    @FXML
    public VBox toDoVbox;

    private Stage newWindowStage;

    private static ProjectController projectController;

    @FXML
    public void initialize() {

        for (Project project : ProjectHandler.toDoProjects) {
            addNewAnchorPane(project);
        }
        for (Project project : ProjectHandler.onGoingProjects) {
            addNewAnchorPane(project);
        }
        for (Project project : ProjectHandler.finishedProjects) {
            addNewAnchorPane(project);
        }
        for (Project project : ProjectHandler.abandonedProjects) {
            addNewAnchorPane(project);
        }

    }

    public void setProjectController(ProjectController projectController) {
        MainController.projectController = projectController;
    }

    @FXML
    void onAddNew(MouseEvent event) throws IOException {
        changeToProjectScreen(null, null, toDoVbox);
    }


    void addNewAnchorPane(Project project) {

        if (toDoVbox == null) {
            System.out.println("toDoVbox is null");
            return;
        }
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
        if (stage == 0) {
            rectangle.setFill(javafx.scene.paint.Color.web("#6ca4c0"));
            projectVBox = toDoVbox;
        } else if (stage > 0 && stage < 100) {
            rectangle.setFill(javafx.scene.paint.Color.web("#ffd735"));
            projectVBox = onGoingVBox;
            ProjectHandler.setOnGoing(project);
        } else {
            rectangle.setFill(javafx.scene.paint.Color.web("#93ef9c"));
            projectVBox = finishedVBox;
            ProjectHandler.setOnGoing(project);
        }

        progressLabel.setLayoutX(25.0);
        progressLabel.setLayoutY(29.0);
        progressLabel.setStyle("-fx-font-family: 'Berlin Sans FB'; -fx-font-size: 15.0;");


        newAnchorPane.getChildren().addAll(rectangle, projectLabel, dateLabel1, editButton, progressLabel, dateLabel2);

        VBox finalProjectVBox = projectVBox;
        editButton.setOnMouseClicked(event -> {
            changeToProjectScreen(newAnchorPane, project, finalProjectVBox);


        });

        projectVBox.getChildren().add(newAnchorPane);

    }

    private void changeToProjectScreen(AnchorPane currentAnchor, Project project, VBox projectVBox) {
        if (newWindowStage == null || !newWindowStage.isShowing()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("projeekran.fxml"));
                Parent root = fxmlLoader.load();
                ProjectController projectController = fxmlLoader.getController();
                projectController.setMainController(this);

                newWindowStage = new Stage();
                newWindowStage.setTitle("New Window");
                newWindowStage.setScene(new Scene(root));
                newWindowStage.show();

                if (currentAnchor != null) {
                    projectController.setProject(currentAnchor, project, projectVBox);
                    projectController.setEvents();
                    projectController.projectTitle.setText(project.getProjectTitle());
                    projectController.projectStage.setValue(project.getProjectStage());

                    int day = Integer.parseInt(project.getProjectFirstDate().substring(0, 2));
                    int month = Integer.parseInt(project.getProjectFirstDate().substring(3, 5));
                    int year = Integer.parseInt(project.getProjectFirstDate().substring(6, 10));
                    projectController.firstDate.setValue(LocalDate.of(year, month, day));


                    int day2 = Integer.parseInt(project.getProjectLastDate().substring(0, 2));
                    int month2 = Integer.parseInt(project.getProjectLastDate().substring(3, 5));
                    int year2 = Integer.parseInt(project.getProjectLastDate().substring(6, 10));
                    projectController.lastDate.setValue(LocalDate.of(year2, month2, day2));
                    projectController.description.setText(project.getDescription());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            newWindowStage.toFront();
        }
    }

}
