package by.htp.library.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class MainApp {
	public static void main(String [] args) {
	
		String parametersConnect = "jdbc:mysql://db4free.net:3306/kyzniatsovahtp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
		String login = "ogulikss";
		String pass = "Korneluk1966";
	try(Connection connection = DriverManager.getConnection(parametersConnect, login, pass)) {
//		Connection connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/kyzniatsovahtp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","ogulikss","Korneluk1966");
		System.out.println(connection);
		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select id_book, book.title, publisher.title from book join publisher on publisher.id_publisher = book.id_publisher");
		
		while(rs.next()) {
			System.out.println("book_id: " + rs.getInt("id_book") + ", book_title: " + rs.getString("book.title") + ", publisher: " + rs.getString("publisher.title"));
		}
		
		PreparedStatement ps = connection.prepareStatement("Select title from publisher where phone_number = ?");
		ps.setString(1, "123");
		
		ResultSet rs2 = ps.executeQuery();
		
		while(rs2.next()) {
			System.out.println("Publisher title: " + rs2.getString("title"));
		}
		
		
	} catch(SQLException e) {
		e.printStackTrace();
	}

}
}
