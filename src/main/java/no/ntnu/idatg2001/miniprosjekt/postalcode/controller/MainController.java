package no.ntnu.idatg2001.miniprosjekt.postalcode.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import no.ntnu.idatg2001.miniprosjekt.postalcode.model.PostalCode;
import no.ntnu.idatg2001.miniprosjekt.postalcode.model.PostalCodeRegister;
import no.ntnu.idatg2001.miniprosjekt.postalcode.model.SearchEnum;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The class for handling the main window.
 *
 * @author 10042
 * @version 12.05.2021
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
    @FXML
    private ChoiceBox<SearchEnum> searchBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipality"));

        tableView.setItems(getPostalCodeListWrapper());

        // set items of search box and select the first one.
        searchBox.getItems().addAll(SearchEnum.values());
        searchBox.getSelectionModel().select(0);
    }

    /**
     * Get the list of postal codes from file
     * wrapped in an observable list.
     *
     * @return the observable list of the postal codes.
     */
    private ObservableList<PostalCode> getPostalCodeListWrapper() {
        while(postalCodeObservableList == null) {
            try {
                postalCodeObservableList =
                        FXCollections.observableArrayList(postalCodeRegister.getPostalCodesFromFile());
            } catch (IOException ioe) {
                showErrorAlert();
            }
        }
        return postalCodeObservableList;
    }

    /**
     * Updates the observable list when the text in
     * the input field is changed or another search mode is chosen.
     * If the input field is blank, all postal codes will be shown.
     * An alert will be shown if the new text is
     * invalid (contains other than numbers and unicode letters).
     */
    @FXML
    public void updateSearch() {
        if(searchField.getText().isBlank()) {
            postalCodeObservableList.setAll(postalCodeRegister.getPostalCodes());
        } else {
            // Update table with search results in chosen search mode.
            List<PostalCode> results = postalCodeRegister
                    .search(searchBox.getSelectionModel().getSelectedItem(),
                    searchField.getText().trim());
            if(results != null) {
                postalCodeObservableList.setAll(results);
            } else {
                showInvalidAlert();
            }
        }
    }

    private void showErrorAlert() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setContentText("Could not read the file.\n" +
                "Creating a new file postalcodes.txt \n" +
                "Please fill it with postal codes from section 3:\n" +
                "\nhttps://www.bring.no/tjenester/adressetjenester/postnummer/postnummertabeller-veiledning\n");
        error.showAndWait();
    }

    /**
     * Create and show an alert of type warning.
     * Will say that input is invalid.
     */
    private void showInvalidAlert() {
        Alert invalidInput = new Alert(Alert.AlertType.WARNING);
        invalidInput.setHeaderText("Invalid input!");
        invalidInput.setContentText("You can only search for numbers (0-9) or the unicode letters.");
        invalidInput.showAndWait();
    }

    /**
     * Shows an alert with information about
     * how to use the search functionality when
     * the info button is clicked.
     */
    @FXML
    public void infoButtonClicked() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setWidth(500);
        info.setHeaderText("Information");
        info.setContentText("You can search for a postal code by:\n" +
                "\t1. The zip code (format 0000)\n" +
                "\t2. The city name\n" +
                "\nThe table will update as you are searching.\n" +
                "\nThere are 4 search modes:\n" +
                "\t1. Starts with: Gets all zip codes or cities that start with given text\n" +
                "\t2. Ends with: Gets all zip codes or cities that end with given text\n" +
                "\t3. Contains: Gets all zip codes or cities that contain given text\n" +
                "\t4. Exact: Gets zip code or cities that match the given text exactly");
        info.showAndWait();
    }
}
