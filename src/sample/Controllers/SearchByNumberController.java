package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Backend.Auto;
import sample.Backend.Database;

public class SearchByNumberController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TextField;

    @FXML
    private Button buttonSearchStart;

    @FXML
    void initialize() {

    }

    public void buttonSearchStartOnClick() throws IOException {


        System.out.println("go search");
        String partOfregistrationNumberOfTheCar = TextField.getText();
        System.out.println(partOfregistrationNumberOfTheCar);
        ObservableList<Auto> database = FXCollections.observableArrayList();//копия базы
        ObservableList<Auto> result = FXCollections.observableArrayList();//база, в которой находятся машины с совпавшей чатью номера

        database = new Database().fromFile();
       // System.out.println(database.size());
        //нашли совпавшие и занесли их в результаты
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getRegistrationNumberOfTheCar()
                    .contains(partOfregistrationNumberOfTheCar)) {
                System.out.println("status:detected");
                result.add(database.get(i));
            }
        }
        //както закрыть старую сцену
        /*
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Scenes/ResultSearchScene.fxml"));
       // stage.initModality(Modality.APPLICATION_MODAL);//заблокировали старое окно
        stage.setTitle("Результаты поиска");
        stage.setScene(new Scene(root, 700, 400));
        stage.setResizable(false);//отключаем изменение размера сцены

        stage.show();
        */

    }
}

