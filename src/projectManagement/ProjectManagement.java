package projectManagement;

import java.util.ArrayList;
import risk.RiskMatrix;

public class ProjectManagement {
	
	private ArrayList<Member> memberList; 
	private Schedule plannedSchedule;
	private Schedule currentSchedule;
	private RiskMatrix riskMatrix;

	public ProjectManagement() {
		
		this.memberList = new ArrayList<Member>();
		this.plannedSchedule = new Schedule();
		this.currentSchedule = new Schedule();
		this.riskMatrix = new RiskMatrix();
		
	}
	
	public double getEarnedValue() {
	return 5.5;	
	}
	
	
}
