package main;
import java.util.ArrayList;

import jsonReader.JsonReader;
import taskSchedule.*;

public class MainAlexander {

	public static void main(String[] args) {
		
		JsonReader reader = new JsonReader();

		//System.out.println(plannedSchedule.getTasks());
		
		ProjectSchedule ps = new ProjectSchedule(reader.plannedScheduleReader(), reader.currentScheduleReader());
		ps.plotChart();
	}

}
