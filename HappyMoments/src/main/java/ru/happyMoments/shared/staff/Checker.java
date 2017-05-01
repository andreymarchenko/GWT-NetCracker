package ru.happyMoments.shared.staff;

import com.google.gwt.user.client.Window;

public class Checker {

    private static int[] monthDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static String checkDate(String date) {
        if (date.length() == 10) {
            try {
                char delimiter = chooseDelimiter(date);
                String[] data = date.split(String.valueOf(delimiter));

                int day = Integer.parseInt(data[0]);
                int month = Integer.parseInt(data[1]);
                int year = Integer.parseInt(data[2]);

                if (checkDayMonthYear(day, month, year)) {
                    return data[0] + " " + data[1] + " " + data[2];
                }

            } catch (Exception e) {
                return "";
            }
        } else if (date.length() == 8) {
            try {
                int day = Integer.parseInt(date.substring(0, 2));
                int month = Integer.parseInt(date.substring(2, 4));
                int year = Integer.parseInt(date.substring(4,8));

                if (checkDayMonthYear(day, month, year)) {
                    return date.substring(0, 2) + " " + date.substring(2, 4)
                            + " " + date.substring(4,8);
                }
            } catch (NumberFormatException e) {
                return "";
            }
        }
        return "";
    }

    public static String checkHourMinute(String time) {
        try {
            int hours = Integer.parseInt(time.split(":")[0]);
            int minutes = Integer.parseInt(time.split(":")[1]);
            if (hours == 24) {
                if (minutes == 0) {
                    return time;
                } else return "";

            } else if (hours < 24 && hours >= 0) {
                if (minutes <= 59 && minutes >= 0) {
                    return time;
                } else return "";
            } else return "";
        } catch (Exception e) {
            return "";
        }
    }

    static boolean checkDayMonthYear(int day, int month, int year) {

        if (day > 0 && day <= 31 && month > 0 && month <= 12 && year > 1916 && year < 2031) {
            if (year % 4 != 0 && month == 2 && day > 28) {
                return false;
            } else if (day > monthDay[month - 1] && month != 2) {
                return false;
            } else return true;
        } else return false;

    }

    public static String checkTime(String time) {
        try {
            String hoursS = time.split("h")[0];
            String minutesS = time.split("h")[1].split("m")[0];

            String newTime = hoursS + ":" + minutesS;

            if (checkHourMinute(newTime).equals(newTime)) {
                return newTime;
            } else {
                return "";
            }

        } catch (Exception e) {
            return "";
        }
    }

    public static char chooseDelimiter(String date) {

        char delimiter = ' ';
        char[] dateChar = date.toCharArray();
        for (int i = 0; i < dateChar.length; i++) {
            int sym = (int) dateChar[i];
            if (!(sym >= 48 && sym <= 57)) {
                delimiter = dateChar[i];
            }
        }
        return delimiter;
    }

    public static boolean checkString(String string) {
        if (string.length() == 0) {
            return false;
        } else if (string.trim().length() == 0) {
            return false;
        } else if (string.length() > 25) {
            return false;
        } else return true;
    }

    public static String convertTime(String time) {
        if(time.contains("h")){
            return checkTime(time);
        }
        else {
            return checkHourMinute(time);
        }
    }
}
