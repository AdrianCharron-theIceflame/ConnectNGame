/**
 * 
 */
package connectNGame_PhaseII;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <p>Course: 420-G20 Programming II</p>
 * <p>Title: ConnectNGame</p>
 * <p>This is the class containing the logic for the Connect N Game</p>
 * @author Adrian Charron
 */
public class ConnectNGame {

	private int rows;
	private int columns;
	private int numConnect;
	private String player1;
	private String player2;
	private char[][] gameBoard;

	/**
	 * lastMove[0] = row<br>
	 * lastMove[1] = column
	 */
	private int[] lastMove = new int[2];
	private boolean player1Turn;
	private WinChecker win;
	private boolean canUndo;
	public static final File SAVED_GAME = new File("currentGame.txt");
	public static final int MAXIMUM_ROWS_COLS = 12;
	public static final int MAXIMUM_CONNECT = 8;
	public static final char[] PLAYER_CHARACTERS = { 'Y', 'R', 'E' };

	/**
	 * ConnectNGame with default parameters
	 */
	public ConnectNGame() {
		rows = 4;
		columns = 4;
		numConnect = 3;
		player1 = "Player 1";
		player2 = "Player 2";
		createGameBoard();
		player1Turn = true;
		win = new WinChecker();
		canUndo = false;
	} // ConnectNGame()

	/**
	 * ConnectNGame with parameters to create a new game
	 * @param ro the number of rows for the new game
	 * @param col the number of columns for the new game
	 * @param con the number of tokens needed to connect and win
	 * @param name1 the name of player 1
	 * @param name2 the name of player 2
	 */
	public ConnectNGame(int ro, int col, int con, String name1, String name2) {
		rows = ro;
		columns = col;
		numConnect = con;
		player1 = name1;
		player2 = name2;
		createGameBoard();
		player1Turn = true;
		win = new WinChecker();
		canUndo = false;
	} // ConnectNGame(int, int, int, String, String)

	/**
	 * ConnectNGame with parameters for loading a game from the saved file
	 * @param ro the number of rows for the new game
	 * @param col the number of columns for the new game
	 * @param con the number of tokens needed to connect and win
	 * @param name1 the name of player 1
	 * @param name2 the name of player 2
	 * @param who the current player's turn (true for player 1, false for player 2)
	 * @param board the current board state
	 */
	public ConnectNGame(int ro, int col, int con, String name1, String name2,
			boolean who, char[][] board) {
		rows = ro;
		columns = col;
		numConnect = con;
		player1 = name1;
		player2 = name2;
		gameBoard = board;
		player1Turn = who;
		win = new WinChecker();
		canUndo = false;
	} // ConnectNGame(int, int, int, String, String, boolean, char[][])

	/**
	 * ConnectNGame with parameters to set all instance variables
	 * @param ro the number of rows for the new game
	 * @param col the number of columns for the new game
	 * @param con the number of tokens needed to connect and win
	 * @param name1 the name of player 1
	 * @param name2 the name of player 2
	 * @param who the current player's turn (true for player 1, false for player 2)
	 * @param board the current board state
	 * @param move the last move to have been made
	 * @param wi the WinChecker object
	 */
	public ConnectNGame(int ro, int col, int con, String name1, String name2,
			boolean who, char[][] board, int[] move, WinChecker wi, boolean undo) {
		rows = ro;
		columns = col;
		numConnect = con;
		player1 = name1;
		player2 = name2;
		gameBoard = board;
		player1Turn = who;
		win = wi;
		canUndo = undo;
	} // ConnectNGame(int, int, int, String, String, boolean, char[][], int[], WinChecker, boolean)

	/**
	 * Sets the number of rows
	 * @param ro the number of rows
	 */
	public void setRows(int ro) {
		rows = ro;
	} // setRows(int)

	/**
	 * Gets the number of rows
	 * @return rows the number of rows
	 */
	public int getRows() {
		return rows;
	} // getRows()

	/**
	 * Sets the number of columns
	 * @param cols the number of columns
	 */
	public void setColumns(int cols) {
		columns = cols;
	} // setColumns(int)

	/**
	 * Gets the number of columns
	 * @return columns the number of columns
	 */
	public int getColumns() {
		return columns;
	} // getColumns()

