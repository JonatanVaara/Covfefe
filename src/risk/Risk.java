package risk;

public class Risk {
	
	private String riskName;
	private long impact;
	private long probability;
	
	public Risk(String riskName, long impact, long probability) {
		
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

	public long getImpact() {
		return impact;
	}

	public void setImpact(long impact) {
		this.impact = impact;
	}

	public long getProbability() {
		return probability;
	}

	public void setProbability(long probability) {
		this.probability = probability;
	}
	
	public long risk() {
		
		return this.getImpact() * this.getProbability();
	}
	
	@Override
	public String toString() {
		String print = "Risk: " + this.getRiskName() + "\nImpact: " + this.getImpact() + "\nProbability: " + this.getProbability() + "\n"; 
		return print;
	}

}
