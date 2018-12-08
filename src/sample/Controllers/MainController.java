package sample.Controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.Backend.Auto;
import sample.Backend.Database;

public class MainController {
    public static Database data = new Database();

    public static Stage resultSearchStage=new Stage();
    public static ObservableList<Auto> matches = FXCollections.observableArrayList();//лист, хранящий совпавшие с частью номера записи(для поиска)

    //region FXML elements
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
    private Label owner;

    @FXML
    private Label addres;

    @FXML
    private Label year;

    @FXML
    private Label color;

    @FXML
    private Label type;

    @FXML
    private Button buttonAdd;

    @FXML
    private MenuButton buttonDelete;

    @FXML
    private MenuItem buttonDeleteByNumber;

    @FXML
    private MenuItem buttonDeleteSelected;

    @FXML
    private MenuButton buttonSearch;

    @FXML
    private MenuItem buttonSearchByNumber;

    @FXML
    private MenuItem buttonSearchByColorEndBrand;

    //endregion
    @FXML
    void initialize() throws Exception {
        data.testAutoСreator(100);
        //data.database=data.fromFile();
        // data.printDatabaseToConsole();
        columm1.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columm2.setCellValueFactory(new PropertyValueFactory<>("registrationNumberOfTheCar"));
        TableView.setItems(data.database);
        showDetails(null);
        TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
        data.toFile(data.database);

    }

    public void showDetails(Auto auto) {

        if (auto != null) {
            owner.setText(auto.getNameOfTheOwner());
            addres.setText(auto.getResidenceAddressOfTheOwner());
            color.setText(auto.getColor());
            type.setText(auto.getCarType());
            year.setText(auto.getYearOfReleaseString());
        } else {
            owner.setText("");
            addres.setText("");
            color.setText("");
            type.setText("");
            year.setText("");
        }
    }


