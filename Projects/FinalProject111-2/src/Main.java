import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class Main {
	public static void main(String[] args) {
		String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "111306009"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "111306009"; // change to your own username
		String password = "mtpex"; // change to your own password
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB Connectd");
			EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					FirstPage frame = new FirstPage(conn);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
