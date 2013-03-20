package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.DateTime;
import data.Context;
import data.dataObject.StaffDO;
import database.DatabaseRoot;

public class StaffDao extends DatabaseRoot {

	public StaffDao() {
		super();
	}

	HashMap<Integer, String> skills = Context.getSkillMap();

	public StaffDO getStaffById(int staffId) {

		String sql = "SELECT staff.staff_id, staff_name, staff_weekly_available_time, skill_id, skill_level, holiday_start_time, holiday_end_time FROM staff LEFT OUTER JOIN staff_skill_level ON staff.staff_id = staff_skill_level.staff_id LEFT OUTER JOIN staff_holidays ON staff.staff_id = staff_holidays.staff_id WHERE staff.staff_id = "
				+ staffId;

		try {
			ResultSet result = db.executeQuery(sql);
			if (result.next()) {
				String staffName = result.getString("staff_name");
				int weekAvailableTime = result
						.getInt("staff_weekly_available_time");
				HashMap<Integer, Double> skillLevels = getSkillLevelByStaffId(staffId);
				HashMap<DateTime, DateTime> holidays = (new HolidaysDao())
						.getHolidaysOfStaff(staffId);
				return new StaffDO(staffId, staffName, weekAvailableTime,
						skillLevels, holidays);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public HashMap<Integer, Double> getSkillLevelByStaffId(int staffId) {

		HashMap<Integer, Double> skillLevel = new HashMap<Integer, Double>();

		String sql = "SELECT skill_id, skill_level FROM staff_skill_level WHERE staff_id="
				+ staffId;
		try {
			ResultSet result = connection.createStatement().executeQuery(sql);
			while (result.next()) {
				skillLevel.put(result.getInt("skill_id"),
						result.getDouble("skill_level"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return skillLevel;
	}

	public void createStaff(StaffDO staff) {

		String staffValues = staff.getStaffId() + ", '" + staff.getStaffName()
				+ "', '" + staff.getStaffWeeklyAvailableTime() + "', '" + "Active" + "'";

		String sql = "INSERT INTO staff VALUES (" + staffValues + " )";

		try {
			db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (DateTime date : staff.getHolidays().keySet()) {
			
			staffValues = staff.getStaffId() + ", '" + date.getDateTime()
					+ "', '" + staff.getHolidays().get(date).getDateTime()
					+ "'";

			sql = "INSERT INTO staff_holidays VALUES (" + staffValues + " )";

			try {
				db.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		for (int skillId : staff.getSkillLevels().keySet()) {

			staffValues = staff.getStaffId() + ", " + skillId + ", "
					+ staff.getSkillLevels().get(skillId);

			sql = "INSERT INTO staff_skill_level VALUES (" + staffValues + " )";

			try {
				db.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void modifyStaff(int staffId, StaffDO staff,
			ArrayList<String> queries) {

		String staffValues = "";
		String sql = "";

		staffValues = "staff_id= " + staff.getStaffId() + ", staff_name='"
				+ staff.getStaffName() + "', staff_weekly_available_time='"
				+ staff.getStaffWeeklyAvailableTime() + "'";

		sql = "UPDATE staff SET " + staffValues + " WHERE staff_id = "
				+ staffId;

		try {
			int result = db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (String i : queries) {
			try {
				int result = db.executeUpdate(i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteStaff(String staffId) {

		// String sql =
		// "DELETE FROM staff NATURAL JOIN staff_skill_level NATURAL JOIN staff-prefence WHERE staff_id = "
		// + staffId;

		// String sql =
		// "DELETE FROM staff NATURAL JOIN staff_skill_level NATURAL JOIN staff_holidays WHERE staff_id = "
		// + staffId;

//		String sql = "DELETE staff, staff_skill_level, staff_holidays FROM staff LEFT OUTER JOIN staff_skill_level ON staff.staff_id = staff_skill_level.staff_id LEFT OUTER JOIN staff_holidays ON staff.staff_id = staff_holidays.staff_id WHERE staff.staff_id = "
//				+ staffId;

		String sql = "UPDATE staff SET status = 'deleted' WHERE staff_id = " + staffId;
		
		int result;

		try {
			result = db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<StaffDO> getAllStaff() {

		ArrayList<StaffDO> staffs = new ArrayList<StaffDO>();

		String sql = "SELECT DISTINCT staff_id FROM staff WHERE status = 'Active'";

		try {
			ResultSet result = connection.createStatement().executeQuery(sql);
			while (result.next()) {
				staffs.add(getStaffById(result.getInt("staff_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return staffs;

		// String sql =
		// "SELECT staff_id, staff_name, staff_weekly_available_time, skill_name, skill_level, "
		// +
		// "project_id task_id, prefence_level FROM staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence";

		/*
		 * String sql =
		 * "SELECT staff_id, staff_name, staff_weekly_available_time FROM staff"
		 * ;
		 * 
		 * ResultSet result = null; try { result =
		 * connection.createStatement().executeQuery(sql); } catch (SQLException
		 * e) { e.printStackTrace(); }
		 * 
		 * StaffDO staffDo = null; ArrayList<StaffDO> listOfStaff = new
		 * ArrayList<StaffDO>();
		 * 
		 * try { while (result.next()) { try { staffDo = new
		 * StaffDO(result.getInt("staff_id"), result.getString("staff_name"),
		 * result.getInt("staff_weekly_available_time"), null, null);
		 * 
		 * listOfStaff.add(staffDo);
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); } } } catch
		 * (SQLException e) { e.printStackTrace(); }
		 * 
		 * return listOfStaff;
		 */

	}

}
