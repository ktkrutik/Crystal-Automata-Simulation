package edu.neu.csye6200.ui;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import edu.neu.csye6200.ca.CACell;
import edu.neu.csye6200.ca.CACrystal;


/**
 * This class uses Observable design pattern and so implements Observer
 * It act as a subscriber
 * CASimulation class running on simulation thread publishes crystal whenever a crystal changed 
 * this class receives the changed crystal using update method on Observer and display that on UI 
 * @author Krutik Tatibandwale
 *
 */

public class CACanvas extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;	
    private final int maxRows = 43;
    private final int maxCols = 84;
	private final int cellSize = 15;
    private Color col = null;
    private long counter = 0L;
    private CACrystal changedCrystal = null;
    private int crysIndex;

    private Logger log = Logger.getLogger(CACanvas.class.getName());

    public void setCrysIndex(int crysIndex) {
		this.crysIndex = crysIndex;
	}

	/**
     * CellAutCanvas constructor
     */
	public CACanvas() {
		col = Color.WHITE;		
	
		log.info("CACanvas initialised");
	}

	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		drawCellularAutomaton(g); // Added-on drawing
    }
		
	/**
	 * Draw the CA graphics panel
	 * @param g object of Graphics class
	 */
	
	public void drawCellularAutomaton(Graphics g) {
		log.info("Drawing cell automation " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, size.width, size.height);
				
		g2d.setColor(Color.RED);
		g2d.drawString("Cellular Automaton 2D", 10, 15);
						
		for (int j = 0; j < maxRows; j++) {
			boolean evenRow = ((j%2) == 0);
		   for (int i = 0; i < maxCols; i++) {
			   int shift = evenRow ? 0 : 10;

			   if(changedCrystal == null) {
				   defGrid(g2d, i*cellSize, j*cellSize + 20, cellSize-1, shift, crysIndex, col);
				   continue;
			   }			   
			   
// this checks the state of the cell and set color for that cell accordingly
			   
			   CACell.CAStates newState = changedCrystal.getArr()[j][i].getCellState();
			   
			   if(newState == CACell.CAStates.WATER) {
				   col = new Color(validColor(118), validColor(204), validColor(248));

			   }
			   else if(newState == CACell.CAStates.ICE) {
				   col = new Color(validColor(73), validColor(96), validColor(243));

			   }
			   else if (newState == CACell.CAStates.VAPOUR){
				   col = new Color(validColor(255), validColor(255), validColor(255));  
			   }
			   
			   // Draw box, one pixel less to create a black outline
			   
			   defGrid(g2d, i*cellSize, j*cellSize + 20, cellSize-1, shift, crysIndex, col);
		   }
		}
		log.info("Painted the UI");
	}
	
	/**
	 * A convenience routine to set the color, select the grid based on rule 
	 * Select and paint the component shape based on rule
	 * 
	 * @param g2d the graphics context
	 * @param x horizontal offset in pixels
	 * @param y vertical offset in pixels
	 * @param size the square size in pixels
	 * @param shift used for making hexagon grid 
	 * @param crysIndex rule type
	 * @param color to fill/draw
	 */
	
	private void defGrid(Graphics2D g2d, int x, int y, int size, int shift, int crysIndex, Color color) {
		
		g2d.setColor(color);
		
		switch(crysIndex) {
			
		case 1:
			g2d.fillOval(x, y, size, size);
			break;
			
		case 2:
			 g2d.fillRect(x + shift, y, size, size);	
			 break;
			 
		default:
			g2d.fillRect(x, y, size, size);	
			break;
		}		
	}
	
	/**
	 * A local routine to ensure that the color value is in the 0 to 255 range
	 * 
	 * @param colorVal type of color
	 * @return color
	 */
	
	private int validColor(int colorVal) {
		if (colorVal > 255)
			colorVal = 255;
		if (colorVal < 0)
			colorVal = 0;
		return colorVal;
	}

	/**
	 * Method of Observer used to get the crystal whenever it gets changed
	 */
	
	@Override
	public void update(Observable o, Object arg) {
		log.info("Received updated crystal");
		changedCrystal = (CACrystal) arg;
		repaint();
		log.info("Repaint UI initiated");
	}	
}
