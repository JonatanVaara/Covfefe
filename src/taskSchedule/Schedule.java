package taskSchedule;

import java.util.ArrayList;

public class Schedule {
	
	private ArrayList<Task> tasks;
	
	public Schedule (ArrayList<Task> tasks) {
		
		this.tasks = tasks;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

}
