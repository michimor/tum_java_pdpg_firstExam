package w02.h;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casino {

	private static boolean running = true;
	private static int tokens = 1000;
	private static CardDeck deck;
	private static int points_user = 0;
	private static int index_user = 0;
	private static int points_dealer = 0;
	private static int index_dealer = 0;

	public static void penguBlackJack() {
		System.out.println("Welcome to Pengu-BlackJack!");
//		resetting all static vars because of Artemis
		running = true;
		tokens = 1000;
		points_user = 0;
		index_user = 0;
		points_dealer = 0;
		index_dealer = 0;
		while(running) {
			System.out.println("(1) Start a game or (2) exit");
			int input = readInt();
			if (input != 1 && input != 2) {
				System.out.println("What?!");
			}
			else {
				if (input == 1) {
					game();
				}
				if (input == 2) {
					end();
				}
			}
		}
	}

	public static void game() {
//		bet
		System.out.println("Your current balance: "+tokens);
		int bet;
		do {
			System.out.println("How much do you want to bet?");
			bet = readInt();
		} while (bet <= 0 || tokens-bet < 0);

//		init vars
		deck = CardDeck.getDeck(420);
		points_user = 0;
		points_dealer = 0;
		index_user = 0;
		index_dealer = 0;

//		start game
		System.out.println("Player cards:");
		drawCard(true);
		drawCard(true);
		System.out.println("Current standing: "+points_user);
		while (points_user < 21) {
			System.out.println("(1) draw another card or (2) stay");
			int input = readInt();
			if (input != 1 && input != 2) {
				System.out.println("What?!");
			}
			else {
				if (input == 1) {
					drawCard(true);
					System.out.println("Current standing: "+points_user);
				}
				if (input == 2) {
					break;
				}
			}
		}
		if (points_user > 21) {
			System.out.println("You lost "+bet+" tokens.");
			tokens = tokens - bet;
		}
		if (points_user == 21) {
			System.out.println("Blackjack! You won "+(bet*2)+" tokens.");
			tokens = tokens + (2*bet);
		}
		if (points_user < 21) {
			System.out.println("Dealer cards:");
			while(points_dealer < 21 && points_dealer < points_user) {
				drawCard(false);
			}
			System.out.println("Dealer: "+points_dealer);
			if (points_dealer > 21) {
				System.out.println("You won "+bet+" tokens.");
				tokens = tokens + bet;
			}
			else {
				System.out.println("Dealer wins. You lost "+bet+" tokens.");
				tokens = tokens - bet;
			}
		}
		if (tokens == 0) {
			System.out.println("Sorry, you are broke. Better Luck next time.");
			end();
		}
	}

	private static void drawCard(boolean user) {
		int card = deck.drawCard();
		if (user) {
			points_user = points_user + card;
			index_user++;
			System.out.println(index_user + " : " + card);
		}
		else {
			points_dealer = points_dealer + card;
			index_dealer++;
			System.out.println(index_dealer + " : " + card);
		}
	}

	public static void end() {
		System.out.println("Your final balance: "+tokens);
		if (tokens > 1000) {
			System.out.println("Wohooo! Ez profit! :D");
		}
		else {
			System.out.println("That's very very sad :(");
		}
		System.out.println("Thank you for playing. See you next time.");
		running = false;
	}

	public static String readString() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int readInt() {
		while (true) {
			String input = readString();
			try {
				return Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("This was not a valid number, please try again.");
			}
		}
	}

	public static void main(String[] args) {
		penguBlackJack();
	}

}
