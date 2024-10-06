import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.sound.midi.VoiceStatus;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Color;

public class TaskInfo extends JFrame implements ValueSetup{
	private JLabel DateLabel,intervalLabel,lblName,TimeLabel,lblTaskType,intervalLabel_2 ,TaskName ;
	private JTextArea noteTextArea;
	private JButton EditButton,ComfirmButton;
	
	private Projects projects=new Projects();
	private JLabel StartDate;
	private JLabel EndDate;
	private JLabel cTime;
	private JLabel bTime;
	private JLabel scheduleName;
	private JLabel lblLocation;
	private JLabel Location;
	private JButton btnIsFinished;
	
	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	private JFrame Warning = new JFrame();
	private Home0504 home;
	
	private int semesterID;
	private int userID;
	private int scheduleID;
	private int taskID;
	private String name;
	
	private boolean timer;
	private boolean isFinished;
	private JPanel Timerpanel;
	private JLabel Finish;
	private String ScheduleName;
	
	private boolean fromS;
	
	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTasks frame = new AddTasks();
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
	public TaskInfo(Connection conn, int userID, int semesterid, int scheduleID, int taskid,String name) throws SQLException {
		
		this.conn = conn;
		stat = conn.createStatement();
		home = new Home0504(conn,userID);
		this.userID = userID;
		this.semesterID = semesterid;
		this.name = name;
		this.scheduleID = scheduleID;
		this.taskID = taskid;
		this.fromS=true;
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		this.setTitle("Add Tasks");
		getBoolean();
		createLabel();	
		createTextArea();
		createButton();
		createPanel();

	}
	
	public TaskInfo(Connection conn, int userID, int semesterid, int taskid,String name) throws SQLException {
		
		this.conn = conn;
		stat = conn.createStatement();
		home = new Home0504(conn,userID);
		this.userID = userID;
		this.taskID = taskid;
		this.semesterID = semesterid;
		this.name = name;
		this.fromS=false;
		stat.execute(sqlc.select("ScheduleID","TaskID" ,taskID, "Task"));
		scheduleID = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		this.setTitle("Add Tasks");
		getBoolean();
		createLabel();	
		createTextArea();
		createButton();
		createPanel();

	}
	
	public void getBoolean() throws SQLException {
		stat.execute(sqlc.select("Timer","TaskID" ,taskID, "Task"));
		int Timer = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		if(Timer==1) {
			timer = true;
		}
		else {
			timer = false;
		}
		
		stat.execute(sqlc.select("isFinished","TaskID" ,taskID, "Task"));
		int finish = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		if(finish==1) {
			isFinished = true;
		}
		else {
			isFinished = false;
		}
	}
	
