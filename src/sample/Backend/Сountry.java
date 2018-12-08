package sample.Backend;

enum Сountry {
    Украина,Россия,Беларусь,Словения,Чехия,Сербия,Польша,Молдова;

    public static boolean isTrueBrand(String counry) {
        Сountry[] array = Сountry.values();
        counry = counry.toLowerCase();
        for (int i = 0; i < array.length; i++) {
            // System.out.println(array[i]);
            if (array[i].toString().toLowerCase().equals(counry)) {
                return true;
            }
        }
        return false;
    }

}
