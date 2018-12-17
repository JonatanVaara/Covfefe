package taskSchedule;

import java.util.ArrayList;
import java.util.Date;

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
	
	//------------------------------------
	//--Return list of completed tasks----
	//------------------------------------
	
	public ArrayList<String> completedTasks(Date checkDate){
		
		ArrayList<String> completedTasks = new ArrayList<>();
		
		for (Task task : this.getTasks()) {
			if(task.taskComplete(checkDate)) {
				completedTasks.add(task.getName());
			}
		}
		return completedTasks;
	}
	
	//-------------------------
	//--GETTERS AND SETTERS----
	//-------------------------
	
	public ArrayList<String> getAllTasksName(){
		
		ArrayList<String> allTasksName = new ArrayList<>();
		
		for (Task task : this.getTasks()) {
			allTasksName.add(task.getName());
		}
		return allTasksName;
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

}
