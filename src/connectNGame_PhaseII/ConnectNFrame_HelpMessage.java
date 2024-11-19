package ConnectNGame_PhaseII;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

/**
 * <p>Course: 420-G20 Programming II</p>
 * <p>Title: ConnectNFrame_HelpMessage</p>
 * <p>The help message of the ConnectNGame</p>
 * @author Adrian Charron
 */
public class ConnectNFrame_HelpMessage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblHelpTitle;
	private JLabel lblGoal;
	private JLabel lbl1;
	private JLabel lbl1_4;
	private JLabel lbl2;
	private JLabel lbl1_1;
	private JLabel lbl1_2;
	private JLabel lbl1_3;
	private JLabel lbl2_1;
	private JLabel lbl2_2;
	private JLabel lbl2_2_1;
	private JLabel lbl2_3;
	private JLabel lblHaveFun;

	/**
	 * Create the panel.
	 */
	public ConnectNFrame_HelpMessage() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		lblHelpTitle = new JLabel("Welcome to Connect N Game");
		lblHelpTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		GridBagConstraints gbc_lblHelpTitle = new GridBagConstraints();
		gbc_lblHelpTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblHelpTitle.gridx = 0;
		gbc_lblHelpTitle.gridy = 0;
		add(lblHelpTitle, gbc_lblHelpTitle);

		lblGoal = new JLabel(
				"The goal of the game is to connect enough tokens in any line. That means a row, column or diagonal.");
		GridBagConstraints gbc_lblGoal = new GridBagConstraints();
		gbc_lblGoal.insets = new Insets(0, 0, 5, 0);
		gbc_lblGoal.gridx = 0;
		gbc_lblGoal.gridy = 1;
		add(lblGoal, gbc_lblGoal);

		lbl1 = new JLabel(
				"1. When you create a new game, you will be able to enter:");
		GridBagConstraints gbc_lbl1 = new GridBagConstraints();
		gbc_lbl1.insets = new Insets(0, 0, 5, 0);
		gbc_lbl1.anchor = GridBagConstraints.WEST;
		gbc_lbl1.gridx = 0;
		gbc_lbl1.gridy = 2;
		add(lbl1, gbc_lbl1);
		
		lbl1_1 = new JLabel("     -Rows: Must be at least 4, or at most 12.");
		GridBagConstraints gbc_lbl1_1 = new GridBagConstraints();
		gbc_lbl1_1.anchor = GridBagConstraints.WEST;
		gbc_lbl1_1.insets = new Insets(0, 0, 5, 0);
		gbc_lbl1_1.gridx = 0;
		gbc_lbl1_1.gridy = 3;
		add(lbl1_1, gbc_lbl1_1);
		
		lbl1_2 = new JLabel("     -Columns: Must be at least 4, or at most 12.");
		GridBagConstraints gbc_lbl1_2 = new GridBagConstraints();
		gbc_lbl1_2.anchor = GridBagConstraints.WEST;
		gbc_lbl1_2.insets = new Insets(0, 0, 5, 0);
		gbc_lbl1_2.gridx = 0;
		gbc_lbl1_2.gridy = 4;
		add(lbl1_2, gbc_lbl1_2);
		
		lbl1_3 = new JLabel("     -Number of tokens to connect: Must be at least 3, and at most 8 or the largest of rows or columns.");
		GridBagConstraints gbc_lbl1_3 = new GridBagConstraints();
		gbc_lbl1_3.anchor = GridBagConstraints.WEST;
		gbc_lbl1_3.insets = new Insets(0, 0, 5, 0);
		gbc_lbl1_3.gridx = 0;
		gbc_lbl1_3.gridy = 5;
		add(lbl1_3, gbc_lbl1_3);

		lbl1_4 = new JLabel("     -Your names: At least 1 character long.");
		GridBagConstraints gbc_lbl1_4 = new GridBagConstraints();
		gbc_lbl1_4.insets = new Insets(0, 0, 5, 0);
		gbc_lbl1_4.anchor = GridBagConstraints.WEST;
		gbc_lbl1_4.gridx = 0;
		gbc_lbl1_4.gridy = 6;
		add(lbl1_4, gbc_lbl1_4);
		
		lbl2 = new JLabel("2. Once you are in a game, you will be able to:");
		GridBagConstraints gbc_lbl2 = new GridBagConstraints();
		gbc_lbl2.insets = new Insets(0, 0, 5, 0);
		gbc_lbl2.anchor = GridBagConstraints.WEST;
		gbc_lbl2.gridx = 0;
		gbc_lbl2.gridy = 7;
		add(lbl2, gbc_lbl2);
		
		lbl2_1 = new JLabel("     -Select a column with the buttons at the top, then click 'Drop Token' to play.");
		GridBagConstraints gbc_lbl2_1 = new GridBagConstraints();
		gbc_lbl2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lbl2_1.anchor = GridBagConstraints.WEST;
		gbc_lbl2_1.gridx = 0;
		gbc_lbl2_1.gridy = 8;
		add(lbl2_1, gbc_lbl2_1);
		
		lbl2_2 = new JLabel("     -Undo the previous turn when the button is available.");
		GridBagConstraints gbc_lbl2_2 = new GridBagConstraints();
		gbc_lbl2_2.insets = new Insets(0, 0, 5, 0);
		gbc_lbl2_2.anchor = GridBagConstraints.WEST;
		gbc_lbl2_2.gridx = 0;
		gbc_lbl2_2.gridy = 9;
		add(lbl2_2, gbc_lbl2_2);
		
		lbl2_2_1 = new JLabel("          -It undoes the previous turn and resets to the previous player.");
		GridBagConstraints gbc_lbl2_2_1 = new GridBagConstraints();
		gbc_lbl2_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lbl2_2_1.anchor = GridBagConstraints.WEST;
		gbc_lbl2_2_1.gridx = 0;
		gbc_lbl2_2_1.gridy = 10;
		add(lbl2_2_1, gbc_lbl2_2_1);
		
		lbl2_3 = new JLabel("     -Save the current state of the board, load an old saved game, create a new game, or quit.");
		GridBagConstraints gbc_lbl2_3 = new GridBagConstraints();
		gbc_lbl2_3.insets = new Insets(0, 0, 5, 0);
		gbc_lbl2_3.anchor = GridBagConstraints.WEST;
		gbc_lbl2_3.gridx = 0;
		gbc_lbl2_3.gridy = 11;
		add(lbl2_3, gbc_lbl2_3);
		
		lblHaveFun = new JLabel("Have fun playing Connect N!!");
		lblHaveFun.setFont(new Font("Arial Black", Font.BOLD, 13));
		GridBagConstraints gbc_lblHaveFun = new GridBagConstraints();
		gbc_lblHaveFun.gridx = 0;
		gbc_lblHaveFun.gridy = 12;
		add(lblHaveFun, gbc_lblHaveFun);

	}

}
