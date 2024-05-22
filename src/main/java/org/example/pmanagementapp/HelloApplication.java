package org.example.pmanagementapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ProjectHandler.loadProjects();

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("anaekran.fxml"));
        Parent mainRoot = mainLoader.load();
        MainController mainController = mainLoader.getController();


        FXMLLoader anotherLoader = new FXMLLoader(getClass().getResource("projeekran.fxml"));
        Parent anotherRoot = anotherLoader.load();
        ProjectController projectController = anotherLoader.getController();

        mainController.setProjectController(projectController);
        projectController.setMainController(mainController);

        stage.setTitle("Main Window");
        stage.setScene(new Scene(mainRoot,1000, 600));

        stage.show();

        stage.setOnCloseRequest(event -> ProjectHandler.saveProjects());
    }

    public static void main(String[] args) {
        launch();
    }
}