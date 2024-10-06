import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.Cursor;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.CaretEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

public class ScheduleInfo extends JFrame implements ValueSetup{
	
	private JPanel mainPanel,CheckBoxpanel,cbContentPanel;
	private JScrollPane scrollPane;
	private boolean checkSelectedBoolean;
	
	private JLabel TimeLabel,WeekLabel,intervalLabel,LocationLabel,noteLabel,
			startDLabel,endDLabel,scheduleTimeLabel,scheduleLocaLabel,writtenTasksLabel,ScheduleNameLabel;
	
	private JTextArea noteTextArea,writtenTasksTextArea ;	
	private JButton DeleteButton,ComfirmButton,PlusButton;
	
	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	private JFrame Warning = new JFrame();
	private Home0504 home;
	
	private int userID;
	private int semesterID;
	private int scheduleID;
	private String name;
	private Task tasks = new Task();
	private CaretEvent ce;



	/**
	 * Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleInfo frame = new ScheduleInfo();
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
	 */
	public ScheduleInfo(Connection conn, int userID, int semesterID, int scheduleID,String name) throws SQLException {
		
		this.conn = conn;
		stat = conn.createStatement();
		home = new Home0504(conn,userID);
		this.scheduleID = scheduleID;
		this.userID = userID;
		this.semesterID = semesterID;
		this.name = name;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		createLabel();
		createTextField();
		createTextArea();
		createButton();
		createPanel();
		refresh();
	}
	
	public void refresh() throws SQLException {
		stat.execute(sqlc.selectByTwoOrder("Name", "SemesterID", semesterID, "ScheduleID", scheduleID, "EndDate", "Task"));
		String[] projectNames = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
		for(String projectname:projectNames) {
			tasks.setTaskName(projectname);
		}
		stat.execute(sqlc.selectByTwoOrder("TaskID", "SemesterID", semesterID, "ScheduleID", scheduleID, "EndDate", "Task"));
		String[] projectids = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
		for(String projectid:projectids) {
			tasks.setTaskID(projectid);
		}
		caretUpdate(ce,tasks);
	}
	
	
	public void createPanel() {
		
		mainPanel = new JPanel();
		mainPanel.setBounds(10, 10, 876, 543);
		mainPanel.add(ScheduleNameLabel);
		mainPanel.add(TimeLabel);
		mainPanel.add(startDLabel);
		mainPanel.add(endDLabel);
		mainPanel.add(intervalLabel);
		mainPanel.add(scheduleTimeLabel);
		mainPanel.add(scheduleLocaLabel);
		mainPanel.add(writtenTasksLabel);
		mainPanel.add(WeekLabel);
		mainPanel.add(noteLabel);
		mainPanel.add(LocationLabel);
		mainPanel.add(noteTextArea);
		mainPanel.add(PlusButton);
		mainPanel.add(DeleteButton);
		mainPanel.add(ComfirmButton);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		CheckBoxpanel = new JPanel(); //沒有add cbContentPanel 
		CheckBoxpanel.setBounds(449, 81, 400, 416);
		mainPanel.add(CheckBoxpanel);
		CheckBoxpanel.setBorder(null);
		CheckBoxpanel.setBackground(new Color(255, 255, 255));
		CheckBoxpanel.setLayout(new BorderLayout(0, 0));
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //一般scroll pane是版面裝不下才會顯示出來
		CheckBoxpanel.add(scrollPane);
		
		
		
		
		cbContentPanel = new JPanel();
		cbContentPanel.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(cbContentPanel);
		cbContentPanel.setLayout(new GridLayout(10, 1, 0, 0));

	}
	
	
	public void createTextField() {
		
	}
	
