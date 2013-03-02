package view.staff;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.dataObject.ProjectDO;
import data.dataObject.StaffDO;

public class StaffSummary extends JPanel {


	private GridBagConstraints gbc = new GridBagConstraints();
	private int yPos = 0;
	
	
	public StaffSummary() {
		
		setLayout(new GridBagLayout());
		
		//Labels
		JLabel blankLabel = new JLabel("");
		
		JLabel lblWeekOne = new JLabel("Week One", JLabel.HORIZONTAL);
		JLabel lblWeekTwo = new JLabel("Week Two", JLabel.HORIZONTAL);
		JLabel lblWeekThree = new JLabel("Week Three", JLabel.HORIZONTAL);
		JLabel lblWeekFour = new JLabel("Week Four", JLabel.HORIZONTAL);
		
		JLabel lblD1 = new JLabel("D1");
		JLabel lblD2 = new JLabel("D2");
		JLabel lblD3 = new JLabel("D3");
		JLabel lblD4 = new JLabel("D4");
		JLabel lblD5 = new JLabel("D5");

		//Add to panel
		
		gbc.gridx = 0;
		gbc.gridy = yPos;
		gbc.gridwidth = 5;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		gbc.fill = GridBagConstraints.BOTH;
		
		add(blankLabel, gbc);
		gbc.gridx = 5;
		add(lblWeekOne, gbc);
		gbc.gridx = 10;
		add(lblWeekTwo, gbc);
		gbc.gridx = 15;
		add(lblWeekThree, gbc);
		gbc.gridx = 20;
		add(lblWeekFour, gbc);

		gbc.gridy = ++yPos;
		gbc.gridx = 0;
		add(blankLabel, gbc);
		gbc.gridwidth = 1;

		for(int i = 0; i < 4; i++) {
			gbc.gridx = 5 + (5*i);
			add(lblD1 = new JLabel("D1"), gbc);
			gbc.gridx = 6 + (5*i);
			add(lblD2 = new JLabel("D2"), gbc);
			gbc.gridx = 7 + (5*i);
			add(lblD3 = new JLabel("D3"), gbc);
			gbc.gridx = 8 + (5*i);
			add(lblD4 = new JLabel("D4"), gbc);
			gbc.gridx = 9 + (5*i);
			add(lblD5 = new JLabel("D5"), gbc);
		}
		
		setVisible(true);

	}

	
	//HashMap arg = HashMap< Staff Objects for the staffName(or just pass staffName), HashMap< Project Name, Duration>>
	//Could change this to some sort of Result object which contains all this info
	public void addData(HashMap<StaffDO , HashMap<String, Integer>> staffAllocProjects) {
		
		int xPos = 0;
		
		for(StaffDO staff : staffAllocProjects.keySet()) {
		
			JLabel staffName = new JLabel(staff.getStaffName());
			
			//HashMap or ArrayList of Result objects
			HashMap<String, Integer> listOfProjects = staffAllocProjects.get(staff);
			
			gbc.gridy = ++yPos;
			gbc.gridx = xPos;
			gbc.gridwidth = 5;
			
			add(staffName, gbc);
			
//			if(project.StartDate > xPos) {
//				int blankLength = projectStartDate - xPos;
//				gbc.gridwidth = blankLength;
//				add(new JLabel(""), gbc);
//				xPos += blankLength;
//				gbc.gridx = xPos;
//			}
			
			for(String project : listOfProjects.keySet()) {
			
				int duration = listOfProjects.get(project);
				
				gbc.gridwidth = duration;
				add(new JLabel(project), gbc);
				xPos += duration;
				
				gbc.gridx = xPos;
				
			}
			
			xPos = 0;
			
		}
		
		
	}
	
	
	public void addController(ActionListener controller) {

	}
}
