package ru.happyMoments.shared.staff;

public class Creator {
    public static String createName() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
