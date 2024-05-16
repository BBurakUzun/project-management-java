package org.example.pmanagementapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    private VBox toDoVbox;

    @FXML
    private ImageView deleteButton;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker firstDate;

    @FXML
    private DatePicker lastDate;

    @FXML
    private Slider projectStage;

    @FXML
    private ImageView saveButton;


    @FXML
    void onAddNew(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("projeekran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void onSave(MouseEvent event) {
        String first = firstDate.toString();
        System.out.println(first);
    }

}
