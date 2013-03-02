package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import database.DatabaseRoot;

public class SkillDao extends DatabaseRoot {

	public String[] getSkillNames() {
		String sql = "SELECT skill_name FROM skill";
		ArrayList<String> skillNames = new ArrayList<String>();
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				skillNames.add(result.getString("skill_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return skillNames.toArray(new String[skillNames.size()]);
	}

	public HashMap<Integer, String> getSkillMap() {
		String sql = "SELECT skill_id, skill_name FROM skill";
		HashMap<Integer, String> skillMap = new HashMap<Integer, String>();
		try {
			ResultSet result = db.executeQuery(sql);
			while (result.next()) {
				skillMap.put(result.getInt("skill_id"),
						result.getString("skill_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skillMap;

	}

	public void addSkill() {

	}

	public void modifySkill() {

	}

	public void removeSkill() {

	}

}
