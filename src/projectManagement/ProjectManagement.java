package projectManagement;

import java.util.ArrayList;

import member.Member;
import risk.RiskMatrix;
import taskSchedule.Schedule;

public class ProjectManagement {

	private ArrayList<Member> memberList;
	private Schedule plannedSchedule;
	private Schedule currentSchedule;
	private RiskMatrix riskMatrix;

	public ProjectManagement() {

		this.memberList = new ArrayList<Member>();
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
	
	public double getTotalTime() {
		double totalTime = 0;
		for(Member member : memberList) {
			totalTime += 1;//member.getAllocatedTaskTime();
		}
		return totalTime;
	}
	
	public double getBudgetAtCompletion() {
		double budgetAtCompletion = 0;
		for (Member member : memberList) {
			budgetAtCompletion += 1;//member.getPlannedTaskTime()*member.getSalary();
		}
		return budgetAtCompletion;
	}
	
	public double getActualCost() {
		return 555.5;
	}
	
	
	
}
