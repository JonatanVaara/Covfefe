package taskSchedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class TaskData {
	private String name;
	private HashMap<LocalDate, Long> timeData;

	public TaskData(String taskName, JSONObject data) {
		name = taskName;
		timeData = new HashMap<>();
		for (Object key : data.keySet()) {
			if (!(key instanceof String)) {
				continue;
			}

			String dateString = (String)key;
			LocalDate date = getDateFromString(dateString);
			if (date != null) {
				timeData.put(date, (Long)data.get(dateString));
			}
		}
	}
	
	static public LocalDate getDateFromString(String dateString) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date;
		try {
			date = LocalDate.parse(dateString, dateFormat);
		} catch (DateTimeParseException e) {
			date = null;
		}
		return date;
	}
	
	public String getName() {
		return this.name;
	}
	
	public long getTotalHours() {
		long totalHours = 0;
		for (long hours : timeData.values()) {
			totalHours += hours;
		}
		return totalHours;
	}
	
	public long getHoursAt(String dateString) {
		LocalDate date = getDateFromString(dateString);
		return timeData.get(date);
	}
	
	public long getHoursBetween(String from, String to) {
		LocalDate startDate = getDateFromString(from);
		if (startDate == null) {
			throw new RuntimeException("'" + from + "' isn't a valid date.");
		}
		
		LocalDate endDate = getDateFromString(to);
		if (endDate == null) {
			throw new RuntimeException("'" + to + "' isn't a valid date.");
		}
		
		// If fromDate is after toDate, swap them.
		if (startDate.isAfter(endDate)) {
			LocalDate tmp = startDate;
			startDate = endDate;
			endDate = tmp;
		}
		
		// Small optimization
		startDate = startDate.minusDays(1);
		endDate = endDate.plusDays(1);
		
		long hours = 0;
		for (LocalDate date : timeData.keySet()) {
			if (date.isAfter(startDate) && date.isBefore(endDate)) {
				hours += timeData.get(date);
			}
		}
		
		return hours;
	}
}
