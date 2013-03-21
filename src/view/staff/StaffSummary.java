package view.staff;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.CellColour;
import util.DateTime;
import view.MainFrame;
import data.dataObject.ResultDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;
import database.dataAccessObject.HolidaysDao;
import database.dataAccessObject.ResultDao;
import database.dataAccessObject.StaffDao;

/**
 * 
 * gui for staff summary
 * 
 * @author Ross, Dominic
 * 
 */
public class StaffSummary extends JPanel {

	private GridBagConstraints gbc = new GridBagConstraints();
	private int yPos = 1;

	private int dayXPos = 8;
	private DateTime currentDateTime;
	private DateTime projectStartDate = new DateTime(2013, 10, 23, 9, 0, 0);
	private JLabel lblDay;
	private CellColour curColour;
	private Color Headers = new Color(220, 20, 60);
	private Color border = new Color(220, 220, 220);
	private ArrayList<Integer> projectids = new ArrayList<Integer>();
	private MainFrame view;
	private int holidayId = 0;

	private ArrayList<Integer> endXPos = new ArrayList<Integer>();

	public StaffSummary(MainFrame view) {
		this.view = view;

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
		for (int staffId : staffIds) {
			dataToShow.put(staffDB.getStaffById(staffId),
					resultDB.getResultByStaff(staffId));
		}

		try {
			projectStartDate = resultDB.getStartingDateTime();
		} catch (NullPointerException ex) {
		}

		this.currentDateTime = projectStartDate;
		addData(dataToShow, projectStartDate);

		setVisible(true);

	}
	

	/**
	 * 
	 */
	public void addDay() {

		// Add Day
		gbc.gridy = 0;
		gbc.gridwidth = 8;
		gbc.gridx = dayXPos;

		String date = currentDateTime.getDateTime();

		Pattern p = Pattern.compile("(.*) (.*)");
		Matcher m = p.matcher(date);

		while (m.find()) {
			date = m.group(1);
		}

		lblDay = new JLabel(date, JLabel.HORIZONTAL);
		lblDay.setBorder(BorderFactory.createLineBorder(border));
		lblDay.setBackground(Headers);
		lblDay.setForeground(Color.white);
		lblDay.setOpaque(true);
		add(lblDay, gbc);

		// Add Hours of Day

		int hourPos = dayXPos;
		gbc.gridx = hourPos;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		JLabel lblHours;

		for (int i = 9; i < 17; i++) {
			lblHours = new JLabel(Integer.toString(i), JLabel.HORIZONTAL);
			lblHours.setBorder(BorderFactory.createLineBorder(border));
			gbc.gridx = hourPos++;
			lblHours.setBackground(Headers);
			lblHours.setForeground(Color.white);
			lblHours.setOpaque(true);
			add(lblHours, gbc);
		}

	}

