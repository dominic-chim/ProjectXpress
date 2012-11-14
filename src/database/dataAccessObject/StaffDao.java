import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDao extends DatabaseRoot {

	public StaffDao() {
		super();
	}

	public StaffDo getStaffById(int staffId) {

		String sql = "SELECT staff_id, staff_name, staff_weekly_available_time, skill_name, skill_level, project_id task_id, prefence_level FROM staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence WHERE staff_id = "
				+ staffId;

		ResultSet result = null;
		try {
			result = db.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		StaffDo staffDo = null;
		try {
			staffDo = new StaffDo(
					result.getInt("staff_id"),
					result.getString("staff_name"),
					result.getInt("staff_weekly_available_time"),
					result.getString("skill_name"),
					result.getInt("skill_level"),
					result.getInt("project_id"),
					result.getInt("task_id"),
					result.getInt("prefence_level"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return staffDo;
	}

	public void createStaff(int Arguments) {

		String sql = "INSERT INTO TABLE task () VALUES ()";
		try {
			ResultSet result = db.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editStaffById(String staffField, int newValue, int staffId) {
		
		//If statement to check staffField is valid?
		String sql = "UPDATE staff, staff_prefence, staff_skill_level SET " + staffField + " = " + newValue
				+ " WHERE staff_id= " + staffId;
	}

}
