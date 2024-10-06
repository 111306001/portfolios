import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;



public class CalendarFrame extends JFrame implements ValueSetup{

	private JPanel userInfopanel ,calendarPanel,timerSelectorpanel,toDopanel,todaypanel,toDoInnerpanel,todayTodopanel,
		tomorrwoTodopanel,tomorrowContentpanel,cbContentPanel1,cbContentPanel2;
	
	private JLabel projectNameLabel,startDLabel,intervalLabel,endDLabel,creditsSumLabel,
		lblStartYourTimer,lblTodaymd,lblTomorrowmd ;
	
	private JButton addScheduleButton,btnAddTask,btnForward,btnBackward ;
	private JComboBox chooseTimerComboBox;
	
	//calendar會用到
	private JScrollPane scrollPane;
    int i = 13;
    int j = 7;
    JPanel[][] panelHolder = new JPanel[i][j]; //用來存放所有格子(13*8的所有空格以panel形式存放)
    private JPanel insideCalPanel;
    //
    
	private Home0504 homePageObj;
	private int userID,semesterID;
	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	private JFrame Warning = new JFrame();
	
	private LocalDateTime taskOnDate;
	private CaretEvent ce;
	private Task tasks1 = new Task();
	private Task tasks2 = new Task();
	private Task tasks3 = new Task();
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CalendarFrame(Connection conn,String semesterID,int userID) throws SQLException {
		this.conn = conn;
		stat = conn.createStatement();
		this.userID = userID;
		this.semesterID = Integer.parseInt(semesterID);
		
		taskOnDate = now;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		getContentPane().setLayout(null);
		homePageObj = new Home0504(conn, userID);
		createLabel();
		createButton();
		createCoboBox();		
		createPanel();
		refresh();
		fillComboBox();
	}
	
	public void refresh() throws SQLException {
		tasks1.clear();
		tasks2.clear();
		String tod = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(taskOnDate);
		stat.execute(sqlc.selectByTwoOrder("Name", "SemesterID", semesterID, "EndDate", tod, "TaskID", "Task"));
		String[] projectNames1 = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
		for(String projectname:projectNames1) {
			tasks1.setTaskName(projectname);
		}
		stat.execute(sqlc.selectByTwoOrder("TaskID", "SemesterID", semesterID, "EndDate", tod, "TaskID", "Task"));
		String[] projectids1 = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
		for(String projectid:projectids1) {
			tasks1.setTaskID(projectid);
		}
		caretUpdate1(ce,tasks1);
		
		String tot = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(taskOnDate.plusDays(1));
		stat.execute(sqlc.selectByTwoOrder("Name", "SemesterID", semesterID, "EndDate", tot, "TaskID", "Task"));
		String[] projectNames2 = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
		for(String projectname:projectNames2) {
			tasks2.setTaskName(projectname);
		}
		stat.execute(sqlc.selectByTwoOrder("TaskID", "SemesterID", semesterID, "EndDate", tot, "TaskID", "Task"));
		String[] projectids2 = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
		for(String projectid:projectids2) {
			tasks2.setTaskID(projectid);
		}
		
		caretUpdate2(ce,tasks2);
	}
	
	public void fillComboBox() throws SQLException{
		for(String id:tasks1.getTaskID()) {
			stat.execute(sqlc.select("Timer", "TaskID", id, "Task"));
			String timer = sqlc.showResultSet(stat.getResultSet());
			if(timer.equals("1")) {
				tasks3.setTaskID(id);
				stat.execute(sqlc.select("Name", "TaskID", id, "Task"));
				String name = sqlc.showResultSet(stat.getResultSet());
				tasks3.setTaskName(name);
				chooseTimerComboBox.addItem(name);
			}
		}
		
		for(String id:tasks2.getTaskID()) {
			stat.execute(sqlc.select("Timer", "TaskID", id, "Task"));
			String timer = sqlc.showResultSet(stat.getResultSet());
			if(timer.equals("1")) {
				tasks3.setTaskID(id);
				stat.execute(sqlc.select("Name", "TaskID", id, "Task"));
				String name = sqlc.showResultSet(stat.getResultSet());
				tasks3.setTaskName(name);
				chooseTimerComboBox.addItem(name);
			}
		}
	}
	
