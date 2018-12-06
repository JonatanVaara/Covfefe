package main;
import java.util.ArrayList;

import jsonReader.JsonReader;
import taskSchedule.*;

public class MainAlexander {

	public static void main(String[] args) {
		
		JsonReader reader = new JsonReader();
		
		Schedule plannedSchedule = new Schedule(reader.plannedScheduleReader());
		Schedule currentSchedule = new Schedule(reader.currentScheduleReader());

		//System.out.println(plannedSchedule.getTasks());
		
		ProjectSchedule ps = new ProjectSchedule(plannedSchedule, currentSchedule);
		ps.plotChart();
	}

}
