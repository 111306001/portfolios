import java.sql.*;

public class Lab18 {
	
	public static void main(String[] args) {
		
		String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "111306001"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "111306001"; // change to your own user name
		String password = "ttgj1"; // change to your own password
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			System.out.println("DB Connectd");
			// Task 1 //
			System.out.println("Query 1:");
			Statement stat = conn.createStatement();
			String query;
			boolean sucess;
		
			query = "SELECT `ID`, `Name`, `Birth`, `Position` FROM `TWICE` WHERE Position = 'Vocal';";
			sucess = stat.execute(query);
			if (sucess) {
				ResultSet result = stat.getResultSet();
				showResultSet(result);
				result.close();
			}
			System.out.println("-".repeat(80));
			// Task 2 //
			query = "INSERT INTO `TWICE`(`ID`, `Name`, `Birth`, `Height`, `Position`) VALUES ('10','Shihyu','1999-01-11','163.78','ACE')";
			sucess = stat.execute(query);
			if (sucess) {
				ResultSet result = stat.getResultSet();
				showResultSet(result);
				result.close();
			}
			// Task 3 //
			System.out.println("Query 2:");
			query = "SELECT * FROM `TWICE` WHERE Birth > '1999'";
			sucess = stat.execute(query);
			if (sucess) {
				ResultSet result = stat.getResultSet();
				showResultSet(result);
				result.close();
			}
			
			System.out.println("-".repeat(80));
			System.out.println("Query 3:");
			// Task 4 //
			query = "DELETE FROM `TWICE` WHERE Name = 'Shihyu';";
			sucess = stat.execute(query);
			if (sucess) {
				ResultSet result = stat.getResultSet();
				showResultSet(result);
				result.close();
			}
			// Task 5 //
			query = "SELECT `ID`, `Name`, `Height` FROM `TWICE` WHERE Height > 161 AND Height < 165";
			sucess = stat.execute(query);
			if (sucess) {
				ResultSet result = stat.getResultSet();
				showResultSet(result);
				result.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// define this static method in your Lab18 class
	public static void showResultSet(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			System.out.printf("%15s", metaData.getColumnLabel(i));
		}
		System.out.println();
		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
					System.out.printf("%15s", result.getString(i));
			}
			System.out.println();
		}
	}
}