package com.example.schedulingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 @author: Yavaar Nosimohomed - Student ID: #003298574

 Javadoc files location: SchedulingSystem/JavaDoc
 README.TXT file location: SchedulingSystem/README.TXT

 My chosen custom report is the User Appointments Report! This report shows the each user's appointment count by
 month! Month can be choosen with radio buttons!

 */


/***
 *
 * This is the main class that gets executed when the programs runs!
 *
 */

public class MainApplication extends Application {

    ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());

    /***
     * start
     * The start method sets the initial scene which is the Main Scene!
     * @param stage Create stage to set the scene!
     * @throws IOException IOException is needed in case of failure!
     */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(rb.getString("lb51"));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    /***
     * main
     * This is the main method that gets executed first! In the main method, the application gets launched!
     * @throws SQLException SQLException in case of failure!
     */
    public static void main(String[] args) throws SQLException {

        launch();

    }
}