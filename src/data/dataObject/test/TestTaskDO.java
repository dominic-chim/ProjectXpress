package data.dataObject.test;


import java.util.ArrayList;
import data.dataObject.TaskDO;
import test.GroupTestCase;
import util.DateTime;

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
		tdo.setTaskRiskLevel("high");
		assertEquals("high", tdo.getTaskRiskLevel());
	}
	
	public void testSetTaskReleaseTime(){
		tdo.setTaskReleaseTime(new DateTime(2012,12,21,00,00,00));
		assertEquals("2012-12-21 00:00:00", tdo.getTaskReleaseTime().getDateTime());
	}
	
	public void testSetTaskStatus(){
		tdo.setTaskStatus("PartFinished");
		assertEquals("PartFinished", tdo.getTaskStatus());
	}
	
	public void testSetRequiredTaskIds(){
		tdo.setRequiredTaskIds(new ArrayList<Integer>());
		assertEquals(new ArrayList<Integer>(),tdo.getRequiredTaskIds());
	}
}

