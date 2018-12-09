package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import jsonReader.JsonReader;
import member.*;
import risk.*;

public class MainHartmut {
	
	
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
		
		MemberAdmin admin = new MemberAdmin(memberList);

		System.out.println(admin.getOverviewMembersPlannedTime());
		System.out.println(admin.getOverviewMembersAllocatedTime());


		System.out.println("--------------");
		
		System.out.println(admin.getMemberPlannedTasks("M001"));
		
		
		System.out.println("--------------");
		System.out.println("Planned budget: " + admin.getPlannedBudget());
		System.out.println("Actual costs: " + admin.getActualCosts());

		System.out.println("--------------");
		
	}

}
