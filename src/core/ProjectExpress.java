package core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controllers.MainController;

import view.Cover;
import view.MainFrame;

/**
 * program entrance
 * 
 * @author Ke CHEN
 * 
 */
public class ProjectExpress {

	static boolean coverStatus = true;
	static Cover iniCover = new Cover();

	public static void main(String[] args) {

		// set look and feel
		try {
			// using Nimbus look and feel
			UIManager.put("control", new Color(245, 245, 245));
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Cannot use Nimbus!");
		}

		// create cover
	
		iniCover.setVisible(true);

	
	}

	public void callMainFrame() {
		iniCover.setVisible(false);
		iniCover.dispose();
		
		MainFrame view = new MainFrame();

		// create controller
		MainController controller = new MainController(view);

		view.setVisible(true);
	}

}
