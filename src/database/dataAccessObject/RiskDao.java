package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseRoot;

public class RiskDao extends DatabaseRoot{
    
    int risk;
    double time,totalTime;  
        
    public int getRiskLeve(){
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
        String sql = "SELECT task_risk_level FROM risk_level";
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

}
