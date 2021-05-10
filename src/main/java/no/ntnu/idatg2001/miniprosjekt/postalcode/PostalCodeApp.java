package no.ntnu.idatg2001.miniprosjekt.postalcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is an application for searching postal codes.
 * The information that is stored about a postal code is the
 * the zip code, the city name and the municipality name.
 *
 * @author 10042
 * @version 20.05.2021
 */
public class PostalCodeApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("controller/MainView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Patient Register");
        stage.setScene(scene);
        stage.show();
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
