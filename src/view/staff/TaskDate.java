package view.staff;

import util.DateTime;
import data.dataObject.TaskDO;

public class TaskDate {

	DateTime date;
	TaskDO task;
	
	public TaskDate(DateTime date, TaskDO task) {
		
		this.date = date;
		this.task = task;
		
	}
	
	public TaskDO getTask() {
		return this.task;
	}
	
	public DateTime getDate() {
		return this.date;
	}
	
}
