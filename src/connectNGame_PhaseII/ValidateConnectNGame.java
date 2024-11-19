/**
 *
 */
package connectNGame_PhaseII;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <p>Course: 420-G20 Programming II</p>
 * <p>Title: ValidateConnectNGame</p>
 * <p>This class is used to validate anything relating to the Connect N Game</p>
 * @author Adrian Charron
 */
public class ValidateConnectNGame {

    /**
     * Validates all the save game data
     * @return 0 if all is valid,
     * <br> -1 if file is missing,
     * <br>-2 if the file is empty,
     * <br>-3 for 1st line error,
     * <br>-4 for 2nd line error,
     * <br>-5 for 3rd line error,
     * <br>-6 for 4th line error,
     * <br>-7 for 5th line error,
     * <br>-8 for 6th line error,
     * <br>-9 for game board error,
     * <br>-10 for a game already over.
     */
    public static int validateSaveGame() {
        int rows, columns, connect, intTest;
        String stringTest;
        char charTest;
        Scanner reader;
        // test if the file is missing
        try {
            reader = new Scanner(ConnectNGame.SAVED_GAME);
        } // try to find the file
        catch (FileNotFoundException e) {
            return -1;
        } // catch if file is missing

        // test if the file is empty
        if (!reader.hasNext()) {
            reader.close();
            return -2;
        } // if reader has next

        // test if 1st line is missing or not a number, or is invalid
        try {
            rows = Integer.parseInt(reader.nextLine());
        } // try to read and convert 1st line
        catch (NumberFormatException | NoSuchElementException e) {
            reader.close();
            return -3;
        } // if cannot convert to int
        // catch if 1st line is missing
        if (validateNumRowsCols(rows)) {
            reader.close();
            return -3;
        } // if number of rows is valid
        // test if 2nd line is missing or not a number, or is invalid
        try {
            columns = Integer.parseInt(reader.nextLine());
        } // try to read and convert 2nd line
        catch (NumberFormatException | NoSuchElementException e) {
            reader.close();
            return -4;
        } // if cannot convert to int
        // catch missing 2nd line
        if (validateNumRowsCols(columns)) {
            reader.close();
            return -4;
        } // if number of columns is valid
        // test if 3rd line is missing or not a number, or is invalid
        try {
            connect = Integer.parseInt(reader.nextLine());
        } // try to read and convert 3rd line
        catch (NumberFormatException | NoSuchElementException e) {
            reader.close();
            return -5;
        } // if cannot convert to int
        // catch missing 3rd line
        if (validateConnectNumber(connect, rows, columns)) {
            reader.close();
            return -5;
        } // if connect number is valid

        // test if 4th line is missing
        try {
            stringTest = reader.nextLine();
        } // try to read 4th line
        catch (NoSuchElementException e) {
            reader.close();
            return -6;
        } // catch missing 4th line
        if (checkName(stringTest)) {
            reader.close();
            return -6;
        } // if 4th line is empty
        // test if 5th line is missing
        try {
            stringTest = reader.nextLine();
        } // try to read 5th line
        catch (NoSuchElementException e) {
            reader.close();
            return -7;
        } // catch missing 5th line
        if (checkName(stringTest)) {
            reader.close();
            return -7;
        } // if 5th line is empty

        // test if 6th line is missing, not a number, or invalid
        try {
            intTest = Integer.parseInt(reader.nextLine());
            switch (intTest) {
                case 1, 2:
                    break;
                default:
                    reader.close();
                    return -8;
            } // switch (intTest)
        } // try to read and convert 6th line
        catch (NoSuchElementException | NumberFormatException e) {
            reader.close();
            return -8;
        } // if missing 6th line
        // if cannot convert to int
        char[][] board = new char[rows][columns];
        for (int ro = 0; ro < rows; ro++) { // for every row
            try { // try to read a row
                StringTokenizer boardRow = new StringTokenizer(reader.nextLine(), "~");
                if (boardRow.countTokens() != columns) // if a row does not have enough elements
                    return -9;
                for (int cols = 0; cols < columns; cols++) { // for every element of a row
                    charTest = boardRow.nextToken().charAt(0);
                    if (charTest != ConnectNGame.PLAYER_CHARACTERS[0]
                            && charTest != ConnectNGame.PLAYER_CHARACTERS[1]
                            && charTest != ConnectNGame.PLAYER_CHARACTERS[2]) // if element is an invalid character
                        return -9;
                    board[ro][cols] = charTest;
                } // for every element of a row
            } // try to read a row
            catch (NoSuchElementException e) {
                reader.close();
                return -9;
            } // catch missing rows
        } // for every row
        WinChecker win = new WinChecker(); // create a WinChecker object
        for (int ro = 0; ro < board.length; ro++) // for every row
            for (int col = 0; col < board[ro].length; col++) { // for every column
                int[] move = {ro, col}; // the current element
                if (win.checkForWin(board, move, connect) != 0) { // if the game is already ended for the current element
                    reader.close();
                    return -10;
                } // end if
            } // end 2 for loops
        reader.close();
        return 0;
    } // validateSaveGame()

    /**
     * Check if the name length > 0
     * @param name the name to be validated
     * @return true for valid,<br>false for invalid.
     */
    public static boolean checkName(String name) {
        return name.isEmpty();
    } // checkName(String)

    /**
     * Validates the connect number
     * @param num number to be validated
     * @param row the number of rows
     * @param col the number of columns
     * @return true for valid number,<br>false for invalid number.
     */
    public static boolean validateConnectNumber(int num, int row, int col) {
        boolean valid = num < 9 && num > 2 && (num <= row || num <= col);
        // if num is within range of max AND min AND within range of row OR column
        return !valid;
    } // validateConnectNumber(int, int, int)

    /**
     * Validates the number of rows or columns
     * @param num the number to be validated
     * @return true for valid,<br>false for invalid.
     */
    public static boolean validateNumRowsCols(int num) {
        boolean valid = num < 13 && num > 3;
        // if num is within range of max and min
        return !valid;
    } // validateNumRowsCols(int)

    /**
     * Validates the token dropped
     * @param col the column of the token dropped
     * @param gameBoard the current state of the game board
     * @return 0 for valid column,<br>-1 for invalid column,<br>-2 for full column.
     */
    public static int validateTokenDropped(int col, char[][] gameBoard) {
        if (col < 0 || col >= gameBoard[0].length) // if the column doesn't exist on the board
            return -1;
        else if (gameBoard[0][col] != ConnectNGame.PLAYER_CHARACTERS[2]) // if the top row isn't empty
            return -2;
        return 0;
    } // validateTokenDropped(int[], char[][])

} // ValidateConnectNGame class
