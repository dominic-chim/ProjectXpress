package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.DateTime;

import data.Context;
import data.dataObject.StaffDO;
import database.DatabaseRoot;

public class StaffDao extends DatabaseRoot {

    public StaffDao() {
        super();
    }

    HashMap<Integer, String> skills = Context.getSkillMap();

    
    public StaffDO getStaffById(int staffId) {

        // String sql =
        // "SELECT staff_id, staff_name, staff_weekly_available_time, skill_name, skill_level, "
        // +
        // "project_id task_id, prefence_level FROM staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence WHERE staff_id = "
        // + staffId;

        String sql = "SELECT staff_id, staff_name, staff_weekly_available_time FROM staff WHERE staff_id = "
                + staffId;

        try {
            ResultSet result = db.executeQuery(sql);
            if(result.next()) {
                String staffName = result.getString("staff_name"); 
                int weekAvailableTime = result.getInt("staff_weekly_available_time");
                HashMap<Integer, Double> skillLevels = getSkillLevelByStaffId(staffId);
                HashMap<DateTime, DateTime> holidays = (new HolidaysDao()).getHolidaysOfStaff(staffId);
                return new StaffDO(staffId, staffName, weekAvailableTime, skillLevels, holidays);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        //Testing
        /*
        HashMap<Integer, Double> skillLevels = new HashMap<Integer, Double>();
        skillLevels.put(1, 1.0);
        HashMap<DateTime, DateTime> holidayDates = new HashMap<DateTime, DateTime>();
        holidayDates.put(null, null);
        

        try {
            while (result.next()) {

                staffDo = new StaffDO(result.getInt("staff_id"),
                        result.getString("staff_name"),
                        result.getInt("staff_weekly_available_time"), skillLevels,
                        holidayDates);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        return null;

    }


    public HashMap<Integer, Double> getSkillLevelByStaffId(int staffId) {

        HashMap<Integer, Double> skillLevel = new HashMap<Integer, Double>();

        String sql = "SELECT skill_id, skill_level FROM staff_skill_level WHERE staff_id=" + staffId;
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                skillLevel.put(result.getInt("skill_id"), result.getDouble("skill_level"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        return skillLevel;
    }

    public void createStaff(StaffDO staff) {

        // String staffValues = staff.getStaffId() + ", '" +
        // staff.getStaffName() + "', '" +
        // staff.getStaffWeeklyAvailableTime() + "', '" +
        // staff.getSkills() + "', '" +
        // staff.getHolidays() + "', '";
                
        
        String staffValues = staff.getStaffId() + ", '" + staff.getStaffName()
                + "', '" + staff.getStaffWeeklyAvailableTime() + "'";
        
        
        // String sql =
        // "INSERT INTO staff NATURAL JOIN staff_holidays NATURAL JOIN staff_preference VALUES ("
        // + staffValues + " )";

        //Insert Staff Info into staff table
        String sql = "INSERT INTO staff VALUES (" + staffValues + " )";

        int result;
        try {
            result = db.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Add Skills to staff skill level table
        HashMap<Integer, Double> skillLevels = staff.getSkillLevels();

        staffValues = "";

        for(int i : skillLevels.keySet()) {
            
            staffValues += staff.getStaffId() + ", '" + skills.get(i) + "', '" + skillLevels.get(i) + "', '";
            
        }
        
        sql = "INSERT INTO staff_skill_level VALUES (" + staffValues + " )";
        try {
            result = db.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void modifyStaff(StaffDO staff) {

        String staffValues ="staff_name='"+ staff.getStaffName() + "', staff_weekly_available_time='"
                + staff.getStaffWeeklyAvailableTime() + "'";

        System.out.println(staffValues);
        
//      for (String i : staff.getSkills()) {
//          staffValues += i = "', '";
//      }
//
//      for (String i : staff.getHolidays()) {
//          staffValues += i = "', '";
//      }

        String sql = "UPDATE staff SET "
                + staffValues + " WHERE staff_id = " + staff.getStaffId();

        try {
            int result = db.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteStaff(String staffId) {

        // String sql =
        // "DELETE FROM staff NATURAL JOIN staff_skill_level NATURAL JOIN staff-prefence WHERE staff_id = "
        // + staffId;

        String sql = "DELETE FROM staff WHERE staff_id = " + staffId;

        int result;

        try {
            result = db.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<StaffDO> getAllStaff() {

        ArrayList<StaffDO> staffs = new ArrayList<StaffDO>();

        String sql = "SELECT DISTINCT staff_id FROM staff";

        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                staffs.add(getStaffById(result.getInt("staff_id")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return staffs;

        // String sql =
        // "SELECT staff_id, staff_name, staff_weekly_available_time, skill_name, skill_level, "
        // +
        // "project_id task_id, prefence_level FROM staff NATUARAL JOIN staff_skill_level NATURAL JOIN staff_prefence";

        /*
        String sql = "SELECT staff_id, staff_name, staff_weekly_available_time FROM staff";

        ResultSet result = null;
        try {
            result = connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StaffDO staffDo = null;
        ArrayList<StaffDO> listOfStaff = new ArrayList<StaffDO>();

        try {
            while (result.next()) {
                try {
                    staffDo = new StaffDO(result.getInt("staff_id"),
                            result.getString("staff_name"),
                            result.getInt("staff_weekly_available_time"), null,
                            null);

                    listOfStaff.add(staffDo);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfStaff;
        */

    }

}
