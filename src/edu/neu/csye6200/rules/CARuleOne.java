package edu.neu.csye6200.rules;

import java.util.logging.Logger;

import edu.neu.csye6200.ca.CACell;
import edu.neu.csye6200.ca.CACrystal;

/**
 * This is rule one class which consist distinct rule from other rule classes
 * This class extends from AbstractCARules class to use the common functionality
 *  
 * @author Krutik Tatibandwale
 *
 */

public class CARuleOne extends AbstractCARules{
	
	private static Logger log = Logger.getLogger(CARuleOne.class.getName());

	/**
	 * This method gets the crystal's current cell and modifies its neighbouring cell's according to the rule
	 */
	
	@Override
	protected void fn(CACrystal crystal, CACell[][] temp, int row, int col, int lRow, int fRow, int lCol, int fCol,
			int waterCount, int vapourCount, int iceCount) {
				
		if(iceCount==1) {
			temp[row][col].setCellState(CACell.CAStates.ICE);
		}
		
		if(iceCount>4 && iceCount<6) {
			temp[row][col].setCellState(CACell.CAStates.VAPOUR);
		}
		
		if(iceCount>5 && iceCount<9) {
			temp[row][col].setCellState(CACell.CAStates.WATER);
		}
		
		if(vapourCount>3 && vapourCount<6) {
			temp[row][col].setCellState(CACell.CAStates.ICE);
		}
		
		if((vapourCount>5 && vapourCount<9) || (iceCount>6 && iceCount<9)) {
			temp[row][col].setCellState(CACell.CAStates.WATER);
		}
				
		log.config("Temporary array configured according to the rule");
	}
}
