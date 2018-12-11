package sample.Backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Owner implements Serializable {

    public String nameOfTheOwner;
   public  DriversLicense driversLicense=new DriversLicense();
  // public  ObservableList<Auto> personalСars= FXCollections.observableArrayList();
   public ArrayList<Auto> personalСars= new ArrayList<>();

    long experience; //стаж- считается в секундах (от наступления эпохи юникса)

    public  DriversLicense getDriversLicense() {
        return this.driversLicense;
    }

    public  void setDriversLicense(DriversLicense driversLicense) {
       this.driversLicense = driversLicense;
    }

    public String getPersonalCars(){
        String string="";
        string+=this.personalСars.size()+":";
        for (int i = 0; i <this.personalСars.size() ; i++) {
            string+=this.personalСars.get(i).brand+"-"+this.personalСars.get(i).getRegistrationNumberOfTheCar()+";";
        }
        return string;
    }



    public void printpersonalСarsToConslole(){
        for (int i = 0; i <personalСars.size() ; i++) {
            System.out.println(personalСars.get(i).getBrand()+" "+personalСars.get(i).getRegistrationNumberOfTheCar());
        }
    }

    public boolean openСategory(String category){
       if(categories.isTrueCategory(category)) {
           switch (category){
               case "A1":
                   driversLicense.setCategoryA1(true);
                   break;
               case "A":
                   driversLicense.setCategoryA(true);
                   break;
               case "B1":
                   driversLicense.setCategoryB1(true);
                   break;
               case "B":
                   driversLicense.setCategoryB(true);
                   break;
               case "C1":
                   driversLicense.setCategoryC1(true);
                   break;
               case "C":
                   driversLicense.setCategoryC(true);
                   break;
               case "D1":
                   driversLicense.setCategoryD1(true);
                   break;
               case "D":
                   driversLicense.setCategoryD(true);
                   break;
               case "BE":
                   driversLicense.setCategoryBE(true);
                   break;
               case "C1E":
                   driversLicense.setCategoryC1E(true);
                   break;
               case "CE":
                   driversLicense.setCategoryCE(true);
                   break;
               case "D1E":
                   driversLicense.setCategoryD1E(true);
                   break;
               case "DE":
                   driversLicense.setCategoryDE(true);
                   break;
               case "T":
                   driversLicense.setCategoryT(true);
                   break;
           }
            return true;
       }
       else return false;

    }

    public ArrayList<Auto> getPersonalСars() {
        return personalСars;
    }


    public String getNameOfTheOwner() {
        return nameOfTheOwner;
    }

    public String getExperience() {

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");

        return dateFormat.format(this.experience);
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public void setNameOfTheOwner(String nameOfTheOwner) {
        this.nameOfTheOwner = nameOfTheOwner;
    }
}
