package view;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.ProjectExpress;

public class Cover extends Window {

	private JPanel CoverP = new JPanel();
	private ProjectExpress initial = new ProjectExpress();

	// Timer timer = new Timer(1000, this);

	public Cover() {
		super(null); // creates a window with no Frame as owner
		setBounds(100, 100, 656, 530);
		setVisible(true);

		final ImageIcon image = new ImageIcon("src/projectexpress.jpeg");
		Color backclr = new Color(18, 41, 83);
		CoverP.add(new JLabel(image));
		CoverP.setBackground(backclr);
		CoverP.setVisible(true);
		add(CoverP);
		setSize(600, 600);
		setName("Project Express");
		pack();
		setLocationRelativeTo(null);

		// timer settings
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				initial.callMainFrame();
			}
		}, 3000);

	}

}
