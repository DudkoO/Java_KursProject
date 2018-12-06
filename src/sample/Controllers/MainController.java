package sample.Controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Backend.Auto;
import sample.Backend.Database;

public class MainController {
    Database data=new Database();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private javafx.scene.control.TableView<Auto> TableView;

    @FXML
    private TableColumn<Auto, String> columm1;

    @FXML
    private TableColumn<Auto, String> columm2;

    @FXML
    private GridPane GridPane;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonDelete;

    @FXML
    private MenuButton buttonSearch;

    @FXML
    private MenuItem buttonSearchByNumber;

    @FXML
    private MenuItem buttonSearchByColorEndBrand;

    @FXML
    void initialize()throws  Exception {
        data.testAutoСreator(10);
       // data.printDatabaseToConsole();
        columm1.setCellValueFactory(new PropertyValueFactory<>("BRAND"));
        columm2.setCellValueFactory(new PropertyValueFactory<>("registrationNumberOfTheCar"));
        TableView.setItems(data.database);
    //region Buttons
        buttonAdd.setOnAction(event -> {});
        buttonDelete.setOnAction(event -> {});


    //endregion

    }
   // void addOnClick()




    public void searchOnClick(javafx.event.ActionEvent actionEvent) throws Exception {
        String id=((MenuItem)actionEvent.getSource()).getId();
        System.out.println(id);
        String searchByNumberID="";

        if(id.equals("buttonSearchByNumber")) {
            Parent root = FXMLLoader.load(getClass().getResource("Scenes/SearchByNumberSample.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Поиск");
            primaryStage.setScene(new Scene(root, 300, 200));
            primaryStage.show();
        }
        else
        {

        }
    }
}