	public void createPanel() throws SQLException {
		
		//第一層級
		userInfopanel = new JPanel();
		userInfopanel.setBounds(33, 34, 350, 120);
		getContentPane().add(userInfopanel);
		userInfopanel.add(projectNameLabel);
		userInfopanel.add(startDLabel);		
		userInfopanel.add(intervalLabel);		
		userInfopanel.add(endDLabel);		
		userInfopanel.add(creditsSumLabel);		
		userInfopanel.add(addScheduleButton);
		userInfopanel.setLayout(null);

		
		calendarPanel = new JPanel();
		calendarPanel.setLayout(null); // Disable layout manager
		calendarPanel.setBounds(33, 175, 400, 320);
		
		//scroll pane
		scrollPane=new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, calendarPanel.getWidth(), calendarPanel.getHeight());
		//scrollPane.setPreferredSize(new Dimension(400, 300));
		
		insideCalPanel=new JPanel(new GridLayout(i, j));
		fillCalendar(); //建立所有格子的方法
		scrollPane.setViewportView(insideCalPanel);
		calendarPanel.add(scrollPane);
		getContentPane().add(calendarPanel);

		/*
		calendarPanel = new JPanel(); //(c,r)
		calendarPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		calendarPanel.setBounds(33, 231, 600, 486);
		calendarPanel.setLayout(new BorderLayout());
		calendarPanel.add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(calendarPanel);
		calendarPanel.setLayout(null);
	
		
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		*/
		
		timerSelectorpanel = new JPanel();
		timerSelectorpanel.setBounds(487, 34, 350, 120);
		timerSelectorpanel.add(chooseTimerComboBox);
		timerSelectorpanel.add(lblStartYourTimer);
		getContentPane().add(timerSelectorpanel);
		timerSelectorpanel.setLayout(null);
		
