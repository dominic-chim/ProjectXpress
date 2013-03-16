package view.staff;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	DateTime projectStartDate;
	JLabel lblDay;

	public StaffSummary() {

		// TODO get projectStartDate
		// DateTime projectStartDate = new DateTime("2013-02-01 10:00:00");
		// this.currentDateTime = projectStartDate;

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

<<<<<<< .mine
		ArrayList<Integer> staffIds = resultDB.getAllStaffInCurrentVersion();
		for (int staffId : staffIds) {
			dataToShow.put(staffDB.getStaffById(staffId),
					resultDB.getResultByStaff(staffId));
		}

		DateTime projectStartDate = resultDB.getStartingDateTime();

		this.currentDateTime = projectStartDate;
		addData(dataToShow, projectStartDate);

=======
		ArrayList<Integer> staffIds = resultDB.getAllStaffInCurrentVersion();
		for (int staffId : staffIds) {
			dataToShow.put(staffDB.getStaffById(staffId),
					resultDB.getResultByStaff(staffId));
		}

		DateTime projectStartDate = resultDB.getStartingDateTime();
		this.currentDateTime = projectStartDate;
		addData(dataToShow, projectStartDate);

>>>>>>> .r256
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
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		JLabel lblHours;

		for (int i = 9; i < 17; i++) {

			lblHours = new JLabel(Integer.toString(i), JLabel.HORIZONTAL);
			lblHours.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			gbc.gridx = hourPos++;

			add(lblHours, gbc);

		}

		dayXPos += 8;
		currentDateTime = DateTime.nextDay(currentDateTime);

	}

	public void addData(
			HashMap<StaffDO, ArrayList<ResultDO>> staffAllocProjects,
			DateTime projectStartDate) {

		int xPos = 0;
		JLabel lblBlank;
		JLabel lblTaskName;

		HashMap<StaffDO, HashMap<DateTime, TaskDO>> staffTasks = new HashMap<StaffDO, HashMap<DateTime, TaskDO>>();

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

			DateTime currentTime = new DateTime(projectStartDate.getYear(),
					projectStartDate.getMonth(), projectStartDate.getDay(), 9,
					0, 0);

			HashMap<DateTime, TaskDO> taskDate = new HashMap<DateTime, TaskDO>();

			for (ResultDO task : listOfTasks) {

				for (DateTime i = task.getStartDateTime(); i.before(task
						.getEndDateTime()); i = DateTime.hourLater(i, 1)) {

					if (staffTasks.get(i) == null) {

<<<<<<< .mine
						taskDate.put(i, task.getTaskDO());

					} else {

						for (DateTime date : taskDate.keySet()) {

							if (i.before(date)) {
								taskDate.put(DateTime.hourLater(date, 1),
										taskDate.get(date));
							}

						}

						taskDate.put(i, task.getTaskDO());

					}
=======
						taskDate.put(i, task.getTaskDO());

					} else {

						for (DateTime date : taskDate.keySet()) {

							if (i.before(date)) {
								taskDate.put(DateTime.hourLater(date, 1),
										taskDate.get(date));
							}

						}

						taskDate.put(i, task.getTaskDO());

					}
				}
			}

			staffTasks.put(staff, taskDate);

			ArrayList<TaskDate> orderedTaskDate = new ArrayList<TaskDate>();

			TaskDate minDate;

			for (int i = taskDate.size(); i > 0; i--) {
				DateTime min = null;

				for (DateTime t : taskDate.keySet()) {
					min = t;
					break;
				}

				TaskDO noResources = null;
				for (DateTime time : taskDate.keySet()) {
					if (time.before(min)) {
						min = time;
					}
				}

				for (DateTime time : taskDate.keySet()) {

					if (min.getDateTime().equals(time.getDateTime())) {

						noResources = taskDate.get(time);
						taskDate.remove(time);
						break;
					}
				}

				minDate = new TaskDate(min, noResources);

				orderedTaskDate.add(minDate);

			}

			for (int i = 0; i < orderedTaskDate.size(); i++) {

				TaskDate dateOfTask = orderedTaskDate.get(i);

				while (currentDateTime.before(DateTime.hourLater(
						dateOfTask.getDate(), 1))) {
					addDay();
>>>>>>> .r256
				}
			}

<<<<<<< .mine
			staffTasks.put(staff, taskDate);

			ArrayList<TaskDate> orderedTaskDate = new ArrayList<TaskDate>();

			TaskDate minDate;

			for (int i = taskDate.size(); i > 0; i--) {
				DateTime min = null;

				for (DateTime t : taskDate.keySet()) {
					min = t;
					break;
				}

				TaskDO noResources = null;
				for (DateTime time : taskDate.keySet()) {
					if (time.before(min)) {
						min = time;
					}
				}

				for (DateTime time : taskDate.keySet()) {

					if (min.getDateTime().equals(time.getDateTime())) {

						noResources = taskDate.get(time);
						taskDate.remove(time);
						break;
					}
				}

				minDate = new TaskDate(min, noResources);

				orderedTaskDate.add(minDate);

			}

			for (int i = 0; i < orderedTaskDate.size(); i++) {

				TaskDate dateOfTask = orderedTaskDate.get(i);
				
				while (currentDateTime.before(DateTime.hourLater(DateTime.hourLater(dateOfTask.getDate(), dateOfTask.getTask().getTaskDuration()), 1))) {
					addDay();
				}

=======
>>>>>>> .r256
				gbc.gridy = yPos;
				gbc.gridx = xPos;

				if (currentTime.before(dateOfTask.getDate())) {
					int blankLength = DateTime.duration(currentTime,
							dateOfTask.getDate());

					gbc.gridwidth = blankLength;
					add(lblBlank = new JLabel(""), gbc);
					lblBlank.setBorder(BorderFactory
							.createLineBorder(Color.BLACK));
					xPos += blankLength;
					gbc.gridx = xPos;
					currentTime = DateTime.hourLater(currentTime, blankLength);
				}

				int gridWidth = 1;

				for (int j = i + 1; j < orderedTaskDate.size(); j++) {

<<<<<<< .mine
					TaskDate nextDateTask = orderedTaskDate.get(j);
=======
					TaskDate nextDateTask = orderedTaskDate.get(j);
					System.out.println(dateOfTask.getTask().getTaskId() + " : "
							+ nextDateTask.getTask().getTaskId());
>>>>>>> .r256

					if (dateOfTask.getTask().getTaskId() == nextDateTask
							.getTask().getTaskId()) {
						gridWidth++;
						i++;
					} else {
						break;
					}
				}

				gbc.gridwidth = gridWidth;
				add(lblTaskName = new JLabel(
						dateOfTask.getTask().getTaskName(), JLabel.HORIZONTAL),
						gbc);
				lblTaskName.setBorder(BorderFactory
						.createLineBorder(Color.BLACK));

				xPos += gridWidth;
				currentTime = DateTime.hourLater(currentTime, gridWidth);

				gbc.gridx = xPos;

			}

			if (currentTime.before(currentDateTime)) {

				int blankLength = DateTime.duration(currentTime,
						currentDateTime);
				gbc.gridwidth = blankLength;
				add(lblBlank = new JLabel(""), gbc);
				lblBlank.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			}

<<<<<<< .mine
			xPos = 0;
=======
		
>>>>>>> .r256
		}
	}
<<<<<<< .mine

=======
>>>>>>> .r256
}
