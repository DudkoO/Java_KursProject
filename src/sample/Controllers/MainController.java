package sample.Controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Backend.Auto;
import sample.Backend.Database;

public class MainController {
    public Database data=new Database();
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
        data.toFile(data.database);
       // data.printDatabaseToConsole();
        columm1.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columm2.setCellValueFactory(new PropertyValueFactory<>("registrationNumberOfTheCar"));
        TableView.setItems(data.database);


    }




    //region Обработчики нажатий
    @FXML
    public void searchOnClick(javafx.event.ActionEvent actionEvent) throws IOException {
        String id=((MenuItem)actionEvent.getSource()).getId();
        String searchByNumberID="buttonSearchByNumber";
        String searchByColorEndBrandId="buttonSearchByColorEndBrand";

        if(id.equals(searchByNumberID)) {
            /*
            FXMLLoader loader=new FXMLLoader();
            System.out.println("go");
            loader.setLocation(getClass().getResource("/sample/Scenes/SearchScene.fxml"));
            loader.load();

            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            */

            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Scenes/SearchScene.fxml"));
            stage.initModality(Modality.APPLICATION_MODAL);//заблокировали старое окно
            stage.setTitle("Поиск");
            stage.setScene(new Scene(root, 600, 100));
            stage.setResizable(false);//отключаем изменение размера сцены
            stage.show();

        }
        else if(id.equals(searchByColorEndBrandId))
        {

        }


    }
    public void addOnClick(){
        System.out.println("addOnClick");

    }
    @FXML
    public void deleteOnClick(){
        System.out.println("deleteOnClick");
        int deleteIndex=TableView.getSelectionModel().getSelectedIndex();
      // сделать диалоговое окно(но лень)


        System.out.println(deleteIndex);
        data.database.remove(deleteIndex);

    }

    //endregion
}

