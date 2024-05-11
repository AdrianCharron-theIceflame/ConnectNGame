package connectNGame_PhaseII;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * <p>Course: 420-G20 Programming II</p>
 * <p>Title: ConnectNFrame_About</p>
 * <p>The about panel of ConnectNGame</p>
 * @author Adrian Charron
 */
public class ConnectNFrame_About extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JLabel lblCreator;
	private JLabel lblYear;
	private JLabel lblCollege;

	/**
	 * Create the panel.
	 */
	public ConnectNFrame_About() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		lblTitle = new JLabel("Connect N Game & Frame");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);

		lblCreator = new JLabel("Adrian Charron");
		GridBagConstraints gbc_lblCreator = new GridBagConstraints();
		gbc_lblCreator.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreator.gridx = 0;
		gbc_lblCreator.gridy = 1;
		add(lblCreator, gbc_lblCreator);

		lblYear = new JLabel("2024");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.insets = new Insets(0, 0, 5, 0);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 2;
		add(lblYear, gbc_lblYear);

		lblCollege = new JLabel("Cégep Heritage College");
		GridBagConstraints gbc_lblCollege = new GridBagConstraints();
		gbc_lblCollege.gridx = 0;
		gbc_lblCollege.gridy = 3;
		add(lblCollege, gbc_lblCollege);

	} // ConnectNFrame_About()

} // ConnectNFrame_About class
