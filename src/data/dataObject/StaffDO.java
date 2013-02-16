package data.dataObject;

import java.util.ArrayList;
import java.util.HashMap;

import data.Holiday;

public class StaffDO {

    private int staffId;
    private String staffName;
    private int staffWeeklyAvailableTime;
    HashMap<Integer, Integer> skillLevels;
    ArrayList<Holiday> holidays;

    public StaffDO(int staffId, String staffName, int staffWeeklyAvailableTime,
            HashMap<Integer, Integer> skillLevels, String[] holidays) {

        setStaffId(staffId);
        setStaffName(staffName);
        setStaffWeeklyAvailableTime(staffWeeklyAvailableTime);
        setSkillLevels(skillLevels);
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

    public HashMap<Integer, Integer> getSkillLevels() {

        return this.skillLevels;
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

    public void setSkillLevels(HashMap<Integer, Integer> skillLevels) {
        this.skillLevels = skillLevels;
    }

    public void setHolidays(String[] holidays) {
        this.holidays = holidays;
    }

    public boolean hasSkill(int skillId) {

        if (skillLevels.get(skillId) == null)
            return false;
        else 
            return true;

    }

}
