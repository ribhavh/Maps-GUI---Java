package hw9;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * CampusPathsMain launches a GUI which enables the user to select the name of
 * two buildings and draws the shortest path between those two buildings when
 * the user clicks Find Path. The Reset button resets the GUI.
 * 
 * @author RibhavHora
 */

public class CampusPathsMain {

	// the panel that contains all the elements of the GUI
	private static CampusView panel;

	// This class does not represent an ADT.

	/**
	 * Launches a GUI
	 * 
	 * @effects Launches a GUI which helps the user find the shortest path
	 *          between two buildings.
	 */
	public static void main(String[] args) {
		panel = new CampusView();
		JFrame frame = new JFrame("Find the Shortest Path between Two Buildings");
		frame.setPreferredSize(new Dimension(1000, 800));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		CampusController c = new CampusController(panel);
		c.addControls();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
