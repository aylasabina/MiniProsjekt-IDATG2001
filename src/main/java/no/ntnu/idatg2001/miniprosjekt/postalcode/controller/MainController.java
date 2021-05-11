package no.ntnu.idatg2001.miniprosjekt.postalcode.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import no.ntnu.idatg2001.miniprosjekt.postalcode.model.PostalCode;
import no.ntnu.idatg2001.miniprosjekt.postalcode.model.PostalCodeRegister;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * The class for handling the main window.
 *
 * @author 10042
 * @version 11.05.2021
 */
public class MainController implements Initializable {

    private final PostalCodeRegister postalCodeRegister = PostalCodeRegister.getPostalCodeRegisterInstance();
    private ObservableList<PostalCode> postalCodeObservableList;

    @FXML
    private TableView<PostalCode> tableView;
    @FXML
    private TableColumn<PostalCode, String> zipCodeColumn;
    @FXML
    private TableColumn<PostalCode, String> cityColumn;
    @FXML
    private TableColumn<PostalCode, String> municipalityColumn;
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipality"));

        try {
            tableView.setItems(getPostalCodeListWrapper());
        } catch (IOException e) {
            System.err.println("An error occured: " + e.getMessage() +
                    "\nCause: " + e.getCause());
        }
    }

    /**
     * Get the list of postal codes wrapped in an observable list.
     *
     * @return the observable list of the postal codes.
     */
    private ObservableList<PostalCode> getPostalCodeListWrapper() throws IOException {
        postalCodeObservableList = FXCollections.observableArrayList(postalCodeRegister.getPostalCodesFromFile());
        return postalCodeObservableList;
    }

    @FXML
    public void textChanged(KeyEvent keyEvent) {
        HashSet<PostalCode> results = postalCodeRegister.searchPostalCodes(searchField.getText());
        postalCodeObservableList.setAll(results);
    }

    @FXML
    public void infoButtonClicked(ActionEvent actionEvent) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText("Information");
        info.setContentText("You can search for a postal code by:\n" +
                "\t1. The zip code (format 0000)\n" +
                "\t2. The city name\n" +
                "The table will update as you are searching.");
        info.showAndWait();
    }
}