	/**
	 * Sets the number of tokens to be connected to win the game
	 * @param con the number of tokens to connect to win
	 */
	public void setNumConnect(int con) {
		numConnect = con;
	} // setNumConnect(int)

	/**
	 * Gets the number of tokens to be connected to win the game
	 * @return numConnect the number of tokens to connect to win
	 */
	public int getNumConnect() {
		return numConnect;
	} // getNumConnect()

	/**
	 * Sets the name of player 1
	 * @param name the name to give to player 1
	 */
	public void setPlayer1(String name) {
		player1 = name;
	} // setPlayer1(String)

	/**
	 * Gets player 1's name
	 * @return player1 the name of player 1
	 */
	public String getPlayer1() {
		return player1;
	} // getPlayer1()

	/**
	 * Sets the name of player 2
	 * @param name the name to give to player 2
	 */
	public void setPlayer2(String name) {
		player2 = name;
	} // setPlayer2(String)

	/**
	 * Gets player 2's name
	 * @return player2 the name of player 2
	 */
	public String getPlayer2() {
		return player2;
	} // getPlayer2()

	/**
	 * Sets the game board of the game
	 * @param board the 2D character array making up the game board
	 */
	public void setGameBoard(char[][] board) {
		gameBoard = board;
	} // setGameBoard(char[][])

	/**
	 * Gets the game board of ConnectNGame
	 * @return gameBoard the 2D char array making up the game board
	 */
	public char[][] getGameBoard() {
		return gameBoard;
	} // getGameBoard()

	/**
	 * Sets the last move taken by a player<br>
	 * @param col the column coordinate of the move played
	 * @param ro the row coordinate of the move played
	 */
	public void setLastMove(int col, int ro) {
		lastMove = new int[2];
		lastMove[1] = col;
		lastMove[0] = ro;
	} // setLastMove(int, int)

	/**
	 * Gets the last move made by a player
	 * @return lastMove the int array [row, column]
	 */
	public int[] getLastMove() {
		return lastMove;
	} // getLastMove()

	/**
	 * Sets if it is player 1's turn
	 * @param yes if it is player 1's turn
	 */
	public void setPlayer1Turn(boolean yes) {
		player1Turn = yes;
	} // setPlayer1Turn(boolean)

	/**
	 * Gets if it's player 1's turn
	 * @return true if it's player 1's turn
	 */
	public boolean getPlayer1Turn() {
		return player1Turn;
	} // getPlayer1Turn()

	/**
	 * Sets the WinChecker object
	 * @param wi the WinChecker object
	 */
	public void setWin(WinChecker wi) {
		win = wi;
	} // setWin(WinChecker)

	/**
	 * Gets the WinChecker object
	 * @return win WinChecker object
	 */
	public WinChecker getWin() {
		return win;
	} // getWin()

	/**
	 * Sets if the player can undo an move
	 * @param undo if players can undo a move
	 */
	public void setUndo(boolean undo) {
		canUndo = undo;
	} // setUndo(boolean)

	/**
	 * Gets if the player can undo a move
	 * @return
	 */
	public boolean getUndo() {
		return canUndo;
	} // getUndo()

	/**
	 * Fills all the characters in the gameBoard
	 */
	private void createGameBoard() {
		canUndo = false;
		gameBoard = new char[rows][columns];
		for (int ro = 0; ro < gameBoard.length; ro++) {
			for (int col = 0; col < gameBoard[ro].length; col++) {
				gameBoard[ro][col] = 'E';
			} // for every element
		} // for every row
	} // createGameBoard()

