import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;


public class TimerFrame extends JFrame implements ValueSetup{
	
    private JPanel timeSetting, time, controll, allPanel;
	private JLabel hr, min, sec, digitLabel;
	private JComboBox<Integer> hrNum, minNum, secNum;
	private JButton comfirm, start, reset;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private Timer timer;
	private int hour, second, minute, ini_hour, ini_minute, ini_sec;
	private String ddHour, ddSecond, ddMinute;
	private Boolean checkComfirmbtn=false;
	private int cTime,bTime;
	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	
	private Home0504 home;
	private JButton btnClose;

	
	/**
	 * @wbp.parser.constructor
	 */
	public TimerFrame(Connection conn,int userID,int taskID) throws SQLException {
		this.conn = conn;
		stat = conn.createStatement();
		home = new Home0504(conn,userID);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		if(taskID!=0) {
				stat.execute(sqlc.select("ConcentrateTime", "TaskID", taskID, "Task"));
				cTime = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
				stat.execute(sqlc.select("BreakTime", "TaskID", taskID, "Task"));
				bTime = Integer.parseInt(sqlc.showResultSet(stat.getResultSet()));
		}else {
			cTime = 0;
			bTime = 0;
		}

		createComb();
		createButton();
		createTimer();
		createPanel();
	}
	
	public TimerFrame(Connection conn,int userID,int cTime,int bTime) throws SQLException {
		home = new Home0504(conn,userID);
		this.cTime = cTime;
		this.bTime = bTime;
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		createComb();
		createButton();
		createTimer();
		createPanel();
	}
	
	
	private void createComb() {
		
		hr = new JLabel("hr");
		hrNum = new JComboBox<Integer>();
		min = new JLabel("min");
		minNum = new JComboBox<Integer>();
		sec = new JLabel("sec");
		secNum = new JComboBox<Integer>();
		
		for(int hour = 0; hour < 24; hour++) {
			hrNum.addItem(hour);
			if(hour==cTime/10000) {
				hrNum.setSelectedIndex(hour);
			}
		}
		for(int minute = 0; minute < 60; minute++) {
			minNum.addItem(minute);
			if(minute==(cTime%10000)/100) {
				minNum.setSelectedIndex(minute);
			}
		}
		for(int second = 0; second < 60; second++) {
			secNum.addItem(second);
			if(second==cTime%100) {
				secNum.setSelectedIndex(second);
			}
		}
		
	}
	private void createButton() {
		
		comfirm = new JButton("Confirm");
		comfirm.setFont(new Font("SansSerif", Font.PLAIN, 15));
		comfirm.setBounds(594, 46, 114, 40);
		class confirmListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				checkComfirmbtn=true;
				hour = (Integer)hrNum.getSelectedItem();  //把combo box裡面的數字抓近來				
				ddHour = (String)dFormat.format(hour);
				
				minute = (Integer)minNum.getSelectedItem();
				ddMinute = (String)dFormat.format(minute);
				
				second=(Integer)secNum.getSelectedItem();
				ddSecond=(String)dFormat.format(second);
				
				String ini_digit = String.format("%s:%s:%s",ddHour,ddMinute,ddSecond);
				digitLabel.setText(ini_digit);
				
				//reset button 會需要初始的時間
				ini_hour = hour;
				ini_minute = minute;
				ini_sec = second;
			}
		}
		comfirm.addActionListener(new confirmListener());
		
		
		
		start = new JButton("Start");
		start.setFont(new Font("SansSerif", Font.PLAIN, 24));
		start.setBounds(91, 409, 171, 69);
		class startListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if (!checkComfirmbtn)
				{
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Null time:Haven't input time.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					countdownTimer();
					timer.start();
				}					
			}
		}
		start.addActionListener(new startListener());
		
		
		
		
		reset = new JButton();
		reset.setFont(new Font("SansSerif", Font.PLAIN, 15));
		ImageIcon resetIcon=home.setImage(".\\img\\restart.png",30,30);
		reset.setIcon(resetIcon);
		reset.setBounds(594, 46, 87, 87);
		class resetListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					timer.stop();		
					// Countdown Timer
					hour = ini_hour;  //把combo box裡面的數字抓近來				
					ddHour = (String)dFormat.format(hour);
					
					minute = ini_minute;
					ddMinute = (String)dFormat.format(minute);
					
					second = ini_sec;
					ddSecond = (String)dFormat.format(second);
					
					String reset_digit = String.format("%s:%s:%s",ddHour,ddMinute,ddSecond);
					digitLabel.setText(reset_digit);
					countdownTimer();
					timer.start();	
				}
				catch(NullPointerException np){
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Null time:Haven't input time.","Error",JOptionPane.ERROR_MESSAGE);
				}

			}
		}
		reset.addActionListener(new resetListener());
		
	}
	
	private void createTimer() {
		digitLabel = new JLabel("00:00:00");
		digitLabel.setFont(new Font("SansSerif", Font.BOLD, 96));
		digitLabel.setBounds(42, 48, 625, 153);
	}
	
	
	
	public void countdownTimer() {
		
		timer = new Timer(1000, new ActionListener() { //1000毫秒=1秒，表示以1秒為單位計時
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				second--;
				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);	
				ddHour = dFormat.format(hour);
				digitLabel.setText(ddHour + ":" + ddMinute + ":" + ddSecond);
				
				if(second == -1) {
					second = 59;
					minute--;
					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);	
					ddHour = dFormat.format(hour);
					digitLabel .setText(ddHour + ":" + ddMinute + ":" + ddSecond);
				}
				
				if(minute == -1) {
					minute = 59;
					hour--;
					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);	
					ddHour = dFormat.format(hour);
					digitLabel .setText(ddHour + ":" + ddMinute + ":" + ddSecond);
				}
				if(minute == 0 && second == 0 && hour == 0) {
					checkComfirmbtn=false; //每次計時完，把confirm按鈕的布林直回歸初始(false:沒按的狀態)
					timer.stop();
				}
			}
		});		
	}
	private void createPanel() {
		
		timeSetting = new JPanel();
		timeSetting.add(hrNum);
		timeSetting.add(hr);
		timeSetting.add(minNum);
		timeSetting.add(min);
		timeSetting.add(secNum);
		timeSetting.add(sec);
		timeSetting.add(comfirm);
		
		time = new JPanel();
		time.add(digitLabel);
		
		controll = new JPanel();
		controll.add(start);
		controll.add(reset);
		
		allPanel = new JPanel(new BorderLayout());
		allPanel.add(timeSetting, BorderLayout.NORTH);
		allPanel.add(time, BorderLayout.CENTER);
		allPanel.add(controll, BorderLayout.SOUTH);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setFont(new Font("SansSerif", Font.PLAIN, 24));
		controll.add(btnClose);
		getContentPane().add(allPanel);
	}
	
	
}
