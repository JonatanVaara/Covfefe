package risk;

import java.util.ArrayList;

public class RiskMatrix {

	private static final String NEW_LINE = System.lineSeparator();
	private ArrayList<Risk> riskList;

	public RiskMatrix(ArrayList<Risk> riskList) {
		this.riskList = riskList;
	}
	
	public ArrayList<Risk> getRisks() {
		return new ArrayList<>(this.riskList);
	}
	
	
	//----------------
	//---GRAPH NEW----
	//----------------
	
	public void getPlotRisks() {
		RiskGraph plotRisks = new RiskGraph(riskList);
		plotRisks.plotChart();
		}
	
	//----------------
	//---TABLE -------
	//----------------
	
	public String printRiskMatrix() {
		String print = ("    Name    \t    Impact\t|    Probability\t|    Risk\t|" + NEW_LINE);
		for (Risk risk : riskList) {
			print += ("    " + risk.getName() + "\t    " + risk.getImpact() + "\t\t|    " + risk.getProbability()
					+ "\t\t\t|    " + risk.getCalculatedRisk() + "\t\t|" + NEW_LINE);
		}
		return print;
	}
}
