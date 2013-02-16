package data.dataObject;

import java.util.Comparator;


public class ProjectComparator implements Comparator<ProjectDO> {

    @Override
    public int compare(ProjectDO project1, ProjectDO project2) {

        if(project1.getProjectPriority() < project2.getProjectPriority()){
            return -1;
        }


        if(project1.getProjectPriority() > project2.getProjectPriority()){
            return 1;
        }

        if(project1.getProjectPriority() < project2.getProjectPriority()){
            return 0;
        }

        return 0;
    }

}
