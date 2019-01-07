package menu;


import java.net.MalformedURLException;

import java.awt.Component;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import projectManagement.ProjectManagement;
import risk.RiskPlot;
import projectManagement.LineChart;

public class Menu {

	private static final int RISK_MATRIX = 1;
	private static final int EARND_VALUE = 2;
	private static final int SCHDULED_VARIENCE = 3;
	private static final int COST_VARIENCE = 4;
	private static final int TIME_SPENT_MEMBER = 5;
	private static final int TASKS_PARTICIPATED_MEMBER = 6;
	private static final int ALL_TIME_SPENT = 7;
	private static final int PLOT_SCHEDULE = 8;
	private static final int QUIT = 9;

	private ProjectManagement projectManagement;
	private Scanner input;

	public Menu() {
		input = new Scanner(System.in);
		projectManagement = new ProjectManagement();
		projectManagement.checkTasks(); // Checks to see that all tasks are the same, throws a runtime exception, ignores case
	}

	public void run(int option)  {
		do {
			//printMenu();
			//System.out.println(" Enter your option: ");

			
			input.nextLine();

			System.out.println();
			switch (option) {
				case RISK_MATRIX:
					printRiskMatrix();
					break;

				case EARND_VALUE:
					projectManagement.printEVChart();
					break;

				case SCHDULED_VARIENCE:
					projectManagement.printSVChart();
					break;

				case COST_VARIENCE:
					projectManagement.printCVChart();
					break;

				case TIME_SPENT_MEMBER:
					System.out.println("Please type member ID here (e.g. M001: ");
					String MemberID1 = input.nextLine();
					try {projectManagement.getMemberTimeAllocated(MemberID1);	}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case TASKS_PARTICIPATED_MEMBER:
					System.out.println("Please type member ID here (e.g. M001: ");
					String MemberID2 = input.nextLine();
					try {projectManagement.getMemberAllocatedTasks(MemberID2);}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;

				case ALL_TIME_SPENT:
					projectManagement.getMemberContribution();
					break;


			case PLOT_SCHEDULE:
				this.projectManagement.printSchedule();
				break;

			case QUIT:
				try {
				gifPlay.goodBye();
				}
				catch (MalformedURLException e) {
					e.printStackTrace();
				}
				System.out.println("Good Bye");
				break;

			}
		} while (option != QUIT);
	}

	private void printMenu() {
		System.out.println(" 1. Risk Matrix");
		System.out.println(" 2. Earned Value");
		System.out.println(" 3. Scheduled Variance");
		System.out.println(" 4. Cost Variance");
		System.out.println(" 5. Time Spent of a certain Member");
		System.out.println(" 6. Tasks participation of a certain Member");		
		System.out.println(" 7. All Time Spent");
		System.out.println(" 8. Plot Schedule");
		System.out.println(" 9. Quit");
	}
	
	public JFrame show(Component component) {
		JFrame frame;
		if (component != null) {
			frame = new JFrame();
			frame.getContentPane().add(component);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} else {
			frame = null;
		}
		return frame;
	}
	
	public void printRiskMatrix() {
		RiskPlot plot = new RiskPlot(projectManagement.getRiskMatrix());
		show(plot.show(800, 600));
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.run(0);
	}
}
