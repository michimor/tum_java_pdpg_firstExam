package w03.p;

public class Switch {

    public static String locationOfLectureHall(String hall) {
        String ret;
        switch (hall) {
            case "MI HS 2", "Interims I 1": {
                ret = "Informatik";
                break;
            }
            case "MW0001", "MW2001": {
                ret = "Maschinenwesen";
                break;
            }
            case "Carl-von-Linde", "N1190": {
                ret = "Innenstadt";
                break;
            }
            case "Interims II 2": {
                ret = "Chemie";
                break;
            }
            default: {
                ret = "Unbekannter Hörsaal";
                break;
            }
        }
        return ret;
    }

    public static int inclusions(char c) {
        int ret;
        switch (c) {
            case 'C', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                    'L', 'M', 'N', 'S', 'T', 'U', 'V', 'W',
                    'X', 'Y', 'Z',
                    'c', 'f', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'r', 's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z',
                    '1', '2', '3', '5', '7': {
                ret = 0;
                break;
            }
            case 'A', 'D', 'O', 'P', 'Q', 'R',
                    'a', 'b', 'd', 'e', 'o', 'p', 'q',
                    '4', '6', '9': {
                ret = 1;
                break;
            }
            case 'B', 'g', '0', '8': {
                ret = 2;
                break;
            }
            default: {
                ret = -1;
                break;
            }
        }
        return ret;
    }

    public static String formatDate(int day, int month, int weekday) {
        int invalid = 0;
        String ret;
        String wd_string = "";
        String invalid_string = "Undefiniertes Datum";
        switch (weekday) {
            case 1: {
                wd_string = "Montag";
                break;
            }
            case 2: {
                wd_string = "Dienstag";
                break;
            }
            case 3: {
                wd_string = "Mittwoch";
                break;
            }
            case 4: {
                wd_string = "Donnerstag";
                break;
            }
            case 5: {
                wd_string = "Freitag";
                break;
            }
            case 6: {
                wd_string = "Samstag";
                break;
            }
            case 7: {
                wd_string = "Sonntag";
                break;
            }
            default: {
                invalid = 1;
                break;
            }
        }
        String mth_string = "";
        switch (month) {
            case 1: {
                mth_string = "Januar";
                break;
            }
            case 2: {
                mth_string = "Februar";
                break;
            }
            case 3: {
                mth_string = "März";
                break;
            }
            case 4: {
                mth_string = "April";
                break;
            }
            case 5: {
                mth_string = "Mai";
                break;
            }
            case 6: {
                mth_string = "Juni";
                break;
            }
            case 7: {
                mth_string = "Juli";
                break;
            }
            case 8: {
                mth_string = "August";
                break;
            }
            case 9: {
                mth_string = "September";
                break;
            }
            case 10: {
                mth_string = "Oktober";
                break;
            }
            case 11: {
                mth_string = "November";
                break;
            }
            case 12: {
                mth_string = "Dezember";
                break;
            }
            default: {
                invalid = 1;
                break;
            }
        }
        switch (invalid) {
            case 0: {
                return wd_string + ", den " + day + ". " + mth_string;
            }
            default: {
                return invalid_string;
            }
        }
    }

    public static int daysInFebruary(int year) {
        int calc1 = year % 4;
        int calc2 = year % 100;
        int calc3 = year % 400;
        switch (calc1) {
            case 0: {
                switch (calc2) {
                    case 0: {
                        break;
                    }
                    default: {
                        return 29;
                    }
                }
                switch (calc3) {
                    case 0: {
                        return 29;
                    }
                }
                return 28;
            }
            default: {
                return 28;
            }
        }
    }

    public static int daysLeftInYearAfter(int day, int month, int year) {
        int days = 0;
        switch (month) {
            case 1:
                days += 31;
            case 2:
                days += daysInFebruary(year);
            case 3:
                days += 31;
            case 4:
                days += 30;
            case 5:
                days += 31;
            case 6:
                days += 30;
            case 7:
                days += 31;
            case 8:
                days += 31;
            case 9:
                days += 30;
            case 10:
                days += 31;
            case 11:
                days += 30;
            case 12:
                days += 31 - day;
                break;
            default:
                days = -1;
        }
        return days;
    }

}
