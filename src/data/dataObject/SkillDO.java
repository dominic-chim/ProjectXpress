package data.dataObject;

/**
 *
 * data object represent a skill
 *
 * @author Ross
 */
public class SkillDO {
	int id;
	String skillName;

	public SkillDO(int id, String skillName) {
		this.id = id;
		this.skillName = skillName;
	}

	public int getId() {
		return id;
	}

	public String getSkillName() {
		return skillName;
	}

}
