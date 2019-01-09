package risk;

import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class RiskGraph extends JFrame {

	private ArrayList<Risk> riskList;
	private static final int LOW_RISK = 15;
	private static final int HIGH_RISK = 25;
	private static final long serialVersionUID = 1L;



	public RiskGraph(ArrayList<Risk> riskList) {
		super("Risk Matrix");

		this.riskList = riskList;
	}

	
	//------------------
	//---CUSTOM RENDERER (for customized bar Colors)
	//------------------

	
	class CustomRenderer extends BarRenderer {
		private static final long serialVersionUID = 1L;

		private Paint[] colors;

		public CustomRenderer(final Paint[] colors) {
			this.colors = colors;

		}

		public Paint getItemPaint(final int row, final int column) {
			return this.colors[column % this.colors.length];
		}

	}
	
	//	--------------------
	//	----PLOT CREATION
	//	--------------------

		public void plotChart() {

		CategoryDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createBarChart("Risk Identifcation Matrix", "Description", "Risk (Impact * Probability)", dataset,
				PlotOrientation.HORIZONTAL, false, true, false);

		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setNoDataMessage("NO DATA!");
		
		//Customized Bar color (depending on risk value)

		Paint[] colors = new Paint[dataset.getColumnCount()];
		for (int i = 0; i < riskList.size(); i++) {
			if (riskList.get(i).getCalculatedRisk() <= LOW_RISK) {
				colors[i] = Color.GREEN;
			} else if (riskList.get(i).getCalculatedRisk() >= HIGH_RISK) {
				colors[i] = Color.RED;
			} else {
				colors[i] = Color.YELLOW;
			}
		}

		final CategoryItemRenderer renderer = new CustomRenderer(colors);

		plot.setRenderer(renderer);

		ChartPanel panel = new ChartPanel(chart);

		setContentPane(panel);

		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

		//	--------------------
		//	----DATASET CREATION
		//	--------------------
	
	public CategoryDataset createDataset() {

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Risk risk : riskList) {
			dataset.setValue(risk.getCalculatedRisk(), "", risk.getName());
		}

		return dataset;
	}

}