package no.ntnu.idatg2001.miniprosjekt.postalcode.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    @FXML
    private ChoiceBox<SearchEnum> searchBox;

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

        searchBox.getItems().addAll(
                SearchEnum.START,
                SearchEnum.END,
                SearchEnum.CONTAIN,
                SearchEnum.EXACT);
        searchBox.getSelectionModel().select(0);
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

    /**
     * Updates the observable list when the text in
     * the input field is changed. If the input field
     * is blank, all postal codes will be shown.
     */
    @FXML
    public void textChanged() {
        if(searchField.getText().isBlank()) {
            postalCodeObservableList.setAll(postalCodeRegister.getPostalCodes());
        } else {
            List<PostalCode> results = postalCodeRegister
                    .search(searchBox.getSelectionModel().getSelectedItem(),
                    searchField.getText().trim());
            postalCodeObservableList.setAll(results);
        }
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

    /**
     * Changes the search mode to selected item.
     */
    public void searchBoxClicked() {
        String searchString = searchField.getText().trim();
        List<PostalCode> results;
        switch(searchBox.getSelectionModel().getSelectedItem()) {
            case END:
                results = postalCodeRegister.search(SearchEnum.END, searchString);
                break;
            case CONTAIN:
                results = postalCodeRegister.search(SearchEnum.CONTAIN, searchString);
                break;
            case EXACT:
                results = postalCodeRegister.search(SearchEnum.EXACT, searchString);
                break;
            default:
                results = postalCodeRegister.search(SearchEnum.START, searchString);
        }
        postalCodeObservableList.setAll(results);
    }
}
