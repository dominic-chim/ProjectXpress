package view.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import data.dataObject.*;

/**
 * 
 * view class construct the view for project tab
 * 
 * @author bob
 * 
 */
public class ProjectPanel extends JPanel {

    private static final long serialVersionUID = -2335250764236170843L;

    // left panel
    ProjectList projectList = new ProjectList();

    // right tabbed pane
    private JTabbedPane rightTabs = new JTabbedPane();

    // main split pane
    private JSplitPane mainContainer = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT, projectList, rightTabs);

    public ProjectPanel() {

        // TODO change these tabs to customized ones
        JScrollPane projectSummary = new JScrollPane( new ProjectSummary());
        rightTabs.addTab("Summary of Projects", projectSummary);

        rightTabs.addTab("Project Allocation", new ProjectAllocation());
        rightTabs.setPreferredSize(new Dimension(600, 600));

        setLayout(new BorderLayout());
        add(mainContainer, BorderLayout.CENTER);
    }

    public ProjectList getProjectList() {
        return projectList;
    }

    public JTabbedPane getRightTabs() {
        return rightTabs;
    }

}
