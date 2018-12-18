package member;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;


public class MemberAdmin {
	
	ArrayList<Member> memberList;
	
	public MemberAdmin (ArrayList<Member> listOfMembers)
	{
		this.memberList = listOfMembers;	
	}

	//-------------------------------------
	//Overview all members and planned time
	//-------------------------------------
	
	public String getOverviewMembersPlannedTime ()
	{
		String overview = "";		
		Iterator<Member> iterator = memberList.iterator();
		    while (iterator.hasNext()) {
		        Member member = iterator.next();
		    	if (iterator.hasNext()) {
		        	overview += member.getName() + ": ";
		        	overview += member.getTotalTimePlanned() + " hours \n";		        
		        	}
		        else {
		        	overview += member.getName() + ": ";
			        overview += member.getTotalTimePlanned() + " hours";
		        }
		     }
		return overview;    
	}
	
	//-------------------------------------
	//Overview all members and allocated time
	//-------------------------------------
	
	public String getOverviewMembersAllocatedTime ()
	{
		String overview = "";
		Iterator<Member> iterator = memberList.iterator();
		    while (iterator.hasNext()) {
		        Member member = iterator.next();
		        if (iterator.hasNext()) {
		        	overview += member.getName() + ": ";
		        	overview += member.getTotalTimeAllocated() + " hours \n";		        
		        	}
		        else {
		        	overview += member.getName() + ": ";
			        overview += member.getTotalTimeAllocated() + " hours";
		        }
		     }
		return overview;
	}
	
	//-------------------------
	//get member-object from ID
	//-------------------------	
	
	public Member retrieveMember (String ID)
	{
		for (Member member : memberList) 
		{
			if(member.getID().equals(ID))
				{return member;}
		}
		// TODO: Needs error handling
		return null;
	}	
	
	//-------------------------------------
	//planned Budget (calculation in member)
	//-------------------------------------
	
	public long getPlannedBudget ()
	{
		long sum = 0;
		for (Member member : memberList)
		{
			sum += member.getPlannedCosts();
		}
		return sum;
	}
	
	//-------------------------------------
	//allocated Budget = actual costs (calculation in member)
	//-------------------------------------	
	
	public long getActualCosts (LocalDate checkDate)
	{

		long sum = 0;
		for (Member member : memberList)
		{
			sum += member.getAllocatedCosts(checkDate);
		}
		return sum;
	}
	
	
	//---------------------------------
	//List with planned Tasks per member
	//---------------------------------
	
	public String getMemberPlannedTasks (String ID)
	{	
		Member chosenMember = retrieveMember(ID);
		String taskList = chosenMember.getPlannedTasks().stream().collect(Collectors.joining("\n"));
		return taskList;
	}
	
	//---------------------------------
	//List with allocated Tasks per member
	//---------------------------------
	
	public String getMemberAllocatedTasks (String ID)
	{	
		Member chosenMember = retrieveMember(ID);
		String taskList = chosenMember.getAllocatedTasks().stream().collect(Collectors.joining("\n"));
		return taskList;
	}
	
	//------------------------------
	//total Time planned for Project
	//------------------------------	
	
	public long getTotalPlannedTime ()
	{
		long totalTime = 0;
		for (Member member : memberList)
		{
			totalTime += member.getTotalTimePlanned();
		}
		return totalTime;
	}
	
	//------------------------------
	//total Time allocated for Project
	//------------------------------		
	
	public long getTotalAllocatedTime ()
	{
		long totalTime = 0;
		for (Member member : memberList)
		{
			totalTime += member.getTotalTimeAllocated();
		}
		return totalTime;
	}
	
	//--------------------------------
	//members Time planned for Project
	//--------------------------------		
	
	public long getMemberPlannedTime (String ID)
	{
		Member chosenMember = retrieveMember(ID);
		return chosenMember.getTotalTimePlanned();
	}
	
	//----------------------------------
	//members Time allocated for Project
	//----------------------------------	
	
	public long getMemberAllocatedTime (String ID)
	{
		Member chosenMember = retrieveMember(ID);
		return chosenMember.getTotalTimeAllocated();
	}
	
	//---------------------------
	//--returns planned costs ---
	//------of given tasked -----
	//---------------------------
	
	public long getPlannedCostsOfTask (ArrayList<String> taskListPlanned)
	{
		long totalCosts = 0;
		
		for (String task : taskListPlanned)
		{
		for (Member member : memberList)
		{
			if(member.getPlannedTaskTime().get(task) != null)
			{totalCosts += (member.getPlannedTaskTime().get(task) * member.getSalary());}
			
		}
		}
		return totalCosts;
	}
	
	//-------------------------------------------
	//Plot every member's time (planned) per task
	//-------------------------------------------
	
	public void getPlotMember () {
	MemberGraph plotMember = new MemberGraph(memberList);
	plotMember.plotChart();
	}
}
