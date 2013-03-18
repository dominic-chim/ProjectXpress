package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import database.DatabaseRoot;

public class RiskDao extends DatabaseRoot{
    
    int risk;
    double time,totalTime;  
        
    public int getRiskLevel(){
    return this.risk;
    }
    
    public void updateRiskLevel(String riskName, int risk){
        String sql = "Update risk_percentage from risk_level where task_risk_level = '"+riskName+"'";
        try {
            db.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public double totalTimeCalc(){
        
        //some calculation
        totalTime = time * risk;
        
        return totalTime;
    }

    public String[] getRiskNames() {
        String sql = "SELECT task_risk_level FROM risk_level ORDER BY risk_percentage";
        ArrayList<String> riskNames = new ArrayList<String>();
        try {
            ResultSet result = db.executeQuery(sql);
            while(result.next()) {
                riskNames.add(result.getString("task_risk_level"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return riskNames.toArray(new String[riskNames.size()]);
    }

    public HashMap<String, Integer> getRiskMap() {
        HashMap<String, Integer> riskLevel = new HashMap<String, Integer>();

        String sql = "SELECT task_risk_level, risk_percentage FROM risk_level";

        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                riskLevel.put(result.getString("task_risk_level"), result.getInt("risk_percentage"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return riskLevel;
    }

}
