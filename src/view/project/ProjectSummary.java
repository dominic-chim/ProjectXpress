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
import javax.swing.JPanel;

import util.CellColour;
import util.DateTime;
import view.staff.StaffSummary;
import data.dataObject.ProjectDO;
import data.dataObject.ResultDO;
import database.dataAccessObject.ProjectDao;
import database.dataAccessObject.ResultDao;

public class ProjectSummary extends JPanel {

	private GridBagConstraints gbc = new GridBagConstraints();
	private int yPos = 1;

	// TODO add error checks if task start date is greater than time scale set
	// on table

	private int dayXPos = 8;
	private DateTime currentDateTime;
	private JLabel lblDay;
	private ArrayList<Integer> projectIds = new ArrayList<Integer>();
	private CellColour colourit = new CellColour();
	final Color Headers = new Color(220,20,60);
	final Color border = new Color(220,220,220);
	final Color cell = new Color(72,118,255);


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

		DateTime projectStartDate = resultDB.getStartingDateTime();
		// DateTime projectStartDate = new DateTime(2013,2,1,9,0,0);

		this.currentDateTime = projectStartDate;
		addData(dataToShow, projectStartDate);

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

	public void addData(HashMap<ProjectDO, ArrayList<ResultDO>> projectResults,
			DateTime projectStartDate) {

		HashMap<DateTime, Integer> resources;
		HashMap<ProjectDO, HashMap<DateTime, Integer>> projectResources = new HashMap<ProjectDO, HashMap<DateTime, Integer>>();

		for (ProjectDO project : projectResults.keySet()) {

			resources = new HashMap<DateTime, Integer>();

			for (ResultDO result : projectResults.get(project)) {

				DateTime time = result.getStartDateTime();

				for (int i = DateTime.duration(result.getStartDateTime(),
						result.getEndDateTime()); i > 0; i--) {

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

			}
			projectResources.put(project, resources);
		}

		int xPos = 0;
		JLabel lblBlank;
		JLabel lblStaffNo;

		for (ProjectDO projects : projectResources.keySet()) {
			projectIds.add(projects.getProjectId());
		}

		colourit.colourCell(projectIds);

		// test
		for (int i = 0; i < colourit.getColor().size(); i++) {
			System.out.println(colourit.getColor().get(i));
		}

		for (ProjectDO projects : projectResources.keySet()) {

			JLabel projectName = new JLabel(projects.getProjectName(),
					JLabel.HORIZONTAL);
			
			projectName.setBackground(Headers);
			projectName.setForeground(Color.white);
			
			//set headers to random generated colour
			/*for (int i = 0; i < projectIds.size(); i++) {
				if (projectIds.get(i).equals(projects.getProjectId()) == true) {
					projectName.setBackground(colourit.getColor().get(i));
				}
			}*/

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

			DateTime currentTime = new DateTime(projectStartDate.getYear(),
					projectStartDate.getMonth(), projectStartDate.getDay(), 9,
					0, 0);

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

			for (HashMap<DateTime, Integer> dateAndResource : orderedDateTime) {

				for (DateTime time : dateAndResource.keySet()) {

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
						add(lblBlank,gbc);
						
						xPos += blankLength;
						gbc.gridx = xPos;
					}

					gbc.gridwidth = 1;

					add(lblStaffNo = new JLabel(
							Integer.toString(dateAndResource.get(time)),
							JLabel.HORIZONTAL), gbc);
					lblStaffNo.setBorder(BorderFactory
							.createLineBorder(border));
					lblStaffNo.setOpaque(true);
					
					//assign matching random colour as seen in row header
					/*for (int i = 0; i < projectIds.size(); i++) {
						if (projectIds.get(i).equals(projects.getProjectId()) == true) {
							lblStaffNo
									.setBackground(colourit.getColor().get(i));
						}
					}*/
					
					lblStaffNo.setBackground(cell);
					lblStaffNo.setForeground(Color.white);
					

					xPos += 1;

					gbc.gridx = xPos;

					currentTime = DateTime.hourLater(time, 1);
				}

				if (currentTime.before(currentDateTime)) {

					int blankLength = DateTime.duration(currentTime,
							currentDateTime);
					gbc.gridwidth = blankLength;
					add(lblBlank = new JLabel(""), gbc);
					lblBlank.setBorder(BorderFactory
							.createLineBorder(border));

				}
			}

			xPos = 0;

		}

	}
	
	
	public  CellColour getColours(){
		return colourit;
		return colourit;
	}

	public ArrayList<Integer> getProjectIds() {
		return projectIds;
	}

	public void addController(ActionListener controller) {

	}
}