package hw9;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import hw5.Edge;
import hw8.Buildings;

/**
 * CampusView represents the view of the GUI.
 * 
 * @author RibhavHora
 */

public class CampusView extends JPanel {
	private static final long serialVersionUID = 7526472295622776147L;
	private BufferedImage pic; // the image of the map

	// This class does not represent an ADT.

	/**
	 * Constructs a CampusView
	 * 
	 * @effects constructs an empty CampusView
	 */
	public CampusView() {
		try {
			pic = ImageIO.read(new File("src/hw8/data/campus_map.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Paints items to the view
	 * 
	 * @param g
	 *            the graphics context where the painting should take place
	 * 
	 * @effects displays the campus map and the shortest distance between
	 * 			two buildings and marks the two buildings.
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// paint background
		super.paintComponent(g);
		g2.drawImage(pic, 0, 0, 1000, 800, this);
		g2.setStroke(new BasicStroke(3)); // To make the lines thicker
		List<Edge<Buildings, Double>> list = CampusController.returnList();
		if (!list.isEmpty()) {
			g2.setColor(Color.GREEN);
			g2.fillOval((int) (list.get(0).getStartNode().getX() / 4.33) - 5,
					(int) (list.get(0).getStartNode().getY() / 3.705) - 5, 15, 15); // first
																					// mark
			g2.setColor(Color.RED);
			g2.fillOval((int) (list.get(list.size() - 1).getEndNode().getX() / 4.33) - 5,
					(int) (list.get(list.size() - 1).getEndNode().getY() / 3.705) - 5, 15, 15); // second
																								// mark
			g2.setColor(Color.BLUE);
			for (Edge<Buildings, Double> e : list) {
				Shape line = new Line2D.Double(e.getStartNode().getX() / 4.33, e.getStartNode().getY() / 3.705,
						(int) e.getEndNode().getX() / 4.33, (int) e.getEndNode().getY() / 3.705); // shape
																									// allows
																									// doubles
				g2.draw(line);
			}
		} 
	}

}
