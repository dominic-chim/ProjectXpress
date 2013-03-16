package view.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import view.project.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class UserManualDialog extends JDialog {

	private JPanel jpnlTopContainer = new JPanel();
	private JPanel jpnlCenterContainer = new JPanel();
	private JPanel jpnlBottomContainer = new JPanel();
	private ProjectPanel projectpnl = new ProjectPanel();
	
    private JButton jbtnCancel = new JButton("Cancel");
    
    private JTabbedPane jtbpTabPane = new JTabbedPane();
    
	public UserManualDialog(JFrame parent) {
		
		super(parent, true);
		
		setLayout(new BorderLayout());

		jtbpTabPane.addTab("How to get started", projectpnl);

		jpnlTopContainer.add(jtbpTabPane);
		
		jpnlCenterContainer.add(jbtnCancel);
		
		
		// container settings
		// jpnlCenterContainer.setPreferredSize(new Dimension(300, 50));
		add(jpnlTopContainer, BorderLayout.NORTH);
		add(new JScrollPane(jpnlCenterContainer), BorderLayout.CENTER);
		add(jpnlBottomContainer, BorderLayout.SOUTH);
		// dialog settings
		pack();
		setTitle("User Manual");
		setLocationRelativeTo(parent);
		setVisible(true);

	}

	public void addControllers(ActionListener controller) {

	}

}
