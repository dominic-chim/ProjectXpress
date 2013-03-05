package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.JFileChooser;

import algorithm.ScheduleAlgorithm;

import data.dataObject.ProjectComparator;
import data.dataObject.ProjectDO;
import data.dataObject.StaffDO;
import database.dataAccessObject.ProjectDao;
import database.dataAccessObject.ResultDao;
import database.dataAccessObject.StaffDao;

import util.DateTime;
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
            ProjectDao projectDB = new ProjectDao();
            sdialog.initJcbxProjects(projectDB.getAllStartedProject());
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
                    ProjectDao projectDB = new ProjectDao();
                    PriorityQueue<ProjectDO> projects = new PriorityQueue<ProjectDO>(5, new ProjectComparator());
                    ArrayList<Integer> projectIds = sdialog.getSelectedProjectIds();
                    for(int projectId : projectIds) {
                        projects.add(projectDB.getProjectById(projectId));
                    }
                    // if want to choose Staff, modify here
                    ArrayList<StaffDO> staffs = (new StaffDao()).getAllStaff();
                    DateTime startingDateTime = new DateTime(sdialog.getStartingDateTime());

                    // TODO change project end date
                    ScheduleAlgorithm algorithm = new ScheduleAlgorithm(startingDateTime, 
                            DateTime.hourLater(startingDateTime, 100), 
                            staffs, projects);

                    ResultDao resultDB = new ResultDao();
                    resultDB.addResults(algorithm.runAlgoritm());



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
