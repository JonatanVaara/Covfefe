package taskSchedule;

import java.text.*;
import java.time.LocalDate;

import org.json.simple.JSONObject;

public class Task {
	
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Task(JSONObject task) {
		
		
		this.name = (String) task.get("Name");
		String startDate = (String) task.get("Start Date");
		String endDate = (String) task.get("End Date");
		
		
		
		if (startDate.contains("d")) {
			this.startDate = null;
		} else {
			this.startDate = TaskData.getDateFromString(startDate);
		}
		if (endDate.contains("d")) {
			this.endDate = null;
		} else {
			this.endDate = TaskData.getDateFromString(endDate);
		}
		
	}

	//-------------------------------
	//---Check if task is completed--
	//-------------------------------
	
	public boolean taskComplete (LocalDate checkDate) {
		
		if (this.getStartDate() != null && this.getEndDate() != null) {
			if (this.getEndDate().isBefore(checkDate)) {
				return true;
			}
		}
		return false;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		String print = ("Task: " + this.getName() + "\nStart Date: " + dateFormat.format(this.getStartDate())); 
		print += ("\nEnd Date: " + dateFormat.format(this.getEndDate()) + "\n");
		return print;
	}
	
}
