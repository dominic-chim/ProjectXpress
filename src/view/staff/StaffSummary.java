package view.staff;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.DateTime;
import data.dataObject.ResultDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

public class StaffSummary extends JPanel {


	private GridBagConstraints gbc = new GridBagConstraints();
	private int yPos = 0;
	
	//TODO add error checks if task start date is greater than time scale set on table
	
	public StaffSummary() {
		
		setLayout(new GridBagLayout());
				
		
		//Labels
		JLabel blankLabel = new JLabel("");
		
		JLabel lblWeekOne = new JLabel("Week One", JLabel.HORIZONTAL);
		lblWeekOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel lblWeekTwo = new JLabel("Week Two", JLabel.HORIZONTAL);
		lblWeekTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel lblWeekThree = new JLabel("Week Three", JLabel.HORIZONTAL);
		lblWeekThree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel lblWeekFour = new JLabel("Week Four", JLabel.HORIZONTAL);
		lblWeekFour.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel lblD1;
		JLabel lblD2;
		JLabel lblD3;
		JLabel lblD4;
		JLabel lblD5;
	
		//Add to panel
		
		gbc.gridx = 0;
		gbc.gridy = yPos;
		gbc.gridwidth = 40;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		gbc.fill = GridBagConstraints.BOTH;
		
		add(blankLabel = new JLabel(""), gbc);
		gbc.gridx = 5;
		add(lblWeekOne, gbc);
		gbc.gridx = 45;
		add(lblWeekTwo, gbc);
		gbc.gridx = 85;
		add(lblWeekThree, gbc);
		gbc.gridx = 125;
		add(lblWeekFour, gbc);

		gbc.gridy = ++yPos;
		gbc.gridx = 0;
		add(blankLabel = new JLabel(""), gbc);
		gbc.gridwidth = 8;

		for(int i = 0; i < 4; i++) {
			gbc.gridx = 5 + (40*i);
			add(lblD1 = new JLabel("D1", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 13 + (40*i);
			add(lblD2 = new JLabel("D2", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 21 + (40*i);
			add(lblD3 = new JLabel("D3", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 29 + (40*i);
			add(lblD4 = new JLabel("D4", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 37 + (40*i);
			add(lblD5 = new JLabel("D5", JLabel.HORIZONTAL), gbc);
			
			lblD1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblD2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblD3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblD4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblD5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		
		gbc.gridy = ++yPos;
		gbc.gridx = 0;
		add(blankLabel = new JLabel(""), gbc);
		gbc.gridwidth = 1;
		
		JLabel lblH1;
		JLabel lblH2;
		JLabel lblH3;
		JLabel lblH4;
		JLabel lblH5;
		JLabel lblH6;
		JLabel lblH7;
		JLabel lblH8;
		
		for(int i = 0; i < 20; i++) {
		
			gbc.gridx = 5 + (8*i);
			add(lblH1 = new JLabel("9", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 6 + (8*i);
			add(lblH2 = new JLabel("10", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 7 + (8*i);
			add(lblH3 = new JLabel("11", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 8 + (8*i);
			add(lblH4 = new JLabel("12", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 9 + (8*i);
			add(lblH5 = new JLabel("13", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 10 + (8*i);
			add(lblH6 = new JLabel("14", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 11 + (8*i);
			add(lblH7 = new JLabel("15", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 12 + (8*i);
			add(lblH8 = new JLabel("16", JLabel.HORIZONTAL), gbc);	
			
			lblH1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblH2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblH3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblH4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblH5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblH6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblH7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblH8.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			
		}
		
		/// ||||||||||||||| Testing ||||||||||| \\\
		
	        //Create StaffDO obj to Test
	        HashMap<Integer, Double> skillLevels = new HashMap<Integer, Double>();
	        skillLevels.put(1, 5.0);
	        HashMap<DateTime, DateTime> holidayDates= new HashMap<DateTime, DateTime>();
	
			StaffDO staffOne = new StaffDO(1, "Ross", 40, skillLevels, holidayDates);
			StaffDO staffTwo = new StaffDO(2, "Bob", 40, skillLevels, holidayDates);
	
			//Create ArrayList of ResultDO to test
			
		
			DateTime dateTime = new DateTime(2013, 04, 05, 10, 30, 0);
			
			ArrayList<Integer> requiredTskIds = new ArrayList<Integer>();
			requiredTskIds.add(5);
			
			DateTime dateStart1= new DateTime(2013, 04, 05, 10, 30, 0);
			DateTime dateEnd1 = new DateTime(2013, 04, 05, 13, 30, 0);
			
			DateTime dateStart2= new DateTime(2013, 04, 06, 14, 30, 0);
			DateTime dateEnd2 = new DateTime(2013, 04, 06, 17, 30, 0);
			
			TaskDO taskOne = new TaskDO(1, 1, "Task One",  1, 3, "RiskLevel", dateTime, "Task Status", requiredTskIds);
			ResultDO resultOne = new ResultDO(5, taskOne, staffOne, dateStart1, dateEnd1);
	
			TaskDO taskTwo = new TaskDO(1, 2, "Task Two",  1, 3, "RiskLevel", dateTime, "Task Status", requiredTskIds);
			ResultDO resultTwo = new ResultDO(5, taskTwo, staffOne, dateStart2, dateEnd2);
	
			
			ArrayList<ResultDO> listOfResultsTest1 = new ArrayList<ResultDO>();
			listOfResultsTest1.add(resultOne);
			listOfResultsTest1.add(resultTwo);
			
			ArrayList<ResultDO> listOfResultsTest2 = new ArrayList<ResultDO>();
			listOfResultsTest2.add(resultOne);
			listOfResultsTest2.add(resultTwo);
			
			HashMap<StaffDO , ArrayList<ResultDO>> test = new HashMap<StaffDO, ArrayList<ResultDO>>();
			test.put(staffOne, listOfResultsTest1);
			test.put(staffTwo, listOfResultsTest2);
			
			
			addData(test);
		
	/// |||||||||| End of Testing ||||||||||| \\\\ 
			
		setVisible(true);

	}

	
	//HashMap arg = HashMap< Staff Objects for the staffName(or just pass staffName), HashMap< Project Name, Duration>>
	//Could change this to some sort of Result object which contains all this info
	public void addData(HashMap<StaffDO , ArrayList<ResultDO>> staffAllocProjects) {
		
		int xPos = 0;
		JLabel lblBlank;
		JLabel lblTaskName;
			
		for(StaffDO staff : staffAllocProjects.keySet()) {
		
			JLabel staffName = new JLabel(staff.getStaffName(), JLabel.HORIZONTAL);
			staffName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			//In Order
			 ArrayList<ResultDO> listOfTasks = staffAllocProjects.get(staff);
			
			gbc.gridy = ++yPos;
			gbc.gridx = xPos;
			gbc.gridwidth = 5;
			
			add(staffName, gbc);
			
			xPos += 5;
			gbc.gridx = xPos;
			
			
			//Change to Real Project Starting Date
			DateTime projectStartDate = new DateTime(2013, 04, 05, 10, 30, 0);

			DateTime currentTime = projectStartDate;
//			DateTime currentTime = listOfTasks.get(0).getStartDateTime();
			
			for(ResultDO task : listOfTasks) {
			
				TaskDO taskDo = task.getTaskDO();
				
				if(currentTime.before(task.getStartDateTime())) {
					int blankLength = DateTime.duration(currentTime, task.getStartDateTime()); 
					gbc.gridwidth = blankLength;
					add(lblBlank = new JLabel(""), gbc);
					lblBlank.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					xPos += blankLength;
					gbc.gridx = xPos;
				}
								
				int duration = taskDo.getTaskDuration();
				
				gbc.gridwidth = duration;
				add(lblTaskName = new JLabel(Integer.toString(taskDo.getTaskId()), JLabel.HORIZONTAL), gbc);
				lblTaskName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				xPos += duration;
				
				gbc.gridx = xPos;
				
				currentTime = task.getEndDateTime();
				
			}
			
			if(xPos < 160) {
				
				gbc.gridwidth = 165-xPos;
				add(lblBlank = new JLabel(""), gbc);
				lblBlank.setBorder(BorderFactory.createLineBorder(Color.BLACK));				
			}
			
			xPos = 0;
			
		}
		
		
	}
	
	
	public void addController(ActionListener controller) {

	}
}
