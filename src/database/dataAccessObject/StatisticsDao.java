package database.dataAccessObject;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.DatabaseRoot;

public class StatisticsDao extends DatabaseRoot {
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
		String sql = "SELECT b.staff_name, COUNT(*) AS Total FROM (SELECT * FROM scheduling_result as test GROUP BY project_id, task_id HAVING max(version)) as a NATURAL JOIN staff as b GROUP BY b.staff_name;";
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
		String sql = " SELECT staff_name, SUM(z.staff_weekly_available_time - m.SUM) AS timeLeft FROM (SELECT staff_name, SUM(task_duration) AS SUM FROM (SELECT * FROM scheduling_result as test GROUP BY project_id, task_id HAVING max(version)) as a NATURAL JOIN staff as b NATURAL JOIN task AS d GROUP BY b.staff_name) AS m NATURAL JOIN staff AS z GROUP BY staff_name;";
		//String sql = "SELECT staff_name, staff_weekly_available_time FROM staff;";
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<Object>();
				row.add(result.getString("staff_name"));
				//row.add(result.getInt("staff_weekly_available_time"));
				row.add(result.getInt("timeLeft"));
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
	
	
	public Object[][] availableStats() {

		String sql = "SELECT staff_id, staff_name, skill_level, skill_name, staff_weekly_available_time FROM staff NATURAL JOIN staff_skill_level NATURAL JOIN skill;";

		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("staff_id"));
				row.add(result.getString("staff_name"));
				row.add(result.getString("skill_level"));
				row.add(result.getString("skill_name"));
				row.add(result.getInt("staff_weekly_available_time"));

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

		String sql = "SELECT staff_id, b.staff_name, COUNT(DISTINCT(a.project_id)) AS projectTotal, COUNT(DISTINCT(c.skill_id)) AS skillTotal, staff_weekly_available_time FROM (SELECT * FROM scheduling_result as test GROUP BY project_id, task_id HAVING max(version)) as a NATURAL JOIN staff as b NATURAL JOIN staff_skill_level as c GROUP BY b.staff_name;";

		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("staff_id"));
				row.add(result.getString("b.staff_name"));
				row.add(result.getString("projectTotal"));
				row.add(result.getString("skillTotal"));
				row.add(result.getInt("staff_weekly_available_time"));

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
