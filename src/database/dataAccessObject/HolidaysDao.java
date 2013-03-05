package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import util.DateTime;

import database.DatabaseRoot;

public class HolidaysDao extends DatabaseRoot {
    
    
    public HashMap<DateTime, DateTime> getHolidaysOfStaff(int staffId) {

        HashMap<DateTime, DateTime> holidays = new HashMap<DateTime, DateTime>();

        String sql = "SELECT holiday_start_time, holiday_end_time FROM staff_holidays WHERE staffId=" + staffId;

        try {
            ResultSet result =  connection.createStatement().executeQuery(sql);
            while(result.next()){
                holidays.put(new DateTime(result.getString("holiday_start_time")), 
                        new DateTime(result.getString("holiday_end_time")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return holidays;
    }
    

    /*
    public int getDaybyMonth(int month){
        int holidaycount = 0;
        String sql = "select holiday_count from historical_holiday_record where month = "+month;
        try {
            ResultSet result =  db.executeQuery(sql);
            while(result.next()){
                holidaycount = result.getInt("holiday_count");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return holidaycount;
        
    }
    */

}
