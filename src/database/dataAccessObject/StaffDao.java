package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.dataObject.StaffDO;
import database.DatabaseRoot;

public class StaffDao extends DatabaseRoot {

	public StaffDao() {
		super();
	}

	public StaffDO getStaffById(int staffId) {

		String sql = "SELECT staff_id, staff_name, staff_weekly_available_time, skill_name, skill_level, "
				+ "project_id task_id, prefence_level FROM staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence WHERE staff_id = "
				+ staffId;

//		ResultSet result = null;
//		try {
//			result = db.executeQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//		System.out.println(result);

//		StaffDO staffDo = null;
//		try {
//			staffDo = new StaffDO(result.getInt("staff_id"),
//					result.getString("staff_name"),
//					result.getInt("staff_weekly_available_time"),
//					result.getString("skill_name"),
//					result.getInt("skill_level"), result.getInt("project_id"),
//					result.getInt("task_id"), result.getInt("prefence_level"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

//		return staffDo;
		return null;
		
	}

	public void createStaff(StaffDO staff) {
		
		String staffValues = staff.getStaffId() + ", '" +
				staff.getStaffName() + "', '" +
				staff.getStaffWeeklyAvailableTime() +  "', '" +
				staff.getSkills() + "', '" +
				staff.getHolidays() + "', '";

//		System.out.println(staffValues);
		
		String sql = 
				"INSERT INTO TABLE staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence" +
				"VALUES ("+ staffValues +")";
		
//		ResultSet result = null;
//		try {
//			result = db.executeQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		

	}

	public void modifyStaff(StaffDO staff) {
		
		String staffValues = 
				staff.getStaffName() + "', '" +
				staff.getStaffWeeklyAvailableTime() +  "', '";
		
		for(String i : staff.getSkills()) {
			staffValues += i = "', '";
		}
		
		for(String i : staff.getHolidays()) {
			staffValues += i = "', '";
		}
		
		String sql = "UPDATE INTO TABLE staff NATURAL JOIN staff_skill_level NATURAL JOIN staff-prefence SET " + staffValues + " WHERE staff_id = " + staff.getStaffId();
		
		try {
			ResultSet result = db.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteStaff(String staffId) {
		
		String sql = "DELETE FROM TABLE staff NATURAL JOIN staff_skill_level NATURAL JOIN staff-prefence WHERE staff_id = " + staffId;
		
		ResultSet result = null;
		
		try {
			result = db.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	
	}

	public ArrayList<StaffDO> getAllStaff() {
		
		String sql = "SELECT staff_id, staff_name, staff_weekly_available_time, skill_name, skill_level, "
				+ "project_id task_id, prefence_level FROM staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence";

		ResultSet result = null;
		try {
			result = db.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		StaffDO staffDo = null;
		ArrayList<StaffDO> listOfStaff = new ArrayList<StaffDO>();
		
		try {
			while(result.next()) {
				try {
					staffDo = new StaffDO(result.getInt("staff_id"),
							result.getString("staff_name"),
							result.getInt("staff_weekly_available_time"),
							null,null);
					
					listOfStaff.add(staffDo);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return listOfStaff;
		
	}
	
}