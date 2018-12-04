package menu;

import java.util.Scanner;

public class Menu {

	private static final int RISK_MATRIX = 1;
	private static final int EARND_VALUE = 2;
	private static final int SCHDULED_VARIENCE = 3;
	private static final int COST_VARIENCE = 4;
	private static final int TIME_SPENT = 5;
	private static final int ALL_TIME_SPENT = 6;
	private static final int PLOT_SCHEDULE = 7;
	private static final int QUIT = 8;

	// ProjectManagement PM
	Scanner input;

	private Menu() {
		input = new Scanner(System.in);
	}

	private void run() {
		int option;
		// ProjectManagement PM = new ProjectManagement();
		do {
			printMenu();
			System.out.println(" Enter your option: ");

			option = input.nextInt();
			input.nextLine();
			System.out.println("");

			switch (option) {

			case RISK_MATRIX:
				// printRiskMatrix();
				break;

			case EARND_VALUE:
				// printEarndValue();
				break;

			case SCHDULED_VARIENCE:
				// printSchduledVarience();
				break;

			case COST_VARIENCE:
				// printCostVarience();
				break;

			case TIME_SPENT:
				// printTimeSpent();
				break;

			case ALL_TIME_SPENT:
				// printAllTimeSpent();
				break;

			case PLOT_SCHEDULE:
				// printPlotSchedule();
				break;

			case QUIT:
				System.out.println(" Hej d�!!");
				break;

			}

		} while (option != QUIT);

	}

	private void printMenu() {

		System.out.println(" 1. Risk Matrix");
		System.out.println(" 2. Earned Value");
		System.out.println(" 3. Schduled Varaince");
		System.out.println(" 4. Cost Variance");
		System.out.println(" 5. Time Spent");
		System.out.println(" 6. All Time Spent");
		System.out.println(" 7. plot Schedule");
		System.out.println(" 8. Quit");
	}

	public static void main(String[] arge) {
		Menu menu = new Menu();
		menu.run();

	}

}
