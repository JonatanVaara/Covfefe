package member;

import java.util.HashMap;
import java.util.Set;

public class Member {
	
	private String ID;
	private String name;
	private long salary;
	private HashMap<String, Long> plannedTaskTime;
	//private HashMap<String, Long> allocatedTaskTime;
	
	public Member(String ID, String name, long salary, HashMap<String, Long> plannedTaskTime ) {
	
		this.ID = ID;
		this.name = name;
		this.salary = salary;
		
		
		this.plannedTaskTime = plannedTaskTime;
		//this.allocatedTaskTime = allocatedTaskTime;
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getSalary() {
		return salary;
	}


	public void setSalary(long salary) {
		this.salary = salary;
	}


	public String getID() {
		return ID;
	}


	public void setID(String ID) {
		this.ID = ID;
	}

	public Set <String> getPlannedTasks (){
		Set <String> tasks = plannedTaskTime.keySet();
      	return tasks;
	}


	public void setPlannedTaskTime(String task, long time) {
		plannedTaskTime.put(name, time);
	}
	
	public long getTotalTimePlanned ()
	{
		long sum = 0;
		for(long value : plannedTaskTime.values())
		{
			sum += value;
		}
		return sum;
	}


	public String toString() {
	
		String print = "\nMember information: \nID: " + this.getID(); 
		print+= "\nName: " + this.getName();
		print += "\nSalary: " + this.getSalary() + " SEK\n";
		print+= this.plannedTaskTime;
		print+= "\n";
		
		return print;
	}


}

