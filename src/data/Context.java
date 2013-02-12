package data;

import java.util.HashMap;

public class Context {

    private static final HashMap<String, Integer> skills = new HashMap<String, Integer>();

    static {
        // TODO read from database
        skills.put("skill1", 1);
        skills.put("skill2", 2);
        skills.put("skill3", 3);
    }

    public HashMap<String, Integer> getSkillMap() {
    	return skills;
    }
    
    /**
     * update skills from db
     * TODO finish it
     */
    public void updateSkills() {
    }
}
