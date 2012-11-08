import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task extends DatabaseRoot {

	private int projectId;
	private int taskId;
	private String taskName;
	private int skillLevel;
	private int duration;
	private int riskLevel;
	private Date startDate;
	private Date endDate;
	private String taskStatus;

	public Task(int projectId, int taskId, String taskName, int skillLevel,
			int duration, int riskLevel, Date startDate, Date endDate,
			String taskStatus) throws SQLException {

		String sql = "Select project_id, task_id, task_name, required_skill, duration, risk_level, start_date, end_date, task_status FROM task WHERE project_id = "
				+ projectId + " AND task+id = " + taskId;

		ResultSet result = db.executeQuery(sql);

		while (result.next()) {
			this.projectId = result.getInt("project_id");
			this.taskId = result.getInt("task_id");
			this.taskName = result.getString("task_name");
			this.skillLevel = result.getInt("required_skill");
			this.duration = result.getInt("duration");
			this.riskLevel = result.getInt("risk_level");
			this.startDate = result.getDate("start_date");
			this.endDate = result.getDate("end_date");
			this.taskStatus = result.getString("task_status");
		}
	}

	public int getSkillLevel() {
		return this.skillLevel;
	}

	public int getDuration() {
		return this.duration;
	}

	public int getRisk() {
		return this.riskLevel;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}
}
