import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class FirstPage extends JFrame {

	private JPanel contentPane;
	private LoginFrame loginFrame;
	private SignUpFrame signUpFrame;
	private Connection conn;
	
	public FirstPage(Connection conn) {
		this.conn = conn;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Welcome to TimeManager");
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewJgoodiesLabel, BorderLayout.SOUTH);
		lblNewJgoodiesLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loginFrame = new LoginFrame(conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				loginFrame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(LoginButton);
		LoginButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		
		JButton SignUpButton = new JButton("Sign Up");
		SignUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					signUpFrame = new SignUpFrame(conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				signUpFrame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(SignUpButton);
		SignUpButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
	}

}
