package member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import taskSchedule.TaskData;

public class Member {

	private String ID;
	private String name;
	private long salary;
	private HashMap<String, Long> plannedTaskTime;
	private HashMap<String, TaskData> allocatedTaskTime;

	public Member(String ID,String name,long salary) {
		this.ID = ID;
		this.name = name;
		this.salary = salary;
		this.plannedTaskTime = new HashMap<>();
		this.allocatedTaskTime = new HashMap<>();
	}

	// -------------------
	// getters & setters
	// -------------------
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

	// -------------------
	// plannedTimeFeatures
	// -------------------
	public List<String> getPlannedTasks() {
		return new ArrayList<String>(plannedTaskTime.keySet());
	}

	public void setPlannedTaskTime(String task, long time) {
		plannedTaskTime.put(name, time);
	}

	public long getTotalTimePlanned() {
		long sum = 0;
		for (long value : plannedTaskTime.values()) {
			sum += value;
		}
		return sum;
	}

	public long getPlannedCosts() {
		long totalCosts = getTotalTimePlanned() * this.salary;
		return totalCosts;
	}

	// -----------------
	// allocatedFeatures
	// -----------------
	public List<String> getAllocatedTasks() {
		List<String> tasks = new ArrayList<String>(allocatedTaskTime.keySet());
		return tasks;
	}
/*
	public void setAllocatedTaskTime(String task, long time) {
		allocatedTaskTime.put(name, time);
	}
*/
	public long getTotalTimeAllocated() {
		long sum = 0;
		for (TaskData taskData : allocatedTaskTime.values()) {
			sum += taskData.getTotalHours();
		}
		return sum;
	}

	public long getAllocatedCosts() {
		long totalCosts = getTotalTimeAllocated() * this.salary;
		return totalCosts;
	}

	// --------
	// toString
	// --------
	public String toString() {
		String print = "\nMember information: \nID: " + this.getID();
		
		print += "\nName: " + this.getName();
		print += "\nSalary: " + this.getSalary() + " SEK\n";
		print += this.plannedTaskTime + "\n";
		print += this.allocatedTaskTime + "\n";
		print += "\n";

		return print;
	}

}
