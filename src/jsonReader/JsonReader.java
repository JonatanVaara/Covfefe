package jsonReader;

import risk.Risk;
import risk.RiskMatrix;
import member.Member;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	
	public static void main(String[] args) {
		
		JsonReader x = new JsonReader();
		ArrayList<Member> memberList = x.memberReader();
		ArrayList<Risk> riskList = x.riskReader();
	
		System.out.println(memberList);
		System.out.println("-----------------------");
		System.out.println(riskList);
		System.out.println("-----------------------");

		RiskMatrix riskMatrix = new RiskMatrix(riskList);
		System.out.println(riskMatrix.matrixPrint());
		System.out.println("-------Test with Member List 0-------");
		System.out.println(memberList.get(0).getName() + ":\n" + "Tasks:" + memberList.get(0).getPlannedTasks()+"\nTotal Time: " + memberList.get(0).getTotalTimePlanned());
		
		// nicer print
		for (String task : memberList.get(0).getPlannedTasks())
		{
			System.out.println(task);
		}
		//RiskMatrix matrix = new RiskMatrix(riskList);
		//matrix.plotMatrix();
		
	}
	
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
			

			Member newMember = new Member(ID, name, salary, plannedTaskTime);

			memberList.add(newMember);
		}
		return memberList;
	}
	
	
	//-----------
	//JSON READER
	//-----------
	
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
