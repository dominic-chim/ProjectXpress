package database.dataAccessObject.test;

import data.dataObject.TaskDO;
import database.dataAccessObject.TaskDao;
import test.GroupTestCase;

public class TestTaskDao extends GroupTestCase {

    private TaskDao taskDao;

    public TestTaskDao(String name) {
        super(name);
    }

    protected void setUp() {
        taskDao = new TaskDao();
    }

    protected void tearDown() {
    }

    // TODO finish it
    public void testGetTaskById() {
        //TaskDO task = new TaskDO(200, 1, "");

    }
    public void testGetTasksByProjectId() {
    }
}
