package database.dataAccessObject;

import java.sql.SQLException;

import database.DatabaseRoot;

public class Risk extends DatabaseRoot{
	
	int risk;
	double time,totalTime;	
		
	public int getRiskLeve(){
	return this.risk;
	}
	
	public void updateRiskLevel(String Riskname, int risk){
		String sql = "Update risk_percentage from risk_level where task_risk_level = '"+Riskname+"'";
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

}
