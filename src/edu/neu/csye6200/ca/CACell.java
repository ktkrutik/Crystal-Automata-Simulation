package edu.neu.csye6200.ca;

import java.util.logging.Logger;

/**
 * This class defines state of the cell.
 * A cell is the smallest entity that contains a particular state. 
 * @author Krutik Tatibandwale
 *
 */

public class CACell {
			
	private static Logger log = Logger.getLogger(CACell.class.getName());

	public static enum CAStates {
		VAPOUR, ICE, WATER;		
	}
	
	private CAStates cellState;

	/**
	 * Cell constructor used to initialize the cell with the default state 
	 * @param cellState contains the state of the cell
	 */
	
	public CACell(CAStates cellState) {
		this.cellState = cellState;
	}

	/**
	 * This getter method returns the current state of the cell
	 * @return cell's current state
	 */
	
	public CAStates getCellState() {
		log.config("Get cell called");
		return cellState;
	}

	/**
	 * Setter method used to set the state of the cell
	 * @param cellState used to set the state of the cell
	 */
	
	public void setCellState(CAStates cellState) {
		log.config("Set cell called");
			this.cellState = cellState;
	}
	
}