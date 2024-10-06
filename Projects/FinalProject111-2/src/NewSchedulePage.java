import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Cursor;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

public class NewSchedulePage extends JFrame implements ValueSetup{
	
	private JPanel mainPanel;
	private JTextField ScheduleNameTextField,LocationtextField;
	private JLabel DateLabel,TimeLabel,LocationLabel ;
	private JTextArea noteTextArea;
	private JButton DeleteButton,ComfirmButton;
	private JComboBox weekOfDayBox,startHourBox, startMinBox, endHourBox, endMinBox;
	
	
	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	private JFrame Warning = new JFrame();
	private Home0504 home;
	private boolean isEdited;
	private int userID;
	private int semesterID;
	private int scheduleID;
	private String name;
	private CalendarFrame calendarframe;
	


	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSchedulePage frame = new NewSchedulePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @wbp.parser.constructor
	 */
	public NewSchedulePage(Connection conn,int semesterID,int userID) throws SQLException {
		isEdited = false;
		home = new Home0504(conn,userID);
		
		this.conn = conn;
		stat = conn.createStatement();
		this.semesterID = semesterID;
		this.userID = userID;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		createLabel();
		createTextField();
		createTextArea();
		createButton();
		createPanel();

	}
	
	public NewSchedulePage(Connection conn,int semesterID,int userID,int scheduleID) throws SQLException {
		isEdited = true;
		home = new Home0504(conn,userID);
		
		this.scheduleID=scheduleID;
		this.conn = conn;
		stat = conn.createStatement();
		this.semesterID = semesterID;
		this.userID = userID;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		createLabel();
		createTextField();
		createTextArea();
		createButton();
		createPanel();
	}
	
