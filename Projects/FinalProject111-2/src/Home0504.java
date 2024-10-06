import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.CaretEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//for timer 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

//for TodayDateLabel
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
public class Home0504 extends JFrame implements ValueSetup{

	/**
	 * Launch the application.
	 */
	
	private JPanel welcomePanel,timeInfoPanel,counterPanel,toDoListpanel,CheckBoxpanel,cbContentPanel;
	private JLabel WelcomeLabel,toDoLabel,IconLabel,TodayDateLabel,userNameLabel,digitLabel,lblTypeInErrands;
	private JButton btnStartTimer,resetButton,AddProjectButton,btnComfirm,infoButton,goToCalButton;
	private JTextField InputErrandTextField;
	private JComboBox <Integer>  hourComboBox,minComboBox,secComboBox;
	private JScrollPane scrollPane;
	//private CheckboxGroup checkboxes = new CheckboxGroup();  


	//for timer
	private Timer timer;
	private int hour,second, minute,ini_hour,ini_minute,ini_sec;
	private String ddHour, ddSecond, ddMinute;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private Boolean checkComfirmbtn=false;
	
	//存放Projects
	private Projects projects=new Projects();
	private CaretEvent ce;
	
	
	/*之前做check box 的(現在不會用到)
	private DefaultListModel<String> dfListModel = new DefaultListModel<String>();
	private JCheckBox[] checkBoxList ;	
	*/
	
	//for user name
	private String userName;
	private int userID;
	
	//跳轉頁面用
	private ProjectEditFrame pEditFrame;
	private ProjectInfoFrame pInfoFrame;
	//private CalendarFrame calendarFrame =new CalendarFrame();
	private String projectNameString=""; //存放之後要傳入calendar frame 跟project info frame 的，這兩個frame 之後用名稱追蹤，抓取資料庫的資料
	
	//選取semester用
	Border border ;
	Boolean checkSelectedBoolean=false;//add button 生成的label有沒有被選取
	
	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	private JFrame Warning = new JFrame();
	
	/**
	 * Create the frame.關於frame的設定寫這
	 * @throws SQLException 
	 */
	public Home0504(Connection conn, int id) throws SQLException {
		this.conn = conn;
		stat = conn.createStatement();
		this.userID = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		getContentPane().setLayout(null);
		this.setTitle("Home Page");
		createTextField();
		createJLabel();	
		createCoboBox();
		createJButton();
		createPanel();
		refresh();

	}
	
	public void refresh() throws SQLException {
		stat.execute(sqlc.select("Name", "UserID", userID, "Semester"));
		String[] projectNames = sqlc.showResultSetMuitiple(stat.getResultSet()).split(",");
		for(String projectname:projectNames) {
			projects.add(projectname);
		}
		caretUpdate(ce,projects);
	}
	
