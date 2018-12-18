package menu;


import java.net.MalformedURLException;

import java.awt.Component;

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
	private static final int TIME_SPENT = 5;
	private static final int ALL_TIME_SPENT = 6;
	private static final int PLOT_SCHEDULE = 7;
	private static final int QUIT = 8;

	private ProjectManagement projectManagement;
	private Scanner input;

	private Menu() {
		input = new Scanner(System.in);
		projectManagement = new ProjectManagement();
		projectManagement.checkTasks(); // Checks to see that all tasks are the same, throws a runtime exception, ignores case
	}

	private void run()  {
		int option;
		do {
			printMenu();
			System.out.println(" Enter your option: ");

			option = input.nextInt();
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

				case TIME_SPENT:
					// printTimeSpent();
					break;

				case ALL_TIME_SPENT:
					// printAllTimeSpent();
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
		System.out.println(" 5. Time Spent");
		System.out.println(" 6. All Time Spent");
		System.out.println(" 7. Plot Schedule");
		System.out.println(" 8. Quit");
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
	
	private void printRiskMatrix() {
		RiskPlot plot = new RiskPlot(projectManagement.getRiskMatrix());
		show(plot.show(800, 600));
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.run();
	}
}
