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

import util.CellColour;
import util.DateTime;
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
		ArrayList<Integer> projectIds = new ArrayList<Integer>();

		for (ProjectDO projects : projectResources.keySet()) {
			projectIds.add(projects.getProjectId());
		}
		CellColour colourit = new CellColour();
		colourit.colourCell(projectIds);

		// test
		for (int i = 0; i < colourit.getColor().size(); i++) {
			System.out.println(colourit.getColor().get(i));
		}

		for (ProjectDO projects : projectResources.keySet()) {

			JLabel projectName = new JLabel(projects.getProjectName(),
					JLabel.HORIZONTAL);
			if(colourit.getColor().containsKey(projects.getProjectId())){
				projectName.setBackground(colourit.getColor().get(projects.getProjectId()));
			}

			projectName.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
						add(lblBlank = new JLabel(""), gbc);
						lblBlank.setBorder(BorderFactory
								.createLineBorder(Color.BLACK));
						xPos += blankLength;
						gbc.gridx = xPos;
					}

					gbc.gridwidth = 1;

					add(lblStaffNo = new JLabel(
							Integer.toString(dateAndResource.get(time)),
							JLabel.HORIZONTAL), gbc);
					lblStaffNo.setBorder(BorderFactory
							.createLineBorder(Color.BLACK));

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
							.createLineBorder(Color.BLACK));

				}
			}

			xPos = 0;

		}

	}

	public void addController(ActionListener controller) {

	}
}