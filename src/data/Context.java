package data;

import java.util.HashMap;

import database.dataAccessObject.RiskDao;
import database.dataAccessObject.SkillDao;

public class Context {

    private static HashMap<String, Integer> skillsRev = new HashMap<String, Integer>();
    private static HashMap<Integer, String> skills;

    private static HashMap<String, Integer> riskLevel = new HashMap<String, Integer>();
    
    static {
        // TODO read from database
        SkillDao skillDao = new SkillDao();
        RiskDao riskDao = new RiskDao();
        HashMap<Integer, String> skillMap = skillDao.getSkillMap();
        skills = skillMap;
        for(int skillId : skillMap.keySet()) {
            skillsRev.put(skillMap.get(skillId), skillId);
        }
        riskLevel = riskDao.getRiskMap();
    }

    
    public static HashMap<String, Integer> getSkillRevMap() {
        
        return skillsRev;
    }
    
    public static HashMap<Integer, String> getSkillMap() {
        
        return skills;
    }
    
    public static HashMap<String, Integer> getRiskLevel() {
        return riskLevel;
    }
    
    /**
     * update skills from db
     */
    public static void updateSkills() {
        
        SkillDao skillDao = new SkillDao();
        HashMap<Integer, String> skillMap = skillDao.getSkillMap();
        
        skills = skillMap;
        skillsRev = new HashMap<String, Integer>();
        for(int skillId : skillMap.keySet()) {
            skillsRev.put(skillMap.get(skillId), skillId);
        }
    }

    /**
     * update risk from db
     */
    public static void updateRisk() {
        RiskDao riskDao = new RiskDao();
        riskLevel.clear();
        riskLevel = riskDao.getRiskMap();
    }
}
