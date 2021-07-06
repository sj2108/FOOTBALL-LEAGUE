import java.util.Scanner;
import football.*;


public class Main {
	public static void main(String[] args)
	{
		System.out.println("                                               Welcome TO Football League");
		while(true)
		{
		System.out.println("Enter: 1.Admin \n       2.User\n       3.to Exit");
		Scanner sc= new Scanner(System.in);
		int choose= sc.nextInt();
		switch(choose)
		{
			case 1 :System.out.println("1. Update Fixture");
					System.out.println("2. Add player");
					System.out.println("3. Update playerStats");
					System.out.println("4. Back");
					int option=sc.nextInt();
					
					switch(option)
					{
						case 1 :Fixture.updateFixture();
								break;
						case 2:Player.AddPlayer();
								break;
						case 3:Player.updatePlayerStats();
								break;
						
						case 4: break;		
						default:break;
					}
					break;
			case 2:	while(true)
			{
					System.out.println("1. Points Table");
					System.out.println("2. Display all the Awards");
					System.out.println("3. Display all the Fixtures");
					System.out.println("4. Display Match Summary");
					System.out.println("5. Display Top strikers");
					System.out.println("6. Display Top Defenders");
					System.out.println("7. Display Top GoalKeepers");
					System.out.println("8. Player Details");
					System.out.println("9. Team Details");
					System.out.println("10. Red Card Players ");
					System.out.println("11. Matches at Venue");
					System.out.println("12. Back");
					int Choice=sc.nextInt();
					switch(Choice)
					{
						case 1:Team.PointsTable();
								break;
								
						case 2:System.out.println("Golden Boot");
								Player.GoldenBoot();
								System.out.println("Golden Glove");
								Player.GoldenGlove();
								break;
						case 3:Fixture.displayFixture();
								break;
						case 4:Fixture.displaysummary();
								break;
						case 5:Player.displayTopStriker();
							
								break;
						case 6:Player.displayTopDefener();
								
								break;
						case 7:Player.displayTopGoalKeeper();
								break;
								
						case 8:Player.displayPlayer();
							    break;
						case 9: 
						
							 System.out.println("Team Name:");
							 String team =sc.next();
								
								while(true) {
									
								System.out.println("1. Squad");
								System.out.println("2. Top Striker");
								System.out.println("3. Top assist Provider");
								System.out.println("4. Fixture");
								System.out.println("5. back");
								int input=sc.nextInt();
								switch(input)
							{
								case 1:System.out.println("Squad");
									Team.DisplayTeam(team);
									break;
								case 2:System.out.println(" Top Striker");	
										Player.displayGoalScorerTeam(team);
										break;
								case 3:System.out.println("Top assist Provider");
										Player.displayAssitProviderTeam(team);
										break;
								case 4:System.out.println(" Fixture");
										Fixture.displayTeamFixture(team);
										break;
								
								default: break;
							}
							if(input>4)
								break;
						}
								//break;
						
						case 10:Player.displayRedCard();
								break;
						case 11: Fixture.displayVenueFixture();
								break;
							
						default:break;
					}
					
				if(Choice>11)
					break;
			}
			break;
			case 3: System.exit(0);
					break;
			default:System.out.println("Wrong choice enter!! ");
	            	break;
    
		}
		
	}

		
	}
	
}
