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

public class MainController extends AbstractController{


    @FXML
    private VBox abandonedVBox;

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
        if (toDoVbox == null) {
            System.out.println("toDoVbox is not initialized");
        } else {
            System.out.println("toDoVbox is initialized");
        }

    }

    public void setProjectController(ProjectController projectController) {
        MainController.projectController = projectController;
    }

    @FXML
    void onAddNew(MouseEvent event) throws IOException {
        changeToProjectScreen(null);
    }



    void addNewAnchorPane(Project project) {

        if (toDoVbox == null) {
            System.out.println("toDoVbox is null");
            return;
        }

            // Create a new AnchorPane
            AnchorPane newAnchorPane = new AnchorPane();
            newAnchorPane.setPrefSize(206.0, 89.0);
            newAnchorPane.setStyle("-fx-background-color: #ffffee; -fx-background-radius: 20px;");

            // Create and configure child elements
            Rectangle rectangle = new Rectangle(13.0, 94.0);
            rectangle.setArcHeight(5.0);
            rectangle.setArcWidth(5.0);
            rectangle.setFill(javafx.scene.paint.Color.web("#6ca4c0"));
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

            Label progressLabel = new Label(String.valueOf(project.getProjectStage()));
            progressLabel.setLayoutX(25.0);
            progressLabel.setLayoutY(29.0);
            progressLabel.setStyle("-fx-font-family: 'Berlin Sans FB'; -fx-font-size: 15.0;");

            Label dateLabel2 = new Label(project.getProjectFirstDate());
            dateLabel2.setLayoutX(25.0);
            dateLabel2.setLayoutY(69.0);

            // Add child elements to the AnchorPane
            newAnchorPane.getChildren().addAll(rectangle, projectLabel, dateLabel1, editButton, progressLabel, dateLabel2);

            editButton.setOnMouseClicked(event -> {
                changeToProjectScreen(newAnchorPane);



            });

            // Add the new AnchorPane to the VBox
            toDoVbox.getChildren().add(newAnchorPane);

    }

    private void changeToProjectScreen(AnchorPane currentAnchor) {
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

                if (currentAnchor != null){
                    projectController.setProject(currentAnchor, toDoVbox);
                    projectController.setEvents();
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            newWindowStage.toFront();
        }
    }

    void delete() {

    }

}