	public void createPanel() throws SQLException {
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(-14, 28, 876, 640);
		infoPanel.add(DateLabel);
		infoPanel.add(intervalLabel);
		infoPanel.add(TimeLabel);
		infoPanel.add(lblName);
		infoPanel.add(TaskName);
		infoPanel.add(lblTaskType);
		infoPanel.add(noteTextArea);
		
		Timerpanel = new JPanel();
		Timerpanel.setBounds(210, 145, 524, 30);
		infoPanel.add(Timerpanel);
		Timerpanel.setLayout(null);
		
		intervalLabel_2 = new JLabel("-");
		intervalLabel_2.setBounds(210, 2, 23, 28);
		Timerpanel.add(intervalLabel_2);
		intervalLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 26));
		
		stat.execute(sqlc.select("ConcentrateTime", "TaskID", taskID, "Task"));
		int ctime = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		
		cTime = new JLabel(String.format("%02d",ctime/10000)+":"+String.format("%02d",(ctime%10000)/100)+":"+String.format("%02d",ctime%100));
		cTime.setBounds(0, 4, 193, 28);
		Timerpanel.add(cTime);
		
		cTime.setHorizontalAlignment(SwingConstants.LEFT);
		cTime.setFont(new Font("SansSerif", Font.PLAIN, 26));
		
		stat.execute(sqlc.select("BreakTime", "TaskID", taskID, "Task"));
		int btime = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		
		bTime = new JLabel(String.format("%02d",btime/10000)+":"+String.format("%02d",(btime%10000)/100)+":"+String.format("%02d",btime%100));
		bTime.setBounds(290, 2, 180, 28);
		Timerpanel.add(bTime);
		
		bTime.setHorizontalAlignment(SwingConstants.LEFT);
		bTime.setFont(new Font("SansSerif", Font.PLAIN, 26));
		
		JButton btnNewButton = new JButton(home.setImage(".\\img\\PlayButton.png",35,35));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TimerFrame tFrame = new TimerFrame(conn,userID,ctime,btime);
					tFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(494, 2, 30, 30);
		Timerpanel.add(btnNewButton);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		stat.execute(sqlc.select("BeginDate","TaskID",taskID,"Task"));
		String beginDate = sqlc.showResultSet(stat.getResultSet());
		StartDate = new JLabel(beginDate);
		StartDate.setHorizontalAlignment(SwingConstants.LEFT);
		StartDate.setFont(new Font("SansSerif", Font.PLAIN, 26));
		StartDate.setBounds(210, 88, 180, 30);
		infoPanel.add(StartDate);
		
		stat.execute(sqlc.select("EndDate","TaskID",taskID,"Task"));
		String endDate = sqlc.showResultSet(stat.getResultSet());
		EndDate = new JLabel(endDate);
		EndDate.setHorizontalAlignment(SwingConstants.LEFT);
		EndDate.setFont(new Font("SansSerif", Font.PLAIN, 26));
		EndDate.setBounds(495, 88, 180, 30);
		infoPanel.add(EndDate);
		
		stat.execute(sqlc.select("ScheduleID","TaskID",taskID,"Task"));
		String ScheduleID = sqlc.showResultSet(stat.getResultSet());
		ScheduleName="";
		
		if(ScheduleID.equals("0")) {
			ScheduleName = "none";
		}
		else {
			stat.execute(sqlc.select("Name","ScheduleID",scheduleID,"Schedule"));
			ScheduleName = sqlc.showResultSet(stat.getResultSet());
		}
		
		scheduleName = new JLabel(ScheduleName);
		scheduleName.setHorizontalAlignment(SwingConstants.LEFT);
		scheduleName.setFont(new Font("SansSerif", Font.PLAIN, 26));
		scheduleName.setBounds(210, 205, 180, 28);
		infoPanel.add(scheduleName);
		
		lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblLocation.setBounds(495, 205, 155, 28);
		infoPanel.add(lblLocation);
		
		stat.execute(sqlc.select("Location","TaskID",taskID,"Task"));
		String location = sqlc.showResultSet(stat.getResultSet());
		Location = new JLabel(location);
		Location.setFont(new Font("SansSerif", Font.PLAIN, 26));
		Location.setBounds(645, 205, 180, 28);
		infoPanel.add(Location);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		
		chckbxNewCheckBox.setSelected(timer);
		Timerpanel.setVisible(timer);
		
		chckbxNewCheckBox.setEnabled(false);
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setBounds(131, 145, 35, 35);
		infoPanel.add(chckbxNewCheckBox);
		

		Finish = new JLabel("(Finish)");
		Finish.setForeground(new Color(0, 0, 255));
		Finish.setFont(new Font("SansSerif", Font.PLAIN, 26));
		Finish.setBounds(399, 30, 140, 30);
		infoPanel.add(Finish);
		btnIsFinished = new JButton("IS FINISHED");
		btnIsFinished.setBounds(50, 438, 155, 28);
		infoPanel.add(btnIsFinished);
		btnIsFinished.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 int result=JOptionPane.showConfirmDialog(Warning,
				               "確定完成"+name+"了嗎?",
				               "提醒",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.INFORMATION_MESSAGE);
					if(result==0) {
						stat.execute(sqlc.updateTaskFinished(taskID));
						isFinished = true;
						btnIsFinished.setVisible(!isFinished);
						Finish.setVisible(isFinished);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnIsFinished.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnIsFinished.setVisible(!isFinished);
		Finish.setVisible(isFinished);
		
		infoPanel.add(EditButton);
		infoPanel.add(ComfirmButton);
		
	
		
	}
	
	
	public void createLabel() throws SQLException {
		DateLabel = new JLabel("Date");
		DateLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		DateLabel.setBounds(50, 88, 100, 28);
		
		intervalLabel= new JLabel("-");
		intervalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		intervalLabel.setBounds(418, 88, 23, 28);
		
		
		lblName = new JLabel("Task Name");
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblName.setBounds(50, 30, 150, 30);
		
		TaskName = new JLabel(name);
		TaskName.setHorizontalAlignment(SwingConstants.LEFT);
		TaskName.setFont(new Font("SansSerif", Font.PLAIN, 26));
		TaskName.setBounds(210, 31, 180, 28);
		
		TimeLabel = new JLabel("Timer");
		TimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		TimeLabel.setBounds(50, 145, 100, 37);
		
		lblTaskType = new JLabel("Belong to");
		lblTaskType.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblTaskType.setBounds(50, 205, 166, 28);
		
		
	}

	
	public void createTextArea() throws SQLException {
		noteTextArea = new JTextArea();
		noteTextArea.setBounds(50, 267, 813, 154);
		noteTextArea.setEditable(false);
		stat.execute(sqlc.select("Introduction","TaskID",taskID,"Task"));
		String introduction = sqlc.showResultSet(stat.getResultSet());
		noteTextArea.setText(introduction);
	}
	
	public void createButton() {
		EditButton = new JButton("Edit");
		EditButton.setBounds(649, 441, 100, 23);
		EditButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		class editListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				AddTasks addTask;
				try {
					addTask = new AddTasks(conn,semesterID,ScheduleName,userID,scheduleID,taskID,name);
					addTask.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		}
		
		ComfirmButton = new JButton("COMFIRM");
		ComfirmButton.setBounds(763, 441, 100, 23);
		ComfirmButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		class comfirmListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(fromS) {
					try {
						ScheduleInfo scheduleinfo = new ScheduleInfo(conn,userID,semesterID,scheduleID,ScheduleName);
						scheduleinfo.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					CalendarFrame cFrame;
					try {
						cFrame = new CalendarFrame(conn,semesterID+"",userID);
						cFrame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					dispose();
				}
			}
		}
		ComfirmButton.addActionListener(new comfirmListener());
		EditButton.addActionListener(new editListener());
	
	}
}
