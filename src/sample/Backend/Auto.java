package sample.Backend;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Auto implements Serializable {
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
   public boolean setRegistrationNumberOfTheCar(String newRegistrationNumberOfTheCar) {
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

   public boolean setBrand(String newBrand) {
        if (Brand.isTrueBrand(newBrand)) {
            this.brand = newBrand;
            return true;
        } else
            System.out.println("Марка введена некорректно!");
        return false;
    }

   public boolean setYearOfRelease(int newYearOfRelease) {
        //System.out.println("Year: "+new Date().getYear());
        if ((newYearOfRelease > 1900) && (newYearOfRelease <= (1900 + new Date().getYear())))//getYear возвращает год от 1900(одному господу известно, почему)
        {
            this.yearOfRelease = newYearOfRelease;
            return true;
        } else
            System.out.println("Год выпуска введён некорректно!");
        return false;
    }

    public boolean setYearOfRelease(String YearOfRelease) {
        //System.out.println("Year: "+new Date().getYear());
        int newYearOfRelease=Integer.parseInt(YearOfRelease);
        if ((newYearOfRelease > 1900) && (newYearOfRelease <= (1900 + new Date().getYear())))//getYear возвращает год от 1900(одному господу известно, почему)
        {
            this.yearOfRelease = newYearOfRelease;
            return true;
        } else
            System.out.println("Год выпуска введён некорректно!");
        return false;
    }

   public boolean setColor(String newColor) {
        if (Color.isTrueColor(newColor)) {
            this.color = newColor;
            return true;
        } else
            System.out.println("Цвет введён некорректно!");
        return false;

    }

   public boolean setResidenceAddressOfTheOwner(String newResidenceAddressOfTheOwner) {
        Pattern ResidenceAddressOfTheOwnerFormat = Pattern.compile("([A-Я][a-я]+),([A-Я][a-я]+),([\\w]{5}),([A-Я][a-я]+),([\\w]*),([\\w]*)");
        // Страна,Город,Улица,Дом,Квартира
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

   public boolean setNameOfTheOwner(String newNameOfTheOwner) {
        Pattern NameOfTheOwnerFormat = Pattern.compile("^([А-Я][а-я]+) ([А-Я][а-я]+) ([А-Я][а-я]+)$");
        // шаблом регулярного выражения для ФИО
        Matcher m = NameOfTheOwnerFormat.matcher(newNameOfTheOwner);
        //обьект искатель.интерпретирует шаблон и выполняет операции сопоставления с входной строкой
        //мы отправили в обьект искатель то, что хотим записать в качестве нового ФИО
        if (m.matches() && newNameOfTheOwner.length() < 164)//вернёт тру, если наш новый ФИО соответствует регулярному выражению
        {
            this.nameOfTheOwner = newNameOfTheOwner;//если тру, тогда записываем новый на место старого
            return true;
        } else
            System.out.println("ФИО введены некорректно!");
        System.out.println();
        return false;
    }

   public boolean setCarType(String newCarType) {
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
    public String getBrand() {
        return this.brand;
    }

    public String getRegistrationNumberOfTheCar() {
        return this.registrationNumberOfTheCar;
    }

    public int getYearOfRelease() {
        return this.yearOfRelease;
    }

    public String getYearOfReleaseString() {
        String string = "";
        string += this.yearOfRelease;
        return string;
    }

    public String getColor() {
        return this.color;
    }

    public String getNameOfTheOwner() {
        return this.nameOfTheOwner;
    }

    public String getResidenceAddressOfTheOwner() {
        return this.residenceAddressOfTheOwner;
    }

    public String getCarType() {
        return this.carType;
    }

    //endregion


}