	public void createPanel() throws SQLException {
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 0, 1, 0));
		mainPanel.setBounds(10, 30, 866, 523);
		mainPanel.add(ScheduleNameTextField);
		mainPanel.add(DateLabel);
		mainPanel.add(TimeLabel);
		mainPanel.add(LocationLabel);
		mainPanel.add(LocationtextField);
		mainPanel.add(noteTextArea);
		mainPanel.add(DeleteButton);
		mainPanel.add(ComfirmButton);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		weekOfDayBox = new JComboBox();
		weekOfDayBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weekOfDayBox.setModel(new DefaultComboBoxModel(new String[] {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"}));
		if(isEdited) {
			stat.execute(sqlc.select("DayOfWeek","ScheduleID", scheduleID, "Schedule"));
			int dow =Integer.parseInt(sqlc.showResultSet(stat.getResultSet())) ;
			weekOfDayBox.setSelectedIndex(dow-1);
		}
		weekOfDayBox.setBounds(120, 115, 217, 30);
		mainPanel.add(weekOfDayBox);
		
		
		startHourBox = new JComboBox();
		String[] startHour = new String[] {"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
		startHourBox.setModel(new DefaultComboBoxModel(startHour));
		startHourBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		startHourBox.setBounds(120, 180, 100, 30);
		mainPanel.add(startHourBox);
		
		startMinBox = new JComboBox();
		startMinBox.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		startMinBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		startMinBox.setBounds(268, 180, 100, 30);
		mainPanel.add(startMinBox);
		
		if(isEdited) {
			stat.execute(sqlc.select("BeginTime","ScheduleID", scheduleID, "Schedule"));
			int startTime =Integer.parseInt(sqlc.showResultSet(stat.getResultSet())) ;
			startHourBox.setSelectedIndex(startTime/100-6);
			startMinBox.setSelectedIndex(startTime%100);
		}
		
		JLabel TimeLabel_1 = new JLabel(":");
		TimeLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		TimeLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 26));
		TimeLabel_1.setBounds(220, 180, 50, 28);
		mainPanel.add(TimeLabel_1);
		
		JLabel TimeLabel_1_1 = new JLabel(":");
		TimeLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		TimeLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 26));
		TimeLabel_1_1.setBounds(561, 180, 50, 28);
		mainPanel.add(TimeLabel_1_1);
		
		endMinBox = new JComboBox();
		endMinBox.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		endMinBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		endMinBox.setBounds(609, 180, 100, 30);
		mainPanel.add(endMinBox);
		
		endHourBox = new JComboBox();
		endHourBox.setModel(new DefaultComboBoxModel(new String[] {"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"}));
		endHourBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		endHourBox.setBounds(461, 180, 100, 30);
		mainPanel.add(endHourBox);
		
		if(isEdited) {
			stat.execute(sqlc.select("EndTime","ScheduleID", scheduleID, "Schedule"));
			int endTime =Integer.parseInt(sqlc.showResultSet(stat.getResultSet())) ;
			endHourBox.setSelectedIndex(endTime/100-6);
			endMinBox.setSelectedIndex(endTime%100);
		}
		
		JLabel TimeLabel_1_2 = new JLabel("~");
		TimeLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		TimeLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 26));
		TimeLabel_1_2.setBounds(389, 180, 50, 28);
		mainPanel.add(TimeLabel_1_2);


	}
	
	public void createTextField() throws SQLException {
		
		ScheduleNameTextField = new JTextField();
		ScheduleNameTextField.setBackground(new Color(240, 240, 240));
		ScheduleNameTextField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ScheduleNameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if(isEdited) {
			stat.execute(sqlc.select("Name","ScheduleID", scheduleID, "Schedule"));
			name = sqlc.showResultSet(stat.getResultSet());
			ScheduleNameTextField.setText(name);
		}
		else {
			ScheduleNameTextField.setText("New Schedule");
		}
		
		ScheduleNameTextField.setFont(new Font("SansSerif", Font.BOLD, 38));
		ScheduleNameTextField.setBounds(10, 10, 402, 80);		
		ScheduleNameTextField.setColumns(10);

		LocationtextField = new JTextField();
		LocationtextField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		LocationtextField.setColumns(10);
		LocationtextField.setBounds(120, 245, 217, 30);
		
		if(isEdited) {
			stat.execute(sqlc.select("Location","ScheduleID", scheduleID, "Schedule"));
			String location = sqlc.showResultSet(stat.getResultSet());
			LocationtextField.setText(location);
		}
		
	}
	
	public void createLabel() {
		
		DateLabel = new JLabel("Week");
		DateLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		DateLabel.setBounds(10, 115, 100, 30);
		
		TimeLabel = new JLabel("Time");
		TimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		TimeLabel.setBounds(10, 180, 100, 28);		
		this.setTitle("New Schedule");
		
		LocationLabel = new JLabel("Location");
		LocationLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		LocationLabel.setBounds(10, 245, 100, 30);
		
		
		
	}
	
	public void createTextArea() throws SQLException {
		noteTextArea = new JTextArea();
		noteTextArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
		noteTextArea.setText("write something......");
		if(isEdited) {
			stat.execute(sqlc.select("Introduction","ScheduleID", scheduleID, "Schedule"));
			String introduction = sqlc.showResultSet(stat.getResultSet());
			noteTextArea.setText(introduction);
		}
		noteTextArea.setBounds(10, 300, 846, 154);
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
	
	public void createButton() {
		
		DeleteButton = new JButton("DELETE");
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isEdited) {
					 int result=JOptionPane.showConfirmDialog(Warning,
				               "確定要刪除"+name+"嗎?",
				               "提醒",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.WARNING_MESSAGE);
					 if(result==0) {
						 try {
							stat.execute(sqlc.delete("ScheduleID",scheduleID ,"Schedule")); 
							stat.execute(sqlc.delete("ScheduleID",scheduleID ,"Task")); 
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					 }
				}
					try {
						calendarframe = new CalendarFrame(conn,semesterID+"",userID);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					calendarframe.setVisible(true);
					dispose();
			}
		});
		DeleteButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		DeleteButton.setBounds(642, 485, 100, 23);
		
		ComfirmButton = new JButton("COMFIRM");
		ComfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String warning = "";
				try {
					int starthour,startmin,endhour,endmin;
					boolean timeava = true;
					starthour = Integer.parseInt(String.valueOf(startHourBox.getSelectedItem()));
					startmin = Integer.parseInt(String.valueOf(startMinBox.getSelectedItem()));
					endhour = Integer.parseInt(String.valueOf(endHourBox.getSelectedItem()));
					endmin = Integer.parseInt(String.valueOf(endMinBox.getSelectedItem()));
					if(endhour<starthour) {
						timeava = false;
					}
					else if(endhour==starthour){
						if(endmin<=startmin)timeava = false;
					}
					if(!timeava) {
						warning = "結束時間需在開始時間之後";
						throw new Exception(warning);
					}
					String name = ScheduleNameTextField.getText();
					String introduction = noteTextArea.getText();
					if(introduction.equals("write something......"))introduction="";
					int beginTime = starthour*100+startmin;
					int endTime = endhour*100+endmin;
					String dayOfWeek = String.valueOf(weekOfDayBox.getSelectedItem());
					
					switch(dayOfWeek) {
						case "MON":
							dayOfWeek = "1";
							break;
						case "TUE":
							dayOfWeek = "2";
							break;
						case "WED":
							dayOfWeek = "3";
							break;
						case "THU":
							dayOfWeek = "4";
							break;
						case "FRI":
							dayOfWeek = "5";
							break;
						case "SAT":
							dayOfWeek = "6";
							break;
						case "SUN":
							dayOfWeek = "7";
							break;
							
						default:
							dayOfWeek = "0";
							break;
					}
					
					String location = LocationtextField.getText();
					
					if(name.equals("")) {
						warning = "請輸入行程名字";
						throw new Exception(warning);
					}

					if(!isEdited) {
						stat.execute(sqlc.ifExistByTwo("SemesterID", semesterID, "Name", name, "Schedule"));
						String ifExist = sqlc.showResultSet(stat.getResultSet());
						if(ifExist.equals("1")) {
							warning = "行程名稱重複";
							throw new Exception(warning);
						}
						stat.execute(sqlc.insertSchedule(name, introduction, beginTime, endTime, dayOfWeek, location, semesterID));
						JOptionPane.showMessageDialog(Warning,"新增 "+name+" 成功!",
	                            "提醒", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						stat.execute(sqlc.updateSchedule(name, introduction, beginTime, endTime, dayOfWeek, location, scheduleID));
						JOptionPane.showMessageDialog(Warning,"更新 "+name+" 成功!",
	                            "提醒", JOptionPane.INFORMATION_MESSAGE);
					}
					calendarframe = new CalendarFrame(conn,semesterID+"",userID);
					calendarframe.setVisible(true);
					dispose();
					
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(Warning,warning,
                            "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		ComfirmButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		ComfirmButton.setBounds(756, 485, 100, 23);
		ImageIcon addIcon=home.setImage(".\\img\\addIcon.png",36,36);
		
	}
}
