/**
 * 
 */
package connectNGame_PhaseII;

import java.io.IOException;
import java.util.Scanner;

/**
 * <p>Course: 420-G20 Programming II</p>
 * <p>Title: ConnectNInterface</p>
 * <p>The command line version of Connect N Game</p>
 * @author Adrian Charron
 */
public class ConnectNInterface {

	private static ConnectNGame game;
	private static final Scanner KEYBOARD = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 					1					2					3					4					5					6					7					8
		// 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567
		// 								Enter 'H' any time to display instructions for the game
		//   For a new game, enter 'N'			 To load a game, enter 'L'				To quit, enter 'Q'
		// 															Welcome to Connect N Game!

		// Title display:
		System.out.printf("%55s\n", "Welcome to Connect N Game!");
		System.out.printf("%70s\n",
				"Enter 'H' any time to display instructions for the game");
		for (int i = 0; i < 87; i++)
			System.out.print("-");
		// Playing the game:
		char exit = 'Y';
		while (exit == 'Y') {
			// Display opening options:
			displayOptions();
			playTheGame();
			declareWinner();
			System.out
					.print("Would you like to play again? ('Y' for yes, 'Q' to quit): ");
			exit = KEYBOARD.next().toUpperCase().charAt(0);
			if (!(exit == 'Y' || exit == 'Q'))
				exit = 'Y';
		}
		System.out.println("Thank you for playing Connect N! Have a nice day!");
		KEYBOARD.close();
		// The end of the game:
	} // main(String[])

	/**
	 * The game-play flow of ConnectNGame
	 */
	private static void playTheGame() {
		String input; // the user's input
		char action; // the action taken by the user
		int column; // column of the token dropped
		do {
			column = -1; // an illegal column
			action = 'x'; // an illegal action
			input = ""; // reset the input
			while (column == -1) { // while column is at -1
				System.out.println("\nCurrent Game Board:");
				displayGameBoard(); // display the game board
				displayWhosTurn(); // display who's turn it is
				System.out.print("Enter a column number (1 to " + game.getColumns()
						+ "),\n'U' to undo,\n'S' to save,\nor 'Q' to quit: "); // display the options of the game
				input = KEYBOARD.next(); // read the input from the user
				try { // try to convert to an integer
					column = Integer.parseInt(input); // convert input to integer
				}
				catch (NumberFormatException e) { // catch a conversion error
					action = input.toUpperCase().charAt(0); // convert input to char
					if (action == 'U' || action == 'S' || action == 'Q'
							|| action == 'H') { // if char matches an action
						column = 99; // set column to an exit value
					}
					else {
						System.err.println("Unknown action taken."); // unknown action taken
					}
				} // end of exception
				if (ValidateConnectNGame.validateTokenDropped(column - 1,
						game.getGameBoard()) != 0
						&& !(action == 'U' || action == 'S' || action == 'Q'
								|| action == 'H')) { // if the token dropped is illegal or the action doesn't match
					System.err.println("Invalid column number.");
					column = -1; // reset column to -1
				}
			} // end of while loop
			if (column != 99) // if column is set to 99 it means an action was taken
				takeTurn(column - 1);
			else { // if column isn't set to 99, the column number is legal
				takeAction(action);
			}
		} while (game.checkForWin() == 0); // condition to exit do/while loop is a win
	} // playTheGame()

	/**
	 * Declares the winner of the game, or if there is a tie.
	 */
	private static void declareWinner() {
		String winner; // winner to be declared
		if (game.getPlayer1Turn()) { // if current player is 1, then last player was the winner 
			winner = game.getPlayer2();
		}
		else { // else if current player is 2, then last player was winner
			winner = game.getPlayer1();
		}
		System.out.println(
				"-------------------------------\nThe game has ended!!\n-------------------------------");
		displayGameBoard(); // display the final state of the game
		int winCondition = game.checkForWin(); // what is the win condition
		switch (winCondition) {
		case 1: // for a 'row' win
			System.out.println("The winner is \"" + winner + "\" with a row!");
			break;
		case 2: // for a 'column' win
			System.out.println("The winner is \"" + winner + "\" with a column!");
			break;
		case 3: // an upper-left win
			System.out.println(
					"The winner is \"" + winner + "\" with an upper-left diagonal!");
			break;
		case 4: // an upper-right win
			System.out.println(
					"The winner is \"" + winner + "\" with an upper-right diagonal!");
			break;
		case 5: // a tie
			System.out.println("The game is a tie!");
			break;
		default: // if something else somehow gets returned
			System.err.println(
					"An unknown error occured! Please contact customer support!!");
			KEYBOARD.close();
			System.exit(-3);
		} // end of switch code block
	} // declareWinner()

	/**
	 * Selects the method for the inputed character
	 * @param action U,S,Q to undo, save, and quit respectively
	 */
	private static void takeAction(char action) {
		switch (action) { // switch for the char
		case 'U': // if undo was selected
			undoLastTurn();
			break;
		case 'S': // if save was selected
			saveGame();
			break;
		case 'Q': // if quit was selected
			quitGame();
			break;
		case 'H': // if help was selected
			displayHelpDialogue();
			break;
		default: // if an unknown is passed into the method
			System.err.println(
					"\nAn unknown error occured! Please contact customer support!!");
			KEYBOARD.close();
			System.exit(-1);
		} // end of switch
	} // takeAction(char)

	/**
	 * Quits the game
	 */
	private static void quitGame() {
		System.out.println("\nThank you for playing! Have a nice day!");
		KEYBOARD.close();
		System.exit(0);
	} // quitGame()

	/**
	 * Undoes the last move made
	 */
	private static void undoLastTurn() {
		if (game.undoTurn()) // if turn was undone successfully
			System.out.println("\nThe last turn was undone.");
		else // if it wasn't
			System.err.println("\nCannot undo a turn at this point.");
	} // undoLastTurn

	/**
	 * Displays the options available upon booting up the game
	 */
	private static void displayOptions() {
		System.out.printf("\n%27s%32s%26s\n", "For a new game, enter 'N'",
				"To load a game, enter 'L'", "To quit, enter 'Q'"); // display the options
		for (int i = 0; i < 87; i++)
			System.out.print("-");
		System.out.println();
		char action = KEYBOARD.next().toUpperCase().charAt(0); // the action inputed by the user
		while (!(action == 'N' || action == 'L' || action == 'Q'
				|| action == 'H')) { // if the char doesn't match
			System.out.print("Please enter 'N', 'L', or 'Q'");
			action = KEYBOARD.next().toUpperCase().charAt(0);
		} // end of while
		switch (action) { // switch for the action taken
		case 'N': // create a new game
			createNewGame();
			break;
		case 'L': // load a past game from save
			loadGame();
			break;
		case 'Q': // quit the game
			quitGame();
			break;
		case 'H': // help message
			displayHelpDialogue();
			displayOptions();
			break;
		default: // if the char doesn't match
			System.err.println(
					"An unknown error occured! Please contact customer support!!");
			KEYBOARD.close();
			System.exit(-2);
		} // end of switch
	} // displayOptions()

	/**
	 * Displays the help message available to all players at any time.
	 */
	private static void displayHelpDialogue() {
		for (int i = 0; i < 122; i++) {
			System.out.print("-");
		}
		System.out.println();
		System.out.println("Welcome to Connect N!\n"
				+ "The goal of the game is to connect enough tokens in any line. That means a row, column or diagonal.\n"
				// 					1					2					3					4					5					6					7					8					9					10				11				12
				// 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123
				+ "1. When you create a new game, you will be able to enter the number of rows, columns, and the number of tokens to connect.\n"
				+ "\t-Then you will be asked to enter your player names.\n"
				+ "2. Once you are in a game, you will be able to save the current state of the game, drop a token down a column, or quit.\n"
				+ "\t-After the first turn, you will also be able to undo the last turn, but only the last turn.\n"
				+ "3. If you load a game, the information of the game will be displayed on top of the board.\n"
				+ "\t-It is worth noting that you will not be able to undo after loading.\n\n"
				+ "!!At any point, you can quit the game, or display this help message again.!!\n\n"
				+ "Please enjoy your time playing Connect N!!!");
		for (int i = 0; i < 122; i++) {
			System.out.print("-");
		}
		System.out.println();
	} // displayHelpDialogue()

	/**
	 * Creates a new game
	 */
	private static void createNewGame() {
		String tempVal;

		// Setting rows
		int rows = 0;
		System.out.print("How many rows do you want on the board (4 to 12): ");
		tempVal = KEYBOARD.next();
		try {
			rows = Integer.parseInt(tempVal);
		} // try to parseInt
		catch (NumberFormatException e) {
			System.err.println("Input was not a number.");
			while (rows == 0) {
				System.out
						.print("Please enter a number of rows (4 to 12), or 'Q' to exit: ");
				tempVal = KEYBOARD.next();
				try {
					rows = Integer.parseInt(tempVal);
				} // try to parseInt
				catch (NumberFormatException w) {
					if (tempVal.equalsIgnoreCase("q"))
						takeAction('Q');
				} // do nothing
			} // while rows == 0 and tempVal != 'Q'
		} // catch conversion error
		while (!ValidateConnectNGame.validateNumRowsCols(rows)) {
			System.err.println("Invalid number of rows");
			System.out
					.print("Please enter a number of rows (4 to 12), or 'Q' to exit: ");
			tempVal = KEYBOARD.next();
			try {
				rows = Integer.parseInt(tempVal);
			} // try to parseInt
			catch (NumberFormatException w) {
				if (tempVal.equalsIgnoreCase("q"))
					takeAction('Q');
			} // do nothing
		} // while rows == 0 and tempVal != 'Q'

		// Setting columns
		int columns = 0;
		System.out.print("How many columns do you want on the board (4 to 12): ");
		tempVal = KEYBOARD.next();
		try {
			columns = Integer.parseInt(tempVal);
		} // try to parseInt
		catch (NumberFormatException e) {
			System.err.println("Input was not a number.");
			while (columns == 0) {
				System.out.print(
						"Please enter a number of columns (4 to 12), or 'Q' to exit: ");
				tempVal = KEYBOARD.next();
				try {
					columns = Integer.parseInt(tempVal);
				} // try to parseInt
				catch (NumberFormatException w) {
					if (tempVal.equalsIgnoreCase("q"))
						takeAction('Q');
				} // do nothing
			} // while columns == 0 and tempVal != 'Q'
		} // catch conversion error
		while (!ValidateConnectNGame.validateNumRowsCols(columns)) {
			System.err.println("Invalid number of columns");
			System.out.print(
					"Please enter a number of columns (4 to 12), or 'Q' to exit: ");
			tempVal = KEYBOARD.next();
			
			try {
				columns = Integer.parseInt(tempVal);
			} // try to parseInt
			catch (NumberFormatException w) {
				if (tempVal.equalsIgnoreCase("q"))
					takeAction('Q');
			} // do nothing
		} // while columns == 0 and tempVal != 'Q'

		// Setting connect number
		int numConnect = 0;
		System.out.print("How many tokens to connect to win (3 to 8): ");
		tempVal = KEYBOARD.next();
		try {
			numConnect = Integer.parseInt(tempVal);
		} // try to parseInt
		catch (NumberFormatException e) {
			System.err.println("Input was not a number.");
			while (numConnect == 0) {
				System.out.print(
						"Please enter a number of tokens to connect (3 to 8), or 'Q' to exit: ");
				tempVal = KEYBOARD.next();
				try {
					numConnect = Integer.parseInt(tempVal);
				} // try to parseInt
				catch (NumberFormatException w) {
					if (tempVal.equalsIgnoreCase("q"))
						takeAction('Q');
				} // do nothing
			} // while numConnect == 0 and tempVal != 'Q'
		} // catch conversion error
		while (!ValidateConnectNGame.validateConnectNumber(numConnect, rows,
				columns)) {
			System.err.println("Invalid number of tokens");
			System.out.print(
					"Please enter a number of tokens to connect (3 to 8), or 'Q' to exit: ");
			tempVal = KEYBOARD.next();
			try {
				numConnect = Integer.parseInt(tempVal);
			} // try to parseInt
			catch (NumberFormatException w) {
				if (tempVal.equalsIgnoreCase("q"))
					takeAction('Q');
			} // do nothing
		} // while numConnect == 0 and tempVal != 'Q'
		KEYBOARD.nextLine();
		// Setting player1 name:
		String player1;
		System.out.print("What is player 1's name: ");
		player1 = KEYBOARD.nextLine();
				while(!ValidateConnectNGame.checkName(player1)) {
					System.err.println("Player 1's name is empty.");
					System.out.print("Please enter player 1's name: ");
					player1 = KEYBOARD.nextLine();
				}

		// Setting player2 name:
		String player2;
		System.out.print("What is player 2's name: ");
		player2 = KEYBOARD.nextLine();
				while(!ValidateConnectNGame.checkName(player2)) {
					System.err.println("Player 2's name is empty.");
					System.out.print("Please enter player 2's name: ");
					player2 = KEYBOARD.nextLine();
				}

		// Creating the game: 
		game = new ConnectNGame(rows, columns, numConnect, player1, player2);
		System.out.println(
				"-------------------------------\nGame has been successfully created\n-------------------------------");
	} // createNewGame()

	/**
	 * Displays the game board and the current player's turn
	 */
	private static void displayGameBoard() {
		for (int i = 0; i < game.getGameBoard().length; i++) {
			for (int j = 0; j < game.getGameBoard()[i].length; j++) {
				System.out.print(String.valueOf(game.getGameBoard()[i][j]) + " ");
			} // for columns
			System.out.println();
		} // for rows
	} // displayGameBoard()

	/**
	 * Displays who's turn it is
	 */
	private static void displayWhosTurn() {
		if (game.getPlayer1Turn()) { // if player 1's turn
			System.out.println(game.getPlayer1() + "'s turn!");
		} // if player 2's turn
		else
			System.out.println(game.getPlayer2() + "'s turn!");
	} // displayWhosTurn()

	/**
	 * Loads the game saved in currentGame.txt
	 */
	private static void loadGame() {
		game = new ConnectNGame();
		int state = 0;
		try {
			state = game.loadGameBoard();
		}
		catch (IOException e) {
			System.err.println(
					"An unexpected error occured\nSaved game cannot be reached.");
			displayOptions();
		}
		switch (state) {
		case 0:
			System.out.println(
					"-------------------------------\nGame successfully loaded\n-------------------------------");
			System.out
					.println("\nNumber of tokens to connect is: " + game.getNumConnect());
			System.out.println("Player 1 is " + game.getPlayer1() + "\nPlayer 2 is "
					+ game.getPlayer2());
			return;
		case -1: // file missing
			System.err.println(
					"The save file does not exist.\nPlease choose a new option.");
			break;
		case -2: // save file empty
			System.err
					.println("Saved Game file is empty.\nPlease choose a new option.");
			break;
		case -3: // 1st line error
			System.err
					.println("Saved Game file corrupted.\nPlease choose a new option.");
			break;
		case -4: // 2nd line error
			System.err
					.println("Saved Game file corrupted.\nPlease choose a new option.");
			break;
		case -5: // 3rd line error
			System.err
					.println("Saved Game file corrupted.\nPlease choose a new option.");
			break;
		case -6: // 4th line error
			System.err
					.println("Saved Game file corrupted.\nPlease choose a new option.");
			break;
		case -7: // 5th line error
			System.err
					.println("Saved Game file corrupted.\nPlease choose a new option.");
			break;
		case -8: // 6th line error
			System.err
					.println("Saved Game file corrupted.\nPlease choose a new option.");
			break;
		case -9: // game board data error
			System.err
					.println("Saved Game file corrupted.\nPlease choose a new option.");
			break;
		case -10: // if the game has already ended
			System.err.println("The game has already ended");
			break;
		default: // unknown value
			System.err.println(
					"An unexpected error occured.\nCannot load game from save file.");
		} // end switch
		// display options again to create new game or exit.
		displayOptions();
	} // loadGame()

	/**
	 * Saves the current game to currentGame.txt
	 */
	private static void saveGame() {
		try {
			game.saveGame();
			System.out.println(
					"-------------------------------\nThe game saved successfully!\n-------------------------------");
		}
		catch (IOException e) {
			System.err.println("The game failed to save:\n" + e.getMessage());
		}
	} // saveGame()

	/**
	 * Takes a turn in the specified column's INDEX
	 * @param column the index of the column to drop a token
	 */
	private static void takeTurn(int column) {
		game.takeTurn(column);
	}

} // ConnectNInterface class
