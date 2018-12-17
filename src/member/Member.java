package member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import taskSchedule.TaskData;

public class Member {

	private String ID;
	private String name;
	private long salary;
	private HashMap<String, Long> plannedTaskTime;
	private HashMap<String, TaskData> allocatedTaskTime;

	private Member() {
		this.plannedTaskTime = new HashMap<>();
		this.allocatedTaskTime = new HashMap<>();
	}

	public static Member Create(JSONObject data) {
		if (data == null) {
			throw new NullPointerException();
		}

		Member member = new Member();
		member.ID = (String)data.get("ID");
		member.name = (String)data.get("name");
		member.salary = (long)data.get("salary");
		
		Object tmpObject = data.get("plannedTaskTime");
		if (tmpObject instanceof JSONObject) {
			JSONObject plannedTaskData = (JSONObject)tmpObject;
			for (Object key : plannedTaskData.keySet()) {
				String taskName = (String)key;
				long taskHours = (long)plannedTaskData.get(key);

				member.plannedTaskTime.put(taskName, taskHours);
			}
		} else {
			throw new RuntimeException("Expected a json object, got " + tmpObject.toString());
		}

		tmpObject = data.get("allocatedTaskTime");
		if (tmpObject instanceof JSONObject) {
			JSONObject allocatedTaskData = (JSONObject)tmpObject;
			for (Object key : allocatedTaskData.keySet()) {
				String taskName = (String)key;
				JSONObject taskObject = (JSONObject)allocatedTaskData.get(key);

				TaskData taskData = new TaskData(taskName, taskObject);
				member.allocatedTaskTime.put(taskName, taskData);
			}
		} else {
			throw new RuntimeException("Expected a json object, got " + tmpObject.toString());
		}

		return member;
	}

	// -------------------
	// getters & setters
	// -------------------
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return this.salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public HashMap<String, Long> getPlannedTaskTime() {
		return this.plannedTaskTime;
	}

	// -------------------
	// plannedTimeFeatures
	// -------------------
	public List<String> getPlannedTasks() {
		return new ArrayList<String>(this.plannedTaskTime.keySet());
	}

	public void setPlannedTaskTime(String task, long time) {
		this.plannedTaskTime.put(name, time);
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
	 * public void setAllocatedTaskTime(String task, long time) {
	 * allocatedTaskTime.put(name, time); }
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
