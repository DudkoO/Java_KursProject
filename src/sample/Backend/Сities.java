package sample.Backend;

enum Сities {
    Украина,Россия,Беларусь,Словения,Чехия,Сербия,Польша,Молдова;

    public static boolean isTrueBrand(String counry) {
        Сities[] array = Сities.values();
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
