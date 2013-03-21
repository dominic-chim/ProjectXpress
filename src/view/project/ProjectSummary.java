package view.project;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.CellColour;
import util.DateTime;
import view.staff.StaffSummary;
import data.dataObject.ProjectDO;
import data.dataObject.ResultDO;
import database.dataAccessObject.ProjectDao;
import database.dataAccessObject.ResultDao;

/**
 * 
 * gui for project summary
 * 
 * @author Dominic, Ross
 *
 */
public class ProjectSummary extends JPanel {

	private GridBagConstraints gbc = new GridBagConstraints();
	private int yPos = 1;

	private int dayXPos = 8;
	private DateTime currentDateTime;
	private JLabel lblDay;
	private ArrayList<Integer> projectIds = new ArrayList<Integer>();
	private CellColour colourit = new CellColour();
	final Color Headers = new Color(220, 20, 60);
	final Color border = new Color(220, 220, 220);
	final Color cell = new Color(72, 118, 255);

	private int xPos = 0;
	private JLabel lblBlank;
	private JLabel lblStaffNo;
	private DateTime currentTime;
	private DateTime time;
	private HashMap<DateTime, Integer> totalResources = new HashMap<DateTime, Integer>();

	DateTime projectStartDate = new DateTime(2013, 3, 21, 9, 0, 0);

	private ArrayList<Integer> endXPos = new ArrayList<Integer>();

	public ProjectSummary() {

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

		HashMap<ProjectDO, ArrayList<ResultDO>> dataToShow = new HashMap<ProjectDO, ArrayList<ResultDO>>();
		ResultDao resultDB = new ResultDao();
		ProjectDao projectDB = new ProjectDao();

		ArrayList<Integer> projectIds = resultDB
				.getAllProjectInCurrentVersion();

		for (int projectId : projectIds) {
			dataToShow.put(projectDB.getProjectById(projectId),
					resultDB.getResultByProject(projectId));
		}

		try {
			projectStartDate = resultDB.getStartingDateTime();
		} catch (NullPointerException ex) {
		}

		this.currentDateTime = projectStartDate;
		
		addData(dataToShow);

		setVisible(true);

	}

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
			lblHours.setBackground(Headers);
			lblHours.setForeground(Color.white);
			lblHours.setOpaque(true);
			gbc.gridx = hourPos++;

