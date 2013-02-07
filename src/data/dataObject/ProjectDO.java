package data.dataObject;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ProjectDO {

    private int projectId;
    private String projectName;
    private GregorianCalendar projectDueDate;
    private int projectPriority;
    private String projectStatus;
    private ArrayList<TaskDO> tasks;

    public ProjectDO() {
        setProjectId(0);
        setProjectName("");
        setProjectDueDate(null);
        setProjectPriority(0);
        setProjectStatus("");
        setTasks(new ArrayList<TaskDO>());
    }

    public ProjectDO(int projectId, String projectName, 
                    GregorianCalendar projectDueDate,
                    int projectPriority, String projectStatus,
                    ArrayList<TaskDO> tasks) {

        setProjectId(projectId);
        setProjectName(projectName);
        setProjectDueDate(projectDueDate);
        setProjectPriority(projectPriority);
        setProjectStatus(projectStatus);
        setTasks(tasks);
    }

    //getters
	public int getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public GregorianCalendar getProjectDueDate() {
		return projectDueDate;
	}

	public int getProjectPriority() {
		return projectPriority;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public ArrayList<TaskDO> getTasks() {
		return tasks;
	}

    // setters
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setProjectDueDate(GregorianCalendar projectDueDate) {
		this.projectDueDate = projectDueDate;
	}

	public void setProjectPriority(int projectPriority) {
		this.projectPriority = projectPriority;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public void setTasks(ArrayList<TaskDO> tasks) {
		this.tasks = tasks;
	}

    public void addTask(TaskDO task) {
        tasks.add(task);
    }
}
