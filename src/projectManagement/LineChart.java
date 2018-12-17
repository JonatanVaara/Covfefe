package projectManagement;

import org.jfree.chart.ChartPanel;

import java.util.Date;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends JFrame {

	private static final long serialVersionUID = 1L;
	ArrayList<Date> dateList;
	private String chartName;

	public LineChart(ArrayList<Date> listOfDates, String chartName) {
		super("Date' Earned Value");

		this.dateList = listOfDates;
		this.chartName = chartName;
	}

	public void plotChart() {

		CategoryDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createLineChart(chartName, "Value", chartName, dataset,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel panel = new ChartPanel(chart);

		setContentPane(panel);

		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// this.pack();
		this.setVisible(true);
	}

//	public CategoryDataset createDataset() {
//
//		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		ProjectManagement projectManagement = new ProjectManagement();
//		if (this.chartName == "Earned Value") {
//			for (Date date : dateList) {
//				dataset.setValue(projectManagement.getEarnedValue(date), date, "Earned Value");
//			}
//		} else if (this.chartName == "Schedule Variance") {
//			for (Date date : dateList) {
//				dataset.setValue(projectManagement.getScheduleVariance(date), date, "Schedule Variance");
//			}
//
//		} else {// Cost Variance
//			for (Date date : dateList) {
//				dataset.setValue(projectManagement.getCostVariance(date), date, "Cost Variance");
//			}
//		}
//		return dataset;
//	}

	public CategoryDataset createDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ProjectManagement projectManagement = new ProjectManagement();
		if (this.chartName == "Earned Value") {
			for (Date date : dateList) {
				dataset.addValue(projectManagement.getEarnedValue(date), "Earned Value", date);
			}
		} else if (this.chartName == "Schedule Variance") {
			for (Date date : dateList) {
				dataset.addValue(projectManagement.getScheduleVariance(date), "Schedule Variance", date);
			}

		} else {// Cost Variance
			for (Date date : dateList) {
				dataset.addValue(projectManagement.getCostVariance(date), "Cost Variance", date);
			}
		}
		return dataset;
	}

}
