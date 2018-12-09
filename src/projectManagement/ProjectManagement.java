package projectManagement;

import java.util.ArrayList;

import member.Member;
import member.MemberAdmin;
import risk.RiskMatrix;
import taskSchedule.Schedule;

public class ProjectManagement {

	private MemberAdmin memberAdmin;
	private Schedule plannedSchedule;
	private Schedule currentSchedule;
	private RiskMatrix riskMatrix;

	public ProjectManagement() {

		this.memberAdmin = new MemberAdmin();
		this.plannedSchedule = new Schedule();
		this.currentSchedule = new Schedule();
		this.riskMatrix = new RiskMatrix();

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
