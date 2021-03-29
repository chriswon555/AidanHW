import java.util.Scanner;//CTRL-SHIFT-O Shortcut

public class TestOneProject {

	public static void main(String[] args) {
		int lives = 3;
		int number = 0;
		int randomNumber = 0;
		int min = 1, max = 10;
		int again = 0;
		int yesNo = 0;
		int difficulty = 0;
		boolean inputSuccess = true;

		Scanner guess = new Scanner(System.in);
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to the number guessing game!");//Opening...

		do { //try again loop
			System.out.println("Please pick a level of difficulty:"); //levels of difficulty
			System.out.println("======================================================");
			System.out.println("1 = Easy(1 to 10) 2 = Medium(1 to 15) 3 = Hard(1 to 20)");

			difficulty = errorTrap(1,3); //Error Trap	

			max = difficulty(difficulty); //Difficulty Level

			randomNumber = (int)(Math.random()*(max-min+1)+min);//random number generator
			// System.out.print(randomNumber); // The random number

			do//start of loop
			{
				System.out.print("Please enter a number between " + min + " and " + max + " : ");

				number = errorTrap(min,max);//error trap

				if(number == randomNumber) 
				{//If number is right...
					System.out.println("Correct! You got it right!");
					lives = 0;
				} else {
					//If number is wrong...
					lives--;

					hotWarmCold(number, randomNumber);//Tells you how close your guess is...

					if(lives == 0) {
						System.out.println("You lose! The number was: " + randomNumber);//Prints out random number
					} else {
						System.out.println("You have " + lives + " lives left.");
					}
				}

			}while(lives > 0); // loop ends when lives reaches 0...			

			System.out.print("Would you like to play again 0=Yes 1=No ");

			yesNo = errorTrap(0,1); //Error Trap

			if (yesNo == 0) //if they play again...
			{
				again = 1;
				lives = 3;
			} else //if they do not want to play again...
			{
				again = 0;
				System.out.println("G A M E  O V E R ! ! !");
			}

		}while(again != 0); //end of try again loop

	}	


	public static void hotWarmCold(int guess, int randomNumber)
	{
		int difference = Math.abs(guess - randomNumber);
		//method to determine how close your guess is to the randomNumber
		if(difference >= 3) 
			System.out.println("Cold!");
		else if(difference == 2) 
			System.out.println("Warm");
		else 
			System.out.println("Hot!");
	}

	public static int difficulty (int difficulty)
	{
		//levels of difficulty
		int max = 10;

		if (difficulty == 1) 
		{
			return 10;
		} else if (difficulty == 2)
		{
			return 15;
		} else if (difficulty == 3) 
		{
			return 20;
		}
		return 10;
	}

	public static int errorTrap(int min, int max)
	{
		//Method for Error Trapping code
		int number = 0;
		boolean inputSuccess = true;
		Scanner input = new Scanner(System.in);
		do
		{
			inputSuccess = true;
			try
			{
				number = input.nextInt();
			}
			catch (Exception e)
			{
				inputSuccess = false;
				System.out.println("Please enter a number...");
				input.next();
			}

			if(inputSuccess && (number < min || number > max))
			{
				System.out.println("Please enter a number within the range...");
				inputSuccess = false;
			}
		}while(inputSuccess == false);

		return number;

	}

}
