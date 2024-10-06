import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class ProjectInfoFrame extends JFrame implements ValueSetup{



	private JButton ComfirmButton,EditButton;
	private JTextArea noteTextArea;
	private JLabel DateLabel,intervalLabel,lblProjectName,StartDateLabel,EndDateLabel,NoteLabel ;
	private ProjectEditFrame pEditFrame;

	private SQLcommon sqlc = new SQLcommon();
	private Connection conn;
	private Statement stat;
	private JFrame Warning = new JFrame();
	private Home0504 home;
	
	private int userID;
	private int semesterID;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ProjectInfoFrame(Connection conn,int userID,String semesterID) throws SQLException {
		this.conn = conn;
		stat = conn.createStatement();
		this.userID = userID;
		
		this.semesterID = Integer.parseInt(semesterID);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		getContentPane().setLayout(null);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createButton();
		createTextArea();
		createLabel();	
		this.setTitle("Project Info");
	}
	
	public void createLabel() throws SQLException {
		DateLabel = new JLabel("Date");
		DateLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		DateLabel.setBounds(56, 136, 100, 30);
		getContentPane().add(DateLabel);
		
		intervalLabel = new JLabel("-");
		intervalLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		intervalLabel.setBounds(421, 136, 23, 30);
		getContentPane().add(intervalLabel);	
		
		stat.execute(sqlc.select("Name","SemesterID",semesterID,"Semester"));
		String name = sqlc.showResultSet(stat.getResultSet());
		lblProjectName = new JLabel(name);
		lblProjectName.setBounds(42, 39, 275, 60);
		lblProjectName.setFont(new Font("SansSerif", Font.BOLD, 48));
		getContentPane().add(lblProjectName);	
		
		stat.execute(sqlc.select("BeginDate","SemesterID",semesterID,"Semester"));
		String beginDate = sqlc.showResultSet(stat.getResultSet());
		StartDateLabel = new JLabel(beginDate);
		StartDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		StartDateLabel.setBounds(210, 136, 150, 30);
		getContentPane().add(StartDateLabel);		
		
		stat.execute(sqlc.select("EndDate","SemesterID",semesterID,"Semester"));
		String endDate = sqlc.showResultSet(stat.getResultSet());
		EndDateLabel = new JLabel(endDate);
		EndDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		EndDateLabel.setBounds(518, 136, 150, 30);
		getContentPane().add(EndDateLabel);
		
		NoteLabel = new JLabel("Note");
		NoteLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
		NoteLabel.setBounds(56, 196, 100, 30);
		getContentPane().add(NoteLabel);
		
	}
	
	
	public void createButton() {
		
		ComfirmButton = new JButton("COMFIRM");
		ComfirmButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		ComfirmButton.setBounds(587, 486, 100, 23);
		getContentPane().add(ComfirmButton);
		ComfirmButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{	
				try {
					home = new Home0504(conn,userID);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				home.setVisible(true);
				dispose();
			}
		});
		
		EditButton = new JButton("EDIT");
		EditButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		EditButton.setBounds(711, 486, 100, 23);
		getContentPane().add(EditButton);
		EditButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{	
				try {
					pEditFrame = new ProjectEditFrame(conn,userID,semesterID);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pEditFrame.setVisible(true);
				dispose();
			}
		});
	}

		
	
	
	public void createTextArea() throws SQLException {
		stat.execute(sqlc.select("Introduction","SemesterID",semesterID,"Semester"));
		String introduction = sqlc.showResultSet(stat.getResultSet());
		noteTextArea = new JTextArea();
		noteTextArea.setFont(new Font("Monospaced", Font.PLAIN, 24));
		noteTextArea.setBounds(56, 250, 750, 200);
		noteTextArea.setText(introduction);
		noteTextArea.setEditable(false);
		getContentPane().add(noteTextArea);
		
	}
	
	public void getText(String string) {
		lblProjectName.setText(string);
	}
}
