import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BankFrame {
	
	private JFrame frame;
	private JLabel coinLabel, outputLabel;
	private JTextField coinField;
	private JButton button;
	private BankAccount bank = new BankAccount();
	
	public BankFrame() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("Frame");
		frame.setSize(450, 200);
		createTextField();
		
	}

}
