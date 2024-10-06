import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{
	
	private User user = new User();
	private HomeFrame frame;
	private JTextField tfUserName, tfPassword;
	private JButton btnEnroll, btnLogin;
	private JPanel panel = (JPanel) this.getContentPane();
	
	public LoginFrame(){
		frame = new HomeFrame();
		createTextField();
		button();
		createLayout();
		setSize(400, 300);
		setTitle("Login");
	}
	private void createTextField() {
		tfUserName = new JTextField(20);
		tfPassword = new JTextField(20);
	}
	private void createLayout() {
		
		JPanel uPanel = new JPanel();
		uPanel.add(new JLabel("Username:"));
		uPanel.add(tfUserName);
		uPanel.setPreferredSize(new Dimension(500, 40));
		add(uPanel);
		
		JPanel pPanel = new JPanel();
		pPanel.add(new JLabel("Password:"));
		pPanel.add(tfPassword);
		pPanel.setPreferredSize(new Dimension(500, 40));
		
		JPanel bPanel = new JPanel();
		bPanel.add(btnEnroll);
		bPanel.add(btnLogin);
		bPanel.setPreferredSize(new Dimension(500, 40));
		
		JPanel allPannel = new JPanel();
		allPannel.add(uPanel);
		allPannel.add(pPanel);
		allPannel.add(bPanel);
		
		setLayout(new BorderLayout(20, 40));
		getContentPane().add(new JPanel(), BorderLayout.NORTH);
		getContentPane().add(allPannel, BorderLayout.CENTER);
		getContentPane().add(new JPanel(), BorderLayout.SOUTH);
		
	}

	private void button() {
		btnEnroll = new JButton("Enroll");
		class enrollListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					user.add(tfUserName.getText(), tfPassword.getText());
				} catch (PasswordError e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel, e1, "Error", JOptionPane.ERROR_MESSAGE );
				} catch (UserError e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel, e1, "Error", JOptionPane.ERROR_MESSAGE );
				}
			}
		};
		btnEnroll.addActionListener(new enrollListener());
		
		btnLogin = new JButton("Login");
		class loginListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				boolean pass = true;
				try {
					user.checkUserExist(tfUserName.getText());
				} catch (UserError e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel, e1, "Error", JOptionPane.ERROR_MESSAGE );
					pass = false;
				}
				try {
					user.checkPassword(tfUserName.getText(), tfPassword.getText());
				} catch (PasswordError e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel, e1, "Error", JOptionPane.ERROR_MESSAGE );
					pass = false;
				}
				if(pass == true) {
					frame.setLabelText(tfUserName.getText());
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
				
			}
		};
		btnLogin.addActionListener(new loginListener());
	}
}