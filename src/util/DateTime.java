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
        //
        //long dateNum = year * 10000000000 + month * 
        dateTime = String.format("%s-%s-%s %s:%s:%s", 
                                year, 
                                addZeroIfNeeded(month),
                                addZeroIfNeeded(day),
                                addZeroIfNeeded(hour),
                                addZeroIfNeeded(minute),
                                addZeroIfNeeded(second));
    }

    public DateTime(Date date) {
    }

    public String getDateTime() {
        return this.dateTime;
    }

    private String addZeroIfNeeded(int num) {
        return num < 10 ? "0" + num : "" + num;
    }


}
