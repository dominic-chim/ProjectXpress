package data.dataObject;

import java.util.HashMap;

import util.DateTime;

public class StaffDO {

    private int staffId;
    private String staffName;
    private int staffWeeklyAvailableTime;
    HashMap<Integer, Integer> skillLevels;
    HashMap<DateTime, Integer> holidayDates;
    

    public StaffDO(int staffId, String staffName, int staffWeeklyAvailableTime,
            HashMap<Integer, Integer> skillLevels, HashMap<DateTime, Integer> holidayDates) {

        setStaffId(staffId);
        setStaffName(staffName);
        setStaffWeeklyAvailableTime(staffWeeklyAvailableTime);
        setSkillLevels(skillLevels);
        setHolidayDates(holidayDates);
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

    public HashMap<Integer, Integer> getSkillLevels() {

        return this.skillLevels;
    }

    public HashMap<DateTime, Integer> getHolidays() {

        return this.holidayDates;
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

    public void setSkillLevels(HashMap<Integer, Integer> skillLevels) {
        this.skillLevels = skillLevels;
    }

    public void setHolidayDates(HashMap<DateTime, Integer> holidayDates) {
        this.holidayDates = holidayDates;
    }

    public boolean hasSkill(int skillId) {

        if (skillLevels.get(skillId) == null)
            return false;
        else 
            return true;

    }

}
