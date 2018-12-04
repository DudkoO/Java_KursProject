package sample.Backend;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Auto {
    String registrationNumberOfTheCar;
    String brand;
    int yearOfRelease;
    String color;
    String nameOfTheOwner;
    String residenceAddressOfTheOwner;
    String carType;

    //region Конструкторы
    public Auto(String registrationNumberOfTheCar, String brand, int yearOfRelease,
                String color, String nameOfTheOwner, String residenceAddressOfTheOwner,
                String carType) {
        this.setRegistrationNumberOfTheCar(registrationNumberOfTheCar);
        this.setBrand(brand);
        this.setYearOfRelease(yearOfRelease);
        this.setColor(color);
        this.setNameOfTheOwner(nameOfTheOwner);
        this.setResidenceAddressOfTheOwner(residenceAddressOfTheOwner);
        this.setCarType(carType);

    }

    public Auto() {
    }

    //endregion
    //region sett-еры
    boolean setRegistrationNumberOfTheCar(String newRegistrationNumberOfTheCar) {
        Pattern registrationNumberOfTheCarFormat = Pattern.compile("\\D{2}\\d{4}\\D{2}");
        // шаблом регулярного выражения для номера(первые два символа-любые буквы, следующие четыре-любые цифры, потом опять две буквы)
        Matcher m = registrationNumberOfTheCarFormat.matcher(newRegistrationNumberOfTheCar);
        //обьект искатель.интерпретирует шаблон и выполняет операции сопоставления с входной строкой
        //мы отправили в обьект искатель то, что хотим записать в качестве нового номера
        if (m.matches())//вернёт тру, если наш новый номер соответствует регулярному выражению
        {
            this.registrationNumberOfTheCar = newRegistrationNumberOfTheCar;//если тру, тогда записываем новый на место старого
            return true;
        } else
            System.out.println("Номер введен некорректно!");
        return false;
    }

    boolean setBrand(String newBrand) {
        if (Brand.isTrueBrand(newBrand)) {
            this.brand = newBrand;
            return true;
        } else
            System.out.println("Марка введена некорректно!");
        return false;
    }

    boolean setYearOfRelease(int newYearOfRelease) {
        //System.out.println("Year: "+new Date().getYear());
        if ((newYearOfRelease > 1900) && (newYearOfRelease <= (1900 + new Date().getYear())))//getYear возвращает год от 1900(одному господу известно, почему)
        {
            this.yearOfRelease = newYearOfRelease;
            return true;
        } else
            System.out.println("Год выпуска введён некорректно!");
        return false;
    }

    boolean setColor(String newColor) {
        if (Color.isTrueColor(newColor)) {
            this.color = newColor;
            return true;
        } else
            System.out.println("Цвет введён некорректно!");
        return false;

    }

    boolean setResidenceAddressOfTheOwner(String newResidenceAddressOfTheOwner) {
        Pattern ResidenceAddressOfTheOwnerFormat = Pattern.compile("([A-Z][a-z]+),([A-Z][a-z]+),([\\w]{5}),([A-Z][a-z]+),([\\w]*),([\\w]*)");
        // Страна,Город,Почтовый индекс,Улица,Дом,Квартира
        // шаблом регулярного выражения для адреса
        Matcher m = ResidenceAddressOfTheOwnerFormat.matcher(newResidenceAddressOfTheOwner);
        //обьект искатель.интерпретирует шаблон и выполняет операции сопоставления с входной строкой
        //мы отправили в обьект искатель то, что хотим записать в качестве нового адреса
        if (m.matches())//вернёт тру, если наш новый адрес соответствует регулярному выражению
        {
            this.residenceAddressOfTheOwner = newResidenceAddressOfTheOwner;//если тру, тогда записываем новый на место старого
            return true;
        } else
            System.out.println("Адрес введен некорректно!");
        return false;
    }

    boolean setNameOfTheOwner(String newNameOfTheOwner) {
        Pattern NameOfTheOwnerFormat = Pattern.compile("^([A-Z][a-z]+) ([A-Z][a-z]+) ([A-Z][a-z]+)$");
        // шаблом регулярного выражения для ФИО
        Matcher m = NameOfTheOwnerFormat.matcher(newNameOfTheOwner);
        //обьект искатель.интерпретирует шаблон и выполняет операции сопоставления с входной строкой
        //мы отправили в обьект искатель то, что хотим записать в качестве нового ФИО
        if (m.matches() && newNameOfTheOwner.length() < 64)//вернёт тру, если наш новый ФИО соответствует регулярному выражению
        {
            this.nameOfTheOwner = newNameOfTheOwner;//если тру, тогда записываем новый на место старого
            return true;
        } else
            System.out.println("ФИО введены некорректно!");
        return false;
    }

    boolean setCarType(String newCarType) {
        if (CarType.isTrueCarType(newCarType)) {
            this.carType = newCarType;
            return true;
        } else
            System.out.println("Тип кузова введён некорректно!");
        return false;
    }

    @Override
    public String toString() {
        return this.getBrand() + "\t" + this.getYearOfRelease();
    }

    //endregion
    //region gett-еры
    String getBrand() {
        return this.brand;
    }

    String getRegistrationNumberOfTheCar() {
        return this.registrationNumberOfTheCar;
    }

    int getYearOfRelease() {
        return this.yearOfRelease;
    }

    String getColor() {
        return this.color;
    }

    String getNameOfTheOwner() {
        return this.nameOfTheOwner;
    }

    String getResidenceAddressOfTheOwner() {
        return this.residenceAddressOfTheOwner;
    }

    String getCarType() {
        return this.carType;
    }

    //endregion
    enum CarType {
        Sedan,
        Hatchback,
        Universal,
        Liftback,
        Coupe,
        Convertible,
        Roadster,
        Stretch,
        Targa,
        SUV,
        Crossover,
        Pickup,
        Truck,
        Van,
        Minivan;

        public static boolean isTrueCarType(String carType) {
            CarType[] array = CarType.values();
            for (int i = 0; i < array.length; i++) {
                // System.out.println(array[i]);
                if (array[i].toString().equals(carType)) {
                    return true;
                }
            }
            return false;
        }

    }

    enum Color {
        RED("#FF0000"), BLUE("#0000FF"), GREEN("#00FF00");
        private String code;

        Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static boolean isTrueColor(String color) {
            Color[] array = Color.values();
            for (int i = 0; i < array.length; i++) {
                // System.out.println(array[i]);
                if (array[i].toString().equals(color)) {
                    return true;
                }
            }
            return false;
        }
    }

    enum Brand {
        BMW, LADA, Lexus, Tesla, Citroen;

        public static boolean isTrueBrand(String brand) {
            Brand[] array = Brand.values();
            for (int i = 0; i < array.length; i++) {
                // System.out.println(array[i]);
                if (array[i].toString().equals(brand)) {
                    return true;
                }
            }
            return false;
        }

    }
}