package data.dataObject;

import java.util.HashMap;

import util.DateTime;

/**
 * 
 * data object for a staff
 * 
 * @author Ross
 *
 */
public class StaffDO {

    private int staffId;
    private String staffName;
    private int staffWeeklyAvailableTime;
    HashMap<Integer, Double> skillLevels;
    HashMap<DateTime, DateTime> holidayHour;
    

    public StaffDO(int staffId, String staffName, int staffWeeklyAvailableTime,
            HashMap<Integer, Double> skillLevels, HashMap<DateTime, DateTime> holidayDates) {

        setStaffId(staffId);
        setStaffName(staffName);
        setStaffWeeklyAvailableTime(staffWeeklyAvailableTime);
        setSkillLevels(skillLevels);
        setHolidayHour(holidayDates);
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

    public HashMap<Integer, Double> getSkillLevels() {

        return this.skillLevels;
    }

    public HashMap<DateTime, DateTime> getHolidays() {

        return this.holidayHour;
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

    public void setSkillLevels(HashMap<Integer, Double> skillLevels) {
        this.skillLevels = skillLevels;
    }

    public void setHolidayHour(HashMap<DateTime, DateTime> holidayDates) {
        this.holidayHour = holidayDates;
    }

    public boolean hasSkill(int skillId) {

        if (skillLevels.get(skillId) == null)
            return false;
        else 
            return true;

    }

}
