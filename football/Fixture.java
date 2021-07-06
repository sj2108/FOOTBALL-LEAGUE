package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fixture {
	
	public static int match_number;
	public static String Team_1;
	public static String Team_2;
	public static String match_type;
	public static String winner;
	public static String match_date;
	public static String venue;
	public static int score_team1;
	public static int score_team2;
	public static String Looser;

	
	public static void updateFixture() {
		try {
			
			
			Connection c =ConnectionProvider.getConnection();
			String q ="update fixture set score_team1=?,score_team2=? where match_no=?";
			PreparedStatement pst = c.prepareStatement(q);
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("match Number:");
		        match_number = Integer.parseInt(br.readLine());
			    //System.out.println("Winner:");
			    //winner=br.readLine();
			   
			    System.out.println("Team1 Score:");
			    score_team1= Integer.parseInt(br.readLine());
			    System.out.println("Team2 Score:");
			    score_team2= Integer.parseInt(br.readLine());
			    
			    
			   
		      //pst.setString(,winner);
		      pst.setInt(1,score_team1);
		      pst.setInt(2,score_team2);
		      pst.setInt(3,match_number);
			  pst.executeUpdate();
			  if(score_team1>0 && score_team2>0)
			  {
				int i=0;
				while(true)
				{
				pst = c.prepareStatement("insert into matchGoals(match_no,playerID,no_of_goals) values(?,?,?) ");
					System.out.println("PlayerID:");
					int pl= Integer.parseInt(br.readLine());
					
					
					
					System.out.println("Goals:");
					int goal= Integer.parseInt(br.readLine());
					
					
					
					  pst.setInt(1,match_number);
				      pst.setInt(2,pl);
				      pst.setInt(3,goal);
				      
					 pst.executeUpdate();
					  
					 pst = c.prepareStatement("update playerStats set goals=goals+? where playerID=?");
						
					  pst.setInt(1,goal);
				      pst.setInt(2,pl);
				      
					  pst.executeUpdate();
					  System.out.println("Enter 0 to complete entry or 1 to insert another entry");
					  i++;
					  if( Integer.parseInt(br.readLine())==0)
						  break;
					
				}
			  }
				pst = c.prepareStatement("select team_1,team_2 from fixture where match_no=?");
				
				pst.setInt(1, match_number);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					Team_1=rs.getString(1);
					Team_2=rs.getString(2);
					if(score_team1==score_team2)
				    	winner="Tie";
				    else if(score_team1>score_team2)
				    	winner=Team_1;
				    else
				    	winner=Team_2;
				    
					
				}
				pst = c.prepareStatement("update fixture set winner=? where match_no=?");
				
				pst.setString(1, winner);
				pst.setInt(2,match_number);
				pst.executeUpdate();
				
				
				if(winner.equals("Tie"))
				{
				pst = c.prepareStatement("update team set tie=tie+1,points=points+1,matches_played=matches_played+1,goals_scored=goals_scored+?,goals_conceded=goals_conceded+? where team_name=?");
				 pst.setInt(1,score_team1);
			      pst.setInt(2,score_team2);
			      pst.setString(3,Team_1);
				  pst.executeUpdate();
				  
				  pst = c.prepareStatement("update team set tie=tie+1,points=points+1,matches_played=matches_played+1,goals_scored=goals_scored+?,goals_conceded=goals_conceded+? where team_name=?");
					 pst.setInt(1,score_team2);
				      pst.setInt(2,score_team1);
				      pst.setString(3,Team_2);
					  pst.executeUpdate();
					 pst.close();
					 
					// System.out.println("Fixture updated");
					
				}
				else
				{
					if(winner.equals(Team_1))
					{
						pst = c.prepareStatement("update team set wins=wins+1,points=points+3,matches_played=matches_played+1,goals_scored=goals_scored+?,goals_conceded=goals_conceded+? where team_name=?");
						 pst.setInt(1,score_team1);
					      pst.setInt(2,score_team2);
					      pst.setString(3,Team_1);
						  pst.executeUpdate();
						
						  pst = c.prepareStatement("update team set loses=loses+1,points=points+3,matches_played=matches_played+1,goals_scored=goals_scored+?,goals_conceded=goals_conceded+? where team_name=?");
							 pst.setInt(1,score_team2);
						      pst.setInt(2,score_team1);
						      pst.setString(3,Team_2);
							  pst.executeUpdate();
							  
							  pst.close();
							
					}
					else
					{
						pst = c.prepareStatement("update team set loses=loses+1,points=points+3,matches_played=matches_played+1,goals_scored=goals_scored+?,goals_conceded=goals_conceded+? where team_name=?");
						 pst.setInt(1,score_team1);
					      pst.setInt(2,score_team2);
					      pst.setString(3,Team_1);
						  pst.executeUpdate();
						
						  
						  pst = c.prepareStatement("update team set wins=wins+1,points=points+3,matches_played=matches_played+1,goals_scored=goals_scored+?,goals_conceded=goals_conceded+? where team_name=?");
							 pst.setInt(1,score_team2);
						      pst.setInt(2,score_team1);
						      pst.setString(3,Team_2);
							  pst.executeUpdate();
							  
							  pst.close();
					}
				}
			//	pst.close();
				
				System.out.println("Fixture updated");
				
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public static void displayFixture() {
		try {
			
		
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * from fixture";
			Statement stmt =c.createStatement();
			ResultSet rs=stmt.executeQuery(q);
			
	         
			String format = "%s\t| %s\t| %s\t| %s\t  | %s\t| %s\t\t| %s\t\t|%s\t| %s\t";
			System.out.println("Match No."+"    "+"Team1        "+"    Team2  "+"    Match Type "+" winner   "+"  "+" Team1_score     "+"Team2_score"+"      Match Date"+"    Venue");
			//rs.next();
			while(rs.next()) 
			{	
				
				
				 
				System.out.println(String.format(format,rs.getInt(1)+"",rs.getString(2)+"   ",rs.getString(3)+"   ",rs.getString(4),rs.getString(5)+" ",rs.getInt(6)+" ",rs.getInt(7)+" ",rs.getString(8),rs.getString(9) ));
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}

	public static void displayTeamFixture(String team) {
		try {
			
		
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * from fixture where team_1=? or team_2=?";
			PreparedStatement pst = c.prepareStatement(q);
	          
		     
		   
		  
		     pst.setString(1,team);
		     pst.setString(2,team);
				ResultSet rs=pst.executeQuery();
				
			
	         
			String format = "%s\t| %s\t| %s\t| %s\t  | %s\t| %s\t\t| %s\t\t|%s\t| %s\t";
			System.out.println("Match No."+"    "+"Team1        "+"    Team2  "+"    Match Type "+" winner   "+"  "+" Team1_score     "+"Team2_score"+"      Match Date"+"      Venue");
			//rs.next();
			while(rs.next()) 
			{	
				
				
				 
				System.out.println(String.format(format,rs.getInt(1)+"",rs.getString(2)+"   ",rs.getString(3)+"   ",rs.getString(4),rs.getString(5)+" ",rs.getInt(6)+" ",rs.getInt(7)+" ",rs.getString(8),rs.getString(9) ));
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}


	public static void displayVenueFixture() {
		try {
			
		
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * from fixture where venue=?";
			PreparedStatement pst = c.prepareStatement(q);
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("Venue:");
		   String team = br.readLine();
		     pst.setString(1,team);
		     
				ResultSet rs=pst.executeQuery();
				
			
	         
			String format = "%s\t| %s\t| %s\t| %s\t  | %s\t| %s\t\t| %s\t\t|%s\t| %s\t";
			System.out.println("Match No."+" "+"Team1        "+"    Team2  "+"       Match Type "+" winner   "+"  "+"   Team1_score     "+"Team2_score"+"      Match Date"+"     Venue");
			//rs.next();
			while(rs.next()) 
			{	
				
				
				 
				System.out.println(String.format(format,rs.getInt(1)+"",rs.getString(2)+"   ",rs.getString(3)+"   ",rs.getString(4),rs.getString(5)+" ",rs.getInt(6)+" ",rs.getInt(7)+" ",rs.getString(8),rs.getString(9) ));
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public static void displaysummary() {
		try {
			
		
			Connection c =ConnectionProvider.getConnection();
			String q ="SELECT * from fixture where match_no=?";
			PreparedStatement pst = c.prepareStatement(q);
	          
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    
		    System.out.println("Match Number:");
		    String Match = br.readLine();
		     pst.setString(1,Match);
		     
				ResultSet rs=pst.executeQuery();
				
			
	         
			String format = "%-10s| %-20s| %-20s| %-10s  | %-20s| %-10s| %-10s|%-20s| %s";
			System.out.println("Match No."+"    "+"Team1           "+"      Team2  "+"              Match Type "+"     winner   "+"  "+"        Team1_score "+"Team2_score"+"      Match Date"+"         Venue");
			//rs.next();
			while(rs.next()) 
			{	
				
				
				 
				System.out.println(String.format(format,rs.getInt(1)+"",rs.getString(2)+"   ",rs.getString(3)+"   ",rs.getString(4),rs.getString(5)+" ",rs.getInt(6)+" ",rs.getInt(7)+" ",rs.getString(8),rs.getString(9) ));
				
			}
			
			String Q ="SELECT players.player_name,players.team_name,matchGoals.no_of_goals from  players inner join matchGoals on players.playerID=matchGoals.playerID  where match_no=?";
			PreparedStatement pt = c.prepareStatement(Q);
			pt.setString(1,Match);
			ResultSet r=pt.executeQuery();
			
			
	         
			String ft =  "%-20s| %-20s| %s";
			System.out.println();
			System.out.println("PlayerName"+ "            Team Name"+"            Goals scored" );   
			//rs.next();
			while(r.next()) 
			{	
				
				
				 
				System.out.println(String.format(ft,r.getString(1)+"",r.getString(2)+"   ",r.getString(3)));
				
			}
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}


	
}
