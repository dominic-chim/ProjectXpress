package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseRoot;

public class HolidaysDao extends DatabaseRoot {
	
	
	

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
}
