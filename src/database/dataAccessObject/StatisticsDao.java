package database.dataAccessObject;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.DatabaseRoot;

/**
 * 
 * database class for statistics
 * 
 * @author Samy
 *
 */
public class StatisticsDao extends DatabaseRoot {
	private String sqlNew = "NEW";
	public StatisticsDao() {
		super();
	}

	public ArrayList<Object> riskStats() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = "SELECT task_risk_level, COUNT(*)/(SELECT COUNT(*) FROM task)*100 AS percentage FROM task GROUP BY task_risk_level;";
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("task_risk_level"));
				row.add(result.getInt("percentage"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public ArrayList<Object> projectStatusStats() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = " SELECT project_status, COUNT(*)/(SELECT COUNT(*) FROM project)*100 AS percentage FROM project GROUP BY project_status;";
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("project_status"));
				row.add(result.getInt("percentage"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public ArrayList<Object> taskStatusStats() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = "SELECT task_status, COUNT(*)/(SELECT COUNT(*) FROM task)*100 AS percentage FROM task GROUP BY task_status;";
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("task_status"));
				row.add(result.getInt("percentage"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public ArrayList<Object> skillStaffCount() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = "SELECT skill_name, COUNT(*) AS Total FROM staff_skill_level NATURAL JOIN skill GROUP BY skill_name;";
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("skill_name"));
				row.add(result.getInt("Total"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public ArrayList<Object> taskCountStaff() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = "SELECT b.staff_name, COUNT(*) AS Total FROM (SELECT * FROM scheduling_result as test WHERE version=(select max(version) from scheduling_result)) as a NATURAL JOIN staff as b GROUP BY b.staff_name;";
		
		
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("b.staff_name"));
				row.add(result.getInt("Total"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public ArrayList<Object> timeAvailablitlyStaff() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = "SELECT staff_name, staff_weekly_available_time FROM (SELECT staff_id, staff_name, ABS(MIN(staff_weekly_available_time)) AS staff_weekly_available_time FROM (SELECT staff_id, staff_name, staff_weekly_available_time FROM staff UNION (SELECT staff_id, staff_name, SUM(staff_weekly_available_time - A.task_remaining_time) AS staff_weekly_available_time FROM (SELECT staff_id, staff_name, SUM(task_remaining_time) AS task_remaining_time FROM(SELECT staff_name, staff_id, task_id, task_name, task_remaining_time FROM (SELECT * FROM scheduling_result as test WHERE version=(select max(version) from scheduling_result)) AS test NATURAL JOIN task NATURAL JOIN staff) AS a GROUP BY staff_id) AS A NATURAL JOIN staff GROUP BY staff_id)) AS B GROUP BY staff_id) AS D;";
		
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("staff_name"));
				row.add(result.getInt("staff_weekly_available_time"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public ArrayList<Object> staffCountProject() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = "SELECT project_name, COUNT(DISTINCT(staff_id)) AS Total FROM project NATURAL JOIN scheduling_result GROUP BY project_name;";
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("project_name"));
				row.add(result.getInt("Total"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}
	
	
	public ArrayList<Object> projectProductivity() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = "SELECT project_name, task_status, COUNT(task_status)AS total FROM task NATURAL JOIN project GROUP BY project_id, task_status;";
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getInt("total"));
				row.add(result.getString("task_status"));
				row.add(result.getString("project_name"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public ArrayList<Object> usedData() {
		ArrayList<Object> output = new ArrayList<Object>();
		String sql = "SELECT staff_name, SUM(task_remaining_time) AS SUM FROM (SELECT * FROM scheduling_result as test where version=(select max(version) from scheduling_result)) as a NATURAL JOIN staff as b NATURAL JOIN task GROUP BY b.staff_name;";
		
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("staff_name"));
				row.add(result.getInt("SUM"));
				output.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public Object[][] availableStats() {

		String sql = "SELECT staff_id, staff_name, skill_level, skill_name FROM staff NATURAL JOIN staff_skill_level NATURAL JOIN skill;";

		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("staff_id"));
				row.add(result.getString("staff_name"));
				row.add(result.getString("skill_level"));
				row.add(result.getString("skill_name"));

				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Object rows[][] = new Object[data.size()][(int) ((ArrayList<Object>) data
				.get(0)).size()];

		for (int i = 0; i < data.size(); i++) {

			@SuppressWarnings("unchecked")
			ArrayList<Object> s = (ArrayList<Object>) data.get(i);
			for (int j = 0; j < s.size(); j++) {
				rows[i][j] = s.get(j);
			}
		}
		return rows;
	}

	public Object[][] projectsList() {

		String sql = "SELECT project_id, project_name, project_priority, project_status FROM project;";

		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("project_id"));
				row.add(result.getString("project_name"));
				row.add(result.getString("project_priority"));
				row.add(result.getString("project_status"));
				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Object rows[][] = new Object[data.size()][(int) ((ArrayList<Object>) data
				.get(0)).size()];

		for (int i = 0; i < data.size(); i++) {

			@SuppressWarnings("unchecked")
			ArrayList<Object> s = (ArrayList<Object>) data.get(i);
			for (int j = 0; j < s.size(); j++) {
				rows[i][j] = s.get(j);
			}
		}
		return rows;
	}

	public Object[][] allStats() {

		String sql = "SELECT staff_id, b.staff_name, COUNT( DISTINCT ( a.project_id) ) AS projectTotal, COUNT( DISTINCT ( c.skill_id) ) AS skillTotal FROM ( SELECT * FROM scheduling_result AS test where version=(select max(version) from scheduling_result)) AS a NATURAL JOIN staff AS b NATURAL JOIN staff_skill_level AS c GROUP BY b.staff_name";
		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("staff_id"));
				row.add(result.getString("b.staff_name"));
				row.add(result.getString("projectTotal"));
				row.add(result.getString("skillTotal"));
				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Object rows[][] = new Object[data.size()][(int) ((ArrayList<Object>) data
				.get(0)).size()];

		for (int i = 0; i < data.size(); i++) {

			@SuppressWarnings("unchecked")
			ArrayList<Object> s = (ArrayList<Object>) data.get(i);
			for (int j = 0; j < s.size(); j++) {
				rows[i][j] = s.get(j);
			}
		}
		return rows;
	}

	public Object[][] scheduledProjects() {

		String sql = "SELECT project_id, project_name, project_priority, COUNT(task_id) AS total , CAST(min(start_datetime) AS DATE)  AS start_datetime, CAST(end_datetime AS DATE) AS end_datetime FROM project NATURAL JOIN task NATURAL JOIN scheduling_result WHERE version IN (SELECT MAX(version) FROM scheduling_result)GROUP BY project_id, version;";

		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("project_id"));
				row.add(result.getString("project_name"));
				row.add(result.getString("project_priority"));
				row.add(result.getString("total"));
				row.add(result.getString("start_datetime"));
				row.add(result.getString("end_datetime"));

				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Object rows[][] = new Object[data.size()][(int) ((ArrayList<Object>) data
				.get(0)).size()];

		for (int i = 0; i < data.size(); i++) {

			@SuppressWarnings("unchecked")
			ArrayList<Object> s = (ArrayList<Object>) data.get(i);
			for (int j = 0; j < s.size(); j++) {
				rows[i][j] = s.get(j);
			}
		}
		return rows;
	}

	public Object[][] scheduledTasks() {

		String sql = "SELECT task_id, task_name, staff_name, CAST(min(start_datetime) AS DATE)  AS start_datetime, CAST(end_datetime AS DATE) AS end_datetime, task_risk_level FROM scheduling_result NATURAL JOIN task NATURAL JOIN project NATURAL JOIN staff WHERE version IN (SELECT MAX(version) FROM scheduling_result) GROUP BY project_id, task_id ORDER BY version;";

		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("task_id"));
				row.add(result.getString("task_name"));
				row.add(result.getString("staff_name"));
				row.add(result.getString("start_datetime"));
				row.add(result.getString("end_datetime"));
				row.add(result.getString("task_risk_level"));

				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Object rows[][] = new Object[data.size()][(int) ((ArrayList<Object>) data
				.get(0)).size()];

		for (int i = 0; i < data.size(); i++) {

			@SuppressWarnings("unchecked")
			ArrayList<Object> s = (ArrayList<Object>) data.get(i);
			for (int j = 0; j < s.size(); j++) {
				rows[i][j] = s.get(j);
			}
		}
		return rows;
	}

	public Object[][] tasksList() {

		String sql = "SELECT task_id, task_name, project_name, skill_name, task_duration, task_status FROM task NATURAL JOIN project FULL JOIN skill WHERE skill_id = task_required_skill;";

		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("task_id"));
				row.add(result.getString("task_name"));
				row.add(result.getString("project_name"));
				row.add(result.getString("skill_name"));
				row.add(result.getString("task_duration"));
				row.add(result.getString("task_status"));

				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Object rows[][] = new Object[data.size()][(int) ((ArrayList<Object>) data
				.get(0)).size()];

		for (int i = 0; i < data.size(); i++) {

			@SuppressWarnings("unchecked")
			ArrayList<Object> s = (ArrayList<Object>) data.get(i);
			for (int j = 0; j < s.size(); j++) {
				rows[i][j] = s.get(j);
			}
		}
		return rows;
	}

}
