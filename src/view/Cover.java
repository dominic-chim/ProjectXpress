package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.ProjectExpress;

public class Cover extends JFrame {

	private JPanel CoverP = new JPanel();
	private ProjectExpress initial = new ProjectExpress();

	// Timer timer = new Timer(1000, this);

	public Cover() {
		final ImageIcon image = new ImageIcon("src/projectexpress.jpeg");
		Color backclr = new Color(18, 41, 83);
		CoverP.add(new JLabel(image));
		CoverP.setBackground(backclr);
		CoverP.setVisible(true);
		add(CoverP);
		setSize(600, 600);
		setTitle("Project Express");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				initial.callMainFrame();
			}
		}, 5000);

	}

}
