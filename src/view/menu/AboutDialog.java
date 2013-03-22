package view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import view.MainFrame;

public class AboutDialog extends JDialog {

	final String[] aboutinfo = {
			"Project Name : ProjectExpress",
			"Version: 1.0",
			"Dominic Chim",
			"Ross Jarvis",
			"Ke Chen (Bob)",
			"Samy Driss",
			"Hongyang Lin (david)",
			"Supervisor: Mohammad Mesgapour",
			"we do not own the copyright for all the material",
			"inlcuded in this project, nor do we claim the said material as our own",
			"we like to thank our supervisor and",
			"everyone that contributed to this project.", "Thank You !!" };
	final ImageIcon image = new ImageIcon("img/projectexpress.jpeg");
	Color backclr = new Color(18, 41, 83);

	public AboutDialog(MainFrame view) {
		super(view, true);
		setLayout(new BorderLayout());
		JPanel JPabout = new JPanel();
		JPabout.setLayout(new GridBagLayout());
		JTextArea jtAbout = new JTextArea();
		GridBagConstraints gbc = new GridBagConstraints();

		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.ipadx = 20;
		gbc.ipady = 20;

		JPabout.add(new JLabel(image), gbc);

		gbc.gridy = 1;
		for (int i = 0; i < aboutinfo.length; i++) {
			jtAbout.append(aboutinfo[i]);
			jtAbout.append("\n");

		}

		jtAbout.setBorder(null);
		jtAbout.setBackground(backclr);
		jtAbout.setForeground(Color.white);
		jtAbout.setOpaque(true);
		jtAbout.setEditable(false);

		JPabout.add(jtAbout, gbc);
		JPabout.setBackground(backclr);

		add(JPabout);
		setTitle("About The Project");
		setSize(500, 650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(view);
		setVisible(true);

	}
}
