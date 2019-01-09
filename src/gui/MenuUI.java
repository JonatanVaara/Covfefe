package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



import projectManagement.ProjectManagement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class MenuUI {

//	private Menu menu = new Menu();
	private ProjectManagement pm = new ProjectManagement();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUI window = new MenuUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1054, 641);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(frame.getContentPane(), popupMenu);
		
		//The top picture
		JLabel lblTopIcon = new JLabel(
				new ImageIcon("TopTitel3.png"));
		lblTopIcon.setBounds(0, 0, 1000, 120);
		frame.getContentPane().add(lblTopIcon);
		
		//Risk Matrix button
		JButton btnRiskMatrix = new JButton("Risk Matrix");
		btnRiskMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printRiskMatrix();
			}
		});
		btnRiskMatrix.setBounds(698, 212, 180, 29);
		frame.getContentPane().add(btnRiskMatrix);
		
		// Close Button
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		btnClose.setBounds(902, 540, 115, 29);
		frame.getContentPane().add(btnClose);

		//Earned Value Button
		JButton btnEarnedValue = new JButton("Earned Value");
		btnEarnedValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printChart("Earned Value");
			}
		});
		btnEarnedValue.setBounds(361, 335, 180, 29);
		frame.getContentPane().add(btnEarnedValue);

		// Schedule Variance button
		JButton btnScheduleVariance = new JButton("Schedule Variance");
		btnScheduleVariance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printChart("Schedule Variance");
			}
		});
		btnScheduleVariance.setBounds(361, 277, 180, 29);
		frame.getContentPane().add(btnScheduleVariance);

		// Cost Variance button
		JButton btnCostVariance = new JButton("Cost Variance");
		btnCostVariance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printChart("Cost Variance");
			}
		});
		btnCostVariance.setBounds(361, 212, 180, 29);
		frame.getContentPane().add(btnCostVariance);

		// Time Spent button
		JButton btnTimespent = new JButton("Time Spent");
		btnTimespent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = JOptionPane.showInputDialog(null, "Enter User ID\n"+
						"Users:\n"+pm.getUserIDName()+"\n"
			);
				try {JOptionPane.showMessageDialog(null, "User: " + ID + " has so far spent " + pm.getMemberTimeAllocated(ID) + " hours on this project");
			} catch (Exception x) {
				JOptionPane.showMessageDialog(null, x.getMessage());
			}
			}
		});

		btnTimespent.setBounds(15, 212, 157, 29);
		frame.getContentPane().add(btnTimespent);

		// All Time Spent button
		JButton btnAllTimeSpent = new JButton("Time Spent All");
		btnAllTimeSpent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.getMemberContribution();
			}
		});
		btnAllTimeSpent.setBounds(15, 335, 157, 29);
		frame.getContentPane().add(btnAllTimeSpent);

		// Plot Schedule button
		JButton btnPlotSchedule = new JButton("Plot Schedule");
		btnPlotSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printSchedule();
			}
		});
		btnPlotSchedule.setBounds(698, 277, 180, 29);
		frame.getContentPane().add(btnPlotSchedule);

		// Task Participation button
		JButton btnTaskParticipation = new JButton("Task Participation");
		btnTaskParticipation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = JOptionPane.showInputDialog(null, "Enter User ID\n"+"Users:\n"+pm.getUserIDName()+"\n");
				try{JOptionPane.showMessageDialog(null, "User: " + ID + " has so far worked on these tasks: \n" + pm.getMemberAllocatedTasks(ID));
				} catch (Exception x){
					JOptionPane.showMessageDialog(null, x.getMessage());
				}

			}
		});
		btnTaskParticipation.setBounds(15, 277, 157, 29);
		frame.getContentPane().add(btnTaskParticipation);
		
		//The next three labels are only the htext labels "Charts", "KPI" and "Planning"
		
		JLabel lblCharts = new JLabel("Member Info");
		lblCharts.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCharts.setBounds(10, 136, 261, 46);
		frame.getContentPane().add(lblCharts);
		
		JLabel lblKPI = new JLabel("KPI");
		lblKPI.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblKPI.setBounds(426, 136, 261, 46);
		frame.getContentPane().add(lblKPI);
		
		JLabel lblPlanning = new JLabel("Planning");
		lblPlanning.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPlanning.setBounds(715, 136, 261, 46);
		frame.getContentPane().add(lblPlanning);

	}
	
	// Things so that input is noticed and responded to

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
