package projectManagement;

import java.util.ArrayList;
import jsonReader.JsonReader;
import member.Member;
import member.MemberAdmin;
import risk.RiskMatrix;
import taskSchedule.ProjectSchedule;
import taskSchedule.Schedule;

public class ProjectManagement {

	JsonReader reader = new JsonReader();
	private MemberAdmin memberAdmin;
	private ProjectSchedule projectSchedule;
	private RiskMatrix riskMatrix;

	public ProjectManagement() {

		this.memberAdmin = new MemberAdmin(reader.memberReader());
		this.projectSchedule = new ProjectSchedule(reader.scheduleReader("currentSchedule"), reader.scheduleReader("plannedSchedule"));
		this.riskMatrix = new RiskMatrix(reader.riskReader());

	}

	public double getEarnedValue() {
		double earnedValue = getBudgetAtCompletion() * 1;//percentageOfCompletion
		return earnedValue;
	}

	public double getScheduledVariance() {
		double scheduledVariance = getEarnedValue() - 1;//plannedValue
		return scheduledVariance;				
	}
	
	public double getCostVariance() {
		double costVariance = getEarnedValue() - 1;//getActualValue();
		return costVariance;
	}
	
	public long getTotalTimePlanned() {
		return memberAdmin.getTotalPlannedTime();
	}
	
	public long getTotalTimeAllocated() {
		return memberAdmin.getTotalAllocatedTime();
	}
	
	public long getBudgetAtCompletion() {
		return memberAdmin.getPlannedBudget();
	}
	
	public long getActualCost() {
		return memberAdmin.getActualCosts();
	}
	
	public String getMemberPlannedTasks (String ID)
	{
		return memberAdmin.getMemberPlannedTasks(ID);	
	}
	
	public long getMemberTimePlanned (String ID)
	{
		return memberAdmin.getMemberPlannedTime(ID);
		
	}
	
}
