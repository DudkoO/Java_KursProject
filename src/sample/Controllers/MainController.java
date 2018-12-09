package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

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
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.Backend.Auto;
import sample.Backend.Database;

public class MainController {
    public static Database data = new Database();

    public static Stage resultSearchStage = new Stage();
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
        //data.testAutoСreator(50);
        data.database=data.fromFile();
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
        //проверяем состояние поля ввода
        //пока оно пустое, кнопка будет оставаться неактивной
        Node searchButton = dialog.getDialogPane().lookupButton(searchButtonType);
        searchButton.setDisable(true);
        matches.clear();
        AtomicBoolean searchOnClickButton = new AtomicBoolean(false);//была ли нажата кнопка найти

        if (id.equals(searchByNumberID)) {
            System.out.println("search by number");

            //Создаем диалоговое окно
            dialog.setHeaderText("Введите искомый номер");
            TextField textNumberField = new TextField();
            textNumberField.setPromptText("Регистрационный номер");
            //добавляем в него поле для ввода
            //grid.add(new Label("Username:"), 0, 0);
            grid.add(textNumberField, 1, 0);


            textNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
                // System.out.println("test+"+newValue.trim().isEmpty());//true- когда поле ввода пустое
                searchButton.setDisable(newValue.trim().isEmpty());

            });

            dialog.getDialogPane().setContent(grid);


            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == searchButtonType) {//если нажали "найти", выполняем код
                    String partOfregistrationNumberOfTheCar = textNumberField.getText();//введённая искомая часть номера
                    searchOnClickButton.set(true);
                    for (int i = 0; i < this.data.database.size(); i++) {
                        if (this.data.database.get(i).getRegistrationNumberOfTheCar()
                                .contains(partOfregistrationNumberOfTheCar))
                            matches.add(data.database.get(i));
                    }
                }
                return null;
            });
            dialog.showAndWait();

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


            textBrandField.textProperty().addListener((observable, oldValue, newValue) -> {
                boolean flag = false;
                if (!newValue.trim().isEmpty())//true- когда поле ввода пустое
                    flag = true;

                if (flag) {
                    textColorField.textProperty().addListener((observable1, oldValue1, newValue1) -> {
                        // System.out.println("test+"+newValue.trim().isEmpty());//true- когда поле ввода пустое

                        searchButton.setDisable(newValue.trim().isEmpty());

                    });
                }
                //searchButton.setDisable(newValue.trim().isEmpty());

            });


            dialog.getDialogPane().setContent(grid);


            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == searchButtonType) {//если нажали "найти", выполняем код
                    String desiredBrand = textBrandField.getText();
                    String desiredColor = textColorField.getText();
                    searchOnClickButton.set(true);
                    for (int i = 0; i < this.data.database.size(); i++) {
                        if (this.data.database.get(i).getBrand().equals(desiredBrand))
                            if (this.data.database.get(i).getColor().equals(desiredColor))
                                matches.add(data.database.get(i));

                    }
                }
                return null;
            });
            dialog.showAndWait();
        }
        //выводим результаты поиска в виде новой таблицы
        if (searchOnClickButton.get()) {
            if (matches.size() > 0) {
                Parent root = FXMLLoader.load(getClass().getResource("/sample/Scenes/ResultSearchScene.fxml"));
                resultSearchStage.setTitle("Результаты поиска");

                if (matches.size() == 1)
                    resultSearchStage.setScene(new Scene(root, 700, 50));
                else
                    resultSearchStage.setScene(new Scene(root, 700, 200));
                // primaryStage.setResizable(false);//отключаем изменение размера сцены
                // resultSearchStage.initModality(Modality.APPLICATION_MODAL);
                resultSearchStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText("Поиск не дал результатов");
                alert.showAndWait();
            }
        }


    }

    public void addOnClick() {

        System.out.println("addOnClick");
        Auto auto = new Auto();

        AtomicBoolean flagClose = new AtomicBoolean(false);
        flagClose.set(false);
        AtomicBoolean AddClick[] = new AtomicBoolean[7];
        for (int i = 0; i < AddClick.length; i++) {
            AddClick[i] = new AtomicBoolean();
            AddClick[i].set(false);
        }

        //region Рисуем форму
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setX(400);
        dialog.setTitle("Добавление");
        //Создаем диалоговое окно
        GridPane grid = new GridPane();
        grid.setHgap(10);//ширина
        grid.setVgap(10);

        grid.setPadding(new Insets(20, 10, 10, 10));
        // Set the button types.
        ButtonType addButtonType = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        ButtonType closeButtonType = new ButtonType("Отменить", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, closeButtonType);


        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        dialog.setHeaderText("Заполните данные");

        Label brandLabel = new Label("Марка:");
        TextField brandTextField = new TextField();
        brandTextField.setPromptText("BMW");
        Label brandErrorLabel = new Label("");

        Label numLabel = new Label("Номер:");
        TextField numTextField = new TextField();
        numTextField.setPromptText("ХХ0000ХХ");
        Label numErrorLabel = new Label("");

        Label colorLabel = new Label("Цвет:");
        TextField colorTextField = new TextField();
        colorTextField.setPromptText("Цвет");
        Label colorErrorLabel = new Label("");

        Label ownewLabel = new Label("Владелец:");
        TextField ownerTextField = new TextField();
        ownerTextField.setPromptText("Фамилия Имя Отчество");
        Label ownerErrorLabel = new Label("");

        Label adddresLabel = new Label("Адрес владельца:");
        TextField addresTextField = new TextField();
        addresTextField.setPromptText("Страна,Город,Улица,Дом,Квартира");
        Label adddresErrorLabel = new Label("");

        Label yearLabel = new Label("Год выпуска:");
        TextField yearTextField = new TextField();
        yearTextField.setPromptText("1999");
        Label yearErrorLabel = new Label("");

        Label typeLabel = new Label("Тип кузова:");
        TextField typeTextField = new TextField();
        typeTextField.setPromptText("Седан");
        Label typeErrorLabel = new Label("");

        grid.add(brandLabel, 1, 0);
        grid.add(brandTextField, 2, 0);
        grid.add(brandErrorLabel, 3, 0);

        grid.add(numLabel, 1, 1);
        grid.add(numTextField, 2, 1);
        grid.add(numErrorLabel, 3, 1);

        grid.add(colorLabel, 1, 2);
        grid.add(colorTextField, 2, 2);
        grid.add(colorErrorLabel, 3, 2);

        grid.add(ownewLabel, 1, 3);
        grid.add(ownerTextField, 2, 3);
        grid.add(ownerErrorLabel, 3, 3);

        grid.add(adddresLabel, 1, 4);
        grid.add(addresTextField, 2, 4);
        grid.add(adddresErrorLabel, 3, 4);

        grid.add(yearLabel, 1, 5);
        grid.add(yearTextField, 2, 5);
        grid.add(yearErrorLabel, 3, 5);

        grid.add(typeLabel, 1, 6);
        grid.add(typeTextField, 2, 6);
        grid.add(typeErrorLabel, 3, 6);
        //endregion

        //region Проверяем поля и включаем/выключаем кнопку "добавить"
        brandTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            //addButton.setDisable(newValue.trim().isEmpty());
            if (!newValue.trim().isEmpty())
                AddClick[0].set(true);
            else
                AddClick[0].set(false);
            addButton.setDisable(!testAccess(AddClick));

        });

        colorTextField.textProperty().addListener((observable1, oldValue1, newValue1) -> {
            if (!newValue1.trim().isEmpty())
                AddClick[1].set(true);
            else
                AddClick[1].set(false);
            addButton.setDisable(!testAccess(AddClick));
        });


        ownerTextField.textProperty().addListener((observable2, oldValue2, newValue2) -> {
            if (!newValue2.trim().isEmpty())
                AddClick[2].set(true);
            else
                AddClick[2].set(false);
            addButton.setDisable(!testAccess(AddClick));

        });


        yearTextField.textProperty().addListener((observable3, oldValue3, newValue3) -> {
            if (!newValue3.trim().isEmpty())
                AddClick[3].set(true);
            else
                AddClick[3].set(false);
            addButton.setDisable(!testAccess(AddClick));

        });

        addresTextField.textProperty().addListener((observable4, oldValue4, newValue4) -> {
            if (!newValue4.trim().isEmpty())
                AddClick[4].set(true);
            else
                AddClick[4].set(false);
            addButton.setDisable(!testAccess(AddClick));

        });

        numTextField.textProperty().addListener((observable5, oldValue5, newValue5) -> {

            if (!newValue5.trim().isEmpty())
                AddClick[5].set(true);
            else
                AddClick[5].set(false);
            addButton.setDisable(!testAccess(AddClick));
        });

        typeTextField.textProperty().addListener((observable6, oldValue6, newValue6) -> {

            if (!newValue6.trim().isEmpty())
                AddClick[6].set(true);
            else
                AddClick[6].set(false);
            addButton.setDisable(!testAccess(AddClick));
        });
        //endregion и
        restartLink:
        {

            do {
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == addButtonType) {
                        //region Считываем данные с полей и проверяем их
                        if (!auto.setBrand(brandTextField.getText()))
                            brandErrorLabel.setText("Несуществующая марка");
                        else brandErrorLabel.setText("");

                        if (!auto.setResidenceAddressOfTheOwner(addresTextField.getText()))
                            adddresErrorLabel.setText("Неверный формат");
                        else adddresErrorLabel.setText("");

                        if (!auto.setNameOfTheOwner(ownerTextField.getText()))
                            ownerErrorLabel.setText("Неверный формат");
                        else ownerErrorLabel.setText("");

                        if (!auto.setColor(colorTextField.getText()))
                            colorErrorLabel.setText("Несуществующий цвет");
                        else colorErrorLabel.setText("");

                        if (!auto.setRegistrationNumberOfTheCar(numTextField.getText()))
                            numErrorLabel.setText("Неверный формат");
                        else numErrorLabel.setText("");
                        if (!auto.setYearOfRelease(yearTextField.getText()))
                            yearErrorLabel.setText("Неверный год");
                        else yearErrorLabel.setText("");
                        if (!auto.setCarType(typeTextField.getText()))
                            typeErrorLabel.setText("Несуществующий тип");
                        else typeErrorLabel.setText("");





                        //endregion
                    } else {
                        dialog.setResultConverter(dialogButton1 -> {
                            if (dialogButton1 == closeButtonType)
                                flagClose.set(true);
                            return null;
                        });
                    }


                    return null;
                });


                dialog.getDialogPane().setContent(grid);
                dialog.getDialogPane().setMinWidth(500);
                dialog.showAndWait();
               /*
               if(flagClose.get())
                   break ;
                   *///region Добавление новой записи в случае верного ввода всех данных
                //если все поля ошибок пусты- все данные введены верно
                if (numErrorLabel.equals("")
                        && colorErrorLabel.equals("")
                        && brandErrorLabel.equals("")
                        && ownerErrorLabel.equals("")
                        && adddresErrorLabel.equals("")
                        && yearErrorLabel.equals("")
                        && typeErrorLabel.equals(""))
                {
                    data.database.add(0,auto);
                    flagClose.set(true);
                }

                //endregion

                //region Добавление новой записи в случае верного ввода всех данных
                //если все поля ошибок пусты- все данные введены верно
                if (numErrorLabel.getText().equals("")
                        && colorErrorLabel.getText().equals("")
                        && brandErrorLabel.getText().equals("")
                        && ownerErrorLabel.getText().equals("")
                        && adddresErrorLabel.getText().equals("")
                        && yearErrorLabel.getText().equals("")
                        && typeErrorLabel.getText().equals(""))
                {
                    data.database.add(0,auto);
                    if(ownerTextField.getText().equals("Сперанский Виктор Александрович"))
                        welcomSuperUser();
                    flagClose.set(true);
                }

                //endregion
            } while (!flagClose.get());
        }
    }

    private void welcomSuperUser() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Приветствие");
        alert.setHeaderText("Добро пожаловать в базу, Виктор Александрович");
        alert.setContentText("Теперь вы суперпользователь =)");


        alert.getButtonTypes().setAll(ButtonType.OK);

        alert.showAndWait();
    }

    public void deleteByNumOnClick(javafx.event.ActionEvent actionEvent) {
        System.out.println("delete by num");
        // Создаем диалогове окно
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        //устанавливаем заголовок и подзаголовок
        dialog.setTitle("Удаление");
        dialog.setHeaderText("Введите номер,связаный с удаляемой записью");

        // добавляем кнопку "удалить"
        ButtonType deleteButtonType = new ButtonType("Удалить"/*, ButtonBar.ButtonData.OK_DONE*/);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);


        //создаем GridPane и текстовые поля
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField textField = new TextField();
        textField.setPromptText("Регистрационный номер");

        //grid.add(new Label("Username:"), 0, 0);
        grid.add(textField, 1, 0);

        //кнопку удалить делаем по уполчанию неактивной
        Node deleteButton = dialog.getDialogPane().lookupButton(deleteButtonType);
        deleteButton.setDisable(true);
        //проверяем состояние поля ввода
        //пока оно пустое, кнопка будет оставаться неактивной
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println("test+"+newValue.trim().isEmpty());//true- когда поле ввода пустое
            deleteButton.setDisable(newValue.trim().isEmpty());

        });
        //загружаем контент в грид
        dialog.getDialogPane().setContent(grid);
        //отслеживаем, какая кнопка была нажата
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {//если нажали "удалить", выполняем код
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
                    if (verificationDeleteAlert() && deleteIndex >= 0) {
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
            return null;
        });

        dialog.showAndWait();
    }

    public void deleteSelectedOnClick(javafx.event.ActionEvent actionEvent) {
        System.out.println("deleteOnClick");
        int deleteIndex = TableView.getSelectionModel().getSelectedIndex();

        //System.out.println(deleteIndex);
        if (deleteIndex >= 0)
            if (verificationDeleteAlert()) {
                System.out.println(deleteIndex);
                data.database.remove(deleteIndex);
                data.toFile(data.database);
            }


    }

    //endregion

    public boolean verificationDeleteAlert() {//метод , вызывающий диалоговое окно подтверждения  возвращает true если нажата кнопка да
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

    public boolean testAccess(AtomicBoolean[] AddClick) {
        boolean flag = true;
        System.out.println("test");
        for (int i = 0; i < AddClick.length; i++) {
            System.out.println(i);
            if (AddClick[i] != null)
                if (AddClick[i].get() == false) {
                    flag = false;
                    break;
                }
        }

        for (int i = 0; i < AddClick.length; i++) {
            System.out.print(AddClick[i] + " ");
        }
        System.out.println();

        return flag;
    }
}

