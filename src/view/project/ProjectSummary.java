package view.project;

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
import data.dataObject.ProjectDO;
import data.dataObject.ResultDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

public class ProjectSummary extends JPanel {

	private GridBagConstraints gbc = new GridBagConstraints();
	private int yPos = 0;

	// TODO add error checks if task start date is greater than time scale set
	// on table

	public ProjectSummary() {

		setLayout(new GridBagLayout());

		new JLabel("");
		JLabel[][] days;

		JLabel lblWeekOne = new JLabel("Week One", JLabel.HORIZONTAL);
		lblWeekOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel lblWeekTwo = new JLabel("Week Two", JLabel.HORIZONTAL);
		lblWeekTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel lblWeekThree = new JLabel("Week Three", JLabel.HORIZONTAL);
		lblWeekThree.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel lblWeekFour = new JLabel("Week Four", JLabel.HORIZONTAL);
		lblWeekFour.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel lblD1 = new JLabel();
		JLabel lblD2 = new JLabel();
		JLabel lblD3 = new JLabel();
		JLabel lblD4 = new JLabel();
		JLabel lblD5 = new JLabel();

		// Add to panel

		gbc.gridx = 0;
		gbc.gridy = yPos;
		gbc.gridwidth = 40;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		gbc.fill = GridBagConstraints.BOTH;

		add(new JLabel(""), gbc);
		gbc.gridx = 5;
		lblWeekOne.setBackground(Color.white);
		add(lblWeekOne, gbc);
		gbc.gridx = 45;
		add(lblWeekTwo, gbc);
		lblWeekTwo.setBackground(Color.white);
		gbc.gridx = 85;
		lblWeekThree.setBackground(Color.white);
		add(lblWeekThree, gbc);
		gbc.gridx = 125;
		lblWeekFour.setBackground(Color.white);
		add(lblWeekFour, gbc);

		gbc.gridy = ++yPos;
		gbc.gridx = 0;
		add(new JLabel(""), gbc);
		gbc.gridwidth = 8;

		// constraints
		int col = 160 + 12; // (5*8)*4 = 4 weeks + 12 offset
		int row = 2; // initial project

		// some function to get amount of projects by id this wioudl be used for
		// rows

		JLabel DayLbl[] = new JLabel[5];

		for (int i = 0; i < 4; i++) {
			gbc.gridx = 5 + (40 * i);
			add(lblD1 = new JLabel("D1", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 13 + (40 * i);
			add(lblD2 = new JLabel("D2", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 21 + (40 * i);
			add(lblD3 = new JLabel("D3", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 29 + (40 * i);
			add(lblD4 = new JLabel("D4", JLabel.HORIZONTAL), gbc);
			gbc.gridx = 37 + (40 * i);
			add(lblD5 = new JLabel("D5", JLabel.HORIZONTAL), gbc);

			DayLbl[0] = lblD1;
			DayLbl[1] = lblD2;
			DayLbl[2] = lblD3;
			DayLbl[3] = lblD4;
			DayLbl[4] = lblD5;

			for (int x = 0; x < 5; x++) {
				DayLbl[x]
						.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				DayLbl[x].setOpaque(true);
				DayLbl[x].setBackground(Color.white);
			}
		}

		gbc.gridy = ++yPos;
		gbc.gridx = 0;
		add(new JLabel(""), gbc);
		gbc.gridwidth = 1;

		JLabel hours[] = new JLabel[8];

		JLabel lblH1, lblH2, lblH3, lblH4, lblH5, lblH6, lblH7, lblH8 = new JLabel();
		days = new JLabel[row][col];

		for (int j = 0; j < days.length; j++) {
			for (int i = 0; i < 20; i++) {

				gbc.gridx = 5 + (8 * i);
				gbc.gridy = j + 2;
				add(lblH1 = new JLabel("9", JLabel.HORIZONTAL), gbc);
				days[j][gbc.gridx] = lblH1;

				gbc.gridx = 6 + (8 * i);
				add(lblH2 = new JLabel("10", JLabel.HORIZONTAL), gbc);
				days[j][gbc.gridx] = lblH2;

				gbc.gridx = 7 + (8 * i);
				add(lblH3 = new JLabel("11", JLabel.HORIZONTAL), gbc);
				days[j][gbc.gridx] = lblH3;

				gbc.gridx = 8 + (8 * i);
				add(lblH4 = new JLabel("12", JLabel.HORIZONTAL), gbc);
				days[j][gbc.gridx] = lblH4;

				gbc.gridx = 9 + (8 * i);
				add(lblH5 = new JLabel("13", JLabel.HORIZONTAL), gbc);
				days[j][gbc.gridx] = lblH5;

				gbc.gridx = 10 + (8 * i);
				add(lblH6 = new JLabel("14", JLabel.HORIZONTAL), gbc);
				days[j][gbc.gridx] = lblH6;

				gbc.gridx = 11 + (8 * i);
				add(lblH7 = new JLabel("15", JLabel.HORIZONTAL), gbc);
				days[j][gbc.gridx] = lblH7;

				gbc.gridx = 12 + (8 * i);
				add(lblH8 = new JLabel("16", JLabel.HORIZONTAL), gbc);
				days[j][gbc.gridx] = lblH8;

				hours[0] = lblH1;
				hours[1] = lblH2;
				hours[2] = lblH3;
				hours[3] = lblH4;
				hours[4] = lblH5;
				hours[5] = lblH6;
				hours[6] = lblH7;
				hours[7] = lblH8;

				for (int x = 0; x < 8; x++) {
					hours[x].setBorder(BorderFactory
							.createLineBorder(Color.BLACK));
					hours[x].setOpaque(true);
					hours[x].setBackground(Color.white);
				}

			}
		}

		testData();

		setVisible(true);

	}

	// Remove when finished Testing
	public void testData() {

		// / ||||||||||||||| Testing ||||||||||| \\\

		// Create StaffDO obj to Test
		HashMap<Integer, Double> skillLevels = new HashMap<Integer, Double>();
		skillLevels.put(1, 5.0);
		HashMap<DateTime, DateTime> holidayDates = new HashMap<DateTime, DateTime>();

		StaffDO staffOne = new StaffDO(1, "Ross", 40, skillLevels, holidayDates);
		new StaffDO(2, "Bob", 40, skillLevels, holidayDates);

		// Create ArrayList of ResultDO to test

		DateTime dateTime = new DateTime(2013, 04, 05, 10, 30, 0);

		ArrayList<Integer> requiredTskIds = new ArrayList<Integer>();
		requiredTskIds.add(1);
		requiredTskIds.add(2);

		DateTime dateStart1 = new DateTime(2013, 04, 05, 10, 30, 0);
		DateTime dateEnd1 = new DateTime(2013, 04, 05, 13, 30, 0);

		DateTime dateStart2 = new DateTime(2013, 04, 06, 14, 30, 0);
		DateTime dateEnd2 = new DateTime(2013, 04, 06, 17, 30, 0);

		TaskDO taskOne = new TaskDO(1, 1, "Task One", 1, 3, 3, "RiskLevel",
				dateTime, "Task Status", requiredTskIds);
		ResultDO resultOne = new ResultDO(1, taskOne, staffOne, dateStart1,
				dateEnd1);

		TaskDO taskTwo = new TaskDO(1, 2, "Task Two", 1, 3, 3, "RiskLevel",
				dateTime, "Task Status", requiredTskIds);
		ResultDO resultTwo = new ResultDO(2, taskTwo, staffOne, dateStart2,
				dateEnd2);

		ArrayList<TaskDO> tasks = new ArrayList<TaskDO>();
		tasks.add(taskOne);
		tasks.add(taskTwo);

		ArrayList<ResultDO> listOfResultsTest1 = new ArrayList<ResultDO>();
		listOfResultsTest1.add(resultOne);
		listOfResultsTest1.add(resultTwo);

		ArrayList<ResultDO> listOfResultsTest2 = new ArrayList<ResultDO>();
		listOfResultsTest2.add(resultOne);
		listOfResultsTest2.add(resultTwo);

		ProjectDO Project1 = new ProjectDO(1, "A", dateEnd1, 0, "Started",
				tasks);
		ProjectDO Project2 = new ProjectDO(2, "B", dateEnd2, 0, "Not Started",
				tasks);

		HashMap<ProjectDO, ArrayList<ResultDO>> test = new HashMap<ProjectDO, ArrayList<ResultDO>>();
		test.put(Project1, listOfResultsTest1);
		test.put(Project2, listOfResultsTest2);

		addData(test);

		// / |||||||||| End of Testing ||||||||||| \\\\

	}

	
		
		
	// HashMap arg = HashMap< Staff Objects for the staffName(or just pass
	// staffName), HashMap< Project Name, Duration>>
	// Could change this to some sort of Result object which contains all this
	// info
	public void addData(HashMap<ProjectDO, ArrayList<ResultDO>> ProjectResults) {

		int xPos = 0;
		for (ProjectDO project : ProjectResults.keySet()) {

			JLabel projectName = new JLabel(project.getProjectName(),
					JLabel.HORIZONTAL);
			projectName.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			ProjectResults.get(project);

			gbc.gridy = yPos++;
			gbc.gridx = xPos;
			gbc.gridwidth = 5;

			add(projectName, gbc);

			xPos = 0;

		}

		xPos += 5;
		gbc.gridx = xPos;

		// Change to Real Project Starting Date
		DateTime projectStartDate = new DateTime(2013, 04, 05, 10, 30, 0);

	}

	public void addController(ActionListener controller) {

	}
}

