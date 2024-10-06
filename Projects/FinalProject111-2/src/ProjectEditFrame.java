import java.awt.EventQueue;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;

public class ProjectEditFrame extends JFrame implements ValueSetup{
	private JLabel PnameLabel,DateLabel,intervalLabel;
	private JButton DeleteButton,ComfirmButton;
	private JPanel titlePanel,datePanel,unchangePanel  ;
	private JTextArea noteTextArea;
	private String name;
	private int userID;
	private JDateChooser beginDateChooser,endDateChooser;
	
	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	private JFrame Warning = new JFrame();
	private Home0504 home;
	private boolean isEdited;
	private int semesterID;
	
	
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @wbp.parser.constructor
	 */
	public ProjectEditFrame(Connection conn, int userID,String name) throws SQLException {
		this.name = name;
		this.conn=conn;
		this.userID=userID;
		this.isEdited = false;
		stat = conn.createStatement();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setTitle("Schedule Edit");
		getContentPane().setLayout(null);
		createJLabel();	
		createTextArea() ;
		createJButton();
		createPanel();


	}
	
	public ProjectEditFrame(Connection conn, int userID,int semesterID) throws SQLException {
		this.semesterID=semesterID;
		this.conn=conn;
		this.userID=userID;
		this.isEdited = true;
		stat = conn.createStatement();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		stat.execute(sqlc.select("Name", "SemesterID", semesterID, "Semester"));
		this.name = sqlc.showResultSet(stat.getResultSet());
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setTitle("Schedule Edit");
		getContentPane().setLayout(null);
		createJLabel();	
		createTextArea() ;
		createJButton();
		createPanel();


	}
	
	public void createPanel() {
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 1345, 103);
		titlePanel.add(PnameLabel);
		PnameLabel.setText(name);
		getContentPane().add(titlePanel);
		titlePanel.setLayout(null);
		
		datePanel = new JPanel();
		datePanel.setBounds(0, 113, 1345, 103);
		datePanel.add(DateLabel);
		datePanel.add(intervalLabel);
		
		getContentPane().add(datePanel);
		datePanel.setLayout(null);
		
		beginDateChooser=new JDateChooser();
		beginDateChooser.setDateFormatString("yyyy-MM-dd");
		beginDateChooser.setBounds(135, 30, 150, 28);
		datePanel.add(beginDateChooser);
		
		endDateChooser = new JDateChooser();
		endDateChooser.setDateFormatString("yyyy-MM-dd");
		endDateChooser.setBounds(343, 28, 150, 30);
		datePanel.add(endDateChooser);
		
		
		
		unchangePanel = new JPanel();
		unchangePanel.setBounds(0, 226, 1345, 398);		
		unchangePanel.add(noteTextArea);
		unchangePanel.add(DeleteButton);
		unchangePanel.add(ComfirmButton);
		getContentPane().add(unchangePanel);
		unchangePanel.setLayout(null);
	}
	
	public void createJLabel() {
		
		PnameLabel=new JLabel();		
		PnameLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
		PnameLabel.setBounds(25, 30, 275, 60);
		
		DateLabel = new JLabel("Date");
		DateLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		DateLabel.setBounds(25, 30, 100, 28);
		
		intervalLabel = new JLabel("-");
		intervalLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		intervalLabel.setBounds(310, 29, 23, 30);
	}
	
	public void createTextArea() throws SQLException {
		noteTextArea = new JTextArea();
		noteTextArea.setFont(new Font("SansSerif", Font.PLAIN, 24));
		noteTextArea.setForeground(new Color(128, 128, 128));
		noteTextArea.setBounds(55, 30, 767, 208);
		if(!isEdited) {
			noteTextArea.setText("write something......");
		}
		else {
			stat.execute(sqlc.select("Introduction", "SemesterID", semesterID, "Semester"));
			noteTextArea.setText(sqlc.showResultSet(stat.getResultSet()));
		}
		noteTextArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(noteTextArea.getText().equals("write something......")) {
					noteTextArea.setText("");
					noteTextArea.setForeground(new Color(0,0,0));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(noteTextArea.getText().equals("")) {
					noteTextArea.setText("write something......");
					noteTextArea.setForeground(new Color(128,128,128));
				}
			}
		});
	}
	
	public void createJButton() {
		DeleteButton = new JButton("DELETE");
		DeleteButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		DeleteButton.setBounds(608, 268, 100, 23);
		class deleteListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(isEdited) {
					 int result=JOptionPane.showConfirmDialog(Warning,
				               "確定要刪除"+name+"嗎?",
				               "提醒",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.WARNING_MESSAGE);
					 if(result==0) {
						 try {
							stat.execute(sqlc.delete("SemesterID",semesterID ,"Semester")); 
							stat.execute(sqlc.delete("SemesterID",semesterID ,"Schedule")); 
							stat.execute(sqlc.delete("SemesterID",semesterID ,"Task")); 
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					 }
				}
					try {
						home = new Home0504(conn,userID);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					home.setVisible(true);
					dispose();
				}
			}
		DeleteButton.addActionListener(new deleteListener());
		
		ComfirmButton = new JButton("COMFIRM");
		ComfirmButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		ComfirmButton.setBounds(722, 268, 100, 23);
		ComfirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				String warning = "";
				String beginDate;
				String endDate;
				String introduction;
				try {
				if(beginDateChooser.getDate()==null || endDateChooser.getDate()==null) {
					warning="請選擇日期";
					throw new Exception(warning);
				}
				else {
					if(beginDateChooser.getDate().compareTo(endDateChooser.getDate())>=0) {
						warning="截止日應在起始日之後";
						throw new Exception(warning);
					}
					else {
						SimpleDateFormat sdfB = new SimpleDateFormat("yyyy-MM-dd");
						beginDate = sdfB.format(beginDateChooser.getDate());
						SimpleDateFormat sdfE = new SimpleDateFormat("yyyy-MM-dd");
						endDate = sdfE.format(endDateChooser.getDate());
					}
				}
				if(noteTextArea.getText().equals("write something......")) {
					introduction = "";
				}
				else {
					introduction = noteTextArea.getText();
				}
				if(!isEdited) {
				stat.execute(sqlc.insertSemester(name,beginDate,endDate,introduction,userID));
					JOptionPane.showMessageDialog(Warning,"新增 "+name+" 成功!",
                            "提醒", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					stat.execute(sqlc.updateSemester(name,beginDate,endDate,introduction,semesterID));
					JOptionPane.showMessageDialog(Warning,"編輯 "+name+" 成功!",
                            "提醒", JOptionPane.INFORMATION_MESSAGE);
				}
					stat.execute(sqlc.selectByTwo("SemesterID", "UserID",userID,"Name", name, "Semester"));
					String id = sqlc.showResultSet(stat.getResultSet());
					ProjectInfoFrame infoFrame = new ProjectInfoFrame(conn,userID,id);
					infoFrame.setVisible(true);
					dispose();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(Warning,warning,
                            "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
}
