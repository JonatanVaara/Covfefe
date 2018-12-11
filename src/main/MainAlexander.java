package main;
import java.util.ArrayList;
import java.util.Date;

import jsonReader.JsonReader;
import taskSchedule.*;

public class MainAlexander {

	public static void main(String[] args) {
		
		JsonReader reader = new JsonReader();

		//System.out.println(plannedSchedule.getTasks());
		
		ProjectSchedule ps = new ProjectSchedule(reader.scheduleReader("plannedSchedule"), reader.scheduleReader("currentSchedule"));
		//ps.plotChart();
		
		Date date = new Date();
		
		System.out.println(ps.completedTasksCurrent(date));
		System.out.println(ps.completedTasksPlanned(date));
	}

}
