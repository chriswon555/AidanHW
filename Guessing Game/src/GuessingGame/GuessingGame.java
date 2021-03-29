package GuessingGame;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
	static Scanner Input = new Scanner (System.in);
	static Random NumGen = new Random();

	public static void main(String[] args) {
		while (true) {
			
			int difficulty = getNumber(3,"What level of difficulty do you want? (1-3)")*10;
			
			int number = NumGen.nextInt(difficulty) + 1;
			boolean didWin = false;
			
			for (int i = 3; i > 0; i--) {
				int guess = getNumber(difficulty,"Guess what number I am thinking of:");
				if (guess == number) {
					didWin = true;
					break;
				}
				System.out.println("Nope, wrong number");
			}
			
			if (!didWin) {
				System.out.println(String.format("The correct number was %d", number));
			}
			if (!playAgain()) {
				System.out.println("Goodbye!");
			}
		}
	}
	
	public static boolean playAgain() {
		String playAgain;
		while (true) {
			System.out.println("Correct! Do you want to play again? (y/n)");
			try {
				playAgain = Input.next().toLowerCase();
			} catch (Exception e) {
				System.out.println("That is not a valid input");
				continue;
			} break;
		}
			if (playAgain.equals("y") || playAgain.equals("yes")) {
				return true;
				} else return false;
			}
	public static int getNumber(int upperLimit, String msg) {
		int num = 0;
		while (true) {
			System.out.println(msg);
			try {
				num = Input.nextInt();
			} catch (Exception e) {
				System.out.println("That is not a valid input");
				continue;
			} 
			if (num >= 1 && num <= upperLimit) {
				return num;
			} else {
				System.out.println("That is not in the correct range");
			}
		}
	}
}

