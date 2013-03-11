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
import data.dataObject.TaskDO;
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

        switch (cmd) {

        case "Skills":

            view.addSkillDialog(skillController);

            break;
        case "Scheduling":
            SchedulingDialog sdialog = view.addSchedulingDialog();
            sdialog.addControllers(new SchedulingController(sdialog));
            ProjectDao projectDB = new ProjectDao();
            StaffDao staffDB = new StaffDao();
            sdialog.initJcbxProjectsAndStaffs(projectDB.getAllStartedProject(), staffDB.getAllStaff());
            sdialog.setVisible(true);
            break;

        default:
            break;
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

            switch (cmd) {
            case "schedule":
                ProjectDao projectDB = new ProjectDao();
                PriorityQueue<ProjectDO> projects = new PriorityQueue<ProjectDO>(
                        5, new ProjectComparator());
                ArrayList<Integer> projectIds = sdialog.getSelectedProjectIds();
                for (int projectId : projectIds) {
                    projects.add(projectDB.getProjectById(projectId));
                }
                // if want to choose Staff, modify here
                ArrayList<StaffDO> staffs = (new StaffDao()).getAllStaff();
                DateTime startingDateTime = new DateTime(
                        sdialog.getStartingDateTime());

                // estimate a duration of all project
                int totalHour = 0;
                for (ProjectDO project : projects) {
                    for (TaskDO task : project.getTasks()) {
                        totalHour += task.getTaskDuration();
                    }
                }
                totalHour = totalHour / staffs.size() * 5;
                // if the duration is not enough double the duration and try again
                boolean success = false;
                while(!success) {
                    try {
                        ScheduleAlgorithm algorithm = new ScheduleAlgorithm(startingDateTime, 
                                DateTime.hourLater(startingDateTime, totalHour), 
                                staffs, projects);
                        ResultDao resultDB = new ResultDao();
                        resultDB.addResults(algorithm.runAlgoritm());
                        success = true;
                    } catch(ArrayIndexOutOfBoundsException exception) {
                        success = false;
                        totalHour *= 2;
                    }
                }

                sdialog.dispose();

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
