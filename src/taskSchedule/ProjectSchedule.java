package taskSchedule;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class ProjectSchedule extends JFrame {

	private Schedule plannedSchedule;
	private Schedule currentSchedule;
	
	private static final long serialVersionUID = 1L; 
	
	public ProjectSchedule(Schedule plannedSchedule, Schedule currentSchedule) {
		super("Project Schedule");
		
		this.plannedSchedule = plannedSchedule;
		this.currentSchedule = currentSchedule;
	}
	
	//---------------------------
	//Plot Ganttchart of schedule
	//---------------------------
	
	public void plotChart() {

		IntervalCategoryDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createGanttChart("Project Schedule", "Tasks", "Timeline", dataset);

		ChartPanel panel = new ChartPanel(chart);

		setContentPane(panel);

		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public IntervalCategoryDataset createDataset() {
		
		TaskSeries series1 = new TaskSeries("Planned Schedule");
		for (taskSchedule.Task task : this.plannedSchedule.getTasks()) {
			Date startDate = Date.from(task.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date endDate = Date.from(task.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			series1.add(new Task(task.getName(), startDate, endDate));
		}
		
		TaskSeries series2 = new TaskSeries("Current Schedule");
		for (taskSchedule.Task task : this.currentSchedule.getTasks()) {
			if (task.getStartDate() != null && task.getEndDate() != null) {
				Date startDate = Date.from(task.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date endDate = Date.from(task.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
				series2.add(new Task(task.getName(), startDate, endDate));
			}
		}
		
		TaskSeriesCollection dataset = new TaskSeriesCollection();
		dataset.add(series1);
		dataset.add(series2);
		
		return dataset;
	}
	
	//------------------------------------
	//--Return list of completed tasks----
	//-------From Current schedule--------
	//------------------------------------
	
	public ArrayList<String> completedTasksCurrent (LocalDate checkDate){
	
		return this.getCurrentSchedule().completedTasks(checkDate);
	}
	
	// ------------------------------------
	// -----Return list of all tasks-------
	// -------From Current schedule--------
	// ------------------------------------
	
	public ArrayList<String> getAllTasksNameCurrent() {

		return this.getCurrentSchedule().getAllTasksName();
	}
	//------------------------------------
	//--Return list of completed tasks----
	//-------From Planned schedule--------
	//------------------------------------
	
	public ArrayList<String> completedTasksPlanned (LocalDate checkDate){
		
		return this.getPlannedSchedule().completedTasks(checkDate);
	}

	// ------------------------------------
	// -----Return list of all tasks-------
	// -------From Planned schedule--------
	// ------------------------------------
	
	public ArrayList<String> getAllTasksNamePlanned() {

		return this.getPlannedSchedule().getAllTasksName();
	}
	
	//-------------------
	//Getters and setters
	//-------------------
	
	public Schedule getPlannedSchedule() {
		return plannedSchedule;
	}

	public void setPlannedSchedule(Schedule plannedSchedule) {
		this.plannedSchedule = plannedSchedule;
	}

	public Schedule getCurrentSchedule() {
		return currentSchedule;
	}

	public void setCurrentSchedule(Schedule currentSchedule) {
		this.currentSchedule = currentSchedule;
	}
}
