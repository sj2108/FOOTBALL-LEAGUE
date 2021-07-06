package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;




public class Player {
	
	public static int PlayerID;
	public static String Player_name;
	public static String Team_name;
	public static int Jersey_no;
	public static String Playing_pos;
	public static String Nationality;
	
	public static void displayPlayer() {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * FROM players left JOIN playerStats ON players.playerID=playerStats.playerID where player_name=?";
			PreparedStatement pst = c.prepareStatement(q);
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("player Name:");
		     Player_name = br.readLine();
		     pst.setString(1,Player_name);
				ResultSet rs=pst.executeQuery();
				
				String format = "%-10s| %-20s| %-15s| %-15s| %-15s| %-20s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %s";
				System.out.println("PlayerID "+"  PlayerName     "+"           TeamName "+"       JersyNumber "+"  PlayingPosition"+"  Nationality   "+"        Matches"+"      Goals   "+"   Assists    "+" Fouls  "+"      YellowCard   "+"RedCard    "+"Tackles    "+"Cleansheets");
				while(rs.next()) 
				{
					
					System.out.println(String.format(format,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getInt(15)));
				}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public static void displayTopStriker() {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * FROM players left JOIN playerStats ON players.playerID=playerStats.playerID  order by playerStats.goals desc limit 10";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			
	          
				
				
			String format = "%-10s| %-20s| %-15s| %-15s| %-15s| %-20s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %s";
			System.out.println("PlayerID "+"  PlayerName     "+"           TeamName "+"       JersyNumber "+"  PlayingPosition"+"  Nationality   "+"        Matches"+"      Goals   "+"   Assists    "+" Fouls  "+"      YellowCard   "+"RedCard    "+"Tackles    "+"Cleansheets");
			while(rs.next()) 
			{
				
				System.out.println(String.format(format,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getInt(15)));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public static void displayTopDefener() {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * FROM players left JOIN playerStats ON players.playerID=playerStats.playerID  order by playerStats.tackles desc limit 10";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			
			String format = "%-10s| %-20s| %-15s| %-15s| %-15s| %-20s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %s";
			System.out.println("PlayerID "+"  PlayerName     "+"           TeamName "+"       JersyNumber "+"  PlayingPosition"+"  Nationality   "+"        Matches"+"      Goals   "+"   Assists    "+" Fouls  "+"      YellowCard   "+"RedCard    "+"Tackles    "+"Cleansheets");
			while(rs.next()) 
			{
				
				System.out.println(String.format(format,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getInt(15)));
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public static void displayTopGoalKeeper() {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * FROM players left JOIN playerStats ON players.playerID=playerStats.playerID  order by playerStats.cleansheets desc limit 3";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			
	          
			String format = "%-10s| %-20s| %-15s| %-15s| %-15s| %-20s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %s";
			System.out.println("PlayerID "+"  PlayerName     "+"           TeamName "+"       JersyNumber "+"  PlayingPosition"+"  Nationality   "+"        Matches"+"      Goals   "+"   Assists    "+" Fouls  "+"      YellowCard   "+"RedCard    "+"Tackles    "+"Cleansheets");
			while(rs.next()) 
			{
				
				System.out.println(String.format(format,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getInt(15)));
			}	
				
		
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public static void GoldenBoot() {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="select *,playerStats.goals from players inner join playerStats on players.playerID=playerStats.playerID where playerStats.playerID = (select playerID from playerStats where goals=(select max(goals) from playerStats) order by goals desc limit 1)";
					 
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			
			String format = "%-10s| %-20s| %-20s| %-10s| %-20s| %-20s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s";
			System.out.println("PlayerID "+"      PlayerName           "+"TeamName "+"            JersyNumber"+" PlayingPosition"+"        Nationality   "+"        Matches"+"      Goals   "+"  Assists     "+"Fouls       "+"YellowCard   "+"RedCard    "+"Tackles     "+"Cleansheets");
			while(rs.next()) 
			{
				
				System.out.println(String.format(format,rs.getInt(1)+"",rs.getString(2)+"     ",rs.getString(3),rs.getInt(4)+"",rs.getString(5),rs.getString(6),rs.getInt(8)+"",rs.getInt(9)+"",rs.getInt(10)+"",rs.getInt(11)+"",rs.getInt(12)+"",rs.getInt(13)+"",rs.getInt(14)+"",rs.getInt(15)+""));
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public static void GoldenGlove() {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="select *,playerStats.goals from players inner join playerStats on players.playerID=playerStats.playerID where playerStats.playerID = (select playerID from playerStats where cleansheets=(select max(cleansheets) from playerStats) order by goals desc limit 1)";
			 
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			
			String format = "%-10s| %-20s| %-20s| %-10s| %-20s| %-20s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s";
			System.out.println("PlayerID "+"      PlayerName           "+"TeamName "+"            JersyNumber"+" PlayingPosition"+"        Nationality   "+"        Matches"+"      Goals   "+"  Assists     "+"Fouls       "+"YellowCard   "+"RedCard    "+"Tackles     "+"Cleansheets");
			while(rs.next()) 
			{
				
				System.out.println(String.format(format,rs.getInt(1)+"",rs.getString(2)+"     ",rs.getString(3),rs.getInt(4)+"",rs.getString(5),rs.getString(6),rs.getInt(8)+"",rs.getInt(9)+"",rs.getInt(10)+"",rs.getInt(11)+"",rs.getInt(12)+"",rs.getInt(13)+"",rs.getInt(14)+"",rs.getInt(15)+""));
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}


	public static void displayGoalScorerTeam(String team) {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * FROM players left JOIN playerStats ON players.playerID=playerStats.playerID   where players.team_name=? order by playerStats.goals desc";
			PreparedStatement pst = c.prepareStatement(q);
	          
		    
		     pst.setString(1,team);
		     
				ResultSet rs=pst.executeQuery();
			
	          
				
				
				String format = "%-10s| %-20s| %-15s| %-15s| %-15s| %-20s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %s";
				System.out.println("PlayerID "+"  PlayerName     "+"           TeamName "+"       JersyNumber "+"  PlayingPosition"+"  Nationality   "+"        Matches"+"      Goals   "+"   Assists    "+" Fouls  "+"      YellowCard   "+"RedCard    "+"Tackles    "+"Cleansheets");
				while(rs.next()) 
				{
					
					System.out.println(String.format(format,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getInt(15)));
				}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
		public static void displayAssitProviderTeam(String team) {
			try {
				
				
				Connection c =ConnectionProvider.getConnection();
				String q ="SELECT * FROM players left JOIN playerStats ON players.playerID=playerStats.playerID   where players.team_name=? order by playerStats.assists desc";
				PreparedStatement pst = c.prepareStatement(q);
		          
			    
			     pst.setString(1,team);
			     
					ResultSet rs=pst.executeQuery();
				
		          
					
					
					String format = "%-10s| %-20s| %-15s| %-15s| %-15s| %-20s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %s";
					System.out.println("PlayerID "+"  PlayerName     "+"           TeamName "+"       JersyNumber "+"  PlayingPosition"+"  Nationality   "+"        Matches"+"      Goals   "+"   Assists    "+" Fouls  "+"      YellowCard   "+"RedCard    "+"Tackles    "+"Cleansheets");
					while(rs.next()) 
					{
						
						System.out.println(String.format(format,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getInt(15)));
					}
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		
	}
		
		public static void displayRedCard() {
			try {
				
				
				Connection c =ConnectionProvider.getConnection();
				String q ="SELECT sum(red_cards) from playerStats ";
				Statement stmt =c.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				rs.next();
				System.out.println("Total Red Cards = "+rs.getInt(1));  
				System.out.println();	
				
				Statement stm =c.createStatement();
				ResultSet r=stm.executeQuery("select players.player_name,players.jersey_no,players.team_name,playerStats.red_cards from players inner join playerStats on players.playerID=playerStats.playerID where red_cards > 0");
				
				
				String format = "%-20s| %-10s| %-15s| %s";
				System.out.println("PlayerName     "+"     JersyNumber "+" TeamName       "+" RedCard ");
				while(r.next()) 
				{	
					
					System.out.println(String.format(format,r.getString(1),r.getInt(2),r.getString(3),r.getInt(4)));
				}
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
	
		}
		
	
		public static void AddPlayer() {
			try {
				
				
				Connection c =ConnectionProvider.getConnection();
				  
			    
			    
			  
			     
				 
				 String q ="insert into players(player_name,team_name,jersey_no,playing_pos,nationality) values(?,?,?,?,?)";
				 PreparedStatement pst = c.prepareStatement(q);
				 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				 System.out.println("player Name:");
			     Player_name = br.readLine();
			     System.out.println("Team Name:");
			     Team_name = br.readLine();
			     System.out.println("Jersey Number:");
			     Jersey_no = Integer.parseInt(br.readLine());
			     System.out.println("Playing pos:");
			     Playing_pos = br.readLine();
			     
			     System.out.println("Nationality:");
			     Nationality = br.readLine();
			     
			     
			    
			    
					pst.setString(1,Player_name);
					pst.setString(2,Team_name);
					pst.setInt(3,Jersey_no);
					pst.setString(4,Playing_pos);
					pst.setString(5,Nationality);
					pst.executeUpdate();
					 
					  pst = c.prepareStatement("select playerID from players where player_name=?");
						 pst.setString(1,Player_name);
						 ResultSet rs=pst.executeQuery();
						 rs.next();
						 int playerid= rs.getInt(1);
						 
						 String Q="insert into playerStats(playerID) values(?)";
						  pst = c.prepareStatement(Q);
						  pst.setInt(1,playerid);
						 
						  pst.executeUpdate();
					 
				 pst.close();
				 System.out.println("Player added");
				}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
		
		public static void updatePlayerStats() {
			try {
				
				
				Connection c =ConnectionProvider.getConnection();
				  
				 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					
				 System.out.println("player Name:");
			      String name = br.readLine();
			    
			    
			      PreparedStatement pst = c.prepareStatement("select playerID from players where player_name=?");
				 pst.setString(1,name);
				 ResultSet rs=pst.executeQuery();
				 rs.next();
				 int playerid= rs.getInt(1);
				
			     
				 
				 String q ="update playerStats set matches_played=?,goals=? ,assists=?, fouls=? ,yellow_cards=? ,red_cards=?, tackles=?, cleansheets=? where playerID=?";
				  pst = c.prepareStatement(q);
				 
			      System.out.println("matches played:");
			      int Matches_played = Integer.parseInt(br.readLine());
			     
			      System.out.println("Goals:");
			      int goals = Integer.parseInt(br.readLine());
			     
			      System.out.println("Assists:");
			      int assists = Integer.parseInt(br.readLine());
			     
			      System.out.println("fouls:");
			      int fouls = Integer.parseInt(br.readLine());
			     
			      System.out.println("Yellow Cards:");
			      int yellow_card = Integer.parseInt(br.readLine());
			     
			      System.out.println("Red cards:");
			      int red_cards = Integer.parseInt(br.readLine());
			     
			      System.out.println("Tackles:");
			      int tackles = Integer.parseInt(br.readLine());
			     
			      System.out.println("Cleansheets:");
			      int cleansheets = Integer.parseInt(br.readLine());
			     
					
					pst.setInt(1,Matches_played);
					pst.setInt(2,goals);
					pst.setInt(3,assists);
					pst.setInt(4,fouls);
				
					pst.setInt(5,yellow_card);
					pst.setInt(6,red_cards);
					pst.setInt(7,tackles);
					pst.setInt(8,cleansheets);
					pst.setInt(9,playerid);
			
					pst.executeUpdate();
					 
					 
				 pst.close();
				 System.out.println("Player added");
				}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
		
		
}


