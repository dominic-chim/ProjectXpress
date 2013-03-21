package data.dataObject.test;

import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;

import data.dataObject.ProjectDO;
import data.dataObject.TaskDO;
import test.GroupTestCase;
import util.DateTime;

/**
 * 
 * test for projectDO
 * 
 * @author Hongyang LIN
 *
 */
public class TestProjectDO extends GroupTestCase{
	
	private ProjectDO pdo;
	
	public TestProjectDO(String name) {
		super(name);
	}
	
	@Before
	protected void setUp() {
		pdo = new ProjectDO(0, null, null, 0, null, null);
	}
	
	@Test
	public void testSetGetProjectId() {
		pdo.setProjectId(1);
		assertEquals(1, pdo.getProjectId());
	}
	
	
	public void testSetGetProjectName() {
		pdo.setProjectName("place");
		assertEquals("place", pdo.getProjectName());
	}
	
	public void testSetGetProjectDueDate() {
		pdo.setProjectDueDate(new DateTime(1992,5,12,12,0,0));
		assertEquals("1992-05-12 12:00:00", pdo.getProjectDueDate().getDateTime());
	}
	
	public void testSetGetProjectPriority(){
		pdo.setProjectPriority(2);
		assertEquals(2, pdo.getProjectPriority());
	}
	
	public void testSetGetProjectStatus(){
		pdo.setProjectStatus("NoStart");
		assertEquals("NoStart", pdo.getProjectStatus());
	
	}
	

}
