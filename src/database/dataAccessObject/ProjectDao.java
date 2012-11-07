package database.dataAccessObject;

import database.DatabaseRoot;
import data.dataObject.ProjectDO;

public class ProjectDao extends DatabaseRoot {

    public ProjectDO getProjectById() {
        String sql = "SELECT project_id";
        db.execute();
        return null;
    }
}
