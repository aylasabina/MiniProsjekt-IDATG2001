package no.ntnu.idatg2001.miniprosjekt.postalcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is an application for searching postal codes.
 * The information that is stored about a postal code is the
 * the zip code, the city name and the municipality name.
 * You can search for a zip code or a city name, with different modes.
 * The current available modes are "starts with", "ends with",
 * "contains" and "exact".
 *
 * Starts with: Gets all zip codes or cities that start with given text
 * Ends with: Gets all zip codes or cities that end with given text
 * Contains: Gets all zip codes or cities that contain given text
 * Exact: Gets zip code or cities that match the given text exactly
 *
 * @author 10042
 * @version 20.05.2021
 */
public class PostalCodeApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("controller/MainView.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Patient Register");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            System.err.println("An IO-exception occured: " + ioe.getMessage() +
                    "\nCause: " + ioe.getCause());
            throw ioe;
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
