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

        // TODO get projectStartDate

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


        // reading data from database and add them to show
		HashMap<StaffDO, ArrayList<ResultDO>> dataToShow = new HashMap<StaffDO, ArrayList<ResultDO>>();
		ResultDao resultDB = new ResultDao();
		StaffDao staffDB = new StaffDao();

        ArrayList<Integer> staffIds = resultDB.getAllStaffInCurrentVersion();
        for(int staffId : staffIds) {
            dataToShow.put(staffDB.getStaffById(staffId), resultDB.getResultByStaff(staffId));
        }

		DateTime projectStartDate = resultDB.getStartingDateTime();
		this.currentDateTime = projectStartDate;
		addData(dataToShow, projectStartDate);

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


	public void addData(HashMap<StaffDO, ArrayList<ResultDO>> staffAllocProjects,
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

			xPos += 8;

			gbc.gridx = xPos;

			// Change to Real Project Starting Date
			// DateTime projectStartDate = new DateTime(2013, 04, 05, 10, 30,
			// 0);

			DateTime currentTime = projectStartDate;
			// DateTime currentTime = listOfTasks.get(0).getStartDateTime();

			System.out.println("Task Size: " + listOfTasks.size());
			
			for (ResultDO task : listOfTasks) {


				TaskDO taskDo = task.getTaskDO();

				while(currentDateTime.before(task.getEndDateTime())) {
					System.out.println(currentDateTime.getDateTime());
					System.out.println(task.getEndDateTime().getDateTime());
					
					addDay();
				}

				
				System.out.println(currentDateTime.getDateTime());

				System.out.println(yPos);
				gbc.gridy = yPos;
				gbc.gridx = xPos;
								
				System.out.println(currentTime.getDateTime());
				System.out.println(task.getStartDateTime().getDateTime());

				
				if (currentTime.before(task.getStartDateTime())) {
					System.out.println("CREATING BLANK");
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

				
				System.out.println("Gridx: " + gbc.gridx);
				System.out.println("Duration: " + duration);

				
				add(lblTaskName = new JLabel(taskDo.getTaskName(), JLabel.HORIZONTAL), gbc);
				lblTaskName.setBorder(BorderFactory
						.createLineBorder(Color.BLACK));

				xPos += duration;

				gbc.gridx = xPos;

				currentTime = task.getEndDateTime();
			}		
			
			if(currentTime.before(currentDateTime)) {
				
				int blankLength =  DateTime.duration(currentTime, currentDateTime);
				gbc.gridwidth = blankLength;
				add(lblBlank = new JLabel(""), gbc);
				lblBlank.setBorder(BorderFactory
						.createLineBorder(Color.BLACK));
				
				
			}
			xPos = 0;			

		}
	}

	public void addController(ActionListener controller) {

	}
}
