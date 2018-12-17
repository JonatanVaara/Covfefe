package jsonReader;

import risk.Risk;
import taskSchedule.Schedule;
import taskSchedule.Task;
import member.Member;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {

	// ------------
	// RISK READER
	// ------------
	public ArrayList<Risk> riskReader() {
		ArrayList<Risk> riskList = new ArrayList<>();
		
		String fileLocation = "risks.json";
		Object object = readJson(fileLocation);
		if (!(object instanceof JSONArray)) {
			throw new RuntimeException("Expected a json array, got " + object.toString());
		}
		
		JSONArray riskArray = (JSONArray)object;
		for (Object riskObject : riskArray) {
			if (riskObject instanceof JSONObject) {
				JSONObject riskData = (JSONObject)riskObject;
				
				String riskName = (String)riskData.get("riskName");
				long impact = (long)riskData.get("impact");
				long probability = (long)riskData.get("probability");
				
				Risk risk = new Risk(riskName, impact, probability);
				riskList.add(risk);
			} else {
				throw new RuntimeException("Expected a json object, got " + riskObject.toString());
			}
		}
		
		return riskList;
	}

	// -------------
	// MEMBER READER
	// -------------

	public ArrayList<Member> memberReader() {
		// TODO: move this into the 'Member' class?
		ArrayList<Member> memberList = new ArrayList<>();
		
		String fileLocation = "members.json";
		Object object = readJson(fileLocation);
		if (!(object instanceof JSONArray)) {
			throw new RuntimeException("Expected a json array, got " + object.toString());
		}
		
		JSONArray memberArray = (JSONArray)object;
		for (Object memberObject : memberArray) {
			if (memberObject instanceof JSONObject) {
				Member member = Member.Create((JSONObject)memberObject);
				memberList.add(member);
			} else {
				throw new RuntimeException("Expected a json object, got " + memberObject.toString());
			}
		}
		
		return memberList;
	}

	public Schedule scheduleReader(String fileName) {

		ArrayList<Task> taskList = new ArrayList<>();

		String fileLocation = fileName + ".json";

		JSONObject jsonObject = (JSONObject)this.readJson(fileLocation);
		JSONArray tasks = (JSONArray)jsonObject.get("tasks");

		Iterator<JSONObject> iterator = tasks.iterator();

		while (iterator.hasNext()) {
			JSONObject object = iterator.next();

			Task newTask = new Task(object);

			taskList.add(newTask);
		}

		Schedule schedule = new Schedule(taskList);
		return schedule;
	}

	// ------------------
	// ACTUAL JSON READER
	// ------------------
	public Object readJson(String fileName) {
		JSONParser parser = new JSONParser();

		Object object = null;
		try {
			object = parser.parse(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}

}
