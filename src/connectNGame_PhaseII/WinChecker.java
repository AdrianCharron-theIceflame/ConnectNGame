/**
 * 
 */
package connectNGame_PhaseII;

/**
 * <p>Course: 420-G20 Programming II</p>
 * <p>Title: WinChecker</p>
 * <p>This class is used to check for a win within the Connect N Game</p>
 * @author Adrian Charron
 */
public class WinChecker {

	private char[][] board;
	private int[] move;
	private int numConnect;

    /**
	 * Checks for a win with the last move dropped
	 * @param gameBoard the current state of the game board
	 * @param lastMove the last move to be played
	 * @param num the number of tokens to be connected to win
	 * @return 0 for no win,<br>1 for row win,<br>2 for column win,<br>3 for upper left win,<br>4 for upper right win,<br>5 for a tie.
	 */
	public int checkForWin(char[][] gameBoard, int[] lastMove, int num) {
		board = gameBoard;
		move = lastMove;
		numConnect = num;
		if (board[move[0]][move[1]] == ConnectNGame.PLAYER_CHARACTERS[2]) {
			return 0;
		}
        if (checkRowWin()) {
            return 1;
		}
		if (checkColumnWin()) {
            return 2;
		}
		if (checkUpperLeftDiagonal()) {
            return 3;
		}
		if (checkUpperRightDiagonal()) {
            return 4;
		}
		if (checkTie()) {
            return 5;
		}
		return 0;
	} // checkForWins(char[][], int[], int)

	/**
	 * Checks for a tie on the board
	 * @return true for a tie, false for not.
	 */
	private boolean checkTie() {
		int fullBoard, turnCounter;
		turnCounter = 0; // how many elements are filled
		fullBoard = board.length * board[0].length; // a full board is rows * columns
		for (int ro = 0; ro < board.length && turnCounter < fullBoard; ro++)
			for (int col = 0; col < board[ro].length && turnCounter < fullBoard
					&& board[ro][col] != ConnectNGame.PLAYER_CHARACTERS[2]; col++)
				/* Conditions of for loop:
				 * ro is within bounds
				 * col is within bounds,
				 * elements filled < full board
				 * current element isn't empty
				 */
				turnCounter++;
		return turnCounter == fullBoard;
	} // checkTie()

	/**
	 * Checks for upper-right diagonal win
	 * @return true for a win,<br>false for none.
	 */
	private boolean checkUpperRightDiagonal() {
		int tokenCount = 1;
		char token = board[move[0]][move[1]];
		int ro = move[0] + 1;
		int col = move[1] - 1;
		while (ro < board.length && col >= 0 && tokenCount < numConnect
				&& board[ro][col] == token) {
			/* While loop conditions:
			 * ro < board's length (increments after a loop)
			 * col >= 0 (decrements after a loop)
			 * tokenCount < numConnect (increments every match)
			 * current element == player's token
			 */
			tokenCount++;
			ro++;
			col--;
		} // while()
		ro = move[0] - 1;
		col = move[1] + 1;
		while (ro >= 0 && col < board.length && tokenCount < numConnect
				&& board[ro][col] == token) {
			/* While loop conditions
			 * ro >= 0(decrements after a loop)
			 * col < board's height (increments after a loop)
			 * tokenCount < numConnect (increments every match)
			 * current element == player's token
			 */
			tokenCount++;
			ro--;
			col++;
		} // while()
		return tokenCount == numConnect;
	} // checkUpperRightDiagonal()

	/**
	 * Checks for an upper-left diagonal win
	 * @return true for a win,<br>false for none.
	 */
	private boolean checkUpperLeftDiagonal() {
		int tokenCount = 1;
		char token = board[move[0]][move[1]];
		int ro = move[0] + 1;
		int col = move[1] + 1;
		while (ro < board.length && col < board[0].length && tokenCount < numConnect
				&& board[ro][col] == token) {
			/* While loop conditions:
			 * ro < board's length (increments after a loop)
			 * col < board's height (increments after a loop)
			 * tokenCount < numConnect (increments every match)
			 * current element == player's token
			 */
			tokenCount++;
			ro++;
			col++;
		} // while()
		ro = move[0] - 1;
		col = move[1] - 1;
		while (ro >= 0 && col >= 0 && tokenCount < numConnect
				&& board[ro][col] == token) {
			/* While loop conditions
			 * ro >= 0(decrements after a loop)
			 * col >= 0 (decrements after a loop)
			 * tokenCount < numConnect (increments every match)
			 * current element == player's token
			 */
			tokenCount++;
			ro--;
			col--;
		} // while()
		return tokenCount == numConnect;
	} // checkUpperLeftDiagonal()

	/**
	 * Checks for a column win
	 * @return true for a win,<br>false for none.
	 */
	private boolean checkColumnWin() {
		int tokenCount = 1;
		char token = board[move[0]][move[1]];
		for (int ro = move[0] + 1; ro < board.length && tokenCount < numConnect
				&& board[ro][move[1]] == token; ro++)
			/* For loop conditions:
			 * ro < board's length (increments every loop)
			 * tokenCount < numConnect (increments every match)
			 * current element == player's token
			 */
			tokenCount++;
		for (int ro = move[0] - 1; ro >= 0 && tokenCount < numConnect
				&& board[ro][move[1]] == token; ro--)
			/* For loop conditions:
			 * ro >= 0 (decrements every loop)
			 * tokenCount < numConnect (increments every match)
			 * current element == player's token
			 */
			tokenCount++;
		return tokenCount == numConnect;
	} // checkColumnWin()

	/**
	 * Checks for a row win
	 * @return true for a win,<br>false for none.
	 */
	private boolean checkRowWin() {
		int tokenCount = 1;
		char token = board[move[0]][move[1]];
		for (int col = move[1] + 1; col < board[move[0]].length
				&& tokenCount < numConnect && board[move[0]][col] == token; col++)
			/* For loop conditions:
			 * col < board's height (increments every loop)
			 * tokenCount < numConnect (increments every match)
			 * current element == player's token
			 */
			tokenCount++;
		for (int col = move[1] - 1; col >= 0 && tokenCount < numConnect
				&& board[move[0]][col] == token; col--)
			/* For loop conditions:
			 * col >= 0 (decrements every loop)
			 * tokenCount < numConnect (increments every match)
			 * current element == player's token
			 */
			tokenCount++;
		return tokenCount == numConnect;
	} // checkRowWin()

} // WinChecker class
