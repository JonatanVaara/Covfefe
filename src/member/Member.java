package member;

import java.util.HashMap;

public class Member {
	
	private String name;
	private long salary;
	private long ID;
	private HashMap<String, Long> plannedTaskTime;
	//private HashMap<String, Long> allocatedTaskTime;
	
	public Member(String name, long salary, long age, HashMap<String, Long> plannedTaskTime ) {
		
		this.name = name;
		this.salary = salary;
		this.ID = age;
		
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


	public long getID() {
		return ID;
	}


	public void setID(long ID) {
		this.ID = ID;
	}


	public HashMap<String, Long> getPlannedTaskTime() {
		return plannedTaskTime;
	}


	public void setPlannedTaskTime(HashMap<String, Long> plannedTaskTime) {
		this.plannedTaskTime = plannedTaskTime;
	}


	public String toString() {
	
		String print = "\nMemeber information: \nName: " + this.getName() + "\nID: " + this.getID();
		print += "\nSalary: " + this.getSalary() + "\n" + this.getPlannedTaskTime();
		
		return print;
	}

}
