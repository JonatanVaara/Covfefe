package projectManagement;

import java.sql.Date;
import java.util.ArrayList;

import jsonReader.JsonReader;
import member.MemberAdmin;
import risk.RiskMatrix;
import taskSchedule.ProjectSchedule;

public class ProjectManagement {

	private JsonReader reader = new JsonReader();
	private MemberAdmin memberAdmin;
	private ProjectSchedule projectSchedule;
	private RiskMatrix riskMatrix;

	public ProjectManagement() {
		this.memberAdmin = new MemberAdmin(reader.memberReader());
		this.projectSchedule = new ProjectSchedule(
			reader.scheduleReader("currentSchedule"),
			reader.scheduleReader("plannedSchedule")
		);
		this.riskMatrix = new RiskMatrix(reader.riskReader());
	}
	
	public RiskMatrix getRiskMatrix() {
		return this.riskMatrix;
	}

	//EV with current schedule
	public long getEarnedValueCurrent(Date checkDate) {
		ArrayList<String> completedTasks = projectSchedule.completedTasksCurrent(checkDate);
		if(completedTasks.size() == 0)
		{return 0;}
		return memberAdmin.getPlannedCostsOfTask(completedTasks);
	}
	
	//EV with planned schedule
		public long getEarnedValuePlanned(Date checkDate) {
			ArrayList<String> completedTasks = projectSchedule.completedTasksPlanned(checkDate);
			if(completedTasks.size() == 0)
			{return 0;}
			return memberAdmin.getPlannedCostsOfTask(completedTasks);
		}

//	public long getScheduledVariance(Date checkDate) {
//		long scheduledVariance = getEarnedValue(checkDate) - 1;// plannedValue
//		return scheduledVariance;
//	}
//
//	public long getCostVariance(Date checkDate) {
//		long costVariance = getEarnedValue(checkDate) - 1;// getActualValue();
//		return costVariance;
//	}

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

	public String getMemberPlannedTasks(String ID) {
		return memberAdmin.getMemberPlannedTasks(ID);
	}

	public long getMemberTimePlanned(String ID) {
		return memberAdmin.getMemberPlannedTime(ID);
	}
}
