package data.dataObject;


public class StaffDO {

	private int staffId;
	private String staffName;
	private int staffWeeklyAvailableTime;
	String[] skills;
	String[] holidays;

	public StaffDO(int staffId, String staffName, int staffWeeklyAvailableTime,
			String[] skills, String[] holidays) {

		setStaffId(staffId);
		setStaffName(staffName);
		setStaffWeeklyAvailableTime(staffWeeklyAvailableTime);
		setSkills(skills);
		setHolidays(holidays);
	}

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

	public String[] getSkills() {

		return this.skills;
	}

	public String[] getHolidays() {

		return this.holidays;
	}

	// public HashMap<Integer, HashMap<Integer, Integer>> getPrefenceLevel(int
	// taskId) {
	// return prefenceLevel;
	// }

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

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public void setHolidays(String[] holidays) {
		this.holidays = holidays;
	}

}
