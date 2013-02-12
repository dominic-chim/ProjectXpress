package data.dataObject.test;

import java.util.GregorianCalendar;
import java.util.ArrayList;
import data.dataObject.TaskDO;
import test.GroupTestCase;

public class TestTaskDO extends GroupTestCase{
	
	private TaskDO tdo;
	
	public TestTaskDO(String name) {
		super(name);
	}
	
	protected void setUp() {
		tdo = new TaskDO();
	}

	public void testSetGetProjectId(){
		tdo.setProjectId(1);
		assertEquals(1, tdo.getProjectId());
	}
	
	public void testSetTaskId(){
		tdo.setTaskId(1);
		assertEquals(1, tdo.getTaskId());
	}
	
	public void testSetTaskName(){
		tdo.setTaskName("Coding");
		assertEquals("Coding", tdo.getTaskName());
	}
	
	public void testSetTaskRequiredSkill(){
		tdo.setTaskRequiredSkill(1);
		assertEquals(1, tdo.getTaskRequiredSkill());
	}
	
	public void testSetTaskDuration(){
		tdo.setTaskDuration(50);
		assertEquals(50, tdo.getTaskDuration());
	}
	
	public void testSetTaskRistLevel(){
		tdo.setTaskRistLevel("high");
		assertEquals("high", tdo.getTaskRistLevel());
	}
	
	public void testSetTaskReleaseTime(){
		tdo.setTaskReleaseTime(new GregorianCalendar(2012,12,21,00,00,00));
		assertEquals(new GregorianCalendar(2012,12,21,00,00,00), tdo.getTaskReleaseTime());
	}
	
	public void testSetTaskStatus(){
		tdo.setTaskStatus("PartFinished");
		assertEquals("PartFinished", tdo.getTaskStatus());
	}
	
	public void testSetRequiredTaskIds(){
		tdo.setRequiredTaskIds(new ArrayList<Integer>(1));
		assertEquals(new ArrayList<Integer>(1),tdo.getRequiredTaskIds());
	}
}

