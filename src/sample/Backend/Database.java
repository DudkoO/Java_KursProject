package sample.Backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class Database {
    public  ObservableList<Auto> database = FXCollections.observableArrayList();
    public void testAutoСreator(int quantity) {
        for (int i = 0; i < quantity; i++) {
            Auto auto = new Auto();
            int roulette = 0;
            Random random = new Random();

            //выбираем случайную марку из перечесления
            Auto.Brand brands[] = Auto.Brand.values();
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
            auto.nameOfTheOwner = "Name #" + (i + 1);
            //пишем адрес
            auto.residenceAddressOfTheOwner = "Addres #" + (i + 1);
            // выбираем случайный цвет из перечисления
            Auto.Color colors[] = Auto.Color.values();
            roulette = random.nextInt(colors.length);
            auto.setColor(colors[roulette].toString());
            //выбираем случайный тип кузова
            Auto.CarType carTypes[] = Auto.CarType.values();
            roulette = random.nextInt(carTypes.length);
            auto.setCarType(carTypes[roulette].toString());
            //генерируем год выпуска
            roulette = random.nextInt(2018 - 1930) + 1930;
            auto.setYearOfRelease(roulette);

            database.add(auto);
        }
    }

    private void printAutoData(Auto auto) {
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
            printAutoData(this.database.get(i));
        }
    }
}
