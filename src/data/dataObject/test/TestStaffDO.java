package data.dataObject.test;

import data.dataObject.ProjectDO;
import test.GroupTestCase;

public class TestStaffDo extends GroupTestCase{
	
	private ProjectDO pdo;
	
	public TestStaffDo(String name) {
		super(name);
	}
	
	protected void setUp() {
		pdo = new ProjectDO(0, null, null, 0, null, null);
	}
	
	public void testSetGetProjectId() {
		pdo.setProjectId(1);
		assertEquals(1, pdo.getProjectId());
	}
}