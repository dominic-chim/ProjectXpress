package data.dataObject;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ProjectDO {

    private int projectId;
    private String projectName;
    private GregorianCalendar projectDueDate;
    private int ProjectPriority;
    private String projectStatus;
    private ArrayList<TaskDO> task;

}
