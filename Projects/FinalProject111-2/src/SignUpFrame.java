import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

	import java.awt.EventQueue;
	import java.sql.*;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.JLabel;
	import com.jgoodies.forms.factories.DefaultComponentFactory;
	import javax.swing.JTextField;
	import java.awt.GridLayout;
	import java.awt.FlowLayout;
	import javax.swing.BoxLayout;
	import java.awt.BorderLayout;
	import javax.swing.SpringLayout;
	import javax.swing.JButton;
	import javax.swing.SwingConstants;

	import java.awt.BorderLayout;
	import java.awt.Dimension;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
	import java.awt.Font;

	public class SignUpFrame extends JFrame{
		private JTextField tfUserName,  tfUserID ;
		private JPasswordField tfPasswordCheck,tfPassword;
		private JButton btnSignUp;
		private Connection conn;
		private JPanel panel = (JPanel) this.getContentPane();
		private SQLcommon sqlc = new SQLcommon();
		private Statement stat;
		private FirstPage firstPage;
		
		public SignUpFrame(Connection conn) throws SQLException {
			this.conn = conn;
			stat = conn.createStatement();
			createTextField();
			createButton();
			createLayout();
			setTitle("Sign Up");
			setSize(600,400);
		}
		
		public void createTextField() {
			tfUserName = new JTextField(20);
			tfUserID = new JTextField(20);
			tfPassword = new JPasswordField(20);
			tfPasswordCheck = new JPasswordField(20);
			//限制只能输入数字 
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
			btnSignUp = new JButton("Sign Up");
			btnSignUp.setFont(new Font("SansSerif", Font.PLAIN, 12));
			btnSignUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					JFrame Warning = new JFrame();
					String name = tfUserName.getText();
					String idStr = tfUserID.getText();
					String password = String.valueOf(tfPassword.getPassword());
					String cpassword = String.valueOf(tfPasswordCheck.getPassword());
					String warning = "未知錯誤，請勿輸入影響程式的字符";
					try {
						if(idStr.length()!=9) {
							warning = "請輸入學號（9位數字）";
							throw new Exception(warning);
						}
						int id = Integer.parseInt(idStr);
						if(name.equals("")) {
							warning = "請輸入姓名";
							throw new Exception(warning);
						}
						if(password.length()<8) {
							warning = "密碼由8位以上字符組成";
							throw new Exception(warning);
						}
						if(!password.equals(cpassword)) {
				        	warning = "密碼不一致";
				        	throw new Exception(warning);
						}
						stat.execute(sqlc.ifExist("ID", id, "User"));
						String ifExist = sqlc.showResultSet(stat.getResultSet());
						System.out.println(ifExist);
						if(ifExist.equals("1")) {
								warning = "學號已被使用";
					        	throw new Exception(warning);
						}
						stat.execute(sqlc.insertUser(name, cpassword, id));
						JOptionPane.showMessageDialog(Warning,"註冊成功！",
	                                  "提醒", JOptionPane.INFORMATION_MESSAGE);
						firstPage = new FirstPage(conn);
						firstPage.setVisible(true);
						dispose();
					}catch(Exception ex) {
						   JOptionPane.showMessageDialog(Warning,warning,
	                                  "錯誤", JOptionPane.ERROR_MESSAGE);
						   warning="未知錯誤，請勿輸入影響程式的字符";
					}
					
					
				}
				
			});
		}
		
		private void createLayout() {
			JPanel uPanel = new JPanel();
			uPanel.setLayout(new GridLayout(0, 2, 0, 0));
			JLabel lblUserid = new JLabel("User  ID  (Only 9 numbers available):");
			lblUserid.setFont(new Font("SansSerif", Font.PLAIN, 14));
			uPanel.add(lblUserid);
			uPanel.add(tfUserID);
			uPanel.setPreferredSize(new Dimension(500, 20));
			JPanel iPanel = new JPanel();
			JLabel lblUserName = new JLabel("User Name:");
			lblUserName.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblUserid.setFont(new Font("SansSerif", Font.PLAIN, 12));
			iPanel.setLayout(new GridLayout(0, 2, 0, 0));
			iPanel.add(lblUserName);
			iPanel.add(tfUserName);
			iPanel.setPreferredSize(new Dimension(500, 20));
			JPanel pPanel = new JPanel();
			pPanel.setLayout(new GridLayout(0, 2, 0, 0));
			JLabel plabel = new JLabel("Password (above 8 letters) :");
			plabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
			pPanel.add(plabel);
			pPanel.add(tfPassword);
			pPanel.setPreferredSize(new Dimension(500, 20));
			JPanel pcPanel = new JPanel();
			pcPanel.setLayout(new GridLayout(0, 2, 0, 0));
			JLabel pclabel = new JLabel("Password Check:");
			pclabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
			pcPanel.add(pclabel);
			pcPanel.add(tfPasswordCheck);
			pcPanel.setPreferredSize(new Dimension(500, 20));
			JPanel bPanel = new JPanel();
			bPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					firstPage = new FirstPage(conn);
					firstPage.setVisible(true);
					dispose();
				}
			});
			btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 12));
			bPanel.add(btnCancel);
			bPanel.add(btnSignUp);
			bPanel.setPreferredSize(new Dimension(500, 40));
			JPanel allPannel = new JPanel();
			allPannel.add(uPanel);
			allPannel.add(iPanel);
			allPannel.add(pPanel);
			allPannel.add(pcPanel);
			allPannel.add(bPanel);
			getContentPane().setLayout(new BorderLayout(20, 40));
			getContentPane().add(new JPanel(), BorderLayout.NORTH);
			getContentPane().add(allPannel, BorderLayout.CENTER);
			getContentPane().add(new JPanel(), BorderLayout.SOUTH);
		}
	}