	public void addData(
			HashMap<StaffDO, ArrayList<ResultDO>> staffAllocProjects,
			DateTime projectStartDate) {

		int xPos = 0;
		JLabel lblBlank;
		JLabel lblTaskName;

		// Get Each Staff
		for (StaffDO staff : staffAllocProjects.keySet()) {

			JLabel staffName = new JLabel(staff.getStaffName(),
					JLabel.HORIZONTAL);

			staffName.setBorder(BorderFactory.createLineBorder(border));
			staffName.setBackground(Headers);
			staffName.setForeground(Color.white);
			staffName.setOpaque(true);

			// Get List Of Tasks related to each staff
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

			HolidaysDao holidayDao = new HolidaysDao();

			HashMap<DateTime, DateTime> holidays = holidayDao
					.getHolidaysOfStaff(staff.getStaffId());

			for (ResultDO task : listOfTasks) {

				for (DateTime i = task.getStartDateTime(); i.before(task
						.getEndDateTime()); i = DateTime.hourLater(i, 1)) {

					for (DateTime dateTime : taskDate.keySet()) {

						if (dateTime.getDateTime().equals(i.getDateTime())) {
							taskDate.remove(dateTime);
							break;
						}
					}

					taskDate.put(i, task.getTaskDO());

				}
			}

			for (DateTime startDate : holidays.keySet()) {
				if (startDate.before(projectStartDate)) {
					if (projectStartDate.getHour() > 9) {
						projectStartDate = new DateTime(
								projectStartDate.getYear(),
								projectStartDate.getMonth(),
								projectStartDate.getDay(), 9, 0, 0);
					} else {
						continue;
					}
				}
				for (DateTime i = startDate; i.before(holidays.get(startDate)); i = DateTime
						.hourLater(i, 1)) {

					for (DateTime dateTime : taskDate.keySet()) {

						if (dateTime.getDateTime().equals(i.getDateTime())) {
							taskDate.remove(dateTime);
							break;
						}
					}

					int duration = DateTime.duration(startDate,
							holidays.get(startDate));
					taskDate.put(i, new TaskDO(0, holidayId, "H", 0, duration,
							duration, "HIGH", null, "Holiday", null));
				}
			}

			holidayId++;

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

				while (currentDateTime.before(DateTime.hourLater(dateOfTask
						.getDate(), dateOfTask.getTask().getTaskDuration()))) {

					addDay();
					dayXPos += 8;
					currentDateTime = DateTime.nextDay(currentDateTime);
				}

				gbc.gridy = yPos;
				gbc.gridx = xPos;

				if (currentTime.before(dateOfTask.getDate())) {
					int blankLength = DateTime.duration(currentTime,
							dateOfTask.getDate());

					gbc.gridwidth = blankLength;

					lblBlank = new JLabel("");
					lblBlank.setBackground(Color.GRAY);
					lblBlank.setOpaque(true);
					lblBlank.setBorder(BorderFactory.createLineBorder(border));
					add(lblBlank, gbc);
					xPos += blankLength;
					gbc.gridx = xPos;
					currentTime = DateTime.hourLater(currentTime, blankLength);
				}

				int gridWidth = 1;

				TaskDO currentTask = dateOfTask.getTask();

				for (int j = i + 1; j < orderedTaskDate.size(); j++) {

					TaskDate nextDateTask = orderedTaskDate.get(j);

					if (currentTask.getTaskId() == nextDateTask.getTask()
							.getTaskId()
							&& currentTask.getProjectId() == nextDateTask
									.getTask().getProjectId()) {
						gridWidth++;
						i++;
					} else {
						break;
					}

				}

				gbc.gridwidth = gridWidth;
				lblTaskName = new JLabel(dateOfTask.getTask().getTaskName(),
						JLabel.HORIZONTAL);

				for (int j = 0; j < view.getids.size(); j++) {

					if (view.getids.get(j).equals(
							dateOfTask.getTask().getProjectId()) == true) {
						lblTaskName.setBackground(view.initialColour.getColor()
								.get(j));
					}

				}

				lblTaskName.setBorder(BorderFactory.createLineBorder(border));
				lblTaskName.setOpaque(true);

				add(lblTaskName, gbc);
				xPos += gridWidth;
				currentTime = DateTime.hourLater(currentTime, gridWidth);

				gbc.gridx = xPos;

			}

			endXPos.add(xPos);

			xPos = 0;
		}

		addEndBlanks();
	}

	public void addEndBlanks() {

		yPos = 2;
		for (Integer xPos : endXPos) {

			gbc.gridy = yPos;
			if (xPos < dayXPos) {

				gbc.gridx = xPos;

				int blankLength = dayXPos - xPos;
				gbc.gridwidth = blankLength;
				JLabel lblEndBlank = new JLabel("");
				lblEndBlank.setBorder(BorderFactory.createLineBorder(border));
				lblEndBlank.setBackground(Color.GRAY);
				lblEndBlank.setOpaque(true);
				add(lblEndBlank, gbc);
			}

			yPos++;
		}
	}
	/**
	 * 
	 * @param init
	 * 
	 * @return 
	 * 
	 * @
	 */
	public void setCellColour(CellColour init) {
		this.curColour = init;
	}

	public void setIds(ArrayList<Integer> getids) {
		for (int i = 0; i < getids.size(); i++) {
			projectids.add(getids.get(i));
		}
	}
}
