package member;

import java.util.HashMap;

public class Member {
	
	private String ID;
	private String name;
	private long salary;
	//private HashMap<String, Long> plannedTaskTime;
	//private HashMap<String, Long> allocatedTaskTime;
	
	//public Member(long ID, String name, long salary, HashMap<String, Long> plannedTaskTime ) {
		public Member(String ID, String name, long salary) {
	
		this.ID = ID;
		this.name = name;
		this.salary = salary;
		
		
		//this.plannedTaskTime = plannedTaskTime;
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


//	public HashMap<String, Long> getPlannedTaskTime() {
//		return plannedTaskTime;
//	}
//
//
//	public void setPlannedTaskTime(HashMap<String, Long> plannedTaskTime) {
//		this.plannedTaskTime = plannedTaskTime;
//	}


	public String toString() {
	
		String print = "\nMember information: \nID:" + this.getID(); 
		print+= "\nName: " + this.getName();
		print += "\nSalary: " + this.getSalary() + " SEK\n";
		//print+= + this.getPlannedTaskTime();
		
		return print;
	}

}
