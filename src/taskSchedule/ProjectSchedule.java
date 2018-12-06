package taskSchedule;

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
	
	public ProjectSchedule(Schedule plannedSchedule, Schedule currentSchedule) {
		super("Project Schedule");
		
		this.plannedSchedule = plannedSchedule;
		this.currentSchedule = currentSchedule;
	}
	
	public void plotChart() {

		IntervalCategoryDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createGanttChart("Project Schedule", "Tasks", "Timeline", dataset);

		ChartPanel panel = new ChartPanel(chart);

		setContentPane(panel);

		this.setSize(800, 400);
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

}
