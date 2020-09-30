package IT114;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuesserHW {
	private int level = 1;
	private int strikes = 0;
	private int maxStrikes = 5;
	private int number = 0;
	private boolean isRunning = false;
	final String saveFile = "numberGuesserSave.txt";
	final String saveKey = "numberGuesserKey.txt";
	private int hc = 3;
	/***
	 * Gets a random number between 1 and level.
	 * 
	 * @param level (level to use as upper bounds)
	 * @return number between bounds
	 */
	public static int getNumber(int level) {
		int range = 9 + ((level - 1) * 5);
		System.out.println("I picked a random number between 1-" + (range + 1) + ", let's see if you can guess.");
		return new Random().nextInt(range) + 1;
	}

	private void win() {
		System.out.println("That's right!");
		level++;// level up!
		saveLevel();
		saveKeys();
		strikes = 0;
		System.out.println("Welcome to level " + level);
		number = getNumber(level);
	}

	private void lose() {
		System.out.println("Uh oh, looks like you need to get some more practice.");
		System.out.println("The correct number was " + number);
		strikes = 0;
		level--;
		if (level < 1) {
			level = 1;
		}
		saveLevel();
		saveKeys();
		number = getNumber(level);
	}

	private void processCommands(String message) {
		if (message.equalsIgnoreCase("quit")) {
			System.out.println("Tired of playing? No problem, see you next time.");
			isRunning = false;
		}
		if(message.equalsIgnoreCase("hint")) {
			int b1 = number+(level*2);
			int b2 = number-(level*2);
			if (b2 < 0) {
				b2 = 0;
			}
			if (hc > 0) {
				hc--;
			System.out.println("Heres a hint: its between " + b1 + " and " + b2);
			System.out.println("You have "+ hc +" left");
		} else {
				System.out.println("Sorry, none left.");
			}
		}
		if (message.equalsIgnoreCase("num")) {
			System.out.println(number);
		}
	}

	private void processGuess(int guess) {
		if (guess < 0) {
			return;
		}
		System.out.println("You guessed " + guess);
		if (guess == number) {
			win();
		} else {
			System.out.println("That's wrong");
			strikes++;
			if (strikes >= maxStrikes) {
				lose();
			} else {
				int remainder = maxStrikes - strikes;
				System.out.println("You have " + remainder + "/" + maxStrikes + " attempts remaining");
				if (guess > number) {
					System.out.println("Lower");
				} else if (guess < number) {
					System.out.println("Higher");
				}
			}
		}
	}

	private int getGuess(String message) {
		int guess = -1;
		try {
			guess = Integer.parseInt(message);
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a number, please try again");

		}
		return guess;
	}

	private void saveLevel() {
		try (FileWriter fw = new FileWriter(saveFile)) {
			fw.write("" + level);// here we need to convert it to a String to record correctly
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void saveKeys() {
		try (FileWriter ky = new FileWriter(saveKey)) {
			ky.write("" + level);// here we need to convert it to a String to record correctly
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean loadLevel() {
		File file = new File(saveFile);
		if (!file.exists()) {
			return false;
		}
		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				int _level = reader.nextInt();
				if (_level > 1) {
					level = _level;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
		return level > 1;
	}
	
	private boolean loadKey() {
		File key = new File(saveKey);
		if (!key.exists()) {
			return false;
		}
		try (Scanner keyread = new Scanner(key)) {
			while (keyread.hasNextLine()) {
				int lvkey = keyread.nextInt();
				if (lvkey == level) {
					loadLevel();
				}
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
	}
	
	void run() {
		try (Scanner input = new Scanner(System.in);) {
			System.out.println("Welcome to Number Guesser 4.0!");
			System.out.println("I'll ask you to guess a number between a range, and you'll have " + maxStrikes
					+ " attempts to guess.");
			if (loadKey()) {
				System.out.println("Successfully loaded level " + level + " let's continue then");
			}
			number = getNumber(level);
			isRunning = true;
			while (input.hasNext()) {
				String message = input.nextLine();
				processCommands(message);
				if (!isRunning) {
					break;
				}
				int guess = getGuess(message);
				processGuess(guess);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		NumberGuesserHW guesser = new NumberGuesserHW();
		guesser.run();
	}
}
