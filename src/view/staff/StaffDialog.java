package view.staff;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import util.DateTime;
import view.MainFrame;
import data.Context;
import data.dataObject.StaffDO;

/**
 * 
 * gui for add staff
 * 
 * @author Ross
 * 
 */
public class StaffDialog extends JDialog {

	HashMap<Integer, String> mapSkills = Context.getSkillMap();
	HashMap<String, Integer> revMapSkills = Context.getSkillRevMap();

	final int textFieldSize = 25;

	// Text Fields for Adding Staff
	JTextField tfId;
	JTextField tfName;
	JTextField tfWeeklyAvail;
	JTextField tfSkillName;
	JTextField tfSkillLevel;
	JTextField tfPrefenceLevel;

	JTextField tfStartDate;
	JTextField tfEndDate;
	JComboBox cbSkillNames;
	JComboBox cbSkillLevels;

	// Buttons
	JButton btnRemoveHoliday = new JButton("Remove Holiday");
	JButton btnAddHoliday = new JButton("Add Holiday");

	JButton btnAddSkill;
	JButton btnRemoveSkill;

	JPanel addStaffPanel;

	DefaultListModel skillListModel;
	DefaultListModel holidayListModel;

	JList skillList;
	JList holidayList;

	JButton addStaff;
	JButton cancel;

	HashMap<Integer, Double> skillLevels = new HashMap<Integer, Double>();
	HashMap<DateTime, DateTime> holidayDates = new HashMap<DateTime, DateTime>();

	boolean checkIfAddStaff = true;

	ArrayList<String> queries = new ArrayList<String>();