			add(lblHours, gbc);

		}

		dayXPos += 8;
		currentDateTime = DateTime.nextDay(currentDateTime);

	}

	public void addData(HashMap<ProjectDO, ArrayList<ResultDO>> projectResults) {

		HashMap<DateTime, Integer> resources;
		HashMap<ProjectDO, HashMap<DateTime, Integer>> projectResources = new HashMap<ProjectDO, HashMap<DateTime, Integer>>();

		for (ProjectDO project : projectResults.keySet()) {
				
			resources = new HashMap<DateTime, Integer>();

			for (ResultDO result : projectResults.get(project)) {
								
					time = result.getStartDateTime();

					int range = DateTime.duration(result.getStartDateTime(),
							result.getEndDateTime());
					for (int i = range; i > 0; i--) {

						boolean exists = false;
						for (DateTime dt : resources.keySet()) {


							
							if (time.getDateTime().equals(dt.getDateTime())) {
								exists = true;

								resources.put(dt, resources.get(dt) + 1);

								break;
							}
						}

						if (!exists) {

							resources.put(time, 1);
						}

						time = DateTime.hourLater(time, 1);

					}
					 
					HashMap<DateTime, DateTime> holidays = result.getStaffDO().getHolidays();	

					 for(DateTime startDate : holidays.keySet()) {
						 if(!(holidays.get(startDate).before(result.getStartDateTime()) || result.getEndDateTime().before(startDate))) {
							 DateTime s = startDate;
							 while(s.before(holidays.get(startDate))) {
									for (DateTime dt : resources.keySet()) {
								
										if (s.getDateTime().equals(dt.getDateTime())) {
								
											if(resources.get(dt) == 1) {
												resources.remove(dt);
											} else {
												resources.put(dt, resources.get(dt) - 1);
											}
											break;
											
										}
										
									}
									s = DateTime.hourLater(s, 1);
							 }
						 }

					 }	
			
			}
			
			
			projectResources.put(project, resources);
		}
		for (ProjectDO projects : projectResources.keySet()) {
			projectIds.add(projects.getProjectId());
		}

		colourit.colourCell(projectIds);

		for (ProjectDO projects : projectResources.keySet()) {

			JLabel projectName = new JLabel(projects.getProjectName(),
					JLabel.HORIZONTAL);

			projectName.setBackground(Headers);
			projectName.setForeground(Color.white);


			projectName.setBorder(BorderFactory.createLineBorder(border));
			projectName.setOpaque(true);

			// In Order
			HashMap<DateTime, Integer> listOfResources = projectResources
					.get(projects);

			gbc.gridy = ++yPos;
			gbc.gridx = xPos;
			gbc.gridwidth = 8;

			add(projectName, gbc);

			xPos += 8;

			gbc.gridx = xPos;

			currentTime = new DateTime(projectStartDate.getYear(),
					projectStartDate.getMonth(), projectStartDate.getDay(), 9,
					0, 0);

			ArrayList<HashMap<DateTime, Integer>> orderedDateTime = orderHashMap(listOfResources);

			for (HashMap<DateTime, Integer> dateAndResource : orderedDateTime) {

				for (DateTime time : dateAndResource.keySet()) {

					int value = 0;
					for (DateTime checkDate : totalResources.keySet()) {
						if (time.getDateTime().equals(checkDate.getDateTime())) {
							value = totalResources.get(checkDate);
							totalResources.remove(checkDate);
							break;
						}
					}

					totalResources.put(time, dateAndResource.get(time) + value);

					while (currentDateTime.before(DateTime.hourLater(time, 1))) {
						addDay();
					}
					gbc.gridy = yPos;
					gbc.gridx = xPos;

					if (currentTime.before(time)) {
						int blankLength = DateTime.duration(currentTime, time);

						gbc.gridwidth = blankLength;
						lblBlank = new JLabel("");
						lblBlank.setBorder(BorderFactory
								.createLineBorder(border));
						lblBlank.setBackground(Color.GRAY);
						lblBlank.setOpaque(true);
						add(lblBlank, gbc);

						xPos += blankLength;
						gbc.gridx = xPos;
					}

					gbc.gridwidth = 1;

					lblStaffNo = new JLabel(Integer.toString(dateAndResource
							.get(time)), JLabel.HORIZONTAL);
					lblStaffNo
							.setBorder(BorderFactory.createLineBorder(border));
					lblStaffNo.setOpaque(true);

					lblStaffNo.setBackground(cell);
					lblStaffNo.setForeground(Color.white);

					add(lblStaffNo, gbc);

					xPos += 1;

					gbc.gridx = xPos;

					currentTime = DateTime.hourLater(time, 1);
				}

			}

			endXPos.add(xPos);
			xPos = 0;

		}

		addTotalResources(orderHashMap(totalResources));
		addEndBlanks();
	}

	public void addTotalResources(
			ArrayList<HashMap<DateTime, Integer>> totalResources) {

		currentTime = new DateTime(projectStartDate.getYear(),
				projectStartDate.getMonth(), projectStartDate.getDay(), 9, 0, 0);

		xPos = 0;
		gbc.gridy = ++yPos;
		gbc.gridx = xPos;
		gbc.gridwidth = 8;

		JLabel lblTotalResources = new JLabel("Total Resources",
				JLabel.HORIZONTAL);
		lblTotalResources.setBorder(BorderFactory.createLineBorder(border));
		lblTotalResources.setBackground(Headers);
		lblTotalResources.setForeground(Color.white);
		lblTotalResources.setOpaque(true);
		add(lblTotalResources, gbc);

		xPos += 8;

		gbc.gridx = xPos;

		for (HashMap<DateTime, Integer> dateAndResource : totalResources) {
			for (DateTime date : dateAndResource.keySet()) {

				if (currentTime.before(date)) {
					int blankLength = DateTime.duration(currentTime, date);

					gbc.gridwidth = blankLength;
					lblBlank = new JLabel("");
					lblBlank.setBorder(BorderFactory.createLineBorder(border));
					lblBlank.setBackground(Color.GRAY);
					lblBlank.setOpaque(true);
					add(lblBlank, gbc);

					xPos += blankLength;
					gbc.gridx = xPos;

				}

				gbc.gridwidth = 1;

				add(lblStaffNo = new JLabel(Integer.toString(dateAndResource
						.get(date)), JLabel.HORIZONTAL), gbc);
				lblStaffNo.setBorder(BorderFactory.createLineBorder(border));
				lblStaffNo.setOpaque(true);

				lblStaffNo.setBackground(cell);
				lblStaffNo.setForeground(Color.white);

				xPos += 1;

				gbc.gridx = xPos;

				currentTime = DateTime.hourLater(date, 1);
			}

		}

		endXPos.add(xPos);

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

	public ArrayList<HashMap<DateTime, Integer>> orderHashMap(
			HashMap<DateTime, Integer> listOfResources) {

		ArrayList<HashMap<DateTime, Integer>> orderedDateTime = new ArrayList<HashMap<DateTime, Integer>>();
		HashMap<DateTime, Integer> minDate;

		for (int i = listOfResources.size(); i > 0; i--) {
			DateTime min = null;
			for (DateTime t : listOfResources.keySet()) {
				min = t;
				break;
			}

			int noResources = 0;
			for (DateTime time : listOfResources.keySet()) {
				if (time.before(min)) {
					min = time;
				}
			}

			for (DateTime time : listOfResources.keySet()) {

				if (min.getDateTime().equals(time.getDateTime())) {

					noResources = listOfResources.get(time);
					listOfResources.remove(time);
					break;
				}
			}

			minDate = new HashMap<DateTime, Integer>();
			minDate.put(min, noResources);
			orderedDateTime.add(minDate);

		}

		return orderedDateTime;

	}

	public CellColour getColours() {
		return colourit;
	}

	public ArrayList<Integer> getProjectIds() {
		return projectIds;
	}

}