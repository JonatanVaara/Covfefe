package main;
import java.time.LocalDate;
import java.util.ArrayList;

import jsonReader.JsonReader;
import projectManagement.ProjectManagement;
import taskSchedule.*;

public class MainAlexander {

	public static void main(String[] args) {
		
		JsonReader reader = new JsonReader();
		
		//System.out.println(plannedSchedule.getTasks());
		
		ProjectSchedule ps = new ProjectSchedule(reader.scheduleReader("plannedSchedule"), reader.scheduleReader("currentSchedule"));
		//ps.plotChart();
		
		//System.out.println(ps.getAllTasksNameCurrent());
		//System.out.println(ps.getAllTasksNamePlanned());
		
		LocalDate date = LocalDate.now();
		
		System.out.println(ps.completedTasksCurrent(date));
		System.out.println(ps.completedTasksPlanned(date));
		
		ProjectManagement pm = new ProjectManagement();
		
		pm.printSchedule();
		
		try {
			pm.checkTasks();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
