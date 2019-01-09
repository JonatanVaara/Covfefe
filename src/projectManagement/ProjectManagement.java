package projectManagement;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import jsonReader.JsonReader;
import member.MemberAdmin;
import risk.RiskMatrix;
import taskSchedule.*;

public class ProjectManagement {

	private JsonReader reader = new JsonReader();
	private MemberAdmin memberAdmin;
	private ProjectSchedule projectSchedule;
	private RiskMatrix riskMatrix;

	public ProjectManagement() {
		this.memberAdmin = new MemberAdmin(reader.memberReader());
		this.projectSchedule = new ProjectSchedule(reader.scheduleReader("plannedSchedule"),
				reader.scheduleReader("currentSchedule"));
		this.riskMatrix = new RiskMatrix(reader.riskReader());

	}

	// ---------------------------------
	// --Check if Tasks are the same----
	// --------in all json-files--------
	// ---------------------------------

	public void checkTasks() {

		ArrayList<String> referenceList = this.projectSchedule.getAllTasksNamePlanned();

		this.checkTasksToList(referenceList, this.projectSchedule.getAllTasksNameCurrent());
		this.checkTasksToList(referenceList, this.memberAdmin.getAllMemberAllocatedTasks());
		this.checkTasksToList(referenceList, this.memberAdmin.getAllMemberPlannedTasks());
	}

	public void checkTasksToList(ArrayList<String> referenceList, ArrayList<String> listToCheck) {

		boolean taskExist = false;

		for (String name : listToCheck) {
			taskExist = false;
			for (String referenceName : referenceList) {
				if (name.equalsIgnoreCase(referenceName)) {
					taskExist = true;
				}
			}
			if (taskExist == false) {
				throw new RuntimeException("Task " + name + " does not exist in Planned Schedule");
			}
		}
	}

	// Forward plot/print Project Schedule
	public void printSchedule() {
		this.projectSchedule.plotChart();
	}

	// Method for sending right dates into the LineChart-class

	public void printChart(String chartName) {

		ArrayList<LocalDate> dates = new ArrayList<LocalDate>();

		LocalDate firstDate = getFirstEndDate("First Date");
		LocalDate endDate = getFirstEndDate("");

		dates.add(firstDate);
		while (firstDate.isBefore(endDate)) {

			firstDate = firstDate.plusDays(14);
			if (firstDate.isAfter(endDate)) {
				dates.add(endDate);
			} else {
				dates.add(firstDate);
			}
		}

		if (chartName == "Cost Variance") {
			LineChart lineChart = new LineChart(dates, "Cost Variance");
			lineChart.plotChart();
		} else if (chartName == "Schedule Variance") {
			LineChart lineChart = new LineChart(dates, "Schedule Variance");
			lineChart.plotChart();
		} else {
			LineChart lineChart = new LineChart(dates, "Earned Value");
			lineChart.plotChart();
		}

	}

	public void printRiskMatrix() {
		riskMatrix.getPlotRisks();

	}

	// -------------------------
	// --GETTERS AND SETTERS----
	// -------------------------

	public RiskMatrix getRiskMatrix() {
		return this.riskMatrix;
	}

	// EV with current schedule
	public long getEarnedValue(LocalDate checkDate) {
		ArrayList<String> completedTasks = projectSchedule.completedTasksCurrent(checkDate);

		if (completedTasks.size() == 0) {
			return 0;
		}

		return memberAdmin.getPlannedCostsOfTask(completedTasks);
	}

	// SV with planned schedule
	public long getScheduleVariance(LocalDate checkDate) {
		long SV = 0;
		long plannedSpent;
		ArrayList<String> plannedCompletedTasks = projectSchedule.completedTasksPlanned(checkDate);
		if (plannedCompletedTasks.size() == 0) {
			plannedSpent = 0;
		} else {
			plannedSpent = memberAdmin.getPlannedCostsOfTask(plannedCompletedTasks);
		}
		SV = this.getEarnedValue(checkDate) - plannedSpent;
		return SV;
	}

	// Method for getting Cost Variance by specific date

	public long getCostVariance(LocalDate checkDate) {
		long costVariance = 0;
		long earnedValue = 0;
		long actualCost = 0;

		earnedValue = getEarnedValue(checkDate);
		actualCost = getActualCost(checkDate);
		costVariance = earnedValue - actualCost;// getActualValue();
		return costVariance;
	}

	// Method for comparing first date and end date for tasks

	public LocalDate getFirstEndDate(String name) {
		LocalDate returnDate = null;
		if (name == "First Date") {
			LocalDate firstDate = null;
			Schedule schedule = projectSchedule.getCurrentSchedule();
			for (Task task : schedule.getTasks()) {
				LocalDate date = task.getStartDate();
				if (firstDate == null || firstDate.isAfter(date)) {
					firstDate = date;
					returnDate = firstDate;
				}
			}
		} else {
			LocalDate endDate = null;
			Schedule schedule = projectSchedule.getCurrentSchedule();
			for (Task task : schedule.getTasks()) {
				LocalDate date = task.getEndDate();
				if (endDate == null || endDate.isBefore(date)) {
					endDate = date;
					returnDate = endDate;

				}
			}
		}

		return returnDate;
	}

	public void getMemberContribution() {
		memberAdmin.getPlotMemberContribution();
	}

	public String getUserIDName() {
		String idName = memberAdmin.getUserIDName();
		return idName;
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

	public String getMemberAllocatedTasks(String ID) throws IOException, Exception {
		return memberAdmin.getMemberAllocatedTasks(ID);
	}

	public long getMemberTimeAllocated(String ID) throws IOException, Exception {
		return memberAdmin.getMemberAllocatedTime(ID);
	}

	public ProjectSchedule getProjectSchedule() {
		return this.projectSchedule;
	}

}
