package com.example.project4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class which extends the javafx.application.Application class. It is essentially
 *  the main entry point of this application.
 *  @author Maryam, Nabihah
 */
public class MainApplication extends Application {
    /**
     * This method overrides the start method and launches stage(window) containing all objects
     * of the JavaFX application.
     * @param stage, stage where application will be set.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("RU Cafe!");
        stage.setScene(scene);
        stage.setOnHidden(e -> Platform.exit());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}