		ImageIcon playIcon=homePageObj.setImage(".\\img\\PlayButton.png",32,32);
		JButton btnplayButton = new JButton(playIcon);
		btnplayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = String.valueOf(chooseTimerComboBox.getSelectedItem());
				int id = Integer.parseInt(tasks3.searchIDbyName(selected));
				try {
					TimerFrame tFrame = new TimerFrame(conn,userID,id);
					tFrame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnplayButton.setBounds(246, 69, 30, 30);
		timerSelectorpanel.add(btnplayButton);
		
		////to do panel 的最內部層級
		todaypanel = new JPanel();
		todaypanel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		todaypanel.setBounds(10, 59, 130, 200);	
		todaypanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		cbContentPanel1 = new JPanel();
		cbContentPanel1.setBackground(new Color(240, 240, 240));
		cbContentPanel1.setLayout(new GridLayout(10, 1, 0, 0));
		todaypanel.add(cbContentPanel1);
	
		
		tomorrowContentpanel = new JPanel();
		tomorrowContentpanel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		tomorrowContentpanel.setBounds(10, 55, 130, 200);		
		tomorrowContentpanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		cbContentPanel2 = new JPanel();
		cbContentPanel2.setBackground(new Color(240, 240, 240));
		cbContentPanel2.setLayout(new GridLayout(10, 1, 0, 0));
		tomorrowContentpanel.add(cbContentPanel2);
		
		//倒數第二內部層級
		
		todayTodopanel = new JPanel();
		todayTodopanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		todayTodopanel.setBounds(0, 10, 150, 270);
		todayTodopanel.add(lblTodaymd);		
		todayTodopanel.add(todaypanel);
		todayTodopanel.setLayout(null);
		
		tomorrwoTodopanel = new JPanel();
		tomorrwoTodopanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tomorrwoTodopanel.setBounds(150, 10, 150, 270);	
		tomorrwoTodopanel.add(tomorrowContentpanel);
		tomorrwoTodopanel.add(lblTomorrowmd);
		tomorrwoTodopanel.setLayout(null);
		
		//to do panel 的裡層級最外層
		toDoInnerpanel = new JPanel();
		toDoInnerpanel.setBounds(27, 10, 300, 280);	
		toDoInnerpanel.add(tomorrwoTodopanel);		
		
		btnBackward = new JButton("");
		btnBackward.setBounds(125, 15, 20, 20);
		tomorrwoTodopanel.add(btnBackward);
		btnBackward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskOnDate = taskOnDate.plusDays(1);
				lblTodaymd.setText(dtfNoYear.format(taskOnDate));
				lblTomorrowmd.setText(dtfNoYear.format(taskOnDate.plusDays(1)));
				
				if(lblTodaymd.getText().equals(dtfNoYear.format(now))) {
					lblTodaymd.setForeground(Color.BLUE);
					lblTodaymd.setText(dtfNoYear.format(taskOnDate)+" (Today)");
				}
				else {
					lblTodaymd.setForeground(Color.BLACK);
				}
				if(lblTomorrowmd.getText().equals(dtfNoYear.format(now))) {
					lblTomorrowmd.setForeground(Color.BLUE);
					lblTomorrowmd.setText(dtfNoYear.format(taskOnDate.plusDays(1))+" (Today)");
				}
				else {
					lblTomorrowmd.setForeground(Color.BLACK);
				}
				try {
					refresh();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		ImageIcon ForwardIcon=homePageObj.setImage(".\\img\\leftPlay.png",32,32);
		ImageIcon BackwardIcon=homePageObj.setImage(".\\img\\rightPlay.png",32,32);
		btnBackward .setIcon(BackwardIcon);
		toDoInnerpanel.add(todayTodopanel);
		
		btnForward = new JButton("");
		btnForward.setBounds(5, 15, 20, 20);
		todayTodopanel.add(btnForward);
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskOnDate = taskOnDate.minusDays(1);
				lblTodaymd.setText(dtfNoYear.format(taskOnDate));
				lblTomorrowmd.setText(dtfNoYear.format(taskOnDate.plusDays(1)));
				if(lblTodaymd.getText().equals(dtfNoYear.format(now))) {
					lblTodaymd.setForeground(Color.BLUE);
					lblTodaymd.setText(dtfNoYear.format(taskOnDate)+" (Today)");
				}
				else {
					lblTodaymd.setForeground(Color.BLACK);
				}
				if(lblTomorrowmd.getText().equals(dtfNoYear.format(now))) {
					lblTomorrowmd.setForeground(Color.BLUE);
					lblTomorrowmd.setText(dtfNoYear.format(taskOnDate.plusDays(1))+" (Today)");
				}
				else {
					lblTomorrowmd.setForeground(Color.BLACK);
				}
				try {
					refresh();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnForward.setIcon(ForwardIcon);
		toDoInnerpanel.setLayout(null);
		

		toDopanel = new JPanel();
		toDopanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		toDopanel.setBounds(487, 175, 350, 320);
		toDopanel.add(toDoInnerpanel);
		toDopanel.add(btnAddTask);
		getContentPane().add(toDopanel);
		toDopanel.setLayout(null);
		

	
		
	}
	
	public void createLabel() throws SQLException {
		
		stat.execute(sqlc.select("Name","SemesterID",semesterID,"Semester"));
		String name = sqlc.showResultSet(stat.getResultSet());
		projectNameLabel = new JLabel(name);
		projectNameLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
		projectNameLabel.setBounds(20, 15, 150, 30);
		
		stat.execute(sqlc.select("BeginDate","SemesterID",semesterID,"Semester"));
		String beginDate = sqlc.showResultSet(stat.getResultSet());
		startDLabel = new JLabel(beginDate);
		startDLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		startDLabel.setBounds(20, 80, 116, 30);
		
		intervalLabel = new JLabel("-");
		intervalLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		intervalLabel.setBounds(146, 80, 22, 30);
		
		stat.execute(sqlc.select("EndDate","SemesterID",semesterID,"Semester"));
		String endDate = sqlc.showResultSet(stat.getResultSet());
		endDLabel = new JLabel(endDate);
		endDLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		endDLabel.setBounds(178, 80, 102, 30);
		
		creditsSumLabel = new JLabel("");
		creditsSumLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		creditsSumLabel.setBounds(377, 26, 144, 36);
		
		lblStartYourTimer = new JLabel("Start Your Timer");
		lblStartYourTimer.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblStartYourTimer.setBounds(10, 27, 230, 20);
		
		lblTodaymd = new JLabel(dtfNoYear.format(taskOnDate)+" (Today)");
		lblTodaymd.setForeground(Color.BLUE);
		lblTodaymd.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblTodaymd.setBounds(30, 10, 113, 33);
		
		lblTomorrowmd = new JLabel(dtfNoYear.format(taskOnDate.plusDays(1)));
		lblTomorrowmd.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblTomorrowmd.setBounds(10, 10, 65, 33);
	}
	
	public void createButton() {
		
		addScheduleButton = new JButton("Add Schedule");
		addScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NewSchedulePage nsp = new NewSchedulePage(conn,semesterID,userID);
					nsp.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addScheduleButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
		addScheduleButton.setBounds(215, 15, 125, 30);
		
		btnAddTask = new JButton("");
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTasks addTasks;
				try {
					addTasks = new AddTasks(conn,semesterID,userID);
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
		ImageIcon addIcon=homePageObj.setImage(".\\img\\addIcon.png",32,32);
		btnAddTask .setIcon(addIcon);
		btnAddTask.setBounds(310, 295, 20, 20);
	}
	
	public void createCoboBox() throws SQLException {
		
		chooseTimerComboBox = new JComboBox();
		chooseTimerComboBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chooseTimerComboBox.setBounds(10, 69, 200, 30);
		chooseTimerComboBox.addItem("none");
	}
	
	
	public void getText(String string) {
		projectNameLabel.setText(string);
	}
	
	public void fillCalendar() throws SQLException { 
		
		//此處建立格子與建立星期與時間以外格子中的label
		
		
        for(int m = 0; m < i; m++) {
        	
            for(int n = 0; n < j; n++) {
               panelHolder[m][n] = new JPanel();
               insideCalPanel.add(panelHolder[m][n]);
            }
         }
        for(int m=0;m<7;m++) {
        	stat.execute(sqlc.selectByTwoOrder("Name","DayOfWeek",m+1,"SemesterID",semesterID, "BeginTime","Schedule"));
        	String[] scheduleNames = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
        	if(!scheduleNames[0].equals("")) {
        		for(int n=0;n<scheduleNames.length;n++) {
        		    JButton scheduleBtn = new JButton(scheduleNames[n]); 
        		    scheduleBtn.setPreferredSize(new Dimension(80, 20));
        		    scheduleBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
            	    panelHolder[n+1][m].add(scheduleBtn);
            	    scheduleBtn.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String scheduleName = scheduleBtn.getText();
							try {
								stat.execute(sqlc.selectByTwo("ScheduleID","SemesterID",semesterID,"Name", scheduleName,"Schedule"));
								int scheduleID = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
								ScheduleInfo scheduleinfo = new ScheduleInfo(conn,userID,semesterID,scheduleID,scheduleName);
								scheduleinfo.setVisible(true);
								dispose();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}							
						}
            	    	
            	    });
        		}
        	}
        }

        
        //此處建立星期label ；mon到sun的變數放在value setup 
        String[] daysOfWeek = {"Mon "+mon, "Tue "+tue, "Wed "+wed, "Thu "+thu, "Fri "+fri, "Sat "+sat,"Sun "+sun};
        JLabel[] dayLabels = new JLabel[daysOfWeek.length];

         for (int w = 0; w < daysOfWeek.length; w++) {
             dayLabels[w] = new JLabel(daysOfWeek[w], JLabel.CENTER);
             if(LocalDate.now().getDayOfWeek().getValue()==w+1) {
            	 dayLabels[w].setForeground(Color.BLUE);
             }
             panelHolder[0][w].add(dayLabels[w]); //[r][c]
         }
         
	}
	public void caretUpdate1(CaretEvent ce,Task tasks1)  { //若沒有使用此方法，按下add之後，沒辦法及時更新新增的label
		   
		  
		  cbContentPanel1.removeAll();
		  
		   int num = tasks1.getTaskName().size();
		   if(tasks1.getTaskName().get(0).equals(""))num=0;
		   JLabel jlabels[] = new JLabel[num];
		   

		   
		   for(int i = 0; i < jlabels.length; i++) { //遍歷schedule 的element
			    JLabel label=new JLabel(tasks1.getTaskName().get(i));
			    label.setName(tasks1.getTaskID().get(i));
			    label.setFont(new Font("SansSerif", Font.PLAIN, 12));
				jlabels[i] = label;
			    cbContentPanel1.add(jlabels[i]);
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
							TaskInfo taskI = new TaskInfo(conn,userID,semesterID,taskID,taskName);
							taskI.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }
				}); 
		   }
			  cbContentPanel1.validate();
			  cbContentPanel1.repaint();
		 }
	public void caretUpdate2(CaretEvent ce,Task tasks2)  { //若沒有使用此方法，按下add之後，沒辦法及時更新新增的label
		   
		  
		  cbContentPanel2.removeAll();
		  
		   int num = tasks2.getTaskName().size();
		   if(tasks2.getTaskName().get(0).equals(""))num=0;
		   JLabel jlabels[] = new JLabel[num];
		   

		   
		   for(int i = 0; i < jlabels.length; i++) { //遍歷schedule 的element
			    JLabel label=new JLabel(tasks2.getTaskName().get(i));
			    label.setName(tasks2.getTaskID().get(i));
			    label.setFont(new Font("SansSerif", Font.PLAIN, 12));
				jlabels[i] = label;
			    cbContentPanel2.add(jlabels[i]);
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
							TaskInfo taskI = new TaskInfo(conn,userID,semesterID,taskID,taskName);
							taskI.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }
				}); 
		   }
			  cbContentPanel2.validate();
			  cbContentPanel2.repaint();
		 }
}
