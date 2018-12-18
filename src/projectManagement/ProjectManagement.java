package projectManagement;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

	//Check to see if a Task exist in Planned Schedule
	public void checkTasks() {
		
		boolean taskExist = false;
		
		for (String name : this.projectSchedule.getAllTasksNamePlanned() ) {
			taskExist = false;
			for (String nameToCompare : this.projectSchedule.getAllTasksNameCurrent()) {
				if(name.equals(nameToCompare)) {
					taskExist = true;
				}
			}
			if (taskExist == false) {
				throw new RuntimeException("Current Schedule contain task, which does not exist in Planned Schedule" + name);
			}
		}
	}
	
	// -------------------------
	// --GETTERS AND SETTERS----
	// -------------------------
	
	public RiskMatrix getRiskMatrix() {
		return this.riskMatrix;
	}

	//EV with current schedule
	public long getEarnedValue(Date checkDate) {
		ArrayList<String> completedTasks = projectSchedule.completedTasksCurrent(checkDate);
		
		if(completedTasks.size() == 0)
		{return 0;}
		System.out.println(memberAdmin.getPlannedCostsOfTask(completedTasks));
		return memberAdmin.getPlannedCostsOfTask(completedTasks);
	}
	
	//EV with planned schedule
		public long getScheduleVariance(Date checkDate) {
			long SV = 0;
			long plannedSpent;
			ArrayList<String> plannedCompletedTasks = projectSchedule.completedTasksPlanned(checkDate);
			if(plannedCompletedTasks.size() == 0)
			{plannedSpent = 0;}
			plannedSpent = memberAdmin.getPlannedCostsOfTask(plannedCompletedTasks);
			SV = this.getEarnedValue(checkDate)-plannedSpent;
			return SV;
		}
		
		
		

//	public long getScheduledVariance(Date checkDate) {
//		long scheduledVariance = getEarnedValue(checkDate) - 1;// plannedValue
//		return scheduledVariance;
//	}
//
	public long getCostVariance(Date checkDate) {
		long costVariance = getEarnedValue(checkDate) - 1;// getActualValue();
		return costVariance;
	}
	
	public void printEVChart() {
		//Placeholder until we have functions to read proper dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
		ArrayList<Date> dates = new ArrayList<Date>();
		Date singleDate = new Date();
		Date testDate2 = new Date();
		
		try {
			singleDate = dateFormat.parse("20/10/2018");
			testDate2 = dateFormat.parse("16/12/2018");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		dates.add(singleDate);
		dates.add(testDate2);
		
		
		LineChart lineChart = new LineChart(dates, "Earned Value");
		lineChart.plotChart();
		
	}
	
	public void printSCChart() {
		//Placeholder until we have functions to read proper dates
		ArrayList<Date> dates = new ArrayList<Date>();
		
		dates.add(new Date(06/12/2018));
		dates.add(new Date(11/12/2018));
		dates.add(new Date(13/12/2018));
		
		LineChart lineChart = new LineChart(dates, "Schedule Variance");
		lineChart.plotChart();
	}
	
	public void printCVChart() {
		//Placeholder until we have functions to read proper dates
		ArrayList<Date> dates = new ArrayList<Date>();
		dates.add(new Date(06/12/2018));
		dates.add(new Date(11/12/2018));
		dates.add(new Date(13/12/2018));
		
		LineChart lineChart = new LineChart(dates, "Cost Variance");
		lineChart.plotChart();
		
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

	public String getMemberPlannedTasks(String ID) {
		return memberAdmin.getMemberPlannedTasks(ID);
	}

	public long getMemberTimePlanned(String ID) {
		return memberAdmin.getMemberPlannedTime(ID);
	}
}
