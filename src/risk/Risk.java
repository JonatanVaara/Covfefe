package risk;

public class Risk {
	
	private String name;
	private long impact;
	private long probability;
	
	public Risk(String name, long impact, long probability) {
		this.name = name;
		this.impact = impact;
		this.probability = probability;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	//---------------------------------------------
	//---CALCULATES RISK FROM PROBABILITY and IMPACT
	//---------------------------------------------
	
	public long getCalculatedRisk() {
		return this.getImpact() * this.getProbability();
	}
	
	@Override
	public String toString() {
		String print = "Risk: " + this.getName() + "\nImpact: " + this.getImpact() + "\nProbability: " + this.getProbability() + "\n"; 
		return print;
	}
}
