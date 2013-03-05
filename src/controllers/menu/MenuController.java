package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import view.MainFrame;
import view.menu.MainMenuBar;
import view.menu.SchedulingDialog;

public class MenuController implements ActionListener {
	
	private MainFrame view;
	private SkillController skillController;

	public MenuController(MainFrame view, SkillController skillController) {

		this.skillController = skillController;
		this.view = view;
		view.getMainMenuBar().addControllers(this);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		final JFileChooser jfc = new JFileChooser();


		switch (cmd) {
		case "New":
			// reset everything -- does meant current data is deleted
			break;
		case "Open":
			// open file in w/e format we choose
			break;
		case "Save":
			// save current data to a format
			break;
		case "Close":
			System.exit(0);
			break;

		case "Skills":
			
			view.addSkillDialog(skillController);
			
			break;
		case "Scheduling":
			SchedulingDialog sdialog = view.addSchedulingDialog();
			sdialog.addControllers(new SchedulingController(sdialog));
            sdialog.setVisible(true);
			break;

		default:
			System.out.println("Invalid Option");
		}
		

	}
	
	
	class SchedulingController implements ActionListener {

        private SchedulingDialog sdialog;

        public SchedulingController(SchedulingDialog sdialog) {
            this.sdialog = sdialog;
        }

		@Override
		public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            switch(cmd) {
                case "schedule":
                    sdialog.tt();
                    break;
                case "cancel":
                    sdialog.dispose();
                    break;
                default:
                    break;
            }
			
		}
		
	}

}
