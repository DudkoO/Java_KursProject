package sample.Backend;

enum Brand {
    BMW, LADA, Lexus, Tesla, Citroen, Ford, Honda, Jeep, Chevrolet;

    public static boolean isTrueBrand(String brand) {
        Brand[] array = Brand.values();
        brand = brand.toLowerCase();
        for (int i = 0; i < array.length; i++) {
            // System.out.println(array[i]);
            if (array[i].toString().toLowerCase().equals(brand)) {
                return true;
            }
        }
        return false;
    }

}
