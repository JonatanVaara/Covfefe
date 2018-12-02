package main;

import java.util.ArrayList;

import jsonReader.JsonReader;
import member.Member;
import risk.Risk;
import risk.RiskMatrix;

public class MainHartmut {
	
	public static void printAllInfoPlanned (ArrayList <Member> ListOfMembers)
	{
		System.out.println("Planned Overview: \n");
		for (Member member : ListOfMembers)
		{
		System.out.println(member.getName() + ": \n");
		for (String task : member.getPlannedTasks())
		{
			System.out.println(task);
		}
		System.out.println();
		System.out.println("Total Time: " + member.getTotalTimePlanned());
		System.out.println("Total Costs: " + member.getPlannedCosts());
		System.out.println("------");}

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
	
public static void main(String[] args) {
		
		JsonReader x = new JsonReader();
		ArrayList<Member> memberList = x.memberReader();
		ArrayList<Risk> riskList = x.riskReader();
	
		System.out.println("---------RISK PART--------------");
		System.out.println(riskList);
		System.out.println("-----------------------");

		RiskMatrix riskMatrix = new RiskMatrix(riskList);
		System.out.println(riskMatrix.matrixPrint());
		//matrix.plotMatrix();

		System.out.println("------------MEMBER PART-----------");

		System.out.println(memberList);

		System.out.println("--------------");
		
		
		// nicer print
		
		printAllInfoPlanned(memberList);
		
		System.out.println("--------------");
		System.out.println("Planned budget: " + getPlannedBudget(memberList));
		System.out.println("Actual costs: " + getActualCosts(memberList));

		System.out.println("--------------");


		
		
	}

}
