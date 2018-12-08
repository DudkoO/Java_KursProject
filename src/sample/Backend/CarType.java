package sample.Backend;

enum CarType {
    Седан, Хэтчбек, Универсал, Лифтбэк, Купе, Кабриолет, Родстер, Тарга, Лимузин, Стретч, Внедорожник, Кроссовер, Пикап, Фургон, Минивэн;


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
