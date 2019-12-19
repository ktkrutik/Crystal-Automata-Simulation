package edu.neu.csye6200.rules;

import java.util.logging.Logger;

import edu.neu.csye6200.ca.CACell;
import edu.neu.csye6200.ca.CACrystal;

/**
 * This is rule two class which consist distinct rule from other rule classes
 * This class extends from AbstractCARules class to use the common functionality
 * 
 * @author Krutik Tatibandwale
 *
 */

public class CARuleTwo extends AbstractCARules{
	
	private static Logger log = Logger.getLogger(CARuleTwo.class.getName());
	
	/**
	 * This method gets the crystal's current cell and modifies its neighbouring cell's according to the rule
	 */


	@Override
	protected void fn(CACrystal crystal, CACell[][] temp, int row, int col, int lRow, int fRow, int lCol, int fCol,
			int waterCount, int vapourCount, int iceCount) {
		
		if(crystal.getArr()[row][col].getCellState()==CACell.CAStates.ICE) {
			temp[lRow][col].setCellState(CACell.CAStates.ICE);
			temp[fRow][col].setCellState(CACell.CAStates.ICE);
			temp[row][lCol].setCellState(CACell.CAStates.ICE);
			temp[row][fCol].setCellState(CACell.CAStates.ICE);
		}
		
		if((iceCount>3 && iceCount<7) && crystal.getArr()[row][col].getCellState()==CACell.CAStates.ICE) {
			temp[lRow][col].setCellState(CACell.CAStates.WATER);
			temp[fRow][col].setCellState(CACell.CAStates.WATER);
			temp[row][lCol].setCellState(CACell.CAStates.WATER);
			temp[row][fCol].setCellState(CACell.CAStates.WATER);
		}

		if((vapourCount>3 && vapourCount<6) && crystal.getArr()[row][col].getCellState()==CACell.CAStates.WATER) {
			temp[lRow][col].setCellState(CACell.CAStates.VAPOUR);
			temp[fRow][col].setCellState(CACell.CAStates.VAPOUR);
			temp[row][lCol].setCellState(CACell.CAStates.VAPOUR);
			temp[row][fCol].setCellState(CACell.CAStates.VAPOUR);
		}
		
		log.config("Temporary array configured according to the rule");
	}
}
