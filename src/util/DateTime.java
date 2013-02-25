package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    private String dateTime = "0000-00-00 00:00:00";
    private final SimpleDateFormat dataBaseDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime(int year, int month, int day, int hour, int minute,
            int second) {
        // TODO throw some exception when these values are invalid
        //
        //long dateNum = year * 10000000000 + month *
        dateTime = String.format("%04s-%02s-%02s %02s:%02s:%02s", year, month,
                day, hour, minute, second);
    }

    public DateTime(Date date) {
    }

    public String getDateTime() {
        return this.dateTime;
    }


    public boolean before(DateTime toCompare) {
        try {
            Date thisDate = dataBaseDateFormat.parse(dateTime);
            Date dateToCompare = dataBaseDateFormat.parse(toCompare.getDateTime());
            return thisDate.before(dateToCompare);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }



}