	public void createPanel() {
		
		welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 350, 150);
		welcomePanel.setLayout(null);
		welcomePanel.add(WelcomeLabel);
		welcomePanel.add(IconLabel);
		welcomePanel.add(userNameLabel);
		getContentPane().add(welcomePanel);
		
		
		counterPanel = new JPanel();
		counterPanel.add(digitLabel);
		counterPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		counterPanel.setBackground(new Color(192, 192, 192));
		counterPanel.setBounds(30, 105, 330, 127);				
		counterPanel.setLayout(null);
		
		
		CheckBoxpanel = new JPanel(); //沒有add cbContentPanel 
		CheckBoxpanel.setBounds(20, 165, 388, 250);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 1, 388, 250);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //一般scroll pane是版面裝不下才會顯示出來
		CheckBoxpanel.add(scrollPane);
		CheckBoxpanel.setLayout(null);
		
		
		
		
		cbContentPanel = new JPanel(); //check box 裡面的 panel
		scrollPane.setViewportView(cbContentPanel);
		cbContentPanel.setLayout(new GridLayout(10, 1, 0, 0));
		//cbContentPanel.add(new JTextField("test"));
		

		
		
		toDoListpanel = new JPanel();
		toDoListpanel.setForeground(new Color(128, 128, 128));
		toDoListpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		toDoListpanel.setBackground(new Color(234, 234, 234));
		toDoListpanel.setBounds(440, 30, 430, 493);
		toDoListpanel.add(AddProjectButton);
		toDoListpanel.add(InputErrandTextField);				
		toDoListpanel.add(toDoLabel);
		toDoListpanel.add(lblTypeInErrands);
		toDoListpanel.add(CheckBoxpanel);
		toDoListpanel.add(infoButton);
		toDoListpanel.add(goToCalButton);
		getContentPane().add(toDoListpanel);
		toDoListpanel.setLayout(null);		
		
	
		
		
		timeInfoPanel = new JPanel();
		timeInfoPanel.setBackground(new Color(240, 240, 240));
		timeInfoPanel.setForeground(new Color(0, 0, 0));
		timeInfoPanel.setBounds(0, 203, 385, 320);
		timeInfoPanel.add(TodayDateLabel);		
		timeInfoPanel.add(btnStartTimer);
		timeInfoPanel.add(resetButton);
		timeInfoPanel.add(hourComboBox);
		timeInfoPanel.add(minComboBox);
		timeInfoPanel.add(secComboBox);
		timeInfoPanel.add(btnComfirm);
		timeInfoPanel.add(counterPanel);
		getContentPane().add(timeInfoPanel);
		timeInfoPanel.setLayout(null);
		
	
		
	

	}
	
	public void createJLabel() throws SQLException {
		
		WelcomeLabel = new JLabel("Welcome!");
		WelcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		WelcomeLabel.setBounds(140, 40, 150, 30);
		
		stat.execute(sqlc.select("Name", "ID", userID, "User"));
		userName = sqlc.showResultSet(stat.getResultSet());
		
		userNameLabel = new JLabel(userName);
		userNameLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		userNameLabel.setBounds(140, 80, 232, 50);
		
		/*
		userName=login.getUserName();
		if (userName!=null) {
			userNameLabel.setText(login.getUserName());
		}
		*/
		
		//
		IconLabel = new JLabel();		
		ImageIcon userIcon=setImage(".\\img\\userIcon.png",80,80);
		IconLabel.setIcon(userIcon);
		IconLabel.setBounds(40, 30, 100, 100);	
		
		//
		TodayDateLabel = new JLabel(dtf.format(now)); //dtf和now都在value set up class
		TodayDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		TodayDateLabel.setBounds(32, 23, 140, 30);
		
		
		toDoLabel = new JLabel("SEMESTER");
		toDoLabel.setBounds(125, 23, 218, 50);		
		toDoLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
		
		
		digitLabel = new JLabel("00:00:00");
		digitLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
		digitLabel.setBounds(70, 10, 200, 107);
		
		
		lblTypeInErrands = new JLabel("Type in errands...");
		lblTypeInErrands.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTypeInErrands.setBounds(64, 83, 184, 32);
		
		

		
	}
	
	public void createJButton() {
		
		btnStartTimer = new JButton("start timer");
		btnStartTimer.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnStartTimer.setBounds(32, 260, 110, 30);
		btnStartTimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				// Countdown Timer
			if(!checkComfirmbtn) {
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "Null time:Haven't input time.","Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				countdownTimer();
				timer.start();	
			}

			}
		});
		

		
		resetButton = new JButton();
		resetButton.setBounds(152, 260, 30, 30);		
		ImageIcon resetIcon=setImage(".\\img\\restart.png",87,87);
		resetButton.setIcon(resetIcon);
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try {
					timer.stop();				
					// Countdown Timer
					hour=ini_hour;  //把combo box裡面的數字抓近來				
					ddHour=(String)dFormat.format(hour);
					
					minute=ini_minute;
					ddMinute=(String)dFormat.format(minute);
					
					second=ini_sec;
					ddSecond=(String)dFormat.format(second);
					
					String reset_digit=String.format("%s:%s:%s",ddHour,ddMinute,ddSecond);
					digitLabel.setText(reset_digit);
					countdownTimer();
					timer.start();
				}
				catch(NullPointerException np){
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Null time:Haven't input time.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		
		btnComfirm = new JButton("confirm");
		btnComfirm.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnComfirm.setBounds(250, 60, 110, 30);
		btnComfirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				// Countdown Timer
				checkComfirmbtn=true;
				hour=(Integer)hourComboBox.getSelectedItem();  //把combo box裡面的數字抓近來				
				ddHour=(String)dFormat.format(hour);
				
				minute=(Integer)minComboBox.getSelectedItem();
				ddMinute=(String)dFormat.format(minute);
				
				second=(Integer)secComboBox.getSelectedItem();
				ddSecond=(String)dFormat.format(second);
				
				String ini_digit=String.format("%s:%s:%s",ddHour,ddMinute,ddSecond);
				digitLabel.setText(ini_digit);
				
				//reset button 會需要初始的時間
				ini_hour=hour;
				ini_minute=minute;
				ini_sec=second;
				
			}
		});
		
		
		AddProjectButton = new JButton("");
		AddProjectButton.setBackground(new Color(190, 190, 190));
		ImageIcon addIcon=setImage(".\\img\\addIcon.png",30,30);
		AddProjectButton.setIcon(addIcon);
		AddProjectButton.setBounds(318, 125, 51, 30);
		AddProjectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				String warning="";
				try{
					String scheduleName=InputErrandTextField.getText();	
				if(projects.checkExist(scheduleName)) { //如果存在=true
					JFrame jFrame = new JFrame();
					warning="計畫名稱重覆";
					InputErrandTextField.setText(null);
					throw new Exception(warning);
				}
				else {
					if(scheduleName.equals("")) {
						warning="請輸入計畫名字";
						throw new Exception(warning);
					}
					pEditFrame = new ProjectEditFrame(conn,userID,scheduleName);
					pEditFrame.setVisible(true);//導到"new project frame"
					InputErrandTextField.setText(null);	
					dispose();
			    	
				}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(Warning,warning,
                            "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
		
		
		infoButton = new JButton("INFO");
		infoButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		infoButton.setBounds(64, 436, 133, 36);
		infoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (checkSelectedBoolean) {
					try {
						stat.execute(sqlc.selectByTwo("SemesterID","UserID",userID,"Name",projectNameString,"Semester"));
						String SemesterID = sqlc.showResultSet(stat.getResultSet());
						pInfoFrame = new ProjectInfoFrame(conn,userID,SemesterID);
						pInfoFrame.setVisible(true);
						dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		
		
		goToCalButton = new JButton("GO TO CALENDAR");
		goToCalButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		goToCalButton.setBounds(219, 436, 133, 36);
		goToCalButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (checkSelectedBoolean) {
					//calendarFrame.setVisible(true); 先註解掉，因為calendar frame 還沒寫完，會大量執行作測試，先將進入點留在該frame
					//要從資料庫抓取Figma 中紫色底資訊到calendarFrame
					//calendarFrame.getText(projectNameString);
					try {
						stat.execute(sqlc.selectByTwo("SemesterID","UserID",userID,"Name",projectNameString,"Semester"));
						String SemesterID = sqlc.showResultSet(stat.getResultSet());
						CalendarFrame calendarFrame = new CalendarFrame(conn,SemesterID,userID);
						calendarFrame.setVisible(true);
						dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		
	}
	
	public void createTextField() {		
		InputErrandTextField = new JTextField();
		InputErrandTextField.setForeground(Color.BLACK);
		InputErrandTextField.setBounds(64, 125, 256, 30);		
		InputErrandTextField.setColumns(10);
		
	}
	
	public void createCoboBox() {
		hourComboBox = new JComboBox<Integer>();
		hourComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		hourComboBox.setBounds(30, 60, 50, 30);
		int hour_max = 24;
		for (int i = 0; i <= hour_max; i++) {
			hourComboBox .addItem((i));		   
		}

	
		
		
		minComboBox = new JComboBox<Integer>();
		minComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		minComboBox.setBounds(110, 60, 50, 30);
		int minute_max = 59;
		for (int i = 0; i <= minute_max; i++) {
			minComboBox .addItem((i));		   
		}

		
		
		
		secComboBox = new JComboBox<Integer>();
		secComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		secComboBox.setBounds(190, 60, 50, 30);		
		int second_max = 59;
		for (int i = 0; i <= second_max; i++) {
			secComboBox .addItem((i));		   
		}		
	}
	
	//import img 用
	public ImageIcon setImage(String imgPath,int imgw,int imgh) {
		
		BufferedImage bufferedIcon=null;
		Image targetImage;
		ImageIcon targetIcon;
		try {
			bufferedIcon = ImageIO.read(new File(imgPath));		
		}
		catch(IOException e){
			e.printStackTrace();
		}
		targetImage  = bufferedIcon.getScaledInstance(imgw, imgh, Image.SCALE_DEFAULT);
		targetIcon = new ImageIcon(targetImage);
		return targetIcon;
		
	}
	
	//timer 機制的方法
	public void countdownTimer() {
		
			timer = new Timer(1000, new ActionListener() { //1000毫秒=1秒，表示以1秒為單位計時
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				second--;
				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);	
				ddHour = dFormat.format(hour);
				digitLabel.setText(ddHour+":"+ddMinute + ":" + ddSecond);
				
				if(second==-1) {
					second = 59;
					minute--;
					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);	
					ddHour = dFormat.format(hour);
					digitLabel .setText(ddHour+":"+ddMinute + ":" + ddSecond);
				}
				
				if(minute==-1) {
					minute = 59;
					hour--;
					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);	
					ddHour = dFormat.format(hour);
					digitLabel .setText(ddHour+":"+ddMinute + ":" + ddSecond);
				}
				if(minute==0 && second==0 && hour==0) {
					checkComfirmbtn=false; 
					timer.stop();
				}
			}
		});		
	}
	
	
	 public void caretUpdate(CaretEvent ce,Projects p)  { //若沒有使用此方法，按下add之後，沒辦法及時更新新增的label
		   
		  
		  cbContentPanel.removeAll();
		  
		   int num = p.getProjects().size();
		   JLabel jlabels[] = new JLabel[num];
		   
		   for(int i = 0; i < jlabels.length; i++) { //遍歷schedule 的element
			    JLabel label=new JLabel(p.getProjects().get(i));
				jlabels[i] = label;
			    cbContentPanel.add(jlabels[i]);
			}
		   for(JLabel label:jlabels) {
			   label.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseClicked(MouseEvent e)  
				    {  
				    	for(JLabel jlabel:jlabels) {
				    		if(jlabel.getForeground()==Color.BLUE) {
				    			Border border = BorderFactory.createLineBorder(Color.BLACK, 0);
				    			jlabel.setBorder(border);
						    	jlabel.setForeground(Color.BLACK);
				    		};
				    	}
				    	//System.out.println(label.getText());
				    	Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
				    	projectNameString=label.getText(); //存放之後要傳入calendar frame 跟project info frame 的
				    	label.setBorder(border);
				    	label.setForeground(Color.BLUE);
				    	checkSelectedBoolean = true;
			    	
				    }  
				}); 
		   }
			  cbContentPanel.validate();
			  cbContentPanel.repaint();
		 }
	}

