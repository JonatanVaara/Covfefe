package main;

import java.util.ArrayList;

import jsonReader.JsonReader;
import risk.Risk;
import risk.RiskMatrix;

public class Menu {
	
	public static void main(String[] args) {
		final String type = "Jonatan";
		if (type.equals("Jonatan")) {
			// DETTA ÄR JONATANS MAIN SERU
			
			JsonReader x = new JsonReader();
			
			ArrayList<Risk> riskList = x.riskReader();
		
			RiskMatrix riskMatrix = new RiskMatrix(riskList);
			System.out.println(riskMatrix.printRiskMatrix());
			
			System.out.println("Hej");
		} else if(type.equals("Christian")) {
			//DETTA ÄR CHRISTIANS DEL
			System.out.println("Jag heter christian");
		}
	}
}
