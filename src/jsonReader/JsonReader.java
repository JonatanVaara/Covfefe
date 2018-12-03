package jsonReader;

import risk.Risk;
import risk.RiskMatrix;
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
		
		String nameToRead = "risk"; //<-- defines name and file name
		ArrayList<Risk> riskList = new ArrayList<>();

		JSONObject jsonObject = readJsonObject(nameToRead);
		JSONArray risk = (JSONArray) jsonObject.get(nameToRead);

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
		
		String nameToRead = "member"; //<-- defines name and file name
		ArrayList<Member> memberList = new ArrayList<>();

		JSONObject jsonObject = this.readJsonObject(nameToRead);
		JSONArray members = (JSONArray) jsonObject.get(nameToRead);

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
	
	//Task Reader
	
	public ArrayList<Task> taskReader() {

		ArrayList<Task> taskList = new ArrayList<>();

		String fileLocation = "task";

		JSONObject jsonObject = this.readJsonObject(fileLocation);
		JSONArray tasks = (JSONArray) jsonObject.get("tasks");

		Iterator<JSONObject> iterator = tasks.iterator();

		while (iterator.hasNext()) {
			JSONObject object = iterator.next();

			Task newTask = new Task(object);

			taskList.add(newTask);
		}
		return taskList;

	}
	
	
	//------------------
	//ACTUAL JSON READER
	//------------------
	
	public JSONObject readJsonObject(String choosenInput) {
		
		String fileName = choosenInput+"s.json";
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
