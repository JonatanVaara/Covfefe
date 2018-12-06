package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import jsonReader.JsonReader;
import member.Member;
import risk.Risk;
import risk.RiskMatrix;

public class MainHartmut {
	
	public static String getOverviewMembersPlannedTime (ArrayList <Member> ListOfMembers)
	{
		String overview = "";
		Set <Member> members = new HashSet<>();
		Iterator<Member> iterator = members.iterator();
		    while (iterator.hasNext()) {
		        Member member = iterator.next();
		        overview += member.getName() + ": ";
		        overview += member.getTotalTimePlanned() + " hours \n";
		        if (!iterator.hasNext()) {
		        	overview += member.getName() + ": ";
		        	overview += member.getTotalTimePlanned() + " hours";		        
		        	}
		     }
		return overview;    
	}
	
	public static String getOverviewMembersAllocatedTime (ArrayList <Member> ListOfMembers)
	{
		String overview = "";
		Set <Member> members = new HashSet<>();
		Iterator<Member> iterator = members.iterator();
		    while (iterator.hasNext()) {
		        Member member = iterator.next();
		        overview += member.getName() + ": ";
		        overview += member.getTotalTimeAllocated() + " hours \n";
		        if (!iterator.hasNext()) {
		        	overview += member.getName() + ": ";
		        	overview += member.getTotalTimeAllocated() + " hours";		        
		        	}
		     }
		return overview;
	}
	
	public static long getPlannedBudget (ArrayList <Member> ListOfMembers)
	{
		long sum = 0;
		for (Member member : ListOfMembers)
		{
			sum += member.getPlannedCosts();
		}
		return sum;
	}
	
	public static long getActualCosts (ArrayList <Member> ListOfMembers)
	{
		long sum = 0;
		for (Member member : ListOfMembers)
		{
			sum += member.getAllocatedCosts();
		}
		return sum;
	}
	
	public static Member retrieveMember (String ID, ArrayList <Member> ListOfMembers)
	{
		for (Member member : ListOfMembers) 
		{
			if(member.getID().equals(ID))
				{return member;}
		}
		// TODO: Needs error handling
		return null;
	}
	
	public static String getMembersPlannedTasks (String ID, ArrayList <Member> ListOfMembers)
	{	
		Member chosenMember = retrieveMember(ID, ListOfMembers);
		String taskList = chosenMember.getPlannedTasks().stream().collect(Collectors.joining("\n"));
		return taskList;
	}
	
	public static String getMembersAllocatedTasks (String ID, ArrayList <Member> ListOfMembers)
	{	
		Member chosenMember = retrieveMember(ID, ListOfMembers);
		String taskList = chosenMember.getAllocatedTasks().stream().collect(Collectors.joining("\n"));
		return taskList;
	}
	
	public static long getTotalPlannedTime (ArrayList <Member> ListOfMembers)
	{
		long totalTime = 0;
		for (Member member : ListOfMembers)
		{
			totalTime += member.getTotalTimePlanned();
		}
		return totalTime;
	}
	
	public static long getTotalAllocatedTime (ArrayList <Member> ListOfMembers)
	{
		long totalTime = 0;
		for (Member member : ListOfMembers)
		{
			totalTime += member.getTotalTimeAllocated();
		}
		return totalTime;
	}
	
	public static long getMembersPlannedTime (String ID, ArrayList <Member> ListOfMembers)
	{
		Member chosenMember = retrieveMember(ID, ListOfMembers);
		return chosenMember.getTotalTimePlanned();
	}
	
	public static long getMembersAllocatedTime (String ID, ArrayList <Member> ListOfMembers)
	{
		Member chosenMember = retrieveMember(ID, ListOfMembers);
		return chosenMember.getTotalTimeAllocated();
	}
	
public static void main(String[] args) {
		
		JsonReader x = new JsonReader();
		ArrayList<Member> memberList = x.memberReader();
		ArrayList<Risk> riskList = x.riskReader();
	
		System.out.println("---------RISK PART--------------");
		System.out.println(riskList);
		System.out.println("-----------------------");

		RiskMatrix riskMatrix = new RiskMatrix(riskList);
		System.out.println(riskMatrix.printRiskMatrix());
		//matrix.plotMatrix();

		System.out.println("------------MEMBER PART-----------");

		System.out.println(getOverviewMembersAllocatedTime(memberList));

		System.out.println("--------------");
		
		System.out.println(getMembersPlannedTasks("M001", memberList));
		
		
		System.out.println("--------------");
		System.out.println("Planned budget: " + getPlannedBudget(memberList));
		System.out.println("Actual costs: " + getActualCosts(memberList));

		System.out.println("--------------");


		
		
	}

}
