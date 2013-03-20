package data.dataObject;

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
