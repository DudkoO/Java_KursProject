package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/MainScene.fxml"));
        primaryStage.setTitle("База данных");
        primaryStage.setScene(new Scene(root, 1000, 500));
       // primaryStage.setResizable(false);//отключаем изменение размера сцены
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
