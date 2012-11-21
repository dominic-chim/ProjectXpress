package data.dataObject.test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import data.dataObject.ProjectDO;
import data.dataObject.TaskDO;
import test.GroupTestCase;

public class TestProjectDO extends GroupTestCase{
	
	private ProjectDO pdo;
	
	public TestProjectDO(String name) {
		super(name);
	}
	
	protected void setUp() {
		pdo = new ProjectDO(0, null, null, 0, null, null);
	}
	
	public void testSetGetProjectId() {
		pdo.setProjectId(1);
		assertEquals(1, pdo.getProjectId());
	}
	
	public void testSetGetProjectName() {
		pdo.setProjectName("place");
		assertEquals("place", pdo.getProjectName());
	}
	
	public void testSetGetProjectDueDate() {
		pdo.setProjectDueDate(new GregorianCalendar(1992,5,12,12,0,0));
		assertEquals(new GregorianCalendar(1992,5,12,12,0,0), pdo.getProjectDueDate());
	}
	
	public void testSetGetProjectPriority(){
		pdo.setProjectPriority(2);
		assertEquals(2, pdo.getProjectPriority());
	}
	
	public void testSetGetProjectStatus(){
		pdo.setProjectStatus("NoStart");
		assertEquals("NoStart", pdo.getProjectStatus());
	
	}
	
	public void testTasks(){
		ArrayList<TaskDO> tasks = new ArrayList<TaskDO>();
		//TaskDO task = new TaskDO(1, 0, null, "fly", 0, null, null, "finish", null);
		pdo.setTasks(tasks);
		assertEquals(tasks, pdo.getTasks());
	
	}
}
