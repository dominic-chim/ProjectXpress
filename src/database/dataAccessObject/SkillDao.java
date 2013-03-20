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

	public void addSkill(int id, String name) {

		System.out.println("Adding Skill");
		String sql = "INSERT INTO skill VALUES (" + id + ", '" + name + "')";
		
		try {
			db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public void modifySkill(int id, String name) {

		
		String sql = "UPDATE staff SET skill_name = '" + name + "' WHERE skill_id = " + id;
		
		try {
			int result = db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	//TODO 
	// If we have time to add
	public void removeSkill() {

	}

}
