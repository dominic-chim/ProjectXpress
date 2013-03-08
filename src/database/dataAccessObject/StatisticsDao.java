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

	/*public Object[][] allFromTest() {

		String sql = "SELECT * FROM test";

		ArrayList<Object> data = new ArrayList<>();

		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				ArrayList<Object> row = new ArrayList<>();
				row.add(result.getString("name"));
				row.add(result.getString("skill"));
				row.add(result.getString("projectalo"));
				row.add(result.getString("taskalo"));
				row.add(result.getInt("hours"));

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
	}*/

}
