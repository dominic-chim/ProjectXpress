package database.dataAccessObject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import test.GroupTestCase;

import data.dataObject.ProjectDO;
import database.dataAccessObject.ProjectDao;

public class TestProjectDao extends GroupTestCase{

    public TestProjectDao(String name) {
        super(name);
    }

    ProjectDao pjdao;

    protected void setUp() {
        pjdao = new ProjectDao();
    }

    public void testGetProjectById() {
        ProjectDO project = pjdao.getProjectById(1);
    }

}
