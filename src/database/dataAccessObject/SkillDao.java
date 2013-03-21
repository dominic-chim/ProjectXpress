package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import data.dataObject.SkillDO;
import database.DatabaseRoot;

/**
 * 
 * database class for table skill
 * 
 * @author Ke CHEN & Ross
 *
 */
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

	public void addSkill(String name) {

		String sql = "INSERT INTO skill (skill_name) VALUES ('" + name  + "') ";
		
		try {
			db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public void modifySkill(SkillDO skill) {
		
		String sql = "UPDATE skill SET skill_name = '" + skill.getSkillName() + "' WHERE skill_id = " + skill.getId();
		
		try {
			int result = db.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
