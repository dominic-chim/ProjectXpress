package view.menu;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import view.MainFrame;
import controllers.menu.SkillController;
import data.Context;
import data.dataObject.SkillDO;

public class SkillDialog extends JDialog {

	// Context context;
	 HashMap<Integer, String> skills = Context.getSkillMap();
	 HashMap<String, Integer> revSkills = Context.getSkillRevMap();

//	HashMap<Integer, String> skills = new HashMap<Integer, String>();

	// SkillDialog
	JButton btnAddSkill = new JButton("Add Skill");
	JButton btnModifySkill = new JButton("Modify Skill");
	JButton btnRemoveSkill = new JButton("Remove Skill");
	DefaultListModel skillListModel;
	JList skillList;

	// Add Dialog
	JDialog addSkillDialog;
	JButton btnAdd = new JButton("Save Skill");
	JButton btnCancel = new JButton("Cancel Add");
	JLabel lblSkillInput = new JLabel("Enter New Skill", JLabel.HORIZONTAL);
	JTextField tfSkillInput = new JTextField();

	// Modify Dialog
	JDialog modifySkillDialog;
	JButton btnModify = new JButton("Save changes");
	JButton btnModifyCancel = new JButton("Cancel Modify");
	JLabel lblModifySkillInput = new JLabel("Enter Modified Skill",
			JLabel.HORIZONTAL);
	JTextField tfModifySkillInput = new JTextField();

	MainFrame view;

	GridBagConstraints gbc = new GridBagConstraints();

	@SuppressWarnings("unchecked")
	public SkillDialog(MainFrame view, SkillController controller) {

		super(view, true);

		this.view = view;
		setLayout(new BorderLayout());

		// Define Skill List
		skillListModel = new DefaultListModel();
		skillList = new JList(skillListModel);

		for (String i : skills.values()) {
			skillListModel.addElement(i);
		}

		// Button Panel
		JPanel skillPanel = new JPanel(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.ipadx = 20;
		gbc.ipady = 20;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;

        JScrollPane skillListScrollPane = new JScrollPane(skillList);

		skillPanel.add(skillListScrollPane, gbc);
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		skillPanel.add(btnRemoveSkill, gbc);
		gbc.gridx = 1;
		skillPanel.add(btnModifySkill, gbc);
		gbc.gridx = 2;
		skillPanel.add(btnAddSkill, gbc);
		skillList.setSelectedIndex(0);

		addController(controller);

		// Add Panels to Dialog

		add(skillPanel, BorderLayout.CENTER);
		// Set Dialog Settings

	}

	public void createSkillDialog() {
		setTitle("Add Skill");
		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void addSkillDialog() {

		addSkillDialog = new JDialog(view, "Add Skill", true);

		tfSkillInput.setHorizontalAlignment(JTextField.CENTER);

		addSkillDialog.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.ipadx = 20;
		gbc.ipady = 20;
		gbc.gridwidth = 2;

		addSkillDialog.add(lblSkillInput, gbc);
		gbc.gridy = 1;
		addSkillDialog.add(tfSkillInput, gbc);
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		addSkillDialog.add(btnCancel, gbc);
		gbc.gridx = 1;
		addSkillDialog.add(btnAdd, gbc);

		addSkillDialog.setSize(300, 125);
		addSkillDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addSkillDialog.setLocationRelativeTo(null);
		addSkillDialog.setVisible(true);

	}

	public void modifySkillDialog() {

		if (skillList.getSelectedValue() != null) {

			modifySkillDialog = new JDialog(view, "Modify Skill", true);

			tfModifySkillInput.setHorizontalAlignment(JTextField.CENTER);
			tfModifySkillInput.setText((String) skillList.getSelectedValue());
			
			modifySkillDialog.setLayout(new GridBagLayout());
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.gridwidth = 2;

			modifySkillDialog.add(lblModifySkillInput, gbc);
			gbc.gridy = 1;
			modifySkillDialog.add(tfModifySkillInput, gbc);
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			modifySkillDialog.add(btnModifyCancel, gbc);
			gbc.gridx = 1;
			modifySkillDialog.add(btnModify, gbc);

			modifySkillDialog.setSize(300, 125);
			modifySkillDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			modifySkillDialog.setLocationRelativeTo(null);
			modifySkillDialog.setVisible(true);

		} else {
			
			JOptionPane.showMessageDialog(view, "No Skill Selected");
			
		}

	}

	public void removeSkillDialog() {

		String selected = (String) skillList.getSelectedValue();

		if (selected != null) {
			skillListModel.removeElement(selected);
			JOptionPane.showMessageDialog(view, "Skill '" + selected
					+ "' Was Succesfully Removed");

		} else {
			JOptionPane.showMessageDialog(view, "No Skill Selected");
		}

	}

	public SkillDO addModifiedSkillToList() {
		
		String modifiedSkill = tfModifySkillInput.getText();		
		int id = revSkills.get(skillList.getSelectedValue());

		skillListModel.setElementAt(modifiedSkill, skillList.getSelectedIndex());
		
		return new SkillDO(id, modifiedSkill);
		
	}
	
	public String addNewSkillToList() {

		String skillInput = tfSkillInput.getText();
		skillListModel.addElement(skillInput);

		tfSkillInput.setText("");
		
		return skillInput;
	}

	public JDialog getAddSkillDialog() {
		return this.addSkillDialog;
	}

	public JDialog getModifySkillDialog() {
		return this.modifySkillDialog;
	}

	public void addController(ActionListener listener) {

		btnAddSkill.addActionListener(listener);
		btnModifySkill.addActionListener(listener);
		btnRemoveSkill.addActionListener(listener);
		btnAdd.addActionListener(listener);
		btnCancel.addActionListener(listener);
		btnModify.addActionListener(listener);
		btnModifyCancel.addActionListener(listener);

	}

}