    //region Обработчики нажатий
    @FXML
    public void searchOnClick(javafx.event.ActionEvent actionEvent) throws IOException {
        String id = ((MenuItem) actionEvent.getSource()).getId();
        String searchByNumberID = "buttonSearchByNumber";
        String searchByColorEndBrandId = "buttonSearchByColorEndBrand";
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Поиск");
        //Создаем диалоговое окно
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));
        // Set the button types.
        ButtonType searchButtonType = new ButtonType("Найти", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(searchButtonType, ButtonType.CANCEL);
        //активирование кнопки найти
        Node searchButton = dialog.getDialogPane().lookupButton(searchButtonType);
        searchButton.setDisable(true);

        matches.clear();

        if (id.equals(searchByNumberID)) {
            System.out.println("search by number");

            dialog.setHeaderText("Введите искомый номер");
            TextField textNumberField = new TextField();
            textNumberField.setPromptText("Регистрационный номер");

            //grid.add(new Label("Username:"), 0, 0);
            grid.add(textNumberField, 1, 0);

            // Do some validation (using the Java 8 lambda syntax).

            textNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
                // System.out.println("test+"+newValue.trim().isEmpty());//true- когда поле ввода пустое
                searchButton.setDisable(newValue.trim().isEmpty());

            });

            dialog.getDialogPane().setContent(grid);

            dialog.showAndWait();
            String partOfregistrationNumberOfTheCar = textNumberField.getText();//введённая искомая часть номера

            for (int i = 0; i < this.data.database.size(); i++) {
                if (this.data.database.get(i).getRegistrationNumberOfTheCar()
                        .contains(partOfregistrationNumberOfTheCar)) {
                    matches.add(data.database.get(i));
                }
            }

        } else if (id.equals(searchByColorEndBrandId)) {
            System.out.println("search by brand end color");

            dialog.setHeaderText("Введите цвет и марку искомого автомобиля");
            TextField textBrandField = new TextField();
            textBrandField.setPromptText("Марка");

            TextField textColorField = new TextField();
            textColorField.setPromptText("Цвет");

            //grid.add(new Label("Username:"), 0, 0);
            grid.add(textBrandField, 1, 0);
            grid.add(textColorField, 2, 0);


// Do some validation (using the Java 8 lambda syntax).
            /*
            ???
            Виктор Александрович, в коде ниже пытаюсь оставлять кнопку поика
            неактивной, пока не заполнены оба поля
            Но делаю неправильно что-то
             */
            textBrandField.textProperty().addListener((observable, oldValue, newValue) -> {
                // System.out.println("test+"+newValue.trim().isEmpty());//true- когда поле ввода пустое
                searchButton.setDisable(newValue.trim().isEmpty());

            });
            textColorField.textProperty().addListener((observable, oldValue, newValue) -> {
                // System.out.println("test+"+newValue.trim().isEmpty());//true- когда поле ввода пустое

                searchButton.setDisable(newValue.trim().isEmpty());

            });

            dialog.getDialogPane().setContent(grid);

            dialog.showAndWait();

            String desiredBrand=textBrandField.getText();
            String desiredColor=textColorField.getText();
            for (int i = 0; i <this.data.database.size() ; i++) {
                if(this.data.database.get(i).getBrand().equals(desiredBrand)){
                    if(this.data.database.get(i).getColor().equals(desiredColor))
                        matches.add(data.database.get(i));

                }
            }

        }
        //выводим результаты поиска в виде новой таблицы
        if(matches.size()>0) {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Scenes/ResultSearchScene.fxml"));
            resultSearchStage.setTitle("Результаты поиска");
            resultSearchStage.setScene(new Scene(root, 700, 300));
            // primaryStage.setResizable(false);//отключаем изменение размера сцены
            resultSearchStage.initModality(Modality.APPLICATION_MODAL);
            resultSearchStage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Поиск не дал результатов");
            alert.showAndWait();
        }

    }

    public void addOnClick() {
        System.out.println("addOnClick");

    }


    public void deleteByNumOnClick(javafx.event.ActionEvent actionEvent) {
        System.out.println("delete by num");
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Удаление");
        dialog.setHeaderText("Введите номер,связаный с удаляемой записью");

// Set the button types.
        ButtonType deleteButtonType = new ButtonType("Удалить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        //Создаем диалоговое окно
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField textField = new TextField();
        textField.setPromptText("Регистрационный номер");

        //grid.add(new Label("Username:"), 0, 0);
        grid.add(textField, 1, 0);


// Enable/Disable login button depending on whether a username was entered.
        //активирование кнопки удалить
        Node deleteButton = dialog.getDialogPane().lookupButton(deleteButtonType);
        deleteButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println("test+"+newValue.trim().isEmpty());//true- когда поле ввода пустое
            deleteButton.setDisable(newValue.trim().isEmpty());

        });

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        /*
        отображать сообщение о необнаруженном номере только если нажали УДАЛИТЬ, а не закрыли окно
        ???
        Виктор Александович, мне нужно, чтобы участок кода ниже
        этого комментария выполнался только
        если юзер нажал кнопку "удалить", а не закрыл окно другим способом.
        Покажите, пожалуйста, как это отследить

         */


        //ищем, есть ли запись с таким номером
        String temp = textField.getText().toString();
        boolean flag = false;
        int deleteIndex = 0;
        for (int i = 0; i < data.database.size(); i++) {
            if (data.database.get(i).getRegistrationNumberOfTheCar().equals(temp)) {
                deleteIndex = i;
                flag = true;
                break;
            }
        }

        if (flag) {
            if (verification() && deleteIndex >= 0) {
                System.out.println(deleteIndex);
                data.database.remove(deleteIndex);
                data.toFile(data.database);
            } else ;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Запись с указанным номером не найдена!");
            alert.showAndWait();
        }

    }

    public void deleteSelectedOnClick(javafx.event.ActionEvent actionEvent) {
        System.out.println("deleteOnClick");
        int deleteIndex = TableView.getSelectionModel().getSelectedIndex();
        //  диалоговое окно


        if (verification() && deleteIndex >= 0) {
            System.out.println(deleteIndex);
            data.database.remove(deleteIndex);
            data.toFile(data.database);
        } else ;


    }

    //endregion

    public boolean verification() {//метод , вызывающий диалоговое окно подтверждения  возвращает true если нажата кнопка да
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Вы действительно хотите удалить этот элемент?");
        alert.setContentText("Восстановить его будет невозможно");
        ButtonType buttonTypeYes = new ButtonType("Да");
        ButtonType buttonTypeNo = new ButtonType("Нет");
        // ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            // System.out.println("yes");
            return true;

        } else {
            // System.out.println("No");
            return false;
        }
    }


}

