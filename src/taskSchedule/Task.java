package taskSchedule;

import java.util.Date;

public class Task {
	
	private String name;
	private Date startDate;
	private Date endDate;
	private boolean taskComplete;
	
	public Task(String name, Date startDate, Date endDate) {
		
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		
		this.taskComplete = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isCompletionTask() {
		return taskComplete;
	}

	public void setCompletionTask(boolean completionTask) {
		this.taskComplete = completionTask;
	}
	
	

}
