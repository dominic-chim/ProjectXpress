package database.dataAccessObject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import database.dataAccessObject.ProjectDao;

public class ProjectDaoTest {

	@Test
	public void testGetProjectById() {
        ProjectDao pjdao = new ProjectDao();
        pjdao.getProjectById(1);
	}

}
