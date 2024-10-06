import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.sound.midi.VoiceStatus;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTasks extends JFrame implements ValueSetup{
	private JTextField nameTextField,LocationtextField;
	private JLabel DateLabel,intervalLabel,lblName,TimeLabel,intervalLabel_1,lblTaskType,lblLocation  ;
	private JComboBox<String> TtypeComboBox;
	private JTextArea noteTextArea;
	private JButton DeleteButton,ComfirmButton;
	
	private JComboBox cHour;
	private JComboBox cMin;
	private JLabel intervalLabel_2;
	private JComboBox cSecond;
	
	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	private JFrame Warning = new JFrame();
	private boolean isEdited;
	private boolean isFromSInfo;
	private int userID;
	private int semesterID;
	private int scheduleID;
	private int taskID;
	private String scheduleName="";
	
	private Projects schedules = new Projects();
	private JComboBox bHour;
	private JLabel intervalLabel_3;
	private JComboBox bMin;
	private JLabel intervalLabel_4;
	private JComboBox bSecond;
	private JLabel intervalLabel_5;
	private JDateChooser beginDateChooser,endDateChooser;
	private JPanel bTimerPanel;
	private int timer;
	private CalendarFrame cFrame;
	private ScheduleInfo sInfo;
	private JLabel HintLabel;
	private String name;
	private Date begindate,enddate;
	

	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddTasks frame = new AddTasks();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * @throws ParseException 
	 * @wbp.parser.constructor
	 */
	public AddTasks(Connection conn, int semesterID,int userID) throws SQLException, ParseException {
		isFromSInfo = false;
		isEdited = false;
		this.conn = conn;
		stat = conn.createStatement();
		this.semesterID = semesterID;
		this.userID = userID;
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		this.setTitle("Add Tasks");
		refresh();
		createTextField();
		createLabel();	
		createCombo();
		createTextArea();
		createButton();
		createPanel();

	}

	public AddTasks(Connection conn, int semesterID,String scheduleName,int userID,int scheduleID, int taskID, String name) throws SQLException, ParseException {
		isFromSInfo = true;
		isEdited = true;
		this.conn = conn;
		stat = conn.createStatement();
		this.semesterID = semesterID;
		this.userID = userID;
		this.scheduleName = scheduleName;
		this.name = name;
		this.taskID = taskID;
		
		stat.execute(sqlc.select("ScheduleID", "TaskID", taskID, "Task"));
		this.scheduleID = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		this.setTitle("Add Tasks");
		refresh();
		createTextField();
		createLabel();	
		createCombo();
		createTextArea();
		createButton();
		createPanel();

	}
	
	public AddTasks(Connection conn, int semesterID,String scheduleName,int userID,int scheduleID) throws SQLException, ParseException {
		isFromSInfo = true;
		isEdited = false;
		this.conn = conn;
		stat = conn.createStatement();
		this.semesterID = semesterID;
		this.userID = userID;
		this.scheduleName = scheduleName;
		this.scheduleID = scheduleID;
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		this.setTitle("Add Tasks");
		refresh();
		createTextField();
		createLabel();	
		createCombo();
		createTextArea();
		createButton();
		createPanel();

	}
	
	public void refresh() throws SQLException {
		stat.execute(sqlc.select("Name", "SemesterID", semesterID, "Schedule"));
		String[] projectNames = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
		for(String projectname:projectNames) {
			schedules.add(projectname);
		}
	}
	public void createPanel() throws SQLException, ParseException {
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(10, 10, 1325, 640);
		infoPanel.add(DateLabel);
		infoPanel.add(intervalLabel);
		infoPanel.add(nameTextField);
		infoPanel.add(TimeLabel);
		infoPanel.add(lblName);
		
		JPanel cTimerPanel = new JPanel();
		cTimerPanel.setBounds(150, 125, 218, 30);
		infoPanel.add(cTimerPanel);
		cTimerPanel.setLayout(null);
		
		intervalLabel_1 = new JLabel("-");
		intervalLabel_1.setBounds(54, 0, 23, 30);
		cTimerPanel.add(intervalLabel_1);
		intervalLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 26));
		
		intervalLabel_2 = new JLabel("-");
		intervalLabel_2.setBounds(141, 0, 23, 30);
		cTimerPanel.add(intervalLabel_2);
		intervalLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 26));
		
		cHour = new JComboBox();
		cHour.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cHour.setBounds(0, 0, 44, 30);
		cTimerPanel.add(cHour);
		cHour.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		
		cMin = new JComboBox();
		cMin.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cMin.setBounds(87, 0, 44, 30);
		cTimerPanel.add(cMin);
		cMin.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		cSecond = new JComboBox();
		cSecond.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cSecond.setBounds(174, 0, 44, 30);
		cTimerPanel.add(cSecond);
		cSecond.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		if(isEdited) {
			stat.execute(sqlc.select("ConcentrateTime", "TaskID", taskID, "Task"));
			int ctime = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
			cHour.setSelectedIndex(ctime/10000);
			cMin.setSelectedIndex((ctime%10000)/100);
			cSecond.setSelectedIndex(ctime%100);
		}
		
		infoPanel.add(lblTaskType);
		infoPanel.add(TtypeComboBox);
		infoPanel.add(LocationtextField);
		infoPanel.add(lblLocation);
		infoPanel.add(noteTextArea);
		infoPanel.add(ComfirmButton);
		infoPanel.add(DeleteButton);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		beginDateChooser = new JDateChooser();
		beginDateChooser.setDateFormatString("yyyy-MM-dd");
		beginDateChooser.setBounds(150, 75, 180, 30);
		infoPanel.add(beginDateChooser);
		
		endDateChooser = new JDateChooser();
		endDateChooser.setDateFormatString("yyyy-MM-dd");
		endDateChooser.setBounds(396, 75, 180, 30);
		infoPanel.add(endDateChooser);
		
		if(isEdited) {
			stat.execute(sqlc.select("BeginDate","TaskID",taskID,"Task"));
			String beginDate = sqlc.showResultSet(stat.getResultSet());
			begindate = new SimpleDateFormat("yyyy-MM-dd").parse(beginDate);
			
			stat.execute(sqlc.select("EndDate","TaskID",taskID,"Task"));
			String endDate = sqlc.showResultSet(stat.getResultSet());
			enddate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			
			beginDateChooser.setDate(begindate);
			endDateChooser.setDate(enddate);
			
		}
		
		JCheckBox timerCheck = new JCheckBox("");
		timerCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(timerCheck.isSelected()) {
					timer = 1;
					cTimerPanel.setVisible(true);
					bTimerPanel.setVisible(true);
					intervalLabel_5.setVisible(true);
					HintLabel.setVisible(true);
				}
				else {
					timer = 0;
					cHour.setSelectedIndex(0);
					cMin.setSelectedIndex(0);
					cSecond.setSelectedIndex(0);
					bHour.setSelectedIndex(0);
					bMin.setSelectedIndex(0);
					bSecond.setSelectedIndex(0);
					cTimerPanel.setVisible(false);
					bTimerPanel.setVisible(false);
					intervalLabel_5.setVisible(false);
					HintLabel.setVisible(false);
				}
			}
		});
		timer = 1;
		timerCheck.setSelected(true);
		if(isEdited) {
			stat.execute(sqlc.select("Timer","TaskID" ,taskID, "Task"));
			int Timer = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
			if(Timer==1) {
				timerCheck.setSelected(true);
			}
			else {
				timerCheck.setSelected(false);
			}
		}
		
		timerCheck.setHorizontalAlignment(SwingConstants.CENTER);
		timerCheck.setBounds(93, 125, 37, 28);
		infoPanel.add(timerCheck);
		
		bTimerPanel = new JPanel();
		bTimerPanel.setBounds(465, 125, 218, 30);
		infoPanel.add(bTimerPanel);
		bTimerPanel.setLayout(null);
		
		intervalLabel_3 = new JLabel("-");
		intervalLabel_3.setBounds(54, 0, 23, 30);
		bTimerPanel.add(intervalLabel_3);
		intervalLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 26));
		
		intervalLabel_4 = new JLabel("-");
		intervalLabel_4.setBounds(141, 0, 23, 30);
		bTimerPanel.add(intervalLabel_4);
		intervalLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 26));
		
		bHour = new JComboBox();
		bHour.setFont(new Font("SansSerif", Font.PLAIN, 16));
		bHour.setBounds(0, 0, 44, 30);
		bTimerPanel.add(bHour);
		bHour.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		
		bMin = new JComboBox();
		bMin.setFont(new Font("SansSerif", Font.PLAIN, 16));
		bMin.setBounds(87, 0, 44, 30);
		bTimerPanel.add(bMin);
		bMin.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		bSecond = new JComboBox();
		bSecond.setFont(new Font("SansSerif", Font.PLAIN, 16));
		bSecond.setBounds(174, 0, 44, 30);
		bTimerPanel.add(bSecond);
		bSecond.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		if(isEdited) {
			stat.execute(sqlc.select("BreakTime", "TaskID", taskID, "Task"));
			int btime = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
			bHour.setSelectedIndex(btime/10000);
			bMin.setSelectedIndex((btime%10000)/100);
			bSecond.setSelectedIndex(btime%100);
		}
		
		intervalLabel_5 = new JLabel("-");
		intervalLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 26));
		intervalLabel_5.setBounds(406, 122, 23, 30);
		infoPanel.add(intervalLabel_5);
		
		HintLabel = new JLabel("Hint: Concentrate Time (h-m-s) - Break Time (h-m-s)");
		HintLabel.setForeground(new Color(128, 128, 128));
		HintLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		HintLabel.setBounds(30, 165, 392, 23);
		infoPanel.add(HintLabel);
		
		
	}
	
	
	public void createLabel() {
		DateLabel = new JLabel("Date");
		DateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		DateLabel.setBounds(30, 75, 100, 30);
		
		intervalLabel= new JLabel("-");
		intervalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		intervalLabel.setBounds(352, 75, 23, 30);
		
		
		lblName = new JLabel("Task Name");
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblName.setBounds(30, 25, 110, 30);
		
		TimeLabel = new JLabel("Timer");
		TimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		TimeLabel.setBounds(30, 125, 76, 28);
		
		lblTaskType = new JLabel("Belong to");
		lblTaskType.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTaskType.setBounds(30, 198, 110, 28);
		
		lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblLocation.setBounds(396, 200, 110, 28);
		
		
	}
	
	public void createTextField() throws SQLException {
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		nameTextField.setColumns(10);
		nameTextField.setBounds(150, 25, 180, 30);
		if(isEdited) {
			nameTextField.setText(name);
		}
		
		
		LocationtextField = new JTextField();
		LocationtextField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		LocationtextField.setColumns(10);
		LocationtextField.setBounds(490, 200, 217, 30);
		if(isEdited) {
			stat.execute(sqlc.select("Location", "TaskID", taskID, "Task"));
			String location = sqlc.showResultSet(stat.getResultSet());
			LocationtextField.setText(location);
		}
		
		
	}
	
	public void createCombo() {
		TtypeComboBox = new JComboBox<String>();
		TtypeComboBox.setBounds(150, 200, 180, 30);
		TtypeComboBox.addItem("none");
		int i=1;
		for (String schedule:schedules.getProjects()) {
			TtypeComboBox.addItem(schedule);
		}
		for(String s:schedules.getProjects()) {
			if(scheduleName.equals(s)) {
				TtypeComboBox.setSelectedIndex(i);
			}
			i++;
		}
	}
	
	public void createTextArea() throws SQLException {
		noteTextArea = new JTextArea();
		noteTextArea.setText("write something......");
		noteTextArea.setBounds(30, 270, 820, 200);
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
		if(isEdited) {
			stat.execute(sqlc.select("Introduction", "TaskID", taskID, "Task"));
			String introduction = sqlc.showResultSet(stat.getResultSet());
			LocationtextField.setText(introduction);
		}
	}
	
	public void createButton() {
		DeleteButton = new JButton("DELETE");
		DeleteButton.setBounds(640, 500, 100, 23);
		DeleteButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		class deleteListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					if(isEdited) {
						int result=JOptionPane.showConfirmDialog(Warning,
					               "確定要刪除"+name+"嗎?",
					               "提醒",
					               JOptionPane.YES_NO_OPTION,
					               JOptionPane.WARNING_MESSAGE);
						 	if(result==0) { 
								stat.execute(sqlc.delete("TaskID",taskID ,"Task")); 
								if(!isFromSInfo||scheduleID==0) {
									cFrame = new CalendarFrame(conn,semesterID+"",userID);
									cFrame.setVisible(true);
									dispose();
								}
								else {
									sInfo = new ScheduleInfo(conn,userID,semesterID,scheduleID,scheduleName);
									sInfo.setVisible(true);
									dispose();
								}
							}
					}
					}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		ComfirmButton = new JButton("COMFIRM");
		ComfirmButton.setBounds(750, 500, 100, 23);
		ComfirmButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		class comfirmListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String warning = "";
				String beginDate;
				String endDate;
				String introduction;
				String name = nameTextField.getText();
				try {
				if(name.equals(null)) {
					warning = "名字不得為空";
					throw new Exception(warning);
				}
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
				int chour,cmin,csecond;
				chour = Integer.parseInt(String.valueOf(cHour.getSelectedItem()));
				cmin = Integer.parseInt(String.valueOf(cMin.getSelectedItem()));
				csecond = Integer.parseInt(String.valueOf(cSecond.getSelectedItem()));
				
				String cTime = String.format("%02d%02d%02d",chour,cmin,csecond);
				
				int bhour, bmin, bsecond;
				bhour = Integer.parseInt(String.valueOf(bHour.getSelectedItem()));
				bmin = Integer.parseInt(String.valueOf(bMin.getSelectedItem()));
				bsecond = Integer.parseInt(String.valueOf(bSecond.getSelectedItem()));
				
				String bTime = String.format("%02d%02d%02d",bhour,bmin,bsecond);
				String location = LocationtextField.getText();
				scheduleName = String.valueOf(TtypeComboBox.getSelectedItem());
				if(!scheduleName.equals("none")) {
					stat.execute(sqlc.selectByTwo("ScheduleID","SemesterID" , semesterID,"Name", scheduleName,"Schedule"));
					scheduleID = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
				}
				else {
					scheduleID=0;
				}
				if(noteTextArea.getText().equals("write something......")) {
					introduction = "";
				}
				else {
					introduction = noteTextArea.getText();
				}
				if(!isEdited) {
					stat.execute(sqlc.insertTask(name, introduction, beginDate, endDate, cTime, bTime, location, scheduleID, semesterID, timer));
					JOptionPane.showMessageDialog(Warning,"新增 "+name+" 成功!",
	                        "提醒", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					stat.execute(sqlc.updateTask(name, introduction, beginDate, endDate, cTime, bTime, location, scheduleID, taskID, timer));
					JOptionPane.showMessageDialog(Warning,"編輯 "+name+" 成功!",
	                        "提醒", JOptionPane.INFORMATION_MESSAGE);
				}
				if(isEdited) {
					TaskInfo taskI = new TaskInfo(conn,userID,semesterID,scheduleID,taskID,name);
					taskI.setVisible(true);
					dispose();
				}
				else {
					if(!isFromSInfo||scheduleID==0) {
						cFrame = new CalendarFrame(conn,semesterID+"",userID);
						cFrame.setVisible(true);
						dispose();
					}
					else {
						sInfo = new ScheduleInfo(conn,userID,semesterID,scheduleID,scheduleName);
						sInfo.setVisible(true);
						dispose();
					}
				}

			}catch(Exception ex) {
				JOptionPane.showMessageDialog(Warning,warning,
                        "錯誤", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			}
		}
		ComfirmButton.addActionListener(new comfirmListener());
		DeleteButton.addActionListener(new deleteListener());
	}
}
