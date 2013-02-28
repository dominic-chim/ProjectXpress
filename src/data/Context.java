package data;

import java.util.HashMap;

import database.dataAccessObject.SkillDao;

public class Context {

    private static final HashMap<String, Integer> skillsRev = new HashMap<String, Integer>();
    private static final HashMap<Integer, String> skills;
    
    static {
        // TODO read from database
        SkillDao skillDao = new SkillDao();
        HashMap<Integer, String> skillMap = skillDao.getSkillMap();
        skills = skillMap;
        for(int skillId : skillMap.keySet()) {
            skillsRev.put(skillMap.get(skillId), skillId);
        }
    }

    public static HashMap<String, Integer> getSkillRevMap() {
    	
        return skillsRev;
    }
    
public static HashMap<Integer, String> getSkillMap() {
    	
        return skills;
    }
    /**
     * update skills from db
     * TODO finish it
     */
    public void updateSkills() {
    }
}
