package taskSchedule;

import java.util.ArrayList;

public class Schedule {
	
	private ArrayList<Task> tasks;
	
	public Schedule (ArrayList<Task> tasks) {
		
		this.tasks = tasks;
	}
	
	public Task searchTaskByName(String name) {   //Search for task by name and get that task
		
		for (Task task : this.getTasks()) {
			
			if(task.getName().equals(name)){
				return task;
			}
		}
		return null;
	}
	
	//-------------------------
	//--GETTERS AND SETTERS----
	//-------------------------
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

}
