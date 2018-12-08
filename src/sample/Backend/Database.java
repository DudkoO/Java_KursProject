package sample.Backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Database implements Serializable {
    public  ObservableList<Auto> database = FXCollections.observableArrayList();
    public void testAutoСreator(int quantity) {
        for (int i = 0; i < quantity; i++) {
            Auto auto = new Auto();
            int roulette = 0;
            Random random = new Random();

            //выбираем случайную марку из перечесления
            Brand brands[] = Brand.values();
            roulette = random.nextInt(brands.length);
            auto.setBrand(brands[roulette].toString());

            //генерируем случайный номер
            char number[] = new char[8];
            roulette = random.nextInt(90 - 65) + 65;
            number[0] = (char) roulette;
            roulette = random.nextInt(90 - 65) + 65;
            number[1] = (char) roulette;
            roulette = random.nextInt(90 - 65) + 65;
            number[6] = (char) roulette;
            roulette = random.nextInt(90 - 65) + 65;
            number[7] = (char) roulette;


            roulette = random.nextInt(57 - 48) + 48;
            number[2] = (char) roulette;
            roulette = random.nextInt(57 - 48) + 48;
            number[3] = (char) roulette;
            roulette = random.nextInt(57 - 48) + 48;
            number[4] = (char) roulette;
            roulette = random.nextInt(57 - 48) + 48;
            number[5] = (char) roulette;
            auto.setRegistrationNumberOfTheCar(new String(number));
            //пишем имя

            String names[]={"Александр","Дмитрий","Сергей","Вадим","Владимир","Павел","Никитос","Евгений"};
            String lastNames[]={"Иванов","Загороднюк","Горбатюк","Симонов","Кацюк","Черный","Броска","Бартальов","Повх","Набока","Рыбко"};
            String middleТame[]={"Сергеевич","Анатолиевич","Олегович","Дмитриевич","Вадимович","Кацюкович","Павлович"};
            String name="";
            roulette=random.nextInt(lastNames.length);
            name+=lastNames[roulette];
            name+=" ";
            roulette=random.nextInt(names.length);
            name+=names[roulette];
            name+=" ";
            roulette=random.nextInt(middleТame.length);
            name+=middleТame[roulette];
            auto.setNameOfTheOwner(name);


            //пишем адрес
            String addres="Украина,Одесса,65000,Шевченко,Говорова,11";
            auto.residenceAddressOfTheOwner=addres;

            // выбираем случайный цвет из перечисления
            Color colors[] = Color.values();
            roulette = random.nextInt(colors.length);
            auto.setColor(colors[roulette].toString());
            //выбираем случайный тип кузова
            CarType carTypes[] = CarType.values();
            roulette = random.nextInt(carTypes.length);
            auto.setCarType(carTypes[roulette].toString());
            //генерируем год выпуска
            roulette = random.nextInt(2018 - 1970) + 1970;
            auto.setYearOfRelease(roulette);

            database.add(auto);
        }
    }

    private void printAutoDataToConsole(Auto auto) {
        System.out.println("------------------------------------------");
        System.out.println("Registration Number Of The Car:" + auto.getRegistrationNumberOfTheCar());
        System.out.println("Brand:" + auto.getBrand());
        System.out.println("Year Of Relese:" + auto.getYearOfRelease());
        System.out.println("Color:" + auto.getColor());
        System.out.println("Name Of The Owner:" + auto.getNameOfTheOwner());
        System.out.println("Residence Address Of The Owner:" + auto.getResidenceAddressOfTheOwner());
        System.out.println("Car Type:" + auto.getCarType());
        System.out.println("------------------------------------------");


    }
    public void printDatabaseToConsole() {
        for (int i = 0; i < this.database.size(); i++) {
            printAutoDataToConsole(this.database.get(i));
        }
    }

    public boolean toFile(ObservableList<Auto> database){
        try {
            // write object to file
            FileOutputStream fos = new FileOutputStream("Database.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Auto>(database));
            oos.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }
    public ObservableList<Auto> fromFile(){
        ArrayList<Auto> database=null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Database.dat")))
        {
            database=(ArrayList) ois.readObject();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        ObservableList<Auto > databaseObservable =FXCollections.observableList(database);

        return databaseObservable;
    }


}
