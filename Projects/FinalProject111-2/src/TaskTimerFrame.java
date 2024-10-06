import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TaskTimerFrame extends JFrame implements ValueSetup{

    private JPanel timeSetting, time, controll, allPanel;
	private JLabel taskName, digitLabel;
	private JButton start, reset;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private Timer timer;
	private int hour, second, minute;
	private String ddHour, ddSecond, ddMinute;

	public TaskTimerFrame() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.FRAME_WIDTH, this.FRAME_HEIGHT);
		taskName = new JLabel("Task Name");
		createTimer();
		countdownTimer();
		createButton();
		createPanel();
	}
	private void createTimer() {
		digitLabel = new JLabel("03:03:03");
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
					timer.stop();
				}
			}
		});		
	}
	private void createButton() {
		start = new JButton("Start");
		start.setFont(new Font("SansSerif", Font.PLAIN, 15));
		start.setBounds(594, 46, 114, 40);
		class startListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				countdownTimer();
				timer.start();
			}
		}
		start.addActionListener(new startListener());
		
		reset = new JButton("Reset");
		reset.setFont(new Font("SansSerif", Font.PLAIN, 15));
		reset.setBounds(594, 46, 114, 40);
		class resetListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				
				countdownTimer();
				timer.start();
			}
		}
		reset.addActionListener(new resetListener());
	}
	public void createPanel() {
		timeSetting = new JPanel();
		timeSetting.add(taskName);
		
		time = new JPanel();
		time.add(digitLabel);
		
		controll = new JPanel();
		controll.add(start);
		controll.add(reset);
		
		allPanel = new JPanel(new BorderLayout());
		allPanel.add(timeSetting, BorderLayout.NORTH);
		allPanel.add(time, BorderLayout.CENTER);
		allPanel.add(controll, BorderLayout.SOUTH);
		this.add(allPanel);
	}

}