	/**
	 * Loads a game from the save-file currentGame.txt
	 * @return 0 if it loads successfully,
	 * <br>-1 if the file is missing,
	 * <br>-2 if the file is empty,
	 * <br>-3 for 1st line error,
	 * <br>-4 for 2nd line error,
	 * <br>-5 for 3rd line error,
	 * <br>-6 for 4th line error,
	 * <br>-7 for 5th line error,
	 * <br>-8 for 6th line error,
	 * <br>-9 for game board error,
	 * <br>-10 for a game already over.
	 * @throws IOException the exception that might occur while reading a file
	 */
	public int loadGameBoard() throws IOException {
		// Validate the saved game
		int state = ValidateConnectNGame.validateSaveGame();

		// If fail, return state of fail
		if (state != 0)
			return state;

		// Create Scanner (Scanner should already be validated, but just in case, throw exception to interface
		Scanner reader = new Scanner(SAVED_GAME);

		// Read in the values
		rows = Integer.parseInt(reader.nextLine());
		columns = Integer.parseInt(reader.nextLine());
		numConnect = Integer.parseInt(reader.nextLine());
		player1 = reader.nextLine();
		player2 = reader.nextLine();
		int isPlayer1 = Integer.parseInt(reader.nextLine());

		// default is already set inside the validator, no use making another default for something that can't happen.
		switch (isPlayer1) {
		case 1:
			player1Turn = true;
			break;
		case 2:
			player1Turn = false;
			break;
		} // Switch 1 or 2 for player 1 or player 2

		// Create a game board with this information
		createGameBoard();

		// fill the game board with the saved game information
		for (int ro = 0; ro < rows; ro++) { // for every row of the game
			StringTokenizer boardRow = new StringTokenizer(reader.nextLine(), "~"); // read a new line of the file into Tokenizer
			for (int cols = 0; cols < columns; cols++) { // for every column of the game
				gameBoard[ro][cols] = boardRow.nextToken().charAt(0); // set the char to the one from the save file
			} // for columns
		} // for rows
		reader.close();
		return state;
	} // loadGameBoard()

	/**
	 * Saves the current state of the game
	 * @throws IOException the exception that might occur during writing to a file
	 */
	public void saveGame() throws IOException {
		FileWriter gameSave = new FileWriter(SAVED_GAME);
		gameSave.write(String.valueOf(rows) + "\n" + String.valueOf(columns) + "\n"
				+ String.valueOf(numConnect) + "\n" + player1 + "\n" + player2 + "\n");
		if (player1Turn) // if player1's turn
			gameSave.append("1");
		else // if player2's turn
			gameSave.append("2");
		for (int ro = 0; ro < gameBoard.length; ro++) {
			gameSave.append("\n");
			for (int col = 0; col < gameBoard[ro].length; col++) {
				if (col == gameBoard[ro].length - 1)
					gameSave.append(String.valueOf(gameBoard[ro][col]));
				else
					gameSave.append(String.valueOf(gameBoard[ro][col]) + "~");
			} // for every column
		} // for every row
		gameSave.close();
	} // saveGame()

	/**
	 * Drops a token down the specified column's index
	 * @param col the index of the column to drop a token
	 * @return 0 for valid,<br>-1 for invalid,<br>-2 for full column
	 */
	public void takeTurn(int col) {
		for (int ro = gameBoard.length - 1; ro >= 0; ro--) { // for every row
			if (gameBoard[ro][col] == PLAYER_CHARACTERS[2]) { // if empty
				lastMove[0] = ro;
				lastMove[1] = col;
				if (player1Turn) { // if player 1
					gameBoard[ro][col] = PLAYER_CHARACTERS[0];
					player1Turn = !player1Turn;
					canUndo = true;
					return;
				} // if player 1
				else { // if player 2
					gameBoard[ro][col] = PLAYER_CHARACTERS[1];
					player1Turn = !player1Turn;
					canUndo = true;
					return;
				} // if player 2
			} // if empty
		} // for every rows
		return;
	} // takeTurn(int)

	/**
	 * Checks for a win in the game board using the last move
	 * @return 0 for no wins,<br>1 for a row win,<br>2 for a column win,<br>3 for a upper-left diagonal win,<br>4 for a upper-right diagonal win,<br>5 for a tie.
	 */
	public int checkForWin() {
		return win.checkForWin(gameBoard, lastMove, numConnect);
	} // checkForWin()

	/**
	 * Undoes the last turn stored in memory, return turn to last player
	 * @return true for turn successfully undone,<br>false for unable to undo turn.
	 */
	public boolean undoTurn() {
		if (canUndo) {
			gameBoard[lastMove[0]][lastMove[1]] = PLAYER_CHARACTERS[2];
			player1Turn = !player1Turn;
			canUndo = false;
			return true;
		} // if player can undo
		return false;
	} // undoTurn()

} // ConnectNGame class