	public StaffDialog(MainFrame view, ActionListener controller, StaffDO staff) {

		super(view, true);

		setLayout(new BorderLayout());
		addStaffPanel = new JPanel();

		// Panel and Layout - Panel will be put in OptionPane
		addStaffPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		// Labels and TextFields for input
		JLabel lblId = new JLabel("Id");
		tfId = new JTextField(textFieldSize);

		JLabel lblName = new JLabel("Name");
		tfName = new JTextField(textFieldSize);

		JLabel lblWeeklyAvail = new JLabel("Weekly Available Time:");
		tfWeeklyAvail = new JTextField("40");
		tfWeeklyAvail.setEditable(false);

		JLabel lblSkillName = new JLabel("Skill");

		cbSkillNames = new JComboBox(mapSkills.values().toArray());
		cbSkillNames.addItem("Skill Name");
		cbSkillNames.setSelectedItem("Skill Name");

		JLabel lblSkillLevel = new JLabel("Skill Level:");

		String[] levels = { "1", "0.5" };
		cbSkillLevels = new JComboBox(levels);
		cbSkillLevels.addItem("Skill Level");
		cbSkillLevels.setSelectedItem("Skill Level");
		// tfSkillLevel = new JTextField(20);

		JLabel lblHolidayStart = new JLabel("Holiday Start");
		JLabel lblHolidayEnd = new JLabel("Holiday End");

		JLabel lblPrefenceLevel = new JLabel("Prefence Level:");
		tfPrefenceLevel = new JTextField(textFieldSize);


		tfStartDate = new JTextField(DateTime.dataBaseDateFormat.format(new Date()));
		tfEndDate = new JTextField(DateTime.dataBaseDateFormat.format(new Date()));

		// Holiday list and Buttons
		holidayListModel = new DefaultListModel();

		JLabel lblHolidayList = new JLabel("List of Holidays");
		holidayList = new JList(holidayListModel);

		// Skill List and Buttons

		skillListModel = new DefaultListModel();

		JLabel lblSkillList = new JLabel("List of Skills");
		skillList = new JList(skillListModel);

		btnAddSkill = new JButton("Add Skill");
		btnRemoveSkill = new JButton("Remove Skill");

		// Add Labels and TextFields to Panel
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;

		addStaffPanel.add(lblId, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		addStaffPanel.add(tfId, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblName, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		addStaffPanel.add(tfName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblWeeklyAvail, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		addStaffPanel.add(tfWeeklyAvail, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblSkillName, gbc);
		gbc.gridx = 1;
		addStaffPanel.add(cbSkillNames, gbc);


		gbc.gridwidth = 1;
		gbc.gridx = 2;
		addStaffPanel.add(cbSkillLevels, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		addStaffPanel.add(btnAddSkill, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;

		addStaffPanel.add(lblSkillList, gbc);
		gbc.gridwidth = 2;

		gbc.gridx = 1;
		addStaffPanel.add(skillList, gbc);

		gbc.gridy = 7;
		addStaffPanel.add(btnRemoveSkill, gbc);


		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblHolidayStart, gbc);
		gbc.gridwidth = 2;

		gbc.gridx = 1;
		addStaffPanel.add(tfStartDate, gbc);


		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 9;
		addStaffPanel.add(lblHolidayEnd, gbc);
		gbc.gridwidth = 2;

		gbc.gridx = 1;
		addStaffPanel.add(tfEndDate, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.gridwidth = 2;
		addStaffPanel.add(btnAddHoliday, gbc);

		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 1;

		addStaffPanel.add(lblHolidayList, gbc);
		gbc.gridwidth = 2;

		gbc.gridx = 1;
		addStaffPanel.add(holidayList, gbc);

		gbc.gridy = 12;
		addStaffPanel.add(btnRemoveHoliday, gbc);

		// Scroll Panels
		JPanel scrollPanel = new JPanel(new BorderLayout());
		scrollPanel.add(addStaffPanel);

		JScrollPane scrollPane = new JScrollPane(addStaffPanel);
		scrollPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();

		// If Modify or Add Staff
		if (staff != null) {

			checkIfAddStaff = false;
			addStaffInfo(staff);
			addStaff = new JButton("Update");

		} else {
			checkIfAddStaff = true;
			addStaff = new JButton("Add");
		}

		cancel = new JButton("Cancel");
		buttonPanel.add(addStaff);
		buttonPanel.add(cancel);

		add(addStaffPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setTitle("Add Staff");
		setSize(500, 400);

		addController(controller);

		JScrollPane addStaffScroll = new JScrollPane(addStaffPanel);

		add(addStaffScroll);
		setLocationRelativeTo(view);

	}

	public void addStaffInfo(StaffDO staff) {

		tfId.setText(Integer.toString(staff.getStaffId()));
		tfId.setEditable(false);
		tfName.setText(staff.getStaffName());
		tfWeeklyAvail.setText(Integer.toString(staff
				.getStaffWeeklyAvailableTime()));

		HashMap<Integer, Double> skills = staff.getSkillLevels();

		for (int i : skills.keySet()) {

			skillListModel.addElement(mapSkills.get(i) + " - Level: "
					+ skills.get(i));
			skillLevels.put(i, skills.get(i));

		}

		for (DateTime date : staff.getHolidays().keySet()) {

			holidayListModel.addElement(date.getDateTime() + " to "
					+ staff.getHolidays().get(date).getDateTime());
			holidayDates.put(date, staff.getHolidays().get(date));

		}

	}

	public StaffDO getStaffInput() {

		String[] holidays = new String[holidayListModel.size()];
		holidayListModel.copyInto(holidays);

		String[] skills = new String[skillListModel.size()];
		skillListModel.copyInto(skills);

		StaffDO staffObj = new StaffDO(Integer.parseInt(tfId.getText()),
				tfName.getText(), Integer.parseInt(tfWeeklyAvail.getText()),
				skillLevels, holidayDates);

		return staffObj;

	}

	public boolean checkStaffInput() {

		try {
			Integer.parseInt(tfId.getText());
		} catch (Exception e) {
			inputError("Invalid Id " + tfId.getText());
			return false;
		}

		if (tfName.getText().length() < 1) {

			inputError("Please enter a name");
			return false;
		}
		
		return true;
	}

	public void addSkill() {

		String skillName = (String) cbSkillNames.getSelectedItem();
		if (skillName == "Skill Name") {

			inputError("Please select a Skill Name");
			return;
		}

		String skillLevel = (String) cbSkillLevels.getSelectedItem();

		if (skillLevel == "Skill Level") {

			inputError("Please select a Skill Level");
			return;

		}

		String skill = "";
		skill += skillName;
		skill += " - Level: " + skillLevel;

		if (skillLevels.containsKey(revMapSkills.get(skillName))) {
			inputError("Skill Already Exists");
			return;

		}

		skillListModel.addElement(skill);

		skillLevels.put(revMapSkills.get(skillName),
				Double.parseDouble(skillLevel));

		if (!checkIfAddStaff) {

			String addSkillQuery = "INSERT INTO staff_skill_level VALUES ( "
					+ tfId.getText() + ", " + revMapSkills.get(skillName)
					+ ", " + Double.parseDouble(skillLevel) + " )";

			queries.add(addSkillQuery);

		}

	}

	public void removeSkill() {

		if (skillList.getSelectedValue() == null) {

			inputError("Please select a skill" + tfId.getText());
			return;

		}

		if (!checkIfAddStaff) {

			String skill = skillList.getSelectedValue().toString();
			String skillName = "";

			Pattern p = Pattern.compile("(.*) - Level: (.*)");
			Matcher m = p.matcher(skill);

			while (m.find()) {
				skillName = m.group(1);
			}

			String removeSkillQuery = "DELETE FROM staff_skill_level WHERE staff_id = "
					+ tfId.getText()
					+ " AND skill_id = "
					+ revMapSkills.get(skillName);

			queries.add(removeSkillQuery);



			skillLevels.remove(revMapSkills.get(skillName));

		}

		skillListModel.removeElement(skillList.getSelectedValue());
	}

	public void addHoliday() {

		String dateAtStart = "";
		String endDate = "";
		DateTime startDateTime = null;
		DateTime endDateTime = null;

		try {
			startDateTime = new DateTime(tfStartDate.getText());
			endDateTime = new DateTime(tfEndDate.getText());
		} catch (Exception e) {
			inputError("Invalid Date Format");
			return;

		}
		
		startDateTime = new DateTime(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDay(), startDateTime.getHour(), 0, 0);
		endDateTime = new DateTime(endDateTime.getYear(), endDateTime.getMonth(), endDateTime.getDay(), endDateTime.getHour(), 0, 0);

		// 2013-02-02 00:00:00
		Pattern p = Pattern.compile("(.*)-(.*)-(.*) (.*):(.*):(.*)");
		Matcher m = p.matcher(tfStartDate.getText());

		String year = "", month = "", day = "", hour = "", min = "", sec = "";
		while (m.find()) {
			year = m.group(1);
			month = m.group(2);
			day = m.group(3);
			hour = m.group(4);
			min = m.group(5);
			sec = m.group(6);
		}

		if (year.length() != 4 || month.length() != 2 || day.length() != 2
				|| hour.length() != 2 || min.length() != 2 || sec.length() != 2) {

			inputError("Invalid Date Format");
			return;

		}
		
		m = p.matcher(tfEndDate.getText());

		while (m.find()) {
			year = m.group(1);
			month = m.group(2);
			day = m.group(3);
			hour = m.group(4);
			min = m.group(5);
			sec = m.group(6);
		}
		
		if (year.length() != 4 || month.length() != 2 || day.length() != 2
				|| hour.length() != 2 || min.length() != 2 || sec.length() != 2) {

			inputError("Invalid Date Format");
			return;

		}
		
		if(startDateTime.getHour() < 9 || startDateTime.getHour() > 16 || endDateTime.getHour() < 9 || endDateTime.getHour() > 16) {
			inputError("Holiday Outside Working Hours");
			return;
		}

		if (startDateTime.getDateTime().equals(endDateTime.getDateTime())
				|| endDateTime.before(startDateTime)) {

			inputError("Invalid Holiday Inputted" + tfId.getText());
			return;

		}

		boolean holidayExists = false;

		for (DateTime startDate : holidayDates.keySet()) {

			for (DateTime i = startDate; i.before(holidayDates.get(startDate)); i = DateTime
					.hourLater(i, 1)) {

				for (DateTime j = startDateTime; j.before(endDateTime); j = DateTime
						.hourLater(j, 1)) {

					if (j.getDateTime().equals(i.getDateTime())) {

						inputError("Holiday Already Exists In this Time Range"
								+ tfId.getText());
						return;

					}
				}
			}

		}

		if (!holidayExists) {

			String holiday = startDateTime.getDateTime() + " to "
					+ endDateTime.getDateTime();

			holidayListModel.addElement(holiday);

			holidayDates.put(startDateTime, endDateTime);

			if (!checkIfAddStaff) {

				String addHolidayQuery = "INSERT INTO staff_holidays VALUES ( "
						+ tfId.getText() + ", '" + startDateTime.getDateTime()
						+ "', '" + endDateTime.getDateTime() + "' )";

				queries.add(addHolidayQuery);

			}

		}

	}

	public void removeHoliday() {

		if (holidayList.getSelectedValue() == null) {

			inputError("Please select a holiday" + tfId.getText());
			return;

		}

		String holiday = holidayList.getSelectedValue().toString();
		String startDate = "";
		String endDate = "";

		Pattern p = Pattern.compile("(.*) to (.*)");
		Matcher m = p.matcher(holiday);

		while (m.find()) {
			startDate = m.group(1);
			endDate = m.group(2);
		}

		if (!checkIfAddStaff) {

			String removeHolidayQuery = "DELETE FROM staff_holidays WHERE staff_id = "
					+ tfId.getText()
					+ " AND holiday_start_time = '"
					+ startDate + "'";

			queries.add(removeHolidayQuery);

		}

		DateTime foundDate = null;

		for (DateTime date : holidayDates.keySet()) {
			if (date.getDateTime().equals(startDate)) {
				foundDate = date;
				break;
			}
		}

		holidayDates.remove(foundDate);
		holidayListModel.removeElement(holidayList.getSelectedValue());

	}

	public ArrayList<String> getQueries() {
		return this.queries;
	}

	public void inputError(String errorMessage) {

		JOptionPane.showMessageDialog(this, errorMessage, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public void addController(ActionListener controller) {

		btnAddHoliday.addActionListener(controller);
		btnRemoveHoliday.addActionListener(controller);
		btnAddSkill.addActionListener(controller);
		btnRemoveSkill.addActionListener(controller);

		addStaff.addActionListener(controller);
		cancel.addActionListener(controller);

	}
}