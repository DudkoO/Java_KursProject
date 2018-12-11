package sample.Backend;

import java.io.Serializable;

public class DriversLicense implements Serializable {
    boolean categoryA1=false;
    final String descriptionСategoryA1
            ="Двух- и трехколесные транспортные средства, рабочий объем силовой установки, у которых не превышает 50 см³. Мощность двигателя – не более 4 кВт;";
    boolean categoryA=false;
    final  String descriptionСategoryA
            ="Вождение мотоциклов, в том числе с боковым прицепом, а также аналогичным двухколесным транспортом объемом двигателя от 50 см³ и более, и мощностью от 4 кВт;";
    boolean categoryB1=false;
    final String descriptionСategoryB1
            ="Квадроциклы и трициклы, масса которых не превышает 400 кг;";
    boolean categoryB=false;
    final  String descriptionСategoryB
            ="Автомобили массой до 3500 кг и количеством мест для пассажиров (кроме водительского) – не более восьми;";
    boolean categoryC1=false;
    final  String descriptionСategoryC1
            ="Грузовые автомобили, вес которых варьируется от 3500 до 7500 кг;";
    boolean categoryC=false;
    final String descriptionСategoryC
            ="Грузовые автомобили, масса которых превышает 7500 кг;";
    boolean categoryD1=false;
    final String descriptionСategoryD1
            ="Автобусы, число пассажирских мест в которых не более 16;";
    boolean categoryD=false;
    final  String descriptionСategoryD
            ="Автобусы,число пассажирских мест более 16;";
    boolean categoryBE=false;
    final  String descriptionСategoryBE
            =" Разрешает вождение легкового авто с прицепом, массой более 750 кг;";
    boolean categoryC1E=false;
    final String descriptionСategoryC1E
            ="Грузовые машины категории «С1» с прицепом от 750 кг;";
    boolean categoryCE=false;
    final String descriptionСategoryCE
            ="Грузовые машины категории  «С» с прицепом от 750 кг;";
    boolean categoryD1E=false;
    final  String descriptionСategoryD1E
            ="Водители помимо управления пассажирским транспортным средством могут использовать прицеп весом от 750 кг.";
    boolean categoryDE=false;
    final String descriptionСategoryDE
            ="Водители помимо управления пассажирским транспортным средством могут использовать прицеп весом от 750 кг.";
    boolean categoryT=false;
    final  String descriptionСategoryT
            ="Позволяет управлять трамваями.";

    //region setters Category
    public void setCategoryA(boolean categoryA) {
        this.categoryA = categoryA;
    }

    public void setCategoryA1(boolean categoryA1) {
        this.categoryA1 = categoryA1;
    }

    public void setCategoryB(boolean categoryB) {
        this.categoryB = categoryB;
    }

    public void setCategoryB1(boolean categoryB1) {
        this.categoryB1 = categoryB1;
    }

    public void setCategoryBE(boolean categoryBE) {
        this.categoryBE = categoryBE;
    }

    public void setCategoryC(boolean categoryC) {
        this.categoryC = categoryC;
    }

    public void setCategoryC1(boolean categoryC1) {
        this.categoryC1 = categoryC1;
    }

    public void setCategoryC1E(boolean categoryC1E) {
        this.categoryC1E = categoryC1E;
    }

    public void setCategoryCE(boolean categoryCE) {
        this.categoryCE = categoryCE;
    }

    public void setCategoryD(boolean categoryD) {
        this.categoryD = categoryD;
    }

    public void setCategoryD1(boolean categoryD1) {
        this.categoryD1 = categoryD1;
    }

    public void setCategoryD1E(boolean categoryD1E) {
        this.categoryD1E = categoryD1E;
    }

    public void setCategoryDE(boolean categoryDE) {
        this.categoryDE = categoryDE;
    }

    public void setCategoryT(boolean categoryT) {
        this.categoryT = categoryT;
    }
    //endregion с

    //region getters Category

    public boolean isCategoryA() {
        return categoryA;
    }

    public boolean isCategoryA1() {
        return categoryA1;
    }

    public boolean getCategoryB() {
        return categoryB;
    }

    public boolean getCategoryB1() {
        return categoryB1;
    }

    public boolean getCategoryBE() {
        return categoryBE;
    }

    public boolean getCategoryC() {
        return categoryC;
    }

    public boolean getCategoryC1() {
        return categoryC1;
    }

    public boolean getCategoryD() {
        return categoryD;
    }

    public boolean getCategoryC1E() {
        return categoryC1E;
    }

    public boolean getCategoryCE() {
        return categoryCE;
    }

    public boolean getCategoryD1() {
        return categoryD1;
    }

    public boolean getCategoryD1E() {
        return categoryD1E;
    }

    public boolean getCategoryDE() {
        return categoryDE;
    }

    public boolean getCategoryT() {
        return categoryT;
    }


    //endregion


    //region getters description
    public String getDescriptionСategoryA() {
        return descriptionСategoryA;
    }

    public String getDescriptionСategoryA1() {
        return descriptionСategoryA1;
    }

    public String getDescriptionСategoryB() {
        return descriptionСategoryB;
    }

    public String getDescriptionСategoryB1() {
        return descriptionСategoryB1;
    }

    public String getDescriptionСategoryC1() {
        return descriptionСategoryC1;
    }

    public String getDescriptionСategoryC() {
        return descriptionСategoryC;
    }

    public String getDescriptionСategoryCE() {
        return descriptionСategoryCE;
    }

    public String getDescriptionСategoryC1E() {
        return descriptionСategoryC1E;
    }

    public String getDescriptionСategoryD() {
        return descriptionСategoryD;
    }

    public String getDescriptionСategoryD1() {
        return descriptionСategoryD1;
    }

    public String getDescriptionСategoryD1E() {
        return descriptionСategoryD1E;
    }

    public String getDescriptionСategoryDE() {
        return descriptionСategoryDE;
    }

    public String getDescriptionСategoryT() {
        return descriptionСategoryT;
    }

    public String getDescriptionСategoryBE() {
        return descriptionСategoryBE;
    }
    //endregion

    public String getCategories(){
        String string="";
        if(categoryA1)
            string+="A1 ";
        if(categoryA)
            string+="A ";
        if(categoryB1)
            string+="B1 ";
        if(categoryB)
            string+="B ";
        if(categoryC1)
            string+="C1 ";
        if(categoryC)
            string+="C ";
        if(categoryD1)
            string+="D1 ";
        if(categoryD)
            string+="D ";
        if(categoryBE)
            string+="BE ";
        if(categoryC1E)
            string+="C1E ";
        if(categoryCE)
            string+="CE ";
        if(categoryD1E)
            string+="D1E ";
        if(categoryDE)
            string+="D ";
        if(categoryT)
            string+="T ";

        return string;
    }
}

enum categories{
    A1,A,B1,B,C1,C,D1,D,BE,C1E,CE,D1E,DE,T;

    public static boolean isTrueCategory(String category){
        categories categories[]= sample.Backend.categories.values();
        boolean flag=false;
        for (int i = 0; i < categories.length; i++) {
            if(category.equals(categories[i].toString())) {
                flag=true;
                break;
            }
        }
        return flag;
    }
}
