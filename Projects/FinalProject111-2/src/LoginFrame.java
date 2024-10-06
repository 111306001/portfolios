import java.awt.EventQueue;
import java.sql.*;

import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.Font;

public class LoginFrame extends JFrame{
	private JTextField tfUserID;
	private JPasswordField tfPassword;
	private JButton btnLogin;
	private Connection conn;
	private Statement stat;
	private SQLcommon sqlc = new SQLcommon();
	private Home0504 home;
	
	private JPanel panel = (JPanel) this.getContentPane();
	public LoginFrame(Connection conn) throws SQLException {
		this.conn = conn;
		stat = conn.createStatement();
		createTextField();
		createButton();
		createLayout();
		setTitle("Login");
		setSize(600,400);
	}
	
	public void createTextField() {
		tfUserID = new JTextField(20);
		tfPassword =  new JPasswordField(20);
		tfUserID.addKeyListener(new KeyAdapter(){ 
			public void keyTyped(KeyEvent e) { 
				int keyChar = e.getKeyChar(); 
			if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){ 
			}
			else {
				e.consume();
			}
			}
		}); 	
	}
	
	public void createButton() {
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idStr = tfUserID.getText();
				String password = String.valueOf(tfPassword.getPassword());
				String warning = "";
				JFrame Warning = new JFrame();
				try {
					if(idStr.length()!=9) {
						warning = "請輸入學號（9位數字）";
						throw new Exception(warning);
					}
					int id = Integer.parseInt(idStr);
					stat.execute(sqlc.ifExist("ID", id, "User"));
					String ifExist = sqlc.showResultSet(stat.getResultSet());
					if(!ifExist.equals("1")) {
							warning = "查無學號，請檢查是否輸入正確";
				        	throw new Exception(warning);
					}
					stat.execute(sqlc.select("Password", "ID", id, "User"));
					String targetPassword = sqlc.showResultSet(stat.getResultSet());
					if(password.equals(targetPassword)) {
						JOptionPane.showMessageDialog(Warning,"登入成功！",
                                "提醒", JOptionPane.INFORMATION_MESSAGE);
						home = new Home0504(conn,id);
						home.setVisible(true);
						dispose();
					}else {
						warning = "密碼錯誤";
			        	throw new Exception(warning);
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(Warning,warning,
                            "錯誤", JOptionPane.ERROR_MESSAGE);
				   warning="未知錯誤，請勿輸入影響程式的字符";
				}
			}
			
		});
	}
	
	private void createLayout() {
		JPanel uPanel = new JPanel();
		JLabel lblUserid = new JLabel("User  ID  :");
		lblUserid.setFont(new Font("SansSerif", Font.PLAIN, 12));
		uPanel.add(lblUserid);
		uPanel.add(tfUserID);
		uPanel.setPreferredSize(new Dimension(500, 40));
		JPanel pPanel = new JPanel();
		JLabel label = new JLabel("Password:");
		label.setFont(new Font("SansSerif", Font.PLAIN, 12));
		pPanel.add(label);
		pPanel.add(tfPassword);
		pPanel.setPreferredSize(new Dimension(500, 40));
		JPanel bPanel = new JPanel();
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirstPage firstPage = new FirstPage(conn);
				firstPage.setVisible(true);
				dispose();
			}
		});
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		bPanel.add(btnCancel);
		bPanel.add(btnLogin);
		bPanel.setPreferredSize(new Dimension(500, 40));
		JPanel allPannel = new JPanel();
		allPannel.add(uPanel);
		allPannel.add(pPanel);
		allPannel.add(bPanel);
		getContentPane().setLayout(new BorderLayout(20, 40));
		getContentPane().add(new JPanel(), BorderLayout.NORTH);
		getContentPane().add(allPannel, BorderLayout.CENTER);
		getContentPane().add(new JPanel(), BorderLayout.SOUTH);
	}
}