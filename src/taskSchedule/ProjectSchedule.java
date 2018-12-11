package taskSchedule;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

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
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// this.pack();
		this.setVisible(true);
	}

	public IntervalCategoryDataset createDataset() {
		
		TaskSeries series1 = new TaskSeries("Planned Schedule");
		for (taskSchedule.Task task : this.plannedSchedule.getTasks()) {
			series1.add(new Task(task.getName(), task.getStartDate(), task.getEndDate()));
		}
		
		TaskSeries series2 = new TaskSeries("Current Schedule");
		for (taskSchedule.Task task : this.currentSchedule.getTasks()) {
			series2.add(new Task(task.getName(), task.getStartDate(), task.getEndDate()));
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
	
	public ArrayList<String> completedTasksCurrent (Date checkDate){
	
		return this.getCurrentSchedule().completedTasks(checkDate);
	}
	
	//------------------------------------
	//--Return list of completed tasks----
	//-------From Current schedule--------
	//------------------------------------
	
	public ArrayList<String> completedTasksPlanned (Date checkDate){
		
		return this.getPlannedSchedule().completedTasks(checkDate);
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
