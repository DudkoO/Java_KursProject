package sample.Backend;

enum Color {
    Красный, Синий, Желтый, Фиолетовый, Зелёный, Белый, Чёрный, Коричневый, Серый, Голубой, Розовый;

    public static boolean isTrueColor(String color) {
        color = color.toLowerCase();
        Color[] array = Color.values();
        for (int i = 0; i < array.length; i++) {
            // System.out.println(array[i]);
            if (array[i].toString().toLowerCase().equals(color)) {
                return true;
            }
        }
        return false;
    }
}
