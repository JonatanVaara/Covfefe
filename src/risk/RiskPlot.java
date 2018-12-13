package risk;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class RiskPlot {
	
	private ArrayList<Risk> risks;
	private JFreeChart chart;
	private XYSeriesCollection dataset;
	private XYSeries zoneSeries;
	
	public RiskPlot(RiskMatrix riskMatrix) {
		if (riskMatrix == null) {
			throw new NullPointerException("Risk matrix == null");
		}
		
		risks = new ArrayList<>();
		
		zoneSeries = new XYSeries("Risk Severity");
		zoneSeries.add(0, 0);
		
		dataset = new XYSeriesCollection();
		dataset.addSeries(zoneSeries);
		
		chart = ChartFactory.createXYLineChart(
			"Risk Matrix",
			"Probability",
			"Severity",
			dataset,
			PlotOrientation.VERTICAL,
			true, false, false
		);
		
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Paint getItemPaint(int series, int item) {
				if (series == getZoneSeries()) {
					Color color;
					switch (item) {
						case 0:
							color = new Color(0xFF, 0x00, 0x00, 0x7F);
							break;

						case 1:
						case 2:
							color = new Color(0xFF, 0xFF, 0x00, 0x7F);
							break;

						case 3:
							color = new Color(0x00, 0xFF, 0x00, 0x7F);
							break;

						default:
							color = Color.black;
							break;
					}
					return color;
				} else {
					return super.getItemPaint(series, item);
				}
			}

			@Override
			protected void drawSecondaryPass(
				Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, int series, int item, ValueAxis domainAxis,
				Rectangle2D dataArea, ValueAxis rangeAxis, CrosshairState crosshairState, EntityCollection entities
			) {
				if (series == getZoneSeries()) {
					if (!getItemShapeVisible(series, item)) {
						return;
					}
					
					RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
			        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
			        
					double xExtent = dataArea.getWidth() / 2;
					double yExtent = dataArea.getHeight() / 2;

					double xCenter = domainAxis.getRange().getCentralValue();
					double yCenter = rangeAxis.getRange().getCentralValue();
					double yTop = yCenter * 2;
					
					for (int i = 0; i < 4; ++i) {
						double x = domainAxis.valueToJava2D((i & 1) != 0 ? 0 : xCenter, dataArea, xAxisLocation);
						double y = rangeAxis.valueToJava2D((i & 2) != 0 ? yCenter : yTop, dataArea, yAxisLocation);
						
						Shape shape = new Rectangle((int)x, (int)y, (int)xExtent, (int)yExtent);
						if (shape.intersects(dataArea)) {
							if (getItemShapeFilled(series, item)) {
								if (getUseFillPaint()) {
									g2.setPaint(getItemFillPaint(series, i));
								} else {
									g2.setPaint(getItemPaint(series, i));
								}
								g2.fill(shape);
							}
						}
					}
				}
				super.drawSecondaryPass(
					g2, plot, dataset, pass, series, item, domainAxis, dataArea, rangeAxis, crosshairState, entities);
			}
		};

		renderer.setDefaultLinesVisible(false);

		// Hide the gridlines
		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);

		//
		plot.setRenderer(renderer);
		
		// Add risks
		for (Risk risk : riskMatrix.getRisks()) {
			addRisk(risk);
		}
	}
	
	public ChartPanel show(int width, int height) {
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(width, height));
		return chartPanel;
	}
	
	/*
	 * This gets the series id for risk zones
	 * Zone series are always at the end for rendering purposes
	 */
	public int getZoneSeries() {
		if (dataset == null) {
			return 0;
		}
		return dataset.getSeriesCount() - 1;
	}
	
	public void addRisk(Risk risk) {
		if (risk != null) {
			risks.add(risk);
			
			final int zoneIndex = getZoneSeries();
			final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
			
			// Update dataset & chart
			dataset.removeSeries(zoneIndex);
			renderer.setSeriesVisibleInLegend(zoneIndex, true);
			
			XYSeries series = new XYSeries(risk.getName());
			series.add(risk.getProbability(), risk.getImpact());
			dataset.addSeries(series);
			
			dataset.addSeries(zoneSeries);
			renderer.setSeriesVisibleInLegend(zoneIndex + 1, false);
		}
	}
	
	public boolean removeRisk(String riskName) {
		boolean removed = false;
		for (int i = 0; i < risks.size() && !removed; ++i) {
			Risk risk = risks.get(i);
			if (risk.getName().equals(riskName)) {
				risks.remove(i);
				dataset.removeSeries(i);
				removed = true;
			}
		}
		return removed;
	}
}
