package view.staff;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class StaffDialog extends JDialog {

	Context context = new Context();
	HashMap<Integer, String> mapSkills = context.getSkillMap();
	HashMap<String, Integer> revMapSkills = context.getSkillRevMap();

	final int textFieldSize = 25;

	// Text Fields for Adding Staff
	JTextField tfId;
	JTextField tfName;
	JTextField tfWeeklyAvail;
	JTextField tfSkillName;
	JTextField tfSkillLevel;
	JTextField tfPrefenceLevel;

	// Holiday Input, start of holiday date until the end of holiday date
	// JComboBox cbMonthStart;
	// JComboBox dayStart;
	// JComboBox monthEnd;
	// JComboBox dayEnd;
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

		// String months[] = { "January", "February", "March", "April", "May",
		// "June", "July", "August", "September", "October", "November",
		// "Decemeber" };
		//
		// cbMonthStart = new JComboBox(months);
		// monthEnd = new JComboBox(months);
		//
		// String days[] = new String[31];
		// for (int i = 1; i <= 31; i++) {
		// days[i - 1] = Integer.toString(i);
		// }
		//
		// dayStart = new JComboBox(days);
		// dayEnd = new JComboBox(days);

		tfStartDate = new JTextField("2013-02-02 00:00:00");
		tfEndDate = new JTextField("2013-02-02 00:00:00");

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

		// gbc.gridx = 0;
		// gbc.gridy = 3;
		// gbc.gridwidth = 1;
		// addStaffPanel.add(lblSkillLevel, gbc);

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

		// gbc.gridwidth = 1;
		// gbc.gridx = 0;
		// gbc.gridy = 5;
		// addStaffPanel.add(lblPrefenceLevel, gbc);
		//
		// gbc.gridx = 1;
		// gbc.gridwidth = 2;
		// addStaffPanel.add(tfPrefenceLevel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblHolidayStart, gbc);
		gbc.gridwidth = 2;

		gbc.gridx = 1;
		addStaffPanel.add(tfStartDate, gbc);
		// gbc.gridx = 2;
		// addStaffPanel.add(dayStart, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 9;
		addStaffPanel.add(lblHolidayEnd, gbc);
		gbc.gridwidth = 2;

		gbc.gridx = 1;
		addStaffPanel.add(tfEndDate, gbc);
		// gbc.gridx = 2;
		// addStaffPanel.add(dayEnd, gbc);
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
		//setVisible(true);
        

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
			JOptionPane.showMessageDialog(this, "Invalid Id " + tfId.getText(),
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (tfName.getText().length() < 1) {

			JOptionPane.showMessageDialog(this, "Please enter a name", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}

		return true;

	}

	public void addSkill() {

		String skillName = (String) cbSkillNames.getSelectedItem();
		if (skillName == "Skill Name") {

			JOptionPane.showMessageDialog(this, "Please select a Skill Name",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String skillLevel = (String) cbSkillLevels.getSelectedItem();

		if (skillLevel == "Skill Level") {

			JOptionPane.showMessageDialog(this, "Please select a Skill Level",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	

		String skill = "";
		skill += skillName;
		skill += " - Level: " + skillLevel;
		
		if(skillLevels.containsKey(revMapSkills.get(skillName))) {
			JOptionPane.showMessageDialog(this, "Skill Already Exists",
					"Error", JOptionPane.ERROR_MESSAGE);
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

			JOptionPane.showMessageDialog(this,
					"Please select a skill" + tfId.getText(), "Error",
					JOptionPane.ERROR_MESSAGE);

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

			for (String i : queries) {
				System.out.println(i);
			}

			skillLevels.remove(revMapSkills.get(skillName));

		}

		skillListModel.removeElement(skillList.getSelectedValue());
	}

	public void addHoliday() {

		String holiday = (String) (tfStartDate.getText() + " to " + tfEndDate
				.getText());
		
		String dateAtStart = "";
		String endDate = "";
	
		
		if (tfStartDate.getText().equals(tfEndDate.getText()) || new DateTime(tfEndDate.getText()).before(new DateTime(tfStartDate.getText()))) {
			JOptionPane.showMessageDialog(this, "Invalid Holiday Inputted"
					+ tfId.getText(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean holidayExists = false;

		for (DateTime startDate : holidayDates.keySet()) {

			for (DateTime i = startDate; i.before(holidayDates.get(startDate)); i = DateTime
					.hourLater(i, 1)) {

				for (DateTime j = new DateTime(tfStartDate.getText()); j
						.before(new DateTime(tfEndDate.getText())); j = DateTime
						.hourLater(j, 1)) {

					if (j.getDateTime().equals(i.getDateTime())) {

						JOptionPane.showMessageDialog(this,
								"Holiday Already Exists In this Time Range"
										+ tfId.getText(), "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}

		}

		if (!holidayExists) {

			holidayListModel.addElement(holiday);

			holidayDates.put(new DateTime(tfStartDate.getText()), new DateTime(
					tfEndDate.getText()));

			if (!checkIfAddStaff) {

				String addHolidayQuery = "INSERT INTO staff_holidays VALUES ( "
						+ tfId.getText() + ", '" + tfStartDate.getText()
						+ "', '" + tfEndDate.getText() + "' )";

				queries.add(addHolidayQuery);

			}

		}

	}

	public void removeHoliday() {

		if (holidayList.getSelectedValue() == null) {

			JOptionPane.showMessageDialog(this, "Please select a holiday"
					+ tfId.getText(), "Error", JOptionPane.ERROR_MESSAGE);

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
		
		JOptionPane.showMessageDialog(this, errorMessage,
				"Error", JOptionPane.ERROR_MESSAGE);
		
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