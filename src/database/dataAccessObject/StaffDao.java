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

		// String sql =
		// "SELECT staff_id, staff_name, staff_weekly_available_time, skill_name, skill_level, "
		// +
		// "project_id task_id, prefence_level FROM staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence WHERE staff_id = "
		// + staffId;

		String sql = "SELECT staff_id, staff_name, staff_weekly_available_time FROM staff WHERE staff_id = "
				+ staffId;

		ResultSet result = null;
		try {
			result = db.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		StaffDO staffDo = null;

		String[] test = {"Test", "Test Two"};
		
		try {
			while (result.next()) {

				staffDo = new StaffDO(result.getInt("staff_id"),
						result.getString("staff_name"),
						result.getInt("staff_weekly_available_time"), test,
						test);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return staffDo;

	}

	public void createStaff(StaffDO staff) {

		// String staffValues = staff.getStaffId() + ", '" +
		// staff.getStaffName() + "', '" +
		// staff.getStaffWeeklyAvailableTime() + "', '" +
		// staff.getSkills() + "', '" +
		// staff.getHolidays() + "', '";

		String staffValues = staff.getStaffId() + ", '" + staff.getStaffName()
				+ "', '" + staff.getStaffWeeklyAvailableTime() + "'";

		// String sql =
		// "INSERT INTO staff NATURAL JOIN staff_holidays NATURAL JOIN staff_preference VALUES ("
		// + staffValues + " )";

		String sql = "INSERT INTO staff VALUES (" + staffValues + " )";

		int result;
		try {
			result = db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void modifyStaff(StaffDO staff) {

		String staffValues ="staff_name='"+ staff.getStaffName() + "', staff_weekly_available_time='"
				+ staff.getStaffWeeklyAvailableTime() + "'";

		System.out.println(staffValues);
		
//		for (String i : staff.getSkills()) {
//			staffValues += i = "', '";
//		}
//
//		for (String i : staff.getHolidays()) {
//			staffValues += i = "', '";
//		}

		String sql = "UPDATE staff SET "
				+ staffValues + " WHERE staff_id = " + staff.getStaffId();

		try {
			int result = db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteStaff(String staffId) {

		// String sql =
		// "DELETE FROM staff NATURAL JOIN staff_skill_level NATURAL JOIN staff-prefence WHERE staff_id = "
		// + staffId;

		String sql = "DELETE FROM staff WHERE staff_id = " + staffId;

		int result;

		try {
			result = db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<StaffDO> getAllStaff() {

		// String sql =
		// "SELECT staff_id, staff_name, staff_weekly_available_time, skill_name, skill_level, "
		// +
		// "project_id task_id, prefence_level FROM staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence";

		String sql = "SELECT staff_id, staff_name, staff_weekly_available_time FROM staff";

		ResultSet result = null;
		try {
			result = db.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		StaffDO staffDo = null;
		ArrayList<StaffDO> listOfStaff = new ArrayList<StaffDO>();

		try {
			while (result.next()) {
				try {
					staffDo = new StaffDO(result.getInt("staff_id"),
							result.getString("staff_name"),
							result.getInt("staff_weekly_available_time"), null,
							null);

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