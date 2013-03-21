package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import util.DateTime;

import database.DatabaseRoot;

/**
 * 
 * class to get holiday info form database
 * 
 * @author Ke CHEN
 *
 */
public class HolidaysDao extends DatabaseRoot {
    
    
    public HashMap<DateTime, DateTime> getHolidaysOfStaff(int staffId) {

        HashMap<DateTime, DateTime> holidays = new HashMap<DateTime, DateTime>();

        String sql = "SELECT holiday_start_time, holiday_end_time FROM staff_holidays WHERE staff_id=" + staffId;

        try {
            ResultSet result =  connection.createStatement().executeQuery(sql);
            while(result.next()){
                holidays.put(new DateTime(result.getString("holiday_start_time")), 
                        new DateTime(result.getString("holiday_end_time")));
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }


        return holidays;
    }
    

}
