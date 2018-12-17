package menu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class gifPlay {
	public static void goodBye() throws MalformedURLException {
		/*
		 * URL url = new URL("giphy.gif"); Icon icon = new ImageIcon(url); JLabel label
		 * = new JLabel(icon);
		 * 
		 * JFrame f = new JFrame("Animation"); f.getContentPane().add(label);
		 * f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); f.pack();
		 * f.setLocationRelativeTo(null); f.setVisible(true);
		 */

		Icon icon = new ImageIcon("giphy.gif");
		JLabel gif = new JLabel(icon);

		JFrame f = new JFrame("Load Image Sample");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.getContentPane().add(gif);
		f.pack();
		f.setVisible(true);
	}
}
