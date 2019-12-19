package edu.neu.csye6200.rules;

import java.util.logging.Logger;

import edu.neu.csye6200.ca.CACell;
import edu.neu.csye6200.ca.CACrystal;

/**
 * This is rule three class which consist distinct rule from other rule classes
 * This class extends from AbstractCARules class to use the common functionality
 * 
 * @author Krutik Tatibandwale
 *
 */


public class CARuleThree extends AbstractCARules{
	
	private static Logger log = Logger.getLogger(CARuleThree.class.getName());


	/**
	 * This method gets the crystal's current cell and modifies its neighbouring cell's according to the rule
	 */

	
	@Override
	protected void fn(CACrystal crystal, CACell[][] temp, int row, int col, int lRow, int fRow, int lCol, int fCol,
			int waterCount, int vapourCount, int iceCount) {
		
		if(row%2==0) {
			if(crystal.getArr()[row][col].getCellState()==CACell.CAStates.ICE) {

				int randInt = (int) (Math.random() * 10);

				switch(randInt) {
					case 0:
					case 1:	
						temp[lRow][lCol].setCellState(CACell.CAStates.ICE);
						temp[lRow][col].setCellState(CACell.CAStates.ICE);
						break;
					
					case 2:
					case 3:	
						temp[row][lCol].setCellState(CACell.CAStates.ICE);
						temp[row][fCol].setCellState(CACell.CAStates.ICE);
						break;
					
					case 4:
						temp[fRow][lCol].setCellState(CACell.CAStates.ICE);
						temp[fRow][col].setCellState(CACell.CAStates.ICE);			
						break;
						
					case 5:
						temp[lRow][lCol].setCellState(CACell.CAStates.ICE);
						temp[fRow][col].setCellState(CACell.CAStates.ICE);			
						break;
					
					case 6:
						temp[lRow][col].setCellState(CACell.CAStates.ICE);
						temp[fRow][lCol].setCellState(CACell.CAStates.ICE);
						break;
					
					case 7:
						temp[lRow][lCol].setCellState(CACell.CAStates.ICE);
						temp[lRow][col].setCellState(CACell.CAStates.ICE);
						temp[row][lCol].setCellState(CACell.CAStates.ICE);
						break;
						
					case 8:
						temp[row][fCol].setCellState(CACell.CAStates.ICE);
						temp[fRow][lCol].setCellState(CACell.CAStates.ICE);
						temp[fRow][col].setCellState(CACell.CAStates.ICE);
						break;
						
					case 9:
						temp[lRow][lCol].setCellState(CACell.CAStates.ICE);
						temp[row][lCol].setCellState(CACell.CAStates.ICE);
						temp[fRow][lCol].setCellState(CACell.CAStates.ICE);
						break;			
				}
			}
			
			if((iceCount>3 && iceCount<6) && crystal.getArr()[row][col].getCellState()==CACell.CAStates.ICE) {

				int randInt = (int) (Math.random() * 10);

				switch(randInt) {
					case 0:
					case 1:
						temp[lRow][lCol].setCellState(CACell.CAStates.WATER);
						temp[fRow][lCol].setCellState(CACell.CAStates.WATER);
						break;
						
					case 2:
					case 3:
						temp[lRow][col].setCellState(CACell.CAStates.WATER);
						temp[row][fCol].setCellState(CACell.CAStates.WATER);
						break;
						
					case 4:
					case 5:
						temp[row][lCol].setCellState(CACell.CAStates.WATER);
						temp[fRow][col].setCellState(CACell.CAStates.WATER);			
						break;
						
					case 6:
					case 7:					
						temp[lRow][lCol].setCellState(CACell.CAStates.WATER);
						temp[row][lCol].setCellState(CACell.CAStates.WATER);
						temp[fRow][lCol].setCellState(CACell.CAStates.WATER);
						break;
						
					case 8:
					case 9:
						temp[lRow][col].setCellState(CACell.CAStates.WATER);
						temp[row][fCol].setCellState(CACell.CAStates.WATER);
						temp[fRow][col].setCellState(CACell.CAStates.WATER);			
						break;
				}			
			}
			
			if((iceCount>5 && iceCount<10) && (crystal.getArr()[row][col].getCellState()==CACell.CAStates.ICE) ||
					(crystal.getArr()[row][col].getCellState()==CACell.CAStates.WATER)){
				temp[lRow][lCol].setCellState(CACell.CAStates.VAPOUR);
				temp[row][lCol].setCellState(CACell.CAStates.VAPOUR);
				temp[fRow][lCol].setCellState(CACell.CAStates.VAPOUR);
			}
		}
		
		else {
			if(crystal.getArr()[row][col].getCellState()==CACell.CAStates.ICE) {

				int randInt = (int) (Math.random() * 10);

				switch(randInt) {
				case 0:
				case 1:	
					temp[lRow][col].setCellState(CACell.CAStates.ICE);
					temp[fRow][fCol].setCellState(CACell.CAStates.ICE);			
					break;
				
				case 2:
				case 3:	
					temp[lRow][lCol].setCellState(CACell.CAStates.ICE);
					temp[fRow][lCol].setCellState(CACell.CAStates.ICE);
					break;
				
				case 4:
					temp[row][lCol].setCellState(CACell.CAStates.ICE);
					temp[row][fCol].setCellState(CACell.CAStates.ICE);
					break;
					
				case 5:
					temp[lRow][col].setCellState(CACell.CAStates.ICE);
					temp[lRow][lCol].setCellState(CACell.CAStates.ICE);
					temp[row][lCol].setCellState(CACell.CAStates.ICE);
					break;
				
				case 6:
					temp[row][fCol].setCellState(CACell.CAStates.ICE);
					temp[fRow][col].setCellState(CACell.CAStates.ICE);
					temp[fRow][fCol].setCellState(CACell.CAStates.ICE);			
					break;
				
				case 7:
					temp[lRow][col].setCellState(CACell.CAStates.ICE);
					temp[row][lCol].setCellState(CACell.CAStates.ICE);
					temp[fRow][lCol].setCellState(CACell.CAStates.ICE);
					break;
					
				case 8:
					temp[lRow][lCol].setCellState(CACell.CAStates.ICE);
					temp[row][fCol].setCellState(CACell.CAStates.ICE);
					temp[fRow][fCol].setCellState(CACell.CAStates.ICE);			
					break;
					
				case 9:
					temp[lRow][lCol].setCellState(CACell.CAStates.ICE);
					temp[row][lCol].setCellState(CACell.CAStates.ICE);
					temp[row][fCol].setCellState(CACell.CAStates.ICE);
					temp[fRow][col].setCellState(CACell.CAStates.ICE);
					break;			
				}
			}
			
			if((iceCount>2 && iceCount<5) && crystal.getArr()[row][col].getCellState()==CACell.CAStates.ICE) {
				
				int randInt = (int) (Math.random() * 10);

				switch(randInt) {
				case 0:
				case 1:
					temp[lRow][lCol].setCellState(CACell.CAStates.WATER);
					temp[row][fCol].setCellState(CACell.CAStates.WATER);
					break;
					
				case 2:
				case 3:
					temp[lRow][lCol].setCellState(CACell.CAStates.WATER);
					temp[fRow][col].setCellState(CACell.CAStates.WATER);
					break;
					
				case 4:
				case 5:
					temp[row][lCol].setCellState(CACell.CAStates.WATER);
					temp[fRow][col].setCellState(CACell.CAStates.WATER);			
					break;
					
				case 6:
				case 7:	
					temp[lRow][col].setCellState(CACell.CAStates.WATER);
					temp[lRow][lCol].setCellState(CACell.CAStates.WATER);
					temp[row][lCol].setCellState(CACell.CAStates.WATER);
					break;
					
				case 8:
				case 9:
					temp[row][fCol].setCellState(CACell.CAStates.WATER);
					temp[fRow][col].setCellState(CACell.CAStates.WATER);
					temp[fRow][fCol].setCellState(CACell.CAStates.WATER);			
					break;
				}			
			}
			
			if((iceCount>5 && iceCount<10) && (crystal.getArr()[row][col].getCellState()==CACell.CAStates.ICE) ||
					(crystal.getArr()[row][col].getCellState()==CACell.CAStates.WATER)){
				temp[lRow][lCol].setCellState(CACell.CAStates.VAPOUR);
				temp[row][fCol].setCellState(CACell.CAStates.VAPOUR);
				temp[fRow][fCol].setCellState(CACell.CAStates.VAPOUR);
			}
		}
		
		log.config("Temporary array configured according to the rule");
	}

}
