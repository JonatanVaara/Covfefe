package risk;

import java.util.ArrayList;

public class RiskMatrix {

	private static final String NEW_LINE = System.lineSeparator();

	private ArrayList<Risk> riskList;

	public RiskMatrix(ArrayList<Risk> riskList) {

		this.riskList = riskList;

	}

	public String matrixPrint() {
		String print = ("    Name    \t    Impact\t|    Probability\t|    Risk\t|" + NEW_LINE);
		for (Risk risk : riskList) {
			print += ("    " + risk.getRiskName() + "\t    " + risk.getImpact() + "\t\t|    " + risk.getProbability()
					+ "\t\t\t|    " + risk.risk() + "\t\t|" + NEW_LINE);
		}

		return print;

	}

}
