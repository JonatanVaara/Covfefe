package member;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class MemberGraph extends JFrame {
	
	ArrayList<Member> memberList;
	
	public MemberGraph (ArrayList<Member> listOfMembers) {
		super("Members' contribution");
		
		this.memberList = listOfMembers;
	}
	
	public void plotChart (){
		
		
		CategoryDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createBarChart("Members' Contribution", "Members", "Hours", dataset, PlotOrientation.VERTICAL, false, true, false);

		ChartPanel panel = new ChartPanel(chart);

		setContentPane(panel);

		this.setSize(900, 500);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// this.pack();
		this.setVisible(true);
	}
	
public CategoryDataset createDataset() {
		
    
    
    
    
    final DefaultCategoryDataset dataset = 
    new DefaultCategoryDataset( );  

    
    for (Member member : memberList)
    {
    	dataset.setValue(member.getTotalTimeAllocated(), "" , member.getName());
    }	
		
		return dataset;
	}

}