	public void createLabel() throws SQLException {
		
		ScheduleNameLabel = new JLabel(name);
		ScheduleNameLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
		ScheduleNameLabel.setBounds(20, 10, 300, 80);
		
		TimeLabel = new JLabel("Time");
		TimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		TimeLabel.setBounds(20, 102, 100, 30);
		
		WeekLabel = new JLabel("Day of Week");
		WeekLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		WeekLabel.setBounds(20, 152, 196, 28);		
		this.setTitle("Schedule Info");
		
		intervalLabel = new JLabel("-");
		intervalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		intervalLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		intervalLabel.setBounds(240, 102, 23, 30);
		
		LocationLabel = new JLabel("Location");
		LocationLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		LocationLabel.setBounds(20, 202, 100, 28);
		
		
		noteLabel = new JLabel("Note");
		noteLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		noteLabel.setBounds(20, 258, 93, 28);
		
		stat.execute(sqlc.select("BeginTime", "ScheduleID", scheduleID, "Schedule"));
		int startTime = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		startDLabel = new JLabel(String.format("%02d",startTime/100)+":"+String.format("%02d",startTime%100));
		startDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startDLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		startDLabel.setBounds(130, 102, 100, 30);
		
		stat.execute(sqlc.select("EndTime", "ScheduleID", scheduleID, "Schedule"));
		int endTime = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		endDLabel = new JLabel(String.format("%02d",endTime/100)+":"+String.format("%02d",endTime%100));
		endDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		endDLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		endDLabel.setBounds(273, 102, 100, 30);

		stat.execute(sqlc.select("DayOfWeek", "ScheduleID", scheduleID, "Schedule"));
		String dow = sqlc.showResultSet(stat.getResultSet());
		switch(dow) {
			case "1":
				dow="MON";
				break;
			case "2":
				dow="TUE";
				break;
			case "3":
				dow="WED";
				break;
			case "4":
				dow="THU";
				break;
			case "5":
				dow="FRI";
				break;
			case "6":
				dow="SAT";
				break;
			case "7":
				dow="SUN";
				break;
			default:
				dow="WTF";
				break;
		}
		
		scheduleTimeLabel = new JLabel(dow);
		scheduleTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scheduleTimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scheduleTimeLabel.setBounds(210, 152, 150, 30);
		
		stat.execute(sqlc.select("Location", "ScheduleID", scheduleID, "Schedule"));
		String location = sqlc.showResultSet(stat.getResultSet());
		
		scheduleLocaLabel = new JLabel(location);
		scheduleLocaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scheduleLocaLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scheduleLocaLabel.setBounds(210, 201, 150, 30);
		
		
		writtenTasksLabel = new JLabel("Task");
		writtenTasksLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		writtenTasksLabel.setBounds(449, 41, 64, 30);

		
		
		
	}
	
	public void createTextArea() throws SQLException {
		
		noteTextArea = new JTextArea();
		noteTextArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		noteTextArea.setBounds(20, 297, 400, 200);
		noteTextArea.setEditable(false);
		
		stat.execute(sqlc.select("Introduction", "ScheduleID", scheduleID, "Schedule"));
		String introduction = sqlc.showResultSet(stat.getResultSet());
		noteTextArea.setText(introduction);
	}
	
	public void createButton() {
		
		DeleteButton = new JButton("EDIT");
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NewSchedulePage eFrame = new NewSchedulePage(conn,semesterID,userID,scheduleID);
					eFrame.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		DeleteButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		DeleteButton.setBounds(626, 510, 100, 23);
		
		ComfirmButton = new JButton("COMFIRM");
		ComfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarFrame cFrame;
				try {
					cFrame = new CalendarFrame(conn,semesterID+"",userID);
					cFrame.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		ComfirmButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		ComfirmButton.setBounds(749, 510, 100, 23);
		
		PlusButton = new JButton("");
		PlusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddTasks addTasks = new AddTasks(conn,semesterID,name,userID,scheduleID);
					addTasks.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ImageIcon pencilIcon=home.setImage(".\\img\\addIcon.png",46,46);
		PlusButton.setIcon(pencilIcon);
		PlusButton.setBounds(522, 41, 30, 30);
		
		
	}
	 public void caretUpdate(CaretEvent ce,Task tasks2)  { //若沒有使用此方法，按下add之後，沒辦法及時更新新增的label
		   
		  
		  cbContentPanel.removeAll();
		  
		   int num = tasks2.getTaskName().size();
		   if(tasks2.getTaskName().get(0).equals(""))num=0;
		   JLabel jlabels[] = new JLabel[num];
		   

		   
		   for(int i = 0; i < jlabels.length; i++) { //遍歷schedule 的element
			    JLabel label=new JLabel(tasks2.getTaskName().get(i));
			    label.setName(tasks.getTaskID().get(i));
			    label.setFont(new Font("SansSerif", Font.PLAIN, 20));
				jlabels[i] = label;
			    cbContentPanel.add(jlabels[i]);
			}
		   for(JLabel label:jlabels) {
			   label.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseEntered(MouseEvent e)  
				    {  

				    	//System.out.println(label.getText());
				    	Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
				    	label.setBorder(border);
				    	label.setForeground(Color.BLUE);
				    	
			    	
				    }  
				    
				    public void mouseExited(MouseEvent e) {
				    	Border border = BorderFactory.createLineBorder(Color.BLACK, 0);
		    			label.setBorder(border);
				    	label.setForeground(Color.BLACK);
				    }
				    
				    public void mouseClicked(MouseEvent e) {
				    	String taskName = label.getText();
				    	int taskID = Integer.parseInt(label.getName());
				    	try {
							TaskInfo taskI = new TaskInfo(conn,userID,semesterID,scheduleID,taskID,taskName);
							taskI.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }
				}); 
		   }
			  cbContentPanel.validate();
			  cbContentPanel.repaint();
		 }
}
