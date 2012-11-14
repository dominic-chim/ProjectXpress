import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public class StaffDo {

	private int staffId;
	private String staffName;
	private int staffWeeklyAvailableTime;
	private String skillName;
	private int skillLevel;
	private HashMap<Integer, HashMap<Integer, Integer>> prefenceLevel;

	// Prefence Level is based on task Id and project Id.
	// Thought it was appropriate to put them together.

	public StaffDo(int staffId, String staffName,
			int staffWeeklyAvailableTime, String skillName, int skillLevel,
			int projectId, int taskId, int prefenceLevel) {

		setStaffId(staffId);
		setStaffName(staffName);
		setStaffWeeklyAvailableTime(staffWeeklyAvailableTime);
		setSkillName(skillName);
		setSkillLevel(skillLevel);

		setPrefenceLevel(projectId, taskId, prefenceLevel);
	}

	// When creating a staff object in the GUI, are we going to have the feature
	// of putting that staff on multiple tasks? If so an array/hash map will be
	// needed as
	// an argument to take in multiple tasks and prefenceLevels.

	// Getters
	public int getStaffId() {
		return staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public int getStaffWeeklyAvailableTime() {
		return staffWeeklyAvailableTime;
	}

	public String getSkillName() {
		return skillName;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public HashMap<Integer, Integer> getPrefenceLevel(int taskId) {
		return prefenceLevel;
	}

	// Setters
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public void setStaffWeeklyAvailableTime(int staffWeeklyAvailableTime) {
		this.staffWeeklyAvailableTime = staffWeeklyAvailableTime;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public void setPrefenceLevel(int projectId, int taskId, int prefenceLevel) {
		if(this.prefenceLevel.)
		this.prefenceLevel.put(taskId, prefenceLevel);
	}
}
