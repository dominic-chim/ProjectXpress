package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    private String dateTime = "0000-00-00 00:00:00";
    private final SimpleDateFormat dataBaseDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime(int year, int month, int day, int hour, int minute, int second) {
        // TODO throw some exception when these values are invalid
        dateTime = String.format("%4d-%2d-%2d %2d:%2d:%2d", 
                                year, month, day, hour, minute, second);
    }

    public DateTime(Date date) {
    }


}
