package risk;

public class Risk {
	
	private String riskName;
	private int impact;
	private int probability;
	
	public Risk(String riskName, int impact, int probability) {
		
		this.riskName = riskName;
		this.impact = impact;
		this.probability = probability;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public int getImpact() {
		return impact;
	}

	public void setImpact(int impact) {
		this.impact = impact;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}
	
	public int risk() {
		
		return this.getImpact() * this.getProbability();
	}
	
	@Override
	public String toString() {
		String print = "Risk: " + this.getRiskName() + "\nImpact: " + this.getImpact() + "\nProbability: " + this.getProbability() + "\n"; 
		return print;
	}

}
