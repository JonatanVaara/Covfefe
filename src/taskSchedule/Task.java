package taskSchedule;

import java.util.Date;
import java.text.*;

import org.json.simple.JSONObject;

public class Task {
	
	private String name;
	private Date startDate;
	private Date endDate;
	
	public Task(JSONObject task) {
		
		
		this.name = (String) task.get("Name");
		String startDate = (String) task.get("Start Date");
		String endDate = (String) task.get("End Date");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			if (startDate.contains("d")) {
				this.startDate = null;
			} else {
				this.startDate = dateFormat.parse(startDate);
			}

			if (endDate.contains("d")) {
				this.endDate = null;
			} else {
				this.endDate = dateFormat.parse(endDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//-------------------------------
	//---Check if task is completed--
	//-------------------------------
	
	public boolean taskComplete (Date checkDate) {
		
		if (this.getStartDate() != null && this.getEndDate() != null) {
			if (this.getEndDate().before(checkDate)) {
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

	@Override
	public String toString() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		String print = ("Task: " + this.getName() + "\nStart Date: " + dateFormat.format(this.getStartDate())); 
		print += ("\nEnd Date: " + dateFormat.format(this.getEndDate()) + "\n");
		return print;
	}
	
}
