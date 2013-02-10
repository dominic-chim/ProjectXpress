package data;

import java.util.HashMap;

public class Skills {

    public static final HashMap<String, Integer> SKILLS = new HashMap<String, Integer>();

    static {
        // TODO read from database
        SKILLS.put("skill1", 1);
        SKILLS.put("skill2", 2);
        SKILLS.put("skill3", 3);
    }

    /**
     * update skills from db
     * TODO finish it
     */
    public void updateSkills() {
    }
}
