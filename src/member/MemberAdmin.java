package member;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class MemberAdmin {
	
	ArrayList<Member> memberList;
	
	public MemberAdmin (ArrayList<Member> listOfMembers)
	{
		this.memberList = listOfMembers;	
	}


	
	//-------------------------
	//get member-object from ID
	//-------------------------	
	
	public Member retrieveMember (String ID) throws IOException
	{
		for (Member member : memberList) 
		{
			if(member.getID().equals(ID))
				{return member;}
		}
		// Error handling if MemberID not exists TEST
		throw new IOException("Invalid ID!");
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

	
	
	public ArrayList<String> getAllMemberPlannedTasks(){
		
		ArrayList<String> listOfTasks = new ArrayList<>();
		
		for (Member member : memberList) {
			listOfTasks.addAll(member.getPlannedTasks());
		}
		return listOfTasks;
	}
	
	//---------------------------------
	//List with allocated Tasks per member
	//---------------------------------
	
	public String getMemberAllocatedTasks (String ID) throws Exception
	{	
		Member chosenMember = retrieveMember(ID);
		String taskList = chosenMember.getAllocatedTasks().stream().collect(Collectors.joining("\n"));
		return taskList;
	}

	public ArrayList<String> getAllMemberAllocatedTasks() {

		ArrayList<String> listOfTasks = new ArrayList<>();

		for (Member member : memberList) {
			listOfTasks.addAll(member.getAllocatedTasks());
		}
		return listOfTasks;
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
	
	
	//----------------------------------
	//member's Time allocated for Project
	//----------------------------------	
	
	public long getMemberAllocatedTime (String ID) throws Exception
	{
		Member chosenMember = retrieveMember(ID);
		return chosenMember.getTotalTimeAllocated();
	}
	
	public String getUserIDName() {
		String member = "";
		for (Member nameID : memberList) {
		member += nameID.getID()+": "+nameID.getName()+"\n";
		}
		if (member == "") {
			return "No members registered";
		}
		return member;
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
	
	public void getPlotMemberContribution() {
	MemberGraph plotMemberContribution = new MemberGraph(memberList);
	plotMemberContribution.plotChart();
	}
}
