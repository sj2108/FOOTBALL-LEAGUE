package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Team {
	
	public static String Team_name;
	public static int wins;
	public static int loses;
	public static int tie;
	public static int points;
	public static int ranking;
	public static String captain;
	public static String manager;
	public static int matches_played;
	
	
	public static void PointsTable() {
		try {
			
			int i=1;
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT team.team_name,team.matches_played,team.points, team.wins,team.loses,team.tie from team order by points desc";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			
	         
			String format = "%-10s| %-15s| %-10s| %-10s| %-10s| %-10s| %s";
			System.out.println("Ranking "+"    "+"Team Name"+"       Matches     "+"points "+"     wins "+"  "+"     loses  "+"     tie");
			//rs.next();
			while(rs.next()) 
			{	
				
				
				 
				System.out.println(String.format(format,i+"",rs.getString(1),rs.getInt(2)+"",rs.getInt(3)+"",rs.getInt(4)+"",rs.getInt(5)+"",rs.getInt(6)+"" ));
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}

	public static void DisplayTeam(String team) {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT team.team_name,team.matches_played,team.points, team.wins,team.loses,team.tie,team.captain,team.manager from team where team_name=?";
			PreparedStatement pst = c.prepareStatement(q);
	          
	
		    
		    
		     
		     pst.setString(1,team);
				ResultSet rs=pst.executeQuery();
			
	         
			String format = "%-15s| %-10s| %-10s| %-10s| %-10s| %-10s|%-15s|%s";
			System.out.println("Team Name"+"        Matches  "+"   points    "+"   wins "+"      "+"loses "+"      tie"+"         Captain"+"          Manager");
			//rs.next();
			while(rs.next()) 
			{	
				
				
				 
				System.out.println(String.format(format,rs.getString(1),rs.getInt(2)+"",rs.getInt(3)+"",rs.getInt(4)+"",rs.getInt(5)+"",rs.getInt(6)+"",rs.getString(7),rs.getString(8) ));
				
			}
			
			String Q ="SELECT jersey_no,player_name,playing_pos from players where team_name=?";
			PreparedStatement ps = c.prepareStatement(Q);
	          
		    
		     ps.setString(1,team);
			 ResultSet r=ps.executeQuery();
			
	         
			String Format = "%-15s|  %-20s|%s";
			System.out.println("jersey Number"+"      Player Name"+"       position");     
			while(r.next()) 
			{	
				
				
				 
				System.out.println(String.format(Format,r.getInt(1),r.getString(2),r.getString(3) ));
				
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
