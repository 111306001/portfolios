import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeFrame extends JFrame{
	
	private JLabel label;
	//your code
	public HomeFrame(){
		this.setTitle("Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,300);
		label = new JLabel();
		label.setFont(new Font("TimesRoman", Font.PLAIN, 60));
		JPanel helloPanel = new JPanel();
		helloPanel.add(label);
		helloPanel.setPreferredSize(new Dimension(500, 100));
		this.setLayout(new BorderLayout(20, 60));
		this.getContentPane().add(helloPanel, BorderLayout.CENTER);
		this.getContentPane().add(new JPanel(), BorderLayout.SOUTH);
		this.getContentPane().add(new JPanel(), BorderLayout.NORTH);
	}
	public void setLabelText(String name) {
		label.setText("Hello " + name + "!");
	}
}