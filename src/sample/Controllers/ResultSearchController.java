package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Backend.Auto;


public class ResultSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private javafx.scene.control.TableView<Auto> TableView;
    @FXML
    private TableColumn<Auto, String> columnBrand;

    @FXML
    private TableColumn<Auto, String> columnNumber;

    @FXML
    private TableColumn<Auto, String> columnColor;

    @FXML
    private TableColumn<Auto, Integer>columnYear;

    @FXML
    private TableColumn<Auto, String> columnOwner;

    @FXML
    private TableColumn<Auto, String> columnAddres;
    @FXML
    private TableColumn<Auto, String> columnType;

    @FXML
    void initialize() {
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnAddres.setCellValueFactory(new PropertyValueFactory<>("residenceAddressOfTheOwner"));
        columnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<>("registrationNumberOfTheCar"));
        columnOwner.setCellValueFactory(new PropertyValueFactory<>("nameOfTheOwner"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("yearOfRelease"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("carType"));
        TableView.setItems(MainController.matches);

    }
}

