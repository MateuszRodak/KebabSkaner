package pl.mr.kebab.util;

import java.sql.Time;

public class Utils {

    public static Time convertToTime(String string) {
        if (string == null) {
            throw new RuntimeException("puste!");
        } else if (string.length() == 8) {
            return Time.valueOf(string);
        } else if (string.length() == 5) {
            return Time.valueOf(string + ":00");
        } else if (string.length() == 2) {
            return Time.valueOf(string + ":00:00");
        }
        throw new RuntimeException("co to!");
    }
}
