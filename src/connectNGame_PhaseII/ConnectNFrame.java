package connectNGame_PhaseII;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * <p>Course: 420-G20 Programming II</p>
 * <p>Title: ConnectNFrame</p>
 * <p>The JFrame version of the ConnectNGame</p>
 * @author Adrian Charron
 */
public class ConnectNFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	private JRadioButton[] columnChoices;
	private JMenuItem newGameMenuItem;
	private JMenu openMenu;
	private JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenu helpMenu;
	private JMenuItem rulesMenuItem;
	private JMenuItem aboutMenuItem;
	private JPanel pnlPlayers;
	private JLabel lblCurrentPlayer;
	private JLabel lblPlayer1;
	private JLabel lblPlayer1Name;
	private JLabel lblPlayerName;
	private JLabel lblPlayer2;
	private JLabel lblPlayer2Name;
	private JPanel pnlTitle;
	private JLabel lblTitle;
	private JButton btnDropToken;
	private JButton btnUndo;
	private JPanel pnlButtons;
	private JPanel pnlGameBoard;
	private JPanel pnlGameInfo;
	private JLabel lblRowInfo;
	private JLabel lblColumnsInfo;
	private JLabel lblNumColumns;
	private JLabel lblNumRows;
	private ConnectNGame game;
	private JLabel lblSpacer;
	private ButtonGroup columnButtonGroup;
	private JButton[][] gameBoard;
	private JLabel lblConnect;
	private JLabel lblNumConnect;
	private JLabel lblInputRows;
	private JLabel lblInputColumns;
	private JLabel lblInputNumConnect;
	private JTextField fldColumns;
	private JTextField fldRows;
	private JTextField fldNumConnect;
	private JLabel lblSpacer2;
	private JLabel lblSpacer3;
	private JLabel lblNewGame;
	private JLabel lblInputPlayer1;
	private JTextField fldPlayer1;
	private JLabel lblInputPlayer2;
	private JTextField fldPlayer2;
	private JButton btnCreateNewGame;
	//	private J

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectNFrame frame = new ConnectNFrame();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConnectNFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				displayGoodbyeMessage();
			}
		});
		setTitle("Connect N Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1012, 563);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayGoodbyeMessage();
				System.exit(0);
			}
		});

		newGameMenuItem = new JMenuItem("New Game");
		newGameMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_newGameMenuItem();
			}
		});
		fileMenu.add(newGameMenuItem);

		saveMenuItem = new JMenuItem("Save Game");
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_saveMenuItem();
			}
		});
		saveMenuItem.setEnabled(false);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);

		openMenu = new JMenu("Open");
		menuBar.add(openMenu);

		loadMenuItem = new JMenuItem("Load Game");
		loadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_loadMenuItem();
			}
		});
		openMenu.add(loadMenuItem);

		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		rulesMenuItem = new JMenuItem("Game Rules");
		rulesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_rulesMenuItem();
			}
		});
		helpMenu.add(rulesMenuItem);

		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_aboutMenuItem();
			}
		});
		helpMenu.add(aboutMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pnlButtons = new JPanel();
		pnlButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setLayout(new GridLayout(0, 5, 0, 0));

		lblSpacer = new JLabel("");
		pnlButtons.add(lblSpacer);

		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_btnUndo();
			}
		});
		btnUndo.setEnabled(false);
		pnlButtons.add(btnUndo);

		btnDropToken = new JButton("Drop Token");
		btnDropToken.setEnabled(false);
		btnDropToken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_btnDropToken();
			}
		});

		lblSpacer2 = new JLabel("");
		pnlButtons.add(lblSpacer2);
		pnlButtons.add(btnDropToken);

		lblSpacer3 = new JLabel("");
		pnlButtons.add(lblSpacer3);

		pnlPlayers = new JPanel();
		pnlPlayers.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlPlayers, BorderLayout.WEST);
		GridBagLayout gbl_pnlPlayers = new GridBagLayout();
		gbl_pnlPlayers.columnWidths = new int[] { 91, 66, 0 };
		gbl_pnlPlayers.rowHeights = new int[] { 26, 26, 23, 0 };
		gbl_pnlPlayers.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlPlayers.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlPlayers.setLayout(gbl_pnlPlayers);

		lblCurrentPlayer = new JLabel("  Current Player: ");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.anchor = GridBagConstraints.EAST;
		gbc_lblCurrentPlayer.fill = GridBagConstraints.VERTICAL;
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 0;
		pnlPlayers.add(lblCurrentPlayer, gbc_lblCurrentPlayer);

		lblPlayerName = new JLabel("");
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.fill = GridBagConstraints.BOTH;
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.gridx = 1;
		gbc_lblPlayerName.gridy = 0;
		pnlPlayers.add(lblPlayerName, gbc_lblPlayerName);

		lblPlayer1 = new JLabel("Yellow Player: ");
		GridBagConstraints gbc_lblPlayer1 = new GridBagConstraints();
		gbc_lblPlayer1.anchor = GridBagConstraints.EAST;
		gbc_lblPlayer1.fill = GridBagConstraints.VERTICAL;
		gbc_lblPlayer1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer1.gridx = 0;
		gbc_lblPlayer1.gridy = 1;
		pnlPlayers.add(lblPlayer1, gbc_lblPlayer1);

		lblPlayer1Name = new JLabel("");
		GridBagConstraints gbc_lblPlayer1Name = new GridBagConstraints();
		gbc_lblPlayer1Name.fill = GridBagConstraints.BOTH;
		gbc_lblPlayer1Name.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayer1Name.gridx = 1;
		gbc_lblPlayer1Name.gridy = 1;
		pnlPlayers.add(lblPlayer1Name, gbc_lblPlayer1Name);

		lblPlayer2 = new JLabel("Red Player: ");
		GridBagConstraints gbc_lblPlayer2 = new GridBagConstraints();
		gbc_lblPlayer2.anchor = GridBagConstraints.EAST;
		gbc_lblPlayer2.fill = GridBagConstraints.VERTICAL;
		gbc_lblPlayer2.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlayer2.gridx = 0;
		gbc_lblPlayer2.gridy = 2;
		pnlPlayers.add(lblPlayer2, gbc_lblPlayer2);

		lblPlayer2Name = new JLabel("");
		GridBagConstraints gbc_lblPlayer2Name = new GridBagConstraints();
		gbc_lblPlayer2Name.fill = GridBagConstraints.BOTH;
		gbc_lblPlayer2Name.gridx = 1;
		gbc_lblPlayer2Name.gridy = 2;
		pnlPlayers.add(lblPlayer2Name, gbc_lblPlayer2Name);

		pnlTitle = new JPanel();
		pnlTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlTitle, BorderLayout.NORTH);
		pnlTitle.setLayout(new GridLayout(0, 1, 0, 0));

		lblTitle = new JLabel("Connect N");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		pnlTitle.add(lblTitle);

		pnlGameBoard = new JPanel();
		pnlGameBoard.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlGameBoard, BorderLayout.CENTER);

		pnlGameInfo = new JPanel();
		pnlGameInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlGameInfo, BorderLayout.EAST);
		GridBagLayout gbl_pnlGameInfo = new GridBagLayout();
		gbl_pnlGameInfo.columnWidths = new int[] { 64, 46, 0 };
		gbl_pnlGameInfo.rowHeights = new int[] { 29, 28, 21, 0 };
		gbl_pnlGameInfo.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlGameInfo.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlGameInfo.setLayout(gbl_pnlGameInfo);

		lblRowInfo = new JLabel("Rows: ");
		GridBagConstraints gbc_lblRowInfo = new GridBagConstraints();
		gbc_lblRowInfo.anchor = GridBagConstraints.EAST;
		gbc_lblRowInfo.fill = GridBagConstraints.VERTICAL;
		gbc_lblRowInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblRowInfo.gridx = 0;
		gbc_lblRowInfo.gridy = 0;
		pnlGameInfo.add(lblRowInfo, gbc_lblRowInfo);

		lblNumRows = new JLabel("");
		lblNumRows.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNumRows = new GridBagConstraints();
		gbc_lblNumRows.fill = GridBagConstraints.VERTICAL;
		gbc_lblNumRows.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumRows.gridx = 1;
		gbc_lblNumRows.gridy = 0;
		pnlGameInfo.add(lblNumRows, gbc_lblNumRows);

		lblColumnsInfo = new JLabel("Columns: ");
		GridBagConstraints gbc_lblColumnsInfo = new GridBagConstraints();
		gbc_lblColumnsInfo.anchor = GridBagConstraints.EAST;
		gbc_lblColumnsInfo.fill = GridBagConstraints.VERTICAL;
		gbc_lblColumnsInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumnsInfo.gridx = 0;
		gbc_lblColumnsInfo.gridy = 1;
		pnlGameInfo.add(lblColumnsInfo, gbc_lblColumnsInfo);

		lblNumColumns = new JLabel("");
		lblNumColumns.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNumColumns = new GridBagConstraints();
		gbc_lblNumColumns.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumColumns.fill = GridBagConstraints.VERTICAL;
		gbc_lblNumColumns.gridx = 1;
		gbc_lblNumColumns.gridy = 1;
		pnlGameInfo.add(lblNumColumns, gbc_lblNumColumns);

		lblConnect = new JLabel("  Tokens to Connect: ");
		GridBagConstraints gbc_lblConnect = new GridBagConstraints();
		gbc_lblConnect.insets = new Insets(0, 0, 0, 5);
		gbc_lblConnect.gridx = 0;
		gbc_lblConnect.gridy = 2;
		pnlGameInfo.add(lblConnect, gbc_lblConnect);

		lblNumConnect = new JLabel("");
		lblNumConnect.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNumConnect = new GridBagConstraints();
		gbc_lblNumConnect.gridx = 1;
		gbc_lblNumConnect.gridy = 2;
		pnlGameInfo.add(lblNumConnect, gbc_lblNumConnect);

		actionPerformed_newGameMenuItem();
	} // ConnectNFrame()

	/**
	 * Action performed upon clicking the load menu item
	 */
	private void actionPerformed_loadMenuItem() {
		int error = -11;
		game = new ConnectNGame();
		try { // try loading the game
			error = game.loadGameBoard();
		}
		catch (IOException e) { // catch the thrown exception
			JOptionPane.showMessageDialog(this,
					"An unknown error occured while loading the game. Please contact customer support or create a new game.",
					"Unknown Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// switch case for error state
		switch (error) {
		case 0: // no errors
			instantiateBoard();
			JOptionPane.showMessageDialog(this, "The game has successfully loaded!",
					"Game Load Success", JOptionPane.PLAIN_MESSAGE);
			saveMenuItem.setEnabled(true);
			break;
		case -1: // missing save file
			JOptionPane.showMessageDialog(this,
					"The saved game file is missing. Please create a new game.",
					"Missing Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -2: // save file empty
			JOptionPane.showMessageDialog(this,
					"The saved game file is empty. Please create a new game.",
					"Empty Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -3: // rows error
			JOptionPane.showMessageDialog(this,
					"Corrupted save file. Please create a new game.",
					"Corrupted Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -4: // columns error
			JOptionPane.showMessageDialog(this,
					"Corrupted save file. Please create a new game.",
					"Corrupted Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -5: // numConnect error
			JOptionPane.showMessageDialog(this,
					"Corrupted save file. Please create a new game.",
					"Corrupted Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -6: // player1 name error
			JOptionPane.showMessageDialog(this,
					"Corrupted save file. Please create a new game.",
					"Corrupted Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -7: // player2 name error
			JOptionPane.showMessageDialog(this,
					"Corrupted save file. Please create a new game.",
					"Corrupted Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -8: // whose turn error
			JOptionPane.showMessageDialog(this,
					"Corrupted save file. Please create a new game.",
					"Corrupted Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -9: // game board error
			JOptionPane.showMessageDialog(this,
					"Corrupted save file. Please create a new game.",
					"Corrupted Save File", JOptionPane.ERROR_MESSAGE);
			break;
		case -10: // game already over
			JOptionPane.showMessageDialog(this,
					"The game is already over. Please create a new game.", "Game Over!",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		default: // unknown number
			JOptionPane.showMessageDialog(this,
					"An unknown error occured. Please contact customer support if the problem is persists.",
					"Unknown Error", JOptionPane.ERROR_MESSAGE);
		} // switch (error state)
	} // loadGame()

	/**
	 * Instantiates the game board
	 */
	private void instantiateBoard() {
		// remove everything from the panel
		pnlGameBoard.removeAll();
		// load game board upon success
		columnChoices = new JRadioButton[game.getColumns()];
		gameBoard = new JButton[game.getRows()][game.getColumns()];
		pnlGameBoard.setLayout(new GridLayout(0, game.getColumns(), 0, 0));
		columnButtonGroup = new ButtonGroup();
		for (int but = 0; but < columnChoices.length; but++) {
			columnChoices[but] = new JRadioButton();
			columnChoices[but].setHorizontalAlignment(SwingConstants.CENTER);
			columnButtonGroup.add(columnChoices[but]);
			pnlGameBoard.add(columnChoices[but]);
		} // for every radio button
		for (int row = 0; row < game.getRows(); row++) {
			for (int column = 0; column < game.getColumns(); column++) {
				if (game.getGameBoard()[row][column] == 'E') {
					gameBoard[row][column] = new JButton("");
					gameBoard[row][column].setBackground(Color.LIGHT_GRAY);
				} // empty slots
				else
					if (game.getGameBoard()[row][column] == 'Y') {
						gameBoard[row][column] = new JButton("Y");
						gameBoard[row][column].setBackground(Color.YELLOW);
					} // yellow tokens
					else {
						gameBoard[row][column] = new JButton("R");
						gameBoard[row][column].setBackground(Color.RED);
					} // Red tokens
				pnlGameBoard.add(gameBoard[row][column]);
			} // for columns in board
		} // for rows in board
		contentPane.add(pnlGameBoard, BorderLayout.CENTER);
		// reload and display
		pnlGameBoard.revalidate();
		pnlGameBoard.repaint();
		// setting fields
		lblNumRows.setText(String.valueOf(game.getRows()));
		lblNumColumns.setText(String.valueOf(game.getColumns()));
		lblNumConnect.setText(String.valueOf(game.getNumConnect()));
		lblPlayer1Name.setText(game.getPlayer1());
		lblPlayer2Name.setText(game.getPlayer2());
		// set who's turn
		if (game.getPlayer1Turn()) {
			lblPlayerName.setText(game.getPlayer1());
		} // if player1's turn
		else {
			lblPlayerName.setText(game.getPlayer2());
		} // if player2's turn
		btnDropToken.setEnabled(true); // enable drop token
	} // instantiateBoard()

	/**
	 * Action performed upon clicking drop token
	 */
	private void actionPerformed_btnDropToken() {
		int index = 0;
		while (index < columnChoices.length && !columnChoices[index].isSelected()) {
			index++;
		} // while loop to find which column is selected
		if (ValidateConnectNGame.validateTokenDropped(index,
				game.getGameBoard()) == -1) { // if token dropped in invalid token
			JOptionPane.showMessageDialog(this,
					"Column mismatch error. Please contact customer support.",
					"Column Mismatch Error", JOptionPane.ERROR_MESSAGE); // error message
			System.exit(-1); // exit with an error state since it's not supposed to be possible
		} // if column is invalid
		else // column valid
			if (ValidateConnectNGame.validateTokenDropped(index,
					game.getGameBoard()) == -2) // token dropped a a full column
				JOptionPane.showMessageDialog(this,
						"Column is full. Please select a different column", "Column Full",
						JOptionPane.ERROR_MESSAGE); // error message
			else { // token dropped is valid
				btnUndo.setEnabled(true); // enable undo option
				game.takeTurn(index); // take a turn at the index
				displayGameBoard(); // display the new game board
				checkForWin(); // check for a win
			} // if token dropped is valid
	} // actionPerformed_btnDropToken()

	/**
	 * Action performed upon creating a new game
	 */
	private void actionPerformed_btnCreateNewGame() {
		// declare variables for use
		int row, column, numConnect;
		row = 99;
		try { // try to convert txt fld to number
			row = Integer.parseInt(fldRows.getText());
		}
		catch (NumberFormatException r) { // catch not a number
			if (fldRows.getText().length() <= 0) {
				JOptionPane.showMessageDialog(this,
						String.format("%s\n%s", "The rows field is empty",
								"Please enter the number of rows."),
						"Rows Empty", JOptionPane.ERROR_MESSAGE); // error message
				return; // end method
			} // if empty
			else {
				JOptionPane.showMessageDialog(this,
						String.format("%s\n%s", "Rows can only be a number.",
								"Please reenter the number of rows."),
						"Rows Not A Number", JOptionPane.ERROR_MESSAGE); // error message
				fldRows.setText(""); // remove text
				return; // end method
			} // if a string
		} // catch if row not a number
		if (!ValidateConnectNGame.validateNumRowsCols(row)) { // check if row valid
			JOptionPane.showMessageDialog(this,
					String.format("%s\n%s", "Rows must be between 4 and 12",
							"Please reenter the number of rows."),
					"Invalid Number of Rows", JOptionPane.ERROR_MESSAGE); // error message
			fldRows.setText(""); // remove txt
			return; // end method
		} // if row not valid
		else { // once row is valid
			column = 99;
			try { // try to convert txt fld to number
				column = Integer.parseInt(fldColumns.getText());
			}
			catch (NumberFormatException c) { // catch column not a number
				if (fldColumns.getText().length() <= 0) {
					JOptionPane.showMessageDialog(this,
							String.format("%s\n%s", "The columns field is empty",
									"Please enter the number of columns."),
							"Columns Empty", JOptionPane.ERROR_MESSAGE); // error message
					return; // end method
				} // if empty
				else {
					JOptionPane.showMessageDialog(this,
							String.format("%s\n%s", "Columns can only be a number.",
									"Please reenter the number of columns."),
							"Columns Not A Number", JOptionPane.ERROR_MESSAGE); // error message
					fldColumns.setText(""); // remove text
					return; // end method
				}
			} // catch column not a number
			if (!ValidateConnectNGame.validateNumRowsCols(column)) { // check column number
				JOptionPane.showMessageDialog(this,
						String.format("%s\n%s", "Columns must be between 4 and 12",
								"Please reenter the number of columns."),
						"Invalid Number of Columns", JOptionPane.ERROR_MESSAGE); // error message
				fldColumns.setText(""); // remove txt
				return; // end method
			} // if column invalid
			else { // once column is valid
				numConnect = 99; // instantiate numConnect
				try { // try to convert txt field to number
					numConnect = Integer.parseInt(fldNumConnect.getText());
				}
				catch (NumberFormatException n) { // catch not a number
					if (fldNumConnect.getText().length() <= 0) {
						JOptionPane.showMessageDialog(this,
								String.format("%s\n%s", "The number of tokens field is empty",
										"Please enter the number of tokens to connect to win."),
								"Number of Tokens Empty", JOptionPane.ERROR_MESSAGE); // error message
						return; // end method
					} // if empty
					else {
						JOptionPane.showMessageDialog(this,
								String.format("%s\n%s",
										"Number of tokens can only be a number.",
										"Please reenter the number of number of tokens."),
								"Number of Tokens Not A Number", JOptionPane.ERROR_MESSAGE); // error message
						fldNumConnect.setText(""); // remove txt from field
						return; // end method
					}
				} // catch not a number
				if (!ValidateConnectNGame.validateConnectNumber(numConnect, row,
						column)) { // check numConnect
					JOptionPane.showMessageDialog(this, String.format("%s\n%s",
							"Number of tokens must be between 3 and 8, and only as large as rows or columns",
							"Please reenter the number of number of tokens."),
							"Invalid Number of Number of Tokens", JOptionPane.ERROR_MESSAGE); // error message
					fldNumConnect.setText(""); // remove txt from field
					return; // end method
				} // if numConnect is invalid
				else
					if (!ValidateConnectNGame.checkName(fldPlayer1.getText())) { // check player1
						JOptionPane.showMessageDialog(this,
								String.format("%s\n%s", "Player 1 name is empty",
										"Please enter player 1's name."),
								"Empty Player 1 Name", JOptionPane.ERROR_MESSAGE); // error message
						fldPlayer1.setText("");
						return; // end method
					} // if player1 is empty
					else
						if (!ValidateConnectNGame.checkName(fldPlayer2.getText())) { // check player2
							JOptionPane.showMessageDialog(this,
									String.format("%s\n%s", "Player 2 name is empty",
											"Please enter player 2's name."),
									"Empty Player 2 Name", JOptionPane.ERROR_MESSAGE); // Error message
							fldPlayer2.setText("");
							return; // end method
						} // if player2 is empty
						else {
							game = new ConnectNGame(row, column, numConnect,
									fldPlayer1.getText(), fldPlayer2.getText()); // game creation
							instantiateBoard(); // create board
							JOptionPane.showMessageDialog(this, "Game successfully created!",
									"Game Created", JOptionPane.PLAIN_MESSAGE); // success message
							saveMenuItem.setEnabled(true); // enable save option
						} // everything is valid, create new game and instantiate new board
			} // if column is valid
		} // if row is valid
	} // actionPerformed_btnCreateNewGame()

	/**
	 * Checks the game board for a win, and shows a message if game over
	 */
	private void checkForWin() {
		String player = "Player";
		if (game.getPlayer1Turn()) {
			player = game.getPlayer2();
		} // if winner is player2
		else {
			player = game.getPlayer1();
		} // if winner is player1
		if (game.checkForWin() == 0) {
			return; // end method
		} // if no win
		else
			if (game.checkForWin() == 1) // if row win
				JOptionPane.showMessageDialog(this, player + " wins with a row!",
						"Row Win!", JOptionPane.INFORMATION_MESSAGE); // display row win
			else
				if (game.checkForWin() == 2) // if column win
					JOptionPane.showMessageDialog(this, player + " wins with a column!",
							"Column Win!", JOptionPane.INFORMATION_MESSAGE); // Display column win
				else
					if (game.checkForWin() == 3) // if upper-left win
						JOptionPane.showMessageDialog(this,
								player + " wins with an upper-left diagonal!",
								"Upper-Left Diagonal Win!", JOptionPane.INFORMATION_MESSAGE); // Display upper-left win
					else
						if (game.checkForWin() == 4) // if upper-right win
							JOptionPane.showMessageDialog(this,
									player + " wins with an upper-right diagonal!",
									"Upper-Right Diagonal Win!", JOptionPane.INFORMATION_MESSAGE); // Display upper-right win
						else
							if (game.checkForWin() == 5) // if a tie
								JOptionPane.showMessageDialog(this,
										"The game is a tie! Nobody wins!", "Game Tied!",
										JOptionPane.INFORMATION_MESSAGE); // Display tie message
		saveMenuItem.setEnabled(false); // cannot save a game that is over
		btnDropToken.setEnabled(false); // if win, cannot drop token
		btnUndo.setEnabled(false); // if win, cannot undo
		for (int i = 0; i < columnChoices.length; i++) {
			columnChoices[i].setEnabled(false);
		} // if game over, disable radio buttons
	} // checkForWin()

	/**
	 * Displays or refreshes the game board
	 */
	private void displayGameBoard() {
		if (game.getPlayer1Turn()) {
			lblPlayerName.setText(game.getPlayer1());
		} // set player1 if player1
		else {
			lblPlayerName.setText(game.getPlayer2());
		} // set player2 if player2
		for (int row = 0; row < game.getRows(); row++) {
			for (int column = 0; column < game.getColumns(); column++) {
				if (game.getGameBoard()[row][column] == 'R') {
					gameBoard[row][column].setText("R");
					gameBoard[row][column].setBackground(Color.RED);
				} // Red tokens
				else
					if (game.getGameBoard()[row][column] == 'Y') {
						gameBoard[row][column].setText("Y");
						gameBoard[row][column].setBackground(Color.YELLOW);
					} // yellow tokens
					else {
						gameBoard[row][column].setText("");
						gameBoard[row][column].setBackground(Color.LIGHT_GRAY);
					} // empty
			} // for columns
		} // for rows
	} // displayGameBoard()

	/**
	 * Action performed upon clicking the new game menu item
	 */
	private void actionPerformed_newGameMenuItem() {
		// Remove save option
		saveMenuItem.setEnabled(false);
		// Remove previous game info
		lblNumRows.setText("");
		lblNumColumns.setText("");
		lblNumConnect.setText("");
		lblPlayer1Name.setText("");
		lblPlayer2Name.setText("");
		lblPlayerName.setText("");
		// remove all from panel and set layout
		pnlGameBoard.removeAll();
		GridBagLayout gbl_pnlGameBoard = new GridBagLayout();
		gbl_pnlGameBoard.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_pnlGameBoard.rowHeights = new int[] { 59, 0, 39, 36, 256, 0 };
		gbl_pnlGameBoard.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_pnlGameBoard.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlGameBoard.setLayout(gbl_pnlGameBoard);

		// newGame lbl
		lblNewGame = new JLabel("New Game");
		lblNewGame.setFont(new Font("Arial Black", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewGame = new GridBagConstraints();
		gbc_lblNewGame.anchor = GridBagConstraints.NORTH;
		gbc_lblNewGame.gridwidth = 4;
		gbc_lblNewGame.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewGame.gridx = 0;
		gbc_lblNewGame.gridy = 0;
		pnlGameBoard.add(lblNewGame, gbc_lblNewGame);

		// rows lbl
		lblInputRows = new JLabel("Number of rows: ");
		GridBagConstraints gbc_lblInputRows = new GridBagConstraints();
		gbc_lblInputRows.anchor = GridBagConstraints.EAST;
		gbc_lblInputRows.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputRows.gridx = 0;
		gbc_lblInputRows.gridy = 1;
		pnlGameBoard.add(lblInputRows, gbc_lblInputRows);

		// rows fld
		fldRows = new JTextField();
		GridBagConstraints gbc_fldRows = new GridBagConstraints();
		gbc_fldRows.anchor = GridBagConstraints.WEST;
		gbc_fldRows.insets = new Insets(0, 0, 5, 5);
		gbc_fldRows.gridx = 1;
		gbc_fldRows.gridy = 1;
		pnlGameBoard.add(fldRows, gbc_fldRows);
		fldRows.setColumns(10);

		// columns lbl
		lblInputColumns = new JLabel("Number of columns: ");
		GridBagConstraints gbc_lblInputColumns = new GridBagConstraints();
		gbc_lblInputColumns.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputColumns.anchor = GridBagConstraints.EAST;
		gbc_lblInputColumns.gridx = 2;
		gbc_lblInputColumns.gridy = 1;
		pnlGameBoard.add(lblInputColumns, gbc_lblInputColumns);

		// columns fld
		fldColumns = new JTextField();
		GridBagConstraints gbc_fldColumns = new GridBagConstraints();
		gbc_fldColumns.anchor = GridBagConstraints.WEST;
		gbc_fldColumns.insets = new Insets(0, 0, 5, 0);
		gbc_fldColumns.gridx = 3;
		gbc_fldColumns.gridy = 1;
		pnlGameBoard.add(fldColumns, gbc_fldColumns);
		fldColumns.setColumns(10);

		// numConnect lbl
		lblInputNumConnect = new JLabel("  Number of tokens to connect: ");
		GridBagConstraints gbc_lblInputNumConnect = new GridBagConstraints();
		gbc_lblInputNumConnect.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblInputNumConnect.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputNumConnect.gridx = 0;
		gbc_lblInputNumConnect.gridy = 2;
		pnlGameBoard.add(lblInputNumConnect, gbc_lblInputNumConnect);

		// numConnect fld
		fldNumConnect = new JTextField();
		GridBagConstraints gbc_fldNumConnect = new GridBagConstraints();
		gbc_fldNumConnect.anchor = GridBagConstraints.SOUTHWEST;
		gbc_fldNumConnect.insets = new Insets(0, 0, 5, 5);
		gbc_fldNumConnect.gridx = 1;
		gbc_fldNumConnect.gridy = 2;
		pnlGameBoard.add(fldNumConnect, gbc_fldNumConnect);
		fldNumConnect.setColumns(10);

		// player1 lbl
		lblInputPlayer1 = new JLabel("Player 1 name: ");
		GridBagConstraints gbc_lblInputPlayer1 = new GridBagConstraints();
		gbc_lblInputPlayer1.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblInputPlayer1.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputPlayer1.gridx = 0;
		gbc_lblInputPlayer1.gridy = 3;
		pnlGameBoard.add(lblInputPlayer1, gbc_lblInputPlayer1);

		// player1 fld
		fldPlayer1 = new JTextField();
		GridBagConstraints gbc_fldPlayer1 = new GridBagConstraints();
		gbc_fldPlayer1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_fldPlayer1.insets = new Insets(0, 0, 5, 5);
		gbc_fldPlayer1.gridx = 1;
		gbc_fldPlayer1.gridy = 3;
		pnlGameBoard.add(fldPlayer1, gbc_fldPlayer1);
		fldPlayer1.setColumns(10);

		// player2 lbl
		lblInputPlayer2 = new JLabel("Player 2 name: ");
		GridBagConstraints gbc_lblInputPlayer2 = new GridBagConstraints();
		gbc_lblInputPlayer2.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblInputPlayer2.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputPlayer2.gridx = 2;
		gbc_lblInputPlayer2.gridy = 3;
		pnlGameBoard.add(lblInputPlayer2, gbc_lblInputPlayer2);

		// player2 fld
		fldPlayer2 = new JTextField();
		GridBagConstraints gbc_fldPlayer2 = new GridBagConstraints();
		gbc_fldPlayer2.insets = new Insets(0, 0, 5, 0);
		gbc_fldPlayer2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_fldPlayer2.gridx = 3;
		gbc_fldPlayer2.gridy = 3;
		pnlGameBoard.add(fldPlayer2, gbc_fldPlayer2);
		fldPlayer2.setColumns(10);

		// Create new game button
		btnCreateNewGame = new JButton("Create New Game!");
		btnCreateNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformed_btnCreateNewGame();
			}
		});
		GridBagConstraints gbc_btnCreateNewGame = new GridBagConstraints();
		gbc_btnCreateNewGame.gridwidth = 5;
		gbc_btnCreateNewGame.gridx = 0;
		gbc_btnCreateNewGame.gridy = 4;
		pnlGameBoard.add(btnCreateNewGame, gbc_btnCreateNewGame);

		// re-instantiate the board
		pnlGameBoard.revalidate();
		pnlGameBoard.repaint();

	} // newGame()

	/**
	 * Action performed upon clicking the save menu item
	 */
	private void actionPerformed_saveMenuItem() {
		try {
			game.saveGame();
		} // try to save the game
		catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					String.format("%s\n%s", "Could not save current game to file.",
							"Please contact customer support to fix this issue."),
					"Unsuccessful Save!!", JOptionPane.ERROR_MESSAGE); // display error message
			return; // end method
		} // catch thrown exception
		JOptionPane.showMessageDialog(this, "Game successfully saved!",
				"Game Saved", JOptionPane.PLAIN_MESSAGE); // display success message
	} // actionPerformed_saveMenuItem()

	/**
	 * Action performed upon clicking the about menu item
	 */
	private void actionPerformed_aboutMenuItem() {
		JOptionPane.showMessageDialog(this, new ConnectNFrame_About(), "About",
				JOptionPane.PLAIN_MESSAGE); // creates an about panel with the about JPanel
	} // actionPerformed_aboutMenuItem()

	/**
	 * Action performed upon clicking the rules menu item
	 */
	private void actionPerformed_rulesMenuItem() {
		JOptionPane.showMessageDialog(this, new ConnectNFrame_HelpMessage(),
				"Game Rules", JOptionPane.PLAIN_MESSAGE); // creates a message panel with the help message JPanel
	} // actionPerformed_rulesMenuItem()

	/**
	 * Action performed upon clicking the undo button
	 */
	private void actionPerformed_btnUndo() {
		boolean state = game.undoTurn(); // store the undo value
		if (state) {
			displayGameBoard();
			JOptionPane.showMessageDialog(this, "The last move has been undone.",
					"Undo Successful", JOptionPane.PLAIN_MESSAGE);
			btnUndo.setEnabled(false);
		} // if undo was successful
		else {
			JOptionPane.showMessageDialog(this,
					"Could not undo last move. Please contact customer support if problem persists.",
					"Undo Error", JOptionPane.ERROR_MESSAGE);
		} // if you cannot undo at this time
	} // actionPerformed_btnUndo()

	/**
	 * Displays a goodbye message
	 */
	private void displayGoodbyeMessage() {
		JOptionPane.showMessageDialog(this,
				"Thank you for playing Connect N! Hope to see you again soon!",
				"Thank You For Playing", JOptionPane.PLAIN_MESSAGE);
	} // displayGoodbyeMessage()

} // ConnectNFrame class
