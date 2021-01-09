import java.util.Scanner;
import java.util.Random;

public class GameOfNim {
	//global variables
	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();
	static int remainingStones = randomStones();
	
	//main function
	public static void main(String[] args) {
		System.out.print("Welcome to Nim \n\n1. One PLayer \n2. Two Player \nPlease select a game mode: ");

		String gameMode = scanner.next();
		validStart(gameMode);
	}
	
	//checks if the user is putting in a valid option for the game mode
	public static void validStart(String x) {
		while (!(x.equals("1") || x.equals("2"))) {
			System.out.print("Invalid Input! \n\n1. One PLayer \n2. Two Player\nPlease select a game mode: ");
			x = scanner.next();
		}
		if(x.equals("1")) {
			onePlayer();
		}
		if (x.equals("2")) {
			twoPlayer();
		}
	}
	
	//method to randomly chose how many stones there are for the game 
	public static int randomStones() {
		int min = 15;
		int max = 31;
		int randomStoneNum = random.nextInt(max-min) + min;
		
		return randomStoneNum;
	}
	
	//checks if the AI is making a random valid choice
	public static int validAIChoice() {
		int min = 1;
		int max = 4;
		int aiChoice = random.nextInt(max-min) + min;
		
		if(aiChoice > remainingStones && remainingStones != 0) {
			max = remainingStones + 1;
			aiChoice = random.nextInt(max-min) + min;
		}
		return aiChoice;
	}
	
	//This method is to make sure the players only enter valid entries during the game
	public static int validStoneChoice(int x) {
		while(x > 3 || x < 1 || x > remainingStones) {
			System.out.print("Invalid Input! \n\nPlease insert the number of stones you would like to take(1-3): ");
			if(scanner.hasNextInt()) {
				x = scanner.nextInt();
			}	
			else {
				player();
			}
		}
		return x;
	}
	
	//method for player entries
	public static int player() {
		int playerChoice = 0;
		int finalPlayerChoice = 0;
		
		while(scanner.hasNext()) {
			if(scanner.hasNextInt()) {
				playerChoice = scanner.nextInt();
				finalPlayerChoice = validStoneChoice(playerChoice);
				break;
			} 
			else {
				System.out.print("Invalid Input! \n\nPlease insert the number of stones you would like to take(1-3): ");
				scanner.next();
			}
		}
		return finalPlayerChoice;
	}
	
	//determines the winner
	public static void winner(int x) {
		if(x % 2 == 1) {
			System.out.println("\nPlayer Two loses. Congratulations Player One!");
			System.out.print("\nDo you want to play again (y/n)?: ");
			String toContinue = scanner.next().toLowerCase();
			toContinueGame(toContinue);
		}
		if(x % 2 == 0) {
			System.out.println("\nPlayer One loses. Congratulations Player Two!");
			System.out.print("\nDo you want to play again (y/n)?: ");
			String toContinue = scanner.next().toLowerCase();
			toContinueGame(toContinue);
		}
	}
		
	//one player game mode (AI)
	public static void onePlayer() {
		int counter = 1;
		int AI = 0; 
		
		while(remainingStones > 0) {
			if(counter % 2 == 1) {
				System.out.print("\nPlayer One: \n\nThere are " + remainingStones + " stones remaining. \n\nPlease insert the number of stones you would like to take(1-3): ");
				int choice1 = player();
				remainingStones = remainingStones - choice1;
				counter++;
			}	
			if(counter % 2 == 0 && remainingStones != 0) {
				System.out.println("\nPlayer Two (AI): \n\nThere are " + remainingStones + " stones remaining.");
				AI = validAIChoice();
				System.out.println("\nPlease insert the number of stones you would like to take(1-3): " + AI);
				
				remainingStones = remainingStones - AI;
				counter++;
			}
		}
		if(remainingStones < 1) {
			winner(counter);
		}
	}

	//two player game mode
	public static void twoPlayer() {
		int counter = 1;
	
		while(remainingStones > 0) {
			if(counter % 2 == 1) {
				System.out.print("\nPlayer One: \n\nThere are " + remainingStones + " stones remaining. \n\nPlease insert the number of stones you would like to take(1-3): ");
				int choice1 = player();
				remainingStones = remainingStones - choice1;
				if(remainingStones == 0) {
					counter++;
					break;
				}
				counter++;
			}	
			if(counter % 2 == 0) {
				System.out.print("\nPlayer Two: \n\nThere are " + remainingStones + " stones remaining. \n\nPlease insert the number of stones you would like to take(1-3): ");
				int choice2 = player();
				remainingStones = remainingStones - choice2;
				counter++;
			}
		}
		if(remainingStones < 1) {
			winner(counter);
		}
	}
	
	//to continue the game or not 
	public static void toContinueGame(String x) {
		while(!(x.equals("y") || x.equals("n"))) {
			System.out.print("Invalid Input! \n\nDo you want to play again (y/n)?: ");
			x = scanner.next().toLowerCase();
		}
		if(x.equals("y")) {
			remainingStones = randomStones();
			System.out.print("Welcome to Nim \n\n1. One PLayer \n2. Two Player \nPlease select a game mode: ");

			String gameMode = scanner.next();
			validStart(gameMode);
		}
		if(x.equals("n")) {
			System.exit(0);
		}
	}
}