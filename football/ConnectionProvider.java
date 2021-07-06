package football;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionProvider {
	private static Connection Con;
   public static Connection getConnection(){  
	try{  
		if(Con==null)
		{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/League","root","#Shreyansh123");  
 
		  
      }
	}
	catch(Exception e)
	{ 
		System.out.println(e);
	} 
	return Con;
  }  
} 
