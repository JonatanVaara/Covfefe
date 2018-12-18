package projectManagement;



import java.time.LocalDate;
import java.util.ArrayList;


import jsonReader.JsonReader;
import member.MemberAdmin;
import risk.RiskMatrix;
import taskSchedule.ProjectSchedule;
import taskSchedule.TaskData;

public class ProjectManagement {

	
	
	private JsonReader reader = new JsonReader();
	private MemberAdmin memberAdmin;
	private ProjectSchedule projectSchedule;
	private RiskMatrix riskMatrix;
	

	public ProjectManagement() {
		this.memberAdmin = new MemberAdmin(reader.memberReader());
		this.projectSchedule = new ProjectSchedule(
			reader.scheduleReader("plannedSchedule"),
			reader.scheduleReader("currentSchedule")
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
	
	//Forward plot/print Project Schedule
	public void printSchedule() {
		this.projectSchedule.plotChart();
	}
	
	// -------------------------
	// --GETTERS AND SETTERS----
	// -------------------------
	
	public RiskMatrix getRiskMatrix() {
		return this.riskMatrix;
	}

	//EV with current schedule
	public long getEarnedValue(LocalDate checkDate) {
		ArrayList<String> completedTasks = projectSchedule.completedTasksCurrent(checkDate);
		System.out.println(completedTasks.toString());
		if(completedTasks.size() == 0)
		{return 0;}
	
		return memberAdmin.getPlannedCostsOfTask(completedTasks);
	}
	
	//EV with planned schedule
		public long getScheduleVariance(LocalDate checkDate) {
			long SV = 0;
			long plannedSpent;
			ArrayList<String> plannedCompletedTasks = projectSchedule.completedTasksPlanned(checkDate);
			if(plannedCompletedTasks.size() == 0)
			{plannedSpent = 0;}
			else{plannedSpent = memberAdmin.getPlannedCostsOfTask(plannedCompletedTasks);}
			SV = this.getEarnedValue(checkDate)-plannedSpent;
			return SV;
		}
		
		
	public long getCostVariance(LocalDate checkDate) {
		long costVariance = 0;
		long earnedValue = 0;
		long actualCost = 0;
		
		earnedValue = getEarnedValue(checkDate);
		actualCost = getActualCost(checkDate);
		costVariance = earnedValue - actualCost;// getActualValue();
		return costVariance;
	}
	
	public void printEVChart() {
		//Placeholder until we have functions to read proper dates
		ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
		LocalDate singleDate = TaskData.getDateFromString("20/10/2018");
		LocalDate testDate2 = TaskData.getDateFromString("16/12/2018");
		
		dates.add(singleDate);
		dates.add(testDate2);
		
		
		LineChart lineChart = new LineChart(dates, "Earned Value");
		lineChart.plotChart();
		
	}
	
	public void printSCChart() {
		//Placeholder until we have functions to read proper dates
		ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
		
		dates.add(TaskData.getDateFromString("06/12/2018"));
		dates.add(TaskData.getDateFromString("11/12/2018"));
		dates.add(TaskData.getDateFromString("13/12/2018"));
		
		LineChart lineChart = new LineChart(dates, "Schedule Variance");
		lineChart.plotChart();
	}
	
	public void printCVChart() {
		//Placeholder until we have functions to read proper dates
		ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
		dates.add(TaskData.getDateFromString("06/12/2018"));
		dates.add(TaskData.getDateFromString("11/12/2018"));
		dates.add(TaskData.getDateFromString("13/12/2018"));
		
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

	public long getActualCost(LocalDate checkDate) {
		return memberAdmin.getActualCosts(checkDate);
	}

	public String getMemberPlannedTasks(String ID) {
		return memberAdmin.getMemberPlannedTasks(ID);
	}

	public long getMemberTimePlanned(String ID) {
		return memberAdmin.getMemberPlannedTime(ID);
	}
	
	public ProjectSchedule getProjectSchedule() {
		return this.projectSchedule;
	}
}
