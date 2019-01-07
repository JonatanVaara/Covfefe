package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import menu.Menu;
import menu.gifPlay;
import projectManagement.ProjectManagement;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class MenuUI {

	private Menu menu = new Menu();
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
		
		JLabel lblTopIcon = new JLabel(new ImageIcon("C:\\Users\\JonatanVaara\\Documents\\GitHub\\Covfefe\\coolMenu.png"));
		lblTopIcon.setBounds(0, 0, 1032, 136);
		//frame.add(new JLabel(new ImageIcon("")));
		frame.getContentPane().add(lblTopIcon);
		
		JButton btnRiskMatrix = new JButton("Risk Matrix");
		btnRiskMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			menu.printRiskMatrix();
			}
		});
		btnRiskMatrix.setBounds(29, 177, 115, 29);
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
		btnEarnedValue.setBounds(179, 177, 157, 29);
		frame.getContentPane().add(btnEarnedValue);
		
		JButton btnScheduleVariance = new JButton("Schedule Variance");
		btnScheduleVariance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printSVChart();
			}
		});
		btnScheduleVariance.setBounds(373, 177, 180, 29);
		frame.getContentPane().add(btnScheduleVariance);
		
		JButton btnCostVariance = new JButton("Cost Variance");
		btnCostVariance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printCVChart();
			}
		});
		btnCostVariance.setBounds(373, 236, 180, 29);
		frame.getContentPane().add(btnCostVariance);
		
		JButton btnTimespent = new JButton("Time Spent");
		btnTimespent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Not yet implemented");
			}
		});
		btnTimespent.setBounds(600, 177, 157, 29);
		frame.getContentPane().add(btnTimespent);
		
		JButton btnAllTimeSpent = new JButton("All Time Spent");
		btnAllTimeSpent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.getMemberContribution();
			}
		});
		btnAllTimeSpent.setBounds(600, 236, 157, 29);
		frame.getContentPane().add(btnAllTimeSpent);
		
		JButton btnPlotSchedule = new JButton("Plot Schedule");
		btnPlotSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pm.printSchedule();
			}
		});
		btnPlotSchedule.setBounds(803, 177, 143, 29);
		frame.getContentPane().add(btnPlotSchedule);
	}

}
