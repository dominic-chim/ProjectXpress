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
import database.dataAccessObject.ResultDao;
import database.dataAccessObject.StaffDao;

public class StaffSummary extends JPanel {

	private GridBagConstraints gbc = new GridBagConstraints();
	private int yPos = 1;

	// TODO add error checks if task start date is greater than time scale set
	// on table

	int dayXPos = 8;
	DateTime currentDateTime;
	JLabel lblDay;

	public StaffSummary() {

		DateTime projectStartDate = new DateTime("2013-02-01 09:00:00");
		this.currentDateTime = projectStartDate;

		setLayout(new GridBagLayout());

		// Labels
		JLabel blankLabel;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		gbc.gridwidth = 8;
		gbc.fill = GridBagConstraints.BOTH;
		add(blankLabel = new JLabel(""), gbc);
		gbc.gridy = 1;
		add(blankLabel = new JLabel(""), gbc);

		HashMap<StaffDO, ArrayList<ResultDO>> test = new HashMap<StaffDO, ArrayList<ResultDO>>();
		ResultDao resultDB = new ResultDao();
		StaffDao staffDB = new StaffDao();
		test.put(staffDB.getStaffById(3), resultDB.getResultByStaff(3));
		// System.out.println(resultDB.getResultByStaff(1));
		// test.put(staffDB.getStaffById(2), resultDB.getResultByStaff(2));
		addData(test, new DateTime(2013, 2, 1, 9, 0, 0));

		setVisible(true);

	}

	public void addDay() {

		// Add Day
		gbc.gridy = 0;
		gbc.gridwidth = 8;
		gbc.gridx = dayXPos;
		
		lblDay = new JLabel(currentDateTime.getDateTime(), JLabel.HORIZONTAL);
		lblDay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(lblDay, gbc);

		// Add Hours of Day
	
		int hourPos = dayXPos;
		gbc.gridx = hourPos;
		gbc.gridy =  1;
		gbc.gridwidth = 1;
		JLabel lblHours;

		for(int i = 9; i < 17; i++) {
			
			lblHours = new JLabel(Integer.toString(i), JLabel.HORIZONTAL);
			lblHours.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			gbc.gridx = hourPos++;
			
			add(lblHours, gbc);
			
		}	

		dayXPos += 8;
		currentDateTime = DateTime.nextDay(currentDateTime);

	}

	// HashMap arg = HashMap< Staff Objects for the staffName(or just pass
	// staffName), HashMap< Project Name, Duration>>
	// Could change this to some sort of Result object which contains all this
	// info
	public void addData(
			HashMap<StaffDO, ArrayList<ResultDO>> staffAllocProjects,
			DateTime projectStartDate) {

		int xPos = 0;
		JLabel lblBlank;
		JLabel lblTaskName;

		for (StaffDO staff : staffAllocProjects.keySet()) {

			JLabel staffName = new JLabel(staff.getStaffName(),
					JLabel.HORIZONTAL);
			staffName.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			// In Order
			ArrayList<ResultDO> listOfTasks = staffAllocProjects.get(staff);

			gbc.gridy = ++yPos;
			gbc.gridx = xPos;
			gbc.gridwidth = 8;

			add(staffName, gbc);

			xPos += 5;
			gbc.gridx = xPos;

			// Change to Real Project Starting Date
			// DateTime projectStartDate = new DateTime(2013, 04, 05, 10, 30,
			// 0);

			DateTime currentTime = projectStartDate;
			// DateTime currentTime = listOfTasks.get(0).getStartDateTime();

			for (ResultDO task : listOfTasks) {

				TaskDO taskDo = task.getTaskDO();

				while(currentDateTime.before(task.getEndDateTime())) {
					addDay();
				}
				
				gbc.gridy = yPos;
								
				if (currentTime.before(task.getStartDateTime())) {
					int blankLength = DateTime.duration(currentTime,
							task.getStartDateTime());
					gbc.gridwidth = blankLength;
					add(lblBlank = new JLabel(""), gbc);
					lblBlank.setBorder(BorderFactory
							.createLineBorder(Color.BLACK));
					xPos += blankLength;
					gbc.gridx = xPos;
				}

				int duration = DateTime.duration(task.getStartDateTime(),
						task.getEndDateTime());

				gbc.gridwidth = duration;
				add(lblTaskName = new JLabel(Integer.toString(taskDo
						.getTaskId()), JLabel.HORIZONTAL), gbc);
				lblTaskName.setBorder(BorderFactory
						.createLineBorder(Color.BLACK));
				xPos += duration;

				gbc.gridx = xPos;

				currentTime = task.getEndDateTime();
			}			
			xPos = 0;			
		}
	}

	public void addController(ActionListener controller) {

	}
}
