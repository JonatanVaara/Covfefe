package jsonReader;

import risk.Risk;
import risk.RiskMatrix;
import taskSchedule.Schedule;
import taskSchedule.Task;
import member.Member;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
		
	//------------
	// RISK READER
	//------------
	public ArrayList<Risk> riskReader() {
		
		String fileLocation = "risks.json"; //<-- defines file name
		ArrayList<Risk> riskList = new ArrayList<>();

		JSONObject jsonObject = readJsonObject(fileLocation);
		JSONArray risk = (JSONArray) jsonObject.get("risk");

		Iterator<JSONObject> iterator = risk.iterator();

		while (iterator.hasNext()) {

			JSONObject object = iterator.next();

			String riskName = (String) object.get("riskName");
			long impact = (long) object.get("impact");
			long probability = (long) object.get("probability");

			Risk newRisk = new Risk(riskName, impact, probability);

			riskList.add(newRisk);
		}
		return riskList;
	}
	
	//-------------
	//MEMBER READER
	//-------------
	
	public ArrayList<Member> memberReader() {
		
		String fileLocation = "members.json"; //<-- defines file name
		ArrayList<Member> memberList = new ArrayList<>();

		JSONObject jsonObject = this.readJsonObject(fileLocation);
		JSONArray members = (JSONArray) jsonObject.get("member");

		Iterator<JSONObject> iterator = members.iterator();

		while (iterator.hasNext()) {
			JSONObject object = iterator.next();
			
			String ID = (String) object.get("ID");
			String name = (String) object.get("name");
			long salary = (long) object.get("salary");
			HashMap<String, Long> plannedTaskTime = (HashMap) object.get("plannedTaskTime");
			HashMap<String, Long> allocatedTaskTime = (HashMap) object.get("allocatedTaskTime");
			

			Member newMember = new Member(ID, name, salary, plannedTaskTime, allocatedTaskTime);

			memberList.add(newMember);
		}
		return memberList;
	}

	//------------------
	//PLANNED SCHEDULE READER
	//------------------

	public Schedule plannedScheduleReader() {

		ArrayList<Task> taskList = new ArrayList<>();

		String fileLocation = "plannedSchedule.json";

		JSONObject jsonObject = this.readJsonObject(fileLocation);
		JSONArray tasks = (JSONArray) jsonObject.get("tasks");

		Iterator<JSONObject> iterator = tasks.iterator();

		while (iterator.hasNext()) {
			JSONObject object = iterator.next();

			Task newTask = new Task(object);

			taskList.add(newTask);
		}
		
		Schedule plannedSchedule = new Schedule(taskList);
		
		return plannedSchedule;
	}
	
	//------------------
	//CURRENT SCHEDULE READER
	//------------------
	
	public Schedule currentScheduleReader() {

		ArrayList<Task> taskList = new ArrayList<>();

		String fileLocation = "currentSchedule.json";

		JSONObject jsonObject = this.readJsonObject(fileLocation);
		JSONArray tasks = (JSONArray) jsonObject.get("tasks");

		Iterator<JSONObject> iterator = tasks.iterator();

		while (iterator.hasNext()) {
			JSONObject object = iterator.next();

			Task newTask = new Task(object);

			taskList.add(newTask);
		}
		
		Schedule currentSchedule = new Schedule(taskList);
		return currentSchedule;
	}
	
	//------------------
	//ACTUAL JSON READER
	//------------------
	
	public JSONObject readJsonObject(String choosenInput) {
		
		String fileName = choosenInput;
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObject = null;
		
		try{
			jsonObject = (JSONObject)parser.parse(new FileReader(fileName));
			return jsonObject;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}

}
