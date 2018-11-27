package jsonReader;

import risk.Risk;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	
	public static void main(String[] args) {
		
		JsonReader x = new JsonReader();
		//ArrayList<Member> memberList = x.memberReader();
		ArrayList<Risk> riskList = x.riskReader();
	
		//System.out.println(memberList);
		System.out.println("-----------------------");
		System.out.println(riskList);
		
		//RiskMatrix matrix = new RiskMatrix(riskList);
		//matrix.plotMatrix();
		
	}
	
	public ArrayList<Risk> riskReader() {

		ArrayList<Risk> riskList = new ArrayList<>();

		JSONObject jsonObject = readJsonObject();
		JSONArray risks = (JSONArray) jsonObject.get("risks");

		Iterator<JSONObject> iterator = risks.iterator();

		while (iterator.hasNext()) {

			JSONObject object = iterator.next();

			String riskName = (String) object.get("riskName");
			int impact = (int) object.get("impact");
			int probability = (int) object.get("probability");

			Risk newRisk = new Risk(riskName, impact, probability);

			riskList.add(newRisk);
		}
		return riskList;
	}
	
	/*public ArrayList<Member> memberReader() {

		ArrayList<Member> memberList = new ArrayList<>();

		JSONObject jsonObject = this.readJsonObject();
		JSONArray members = (JSONArray) jsonObject.get("members");

		Iterator<JSONObject> iterator = members.iterator();

		while (iterator.hasNext()) {
			JSONObject object = iterator.next();

			String name = (String) object.get("name");
			int age = (int) object.get("age");
			long salary = (long) object.get("salary");

			Member newMember = new Member(name, age, salary);

			memberList.add(newMember);
		}
		return memberList;
	}*/
	
	public JSONObject readJsonObject() {
		
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObject = null;
		
		try{
			jsonObject = (JSONObject)parser.parse(new FileReader("management.json"));
			return jsonObject;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}

}
