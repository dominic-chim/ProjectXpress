package data;

import java.util.HashMap;

import database.dataAccessObject.SkillDao;

public class Context {

    private static final HashMap<String, Integer> skills = new HashMap<String, Integer>();

    static {
        // TODO read from database
        SkillDao skillDao = new SkillDao();
        HashMap<Integer, String> skillMap = skillDao.getSkillMap();
        for(int skillId : skillMap.keySet()) {
            skills.put(skillMap.get(skillId), skillId);
        }
    }

    public static HashMap<String, Integer> getSkillMap() {
        return skills;
    }
    
    /**
     * update skills from db
     * TODO finish it
     */
    public void updateSkills() {
    }
}
