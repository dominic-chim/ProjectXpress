package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

import util.DateTime;
import view.MainFrame;
import view.menu.RiskDialog;
import view.menu.SchedulingDialog;
import view.menu.UserManualDialog;
import algorithm.ScheduleAlgorithm;
import data.Context;
import data.dataObject.ProjectComparator;
import data.dataObject.ProjectDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;
import database.dataAccessObject.ProjectDao;
import database.dataAccessObject.ResultDao;
import database.dataAccessObject.RiskDao;
import database.dataAccessObject.StaffDao;

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

        case "Risk":
            RiskDialog riskDialog = new RiskDialog(view);
            riskDialog.addController(new RiskDialogBtnListener(riskDialog));
            riskDialog.setVisible(true);

            break;
        case "Scheduling":
            SchedulingDialog sdialog = view.addSchedulingDialog();
            sdialog.addControllers(new SchedulingController(sdialog));
            ProjectDao projectDB = new ProjectDao();
            StaffDao staffDB = new StaffDao();
            sdialog.initJcbxProjectsAndStaffs(projectDB.getAllStartedProject(),
                    staffDB.getAllStaff());
            sdialog.setVisible(true);
            break;

        case "User Manual":

            UserManualDialog userManualDialog = view.addUserManualDialog();
            userManualDialog.addControllers(new UserManualController(userManualDialog));

            break;

        default:
            break;
        }

    }

    /**
     * controller for Buttons in RiskDialog
     */
    class RiskDialogBtnListener implements ActionListener {

        private RiskDialog rDialog;

        public RiskDialogBtnListener(RiskDialog rDialog) {
            this.rDialog = rDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "Update":
                    RiskDao riskDB = new RiskDao();
                    try {
                        HashMap<String, Integer> userInput = rDialog.getInputMap();
                        for(String riskName : userInput.keySet()) {
                            riskDB.updateRiskLevel(riskName, userInput.get(riskName));
                        }
                        Context.updateRisk();
                        rDialog.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rDialog, 
                                ex.getMessage(), 
                                "Invalid Input", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "Cancel":
                    rDialog.dispose();
                    break;
                default:
                    break;
            }

        }
    }

    class UserManualController implements ActionListener {

        private UserManualDialog userManualDialog;

        public UserManualController(UserManualDialog userManualDialog) {

            this.userManualDialog = userManualDialog;

        }

        public void actionPerformed(ActionEvent e) {

            String cmd = e.getActionCommand();

            switch (cmd) {
            }

        }

    }

    /**
     * Scheduling Dialog listener
     */
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

                // if the duration is not enough double the duration and try
                // again
                boolean success = false;
                int totalHour = 0;
                while (!success) {
                    ProjectDao projectDB = new ProjectDao();
                    PriorityQueue<ProjectDO> projects = new PriorityQueue<ProjectDO>(
                            5, new ProjectComparator());

                    ArrayList<Integer> projectIds = sdialog.getSelectedProjectIds();
                    for (int projectId : projectIds) {
                        projects.add(projectDB.getProjectById(projectId));
                    }
                    ArrayList<StaffDO> staffs = new ArrayList<StaffDO>();
                    for (int staffId : sdialog.getSelectedStaffIds()) {
                        staffs.add((new StaffDao()).getStaffById(staffId));
                    }

                    DateTime startingDateTime = null;
                    try {
                        startingDateTime = new DateTime(sdialog.getStartingDateTime());
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(sdialog, 
                                "DateTime format error, please insert (yyyy-mm-dd hh:mm:ss)", 
                                "Invalid Input", 
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // check if the input projects and staffs can have a valid result 
                    if(staffs.size() == 0) {
                        JOptionPane.showMessageDialog(sdialog, 
                                "Staff list cannot be empty", "Invalid Input", 
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if(projects.size() == 0) {
                        JOptionPane.showMessageDialog(sdialog, 
                                "Project list cannot be empty", "Invalid Input", 
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        HashSet<Integer> skillSet = new HashSet<Integer>();
                        // add all required skills to a set
                        for(ProjectDO project : projects) {
                            for(TaskDO task : project.getTasks()) {
                                skillSet.add(task.getTaskRequiredSkill());
                            }
                        }
                        // remove skill in the set if a staff have that skill
                        for(StaffDO staff : staffs) {
                            for (int skillId : staff.getSkillLevels().keySet()) {
                                // using property of set to remove exists skill
                                skillSet.add(skillId);
                                skillSet.remove(skillId);
                            }
                        }
                        if(!skillSet.isEmpty()) {
                            JOptionPane.showMessageDialog(sdialog, 
                                    "Staffs do not have enough skill", "Invalid Input", 
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }


                    }

                    // estimate a duration of all project
                    if (totalHour == 0) {
                        for (ProjectDO project : projects) {
                            for (TaskDO task : project.getTasks()) {
                                totalHour += task.getTaskDuration();
                            }
                        }
                        totalHour = totalHour / staffs.size() * 5;
                    }

                    try {
                        ScheduleAlgorithm algorithm = new ScheduleAlgorithm(
                                startingDateTime, DateTime.hourLater(
                                        startingDateTime, totalHour), staffs,
                                projects);
                        ResultDao resultDB = new ResultDao();
                        resultDB.addResults(algorithm.runAlgoritm());
                        success = true;
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        success = false;
                        totalHour *= 2;
                    } 
                }

                // refresh gui
                view.refresh();
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
