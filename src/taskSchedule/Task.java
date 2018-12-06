package taskSchedule;

import java.util.Date;
import java.text.*;

import org.json.simple.JSONObject;

public class Task {
	
	private String name;
	private Date startDate;
	private Date endDate;
	private boolean taskComplete;
	
	public Task(JSONObject task) {
		
		
		this.name = (String) task.get("Name");
		String startDate = (String) task.get("Start Date");
		String endDate = (String) task.get("End Date");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		try {
			this.startDate = dateFormat.parse(startDate);
			this.endDate = dateFormat.parse(endDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		this.taskComplete = false;
	}
	
	//------------------------
	//---GETTERS AND SETTERS--
	//------------------------
	
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

	@Override
	public String toString() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		String print = ("Task: " + this.getName() + "\nStart Date: " + dateFormat.format(this.getStartDate())); 
		print += ("\nEnd Date: " + dateFormat.format(this.getEndDate()) + "\n");
		return print;
	}
	
}
