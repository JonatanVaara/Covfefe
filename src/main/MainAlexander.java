package main;
import java.util.ArrayList;
import java.util.Date;

import jsonReader.JsonReader;
import projectManagement.ProjectManagement;
import taskSchedule.*;

public class MainAlexander {

	public static void main(String[] args) {
		
		JsonReader reader = new JsonReader();

		//System.out.println(plannedSchedule.getTasks());
		
		ProjectSchedule ps = new ProjectSchedule(reader.scheduleReader("plannedSchedule"), reader.scheduleReader("currentSchedule"));
		//ps.plotChart();
		
		System.out.println(ps.getAllTasksNameCurrent());
		System.out.println(ps.getAllTasksNamePlanned());
		
		/*Date date = new Date();
		
		System.out.println(ps.completedTasksCurrent(date));
		System.out.println(ps.completedTasksPlanned(date));*/
		
		ProjectManagement pm = new ProjectManagement();
		
		try {
			pm.checkTasks();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
