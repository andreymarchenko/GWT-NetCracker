package ru.happyMoments.shared.staff;


public class Checker {

    static boolean checkHourMinute(String time) {
        int hours = Integer.parseInt(time.split(":")[0]);
        int minutes = Integer.parseInt(time.split(":")[1]);
        if (hours == 24) {
            if (minutes == 0) {
                return true;
            } else return false;

        } else if (hours < 24 && hours >= 0) {
            if (minutes <= 59 && minutes >= 0) {
                return true;
            } else return false;
        } else return false;
    }

    static boolean checkDayMonthYear(String time) {
        int hours = Integer.parseInt(time.split(":")[0]);
        int minutes = Integer.parseInt(time.split(":")[1]);
        if (hours == 24) {
            if (minutes == 0) {
                return true;
            } else return false;

        } else if (hours < 24 && hours >= 0) {
            if (minutes <= 59 && minutes >= 0) {
                return true;
            } else return false;
        } else return false;
    }

    public static boolean checkTime(String time) {
        if (time.length() == 5) {
            try {
                boolean result = checkHourMinute(time);
                return result;
            } catch (Exception e) {
                return false;
            }
        } else if (time.length() == 6 && time.charAt(2) == 'h' && time.charAt(5) == 'm') {
            try {
                boolean result = checkHourMinute(time.split("h")[0] + time.split("h")[1].split("m")[0]);
                return result;
            } catch (Exception e) {
                return false;
            }
        } else return false;
    }

    /*public static boolean checkDate(String date) {
        if (date.length() == 10) {
            try {
                boolean result = checkHourMinute(time);
                return result;
            } catch (Exception e) {
                return false;
            }
        }
        else if(date.length() == 8)
    }*/
}
