package view.staff;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import data.dataObject.StaffDO;

import view.MainFrame;

public class StaffDialog extends JDialog {

	// Text Fields for Adding Staff
	JTextField tfId;
	JTextField tfName;
	JTextField tfWeeklyAvail;
	JTextField tfSkillName;
	JTextField tfSkillLevel;
	JTextField tfPrefenceLevel;

	// Holiday Input, start of holiday date until the end of holiday date
	JComboBox monthStart;
	JComboBox dayStart;
	JComboBox monthEnd;
	JComboBox dayEnd;

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

	public StaffDialog(MainFrame view, ActionListener controller, StaffDO staff) {

		setLayout(new BorderLayout());

		addStaffPanel = new JPanel();

		// Panel and Layout - Panel will be put in OptionPane
		addStaffPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		// Labels and TextFields for input
		JLabel lblId = new JLabel("Id");
		tfId = new JTextField(20);

		JLabel lblName = new JLabel("Name");
		tfName = new JTextField(20);

		JLabel lblWeeklyAvail = new JLabel("Weekly Available Time:");
		tfWeeklyAvail = new JTextField(20);

		JLabel lblSkillName = new JLabel("Skill Name:");
		tfSkillName = new JTextField(20);

		JLabel lblSkillLevel = new JLabel("Skill Level:");
		tfSkillLevel = new JTextField(20);

		JLabel lblHolidayStart = new JLabel("Holiday Start");
		JLabel lblHolidayEnd = new JLabel("Holiday End");

		JLabel lblPrefenceLevel = new JLabel("Prefence Level:");
		tfPrefenceLevel = new JTextField(20);

		String months[] = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"Decemeber" };

		monthStart = new JComboBox(months);
		monthEnd = new JComboBox(months);

		String days[] = new String[31];
		for (int i = 1; i <= 31; i++) {
			days[i - 1] = Integer.toString(i);
		}

		dayStart = new JComboBox(days);
		dayEnd = new JComboBox(days);

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
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		addStaffPanel.add(tfSkillName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblSkillLevel, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		addStaffPanel.add(tfSkillLevel, gbc);

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
		gbc.gridx = 1;
		addStaffPanel.add(monthStart, gbc);
		gbc.gridx = 2;
		addStaffPanel.add(dayStart, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		addStaffPanel.add(lblHolidayEnd, gbc);
		gbc.gridx = 1;
		addStaffPanel.add(monthEnd, gbc);
		gbc.gridx = 2;
		addStaffPanel.add(dayEnd, gbc);

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

		//If Modify or Add Staff
		if (staff != null) {

			
			addStaffInfo(staff);
			addStaff = new JButton("Update");

		} else {
			
			addStaff = new JButton("Add");
		}

		cancel = new JButton("Cancel");
		buttonPanel.add(addStaff);
		buttonPanel.add(cancel);

		add(addStaffPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setTitle("Add Staff");
		setSize(400, 425);

		addController(controller);
		add(addStaffPanel);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	public void addStaffInfo(StaffDO staff) {

		tfId.setText(Integer.toString(staff.getStaffId()));
		tfName.setText(staff.getStaffName());
		tfWeeklyAvail.setText(Integer.toString(staff
				.getStaffWeeklyAvailableTime()));

		for (String i : staff.getSkills()) {

			skillListModel.addElement(i);

		}

		for (String i : staff.getHolidays()) {

			holidayListModel.addElement(i);

		}
	}

	public StaffDO getStaffInput() {

		String[] holidays = new String[holidayListModel.size()];
		holidayListModel.copyInto(holidays);

		String[] skills = new String[skillListModel.size()];
		skillListModel.copyInto(skills);

		StaffDO staffObj = new StaffDO(Integer.parseInt(tfId.getText()),
				tfName.getText(), Integer.parseInt(tfWeeklyAvail.getText()),
				skills, holidays);

		// HashMap<String, String> staffInput = new HashMap<String, String>();
		//
		// staffInput.put("Id", tfId.getText());
		// staffInput.put("Name", tfName.getText());
		// staffInput.put("WeeklyAvail", tfWeeklyAvail.getText());

		// String[] holidays = new String[holidayListModel.size()];
		// holidayListModel.copyInto(holidays);
		//
		// int j = 1;
		// for(String i : holidays) {
		// staffInput.put("Holiday " + j, i);
		// j++;
		// }
		//
		// String[] skills = new String[skillListModel.size()];
		// skillListModel.copyInto(skills);
		//
		// j = 1;
		// for(String i : skills) {
		// staffInput.put("Skill " + j, i);
		// j++;
		// }
		// staffInput.put("PrefenceLevel", tfPrefenceLevel.getText());

		return staffObj;

	}

	public void addSkill() {

		String skill = "";
		skill += tfSkillName.getText();
		skill += " - Level: " + tfSkillLevel.getText();
		skillListModel.addElement(skill);
	}

	public void removeSkill() {

		skillListModel.removeElement(skillList.getSelectedValue());

	}

	public void addHoliday() {

		String holiday = (String) (monthStart.getSelectedItem() + " "
				+ dayStart.getSelectedItem() + " to "
				+ monthEnd.getSelectedItem() + " " + dayEnd.getSelectedItem());

		holidayListModel.addElement(holiday);

	}

	public void removeHoliday() {

		holidayListModel.removeElement(holidayList.getSelectedValue());

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