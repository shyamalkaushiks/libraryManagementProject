package in.inueron.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Dbconnection {
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("Driver loaded succesufully....");
		}

	public  static  Connection jdbcconnection() throws SQLException, IOException
		// TODO Auto-generated method stu	public static Connection   jdbcconnection() throws SQLException, IOException
		{
	
			   String username="root";
				String password="password";
			
			Connection connection=DriverManager.getConnection("jdbc:mysql:///shyamal",username,password);
			System.out.println("Connection is created");
			return connection;
			
			
			
		}

	
}
