package hw9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import hw5.Edge;
import hw8.Buildings;
import java.util.*;
import javax.swing.*;

/**
 * CampusController represents the controller of the GUI and creates and adds
 * all the elements of the GUI like buttons and drop down menus.
 * 
 * @author RibhavHora
 */

public class CampusController {
	private JButton button; // the Find Path button
	private JButton reset; // the Reset button
	private JComboBox<String> firstB; // the first drop down menu
	private JComboBox<String> secondB; // the second drop down menu
	private CampusView panel; // the panel of the GUI
	private static List<Edge<Buildings, Double>> list; // the path between
														// buildings

	// This class does not represent an ADT.

	/**
	 * Constructs a CampusController
	 * 
	 * @param p
	 *            the panel of the GUI you want to use
	 * 
	 * 
	 * @effects constructs a CampusController
	 */
	public CampusController(CampusView p) {
		button = new JButton("Find Path");
		firstB = new JComboBox<String>(CampusModel.returnBuildings());
		secondB = new JComboBox<String>(CampusModel.returnBuildings());
		reset = new JButton("Reset");
		list = new ArrayList<Edge<Buildings, Double>>();
		panel = p;
	}

	/**
	 * Adds controls and functionality to the controls
	 * 
	 * @effects adds controls the panel and makes them functional
	 */
	public void addControls() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String startNode = (String) firstB.getSelectedItem();
				String endNode = (String) secondB.getSelectedItem();
				if (!startNode.equals("Select Building") && !endNode.equals("SelectBuilding")) {
					list = CampusModel.findPath(startNode, endNode);
					panel.repaint();
				}
			}
		});
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list = new ArrayList<Edge<Buildings, Double>>();
				firstB.setSelectedIndex(0);
				secondB.setSelectedIndex(0);
				panel.repaint();
			}
		});
		panel.add(firstB);
		panel.add(secondB);
		panel.add(button);
		panel.add(reset);
	}

	/**
	 * Returns a list of the path between two buildings
	 * 
	 * @returns the list of the path between the two buildings.
	 */
	public static List<Edge<Buildings, Double>> returnList() {
		return new ArrayList<Edge<Buildings, Double>>(list);
	}

}
