package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//import menu.Menu;
import menu.gifPlay;
import projectManagement.ProjectManagement;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JTree;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Font;

public class MenuUI {

//	private Menu menu = new Menu();
	private ProjectManagement pm = new ProjectManagement();
	private JFrame frame;
	private JLabel lblTopIcon;

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

		JLabel lblTopIcon = new JLabel(
				new ImageIcon("TopTitel3.png"));
		lblTopIcon.setBounds(0, 0, 1000, 120);
		// frame.add(new JLabel(new ImageIcon("")));
		frame.getContentPane().add(lblTopIcon);

		JButton btnRiskMatrix = new JButton("Risk Matrix");
		btnRiskMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printRiskMatrix();
			}
		});
		btnRiskMatrix.setBounds(698, 212, 180, 29);
		frame.getContentPane().add(btnRiskMatrix);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				try {
//					gifPlay.goodBye();
//				} catch (MalformedURLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				try {
//					TimeUnit.SECONDS.sleep(5);
//				} catch (InterruptedException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}

				System.exit(0);
			}
		});
		btnClose.setBounds(902, 540, 115, 29);
		frame.getContentPane().add(btnClose);

		JButton btnEarnedValue = new JButton("Earned Value");
		btnEarnedValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printEVChart();
			}
		});
		btnEarnedValue.setBounds(361, 335, 180, 29);
		frame.getContentPane().add(btnEarnedValue);

		JButton btnScheduleVariance = new JButton("Schedule Variance");
		btnScheduleVariance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printSVChart();
			}
		});
		btnScheduleVariance.setBounds(361, 277, 180, 29);
		frame.getContentPane().add(btnScheduleVariance);

		JButton btnCostVariance = new JButton("Cost Variance");
		btnCostVariance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printCVChart();
			}
		});
		btnCostVariance.setBounds(361, 212, 180, 29);
		frame.getContentPane().add(btnCostVariance);

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

		JButton btnAllTimeSpent = new JButton("Time Spent All");
		btnAllTimeSpent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.getMemberContribution();
			}
		});
		btnAllTimeSpent.setBounds(15, 335, 157, 29);
		frame.getContentPane().add(btnAllTimeSpent);

		JButton btnPlotSchedule = new JButton("Plot Schedule");
		btnPlotSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printSchedule();
			}
		});
		btnPlotSchedule.setBounds(698, 277, 180, 29);
		frame.getContentPane().add(btnPlotSchedule);

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
		
		JLabel lblCharts = new JLabel("Member Info");
		lblCharts.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCharts.setBounds(10, 136, 261, 46);
		frame.getContentPane().add(lblCharts);
		
		JLabel lblBudget = new JLabel("KPI");
		lblBudget.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBudget.setBounds(426, 136, 261, 46);
		frame.getContentPane().add(lblBudget);
		
		JLabel lblPlanning = new JLabel("Planning");
		lblPlanning.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPlanning.setBounds(715, 136, 261, 46);
		frame.getContentPane().add(lblPlanning);

	}

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
