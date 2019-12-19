package edu.neu.csye6200.rules;

import java.util.logging.Logger;

import edu.neu.csye6200.ca.CACell;
import edu.neu.csye6200.ca.CACrystal;

/**
 * This is an abstract class used to abstract out common functionalities amongst distinct CARule
 * This implements CARules interface
 * 
 * @author Krutik Tatibandwale
 *
 */

public abstract class AbstractCARules implements CARules{

	/**
	 * This method accepts a crystal, do some common computation and sends the crystal to specific rule class
	 * The rule class generate different/new crystal based on distinct rule logic and returns the crystal 
	 */
	private static Logger log = Logger.getLogger(AbstractCARules.class.getName());

	public CACrystal genNextCrystal(CACrystal crystal){
				
		CACell temp[][] = new CACell [crystal.getRowLen()][crystal.getColLen()];
		for(int i=0; i<crystal.getRowLen(); i++) {
			for(int j=0; j<crystal.getColLen(); j++) {
				temp[i][j] = new CACell(crystal.getArr()[i][j].getCellState());				
			}
			log.config("Temporary array created to handle rule set");
		}

		int neighbour [] = new int[3];
				
		for(int row=0; row<crystal.getRowLen(); row++) {
			for(int col=0; col<crystal.getColLen(); col++) {
				
				neighbour = calNeighbour(crystal, row, col);
				
					int vapourCount = neighbour[0];
					int waterCount = neighbour[1];
					int iceCount = neighbour[2];

// this calculation is done to avoid the 2D array boundary condition
// to get the next state of crystal I compute the neighbour states
// but if current cell location is {0,0} then it will not be possible to compute the neighbours of this cell
// so either we can ignore/leave the boundary cells
// or do some computation to handle situation like this
// I, however, have wrap around my 2D array which gives an appearance of an infinite grid 
					
					int lRow = row-1 >=0 ? row-1 : crystal.getRowLen()-1;
					int fRow = row+1 < crystal.getRowLen() ? row+1 : 0;
					int lCol = col-1 >=0 ? col-1 : crystal.getColLen()-1;
					int fCol = col+1 < crystal.getColLen() ? col+1 : 0;
					
					fn(crystal, temp, row, col, lRow, fRow, lCol, fCol, vapourCount, waterCount, iceCount);					
			}
		}
		crystal.setArr(temp);
		return crystal;

	}
	
	/**
	 * This method is overridden by distinct rule class
	 * This method computes the next crystal from the previous crystal 
	 * Different rule class has their own rule to compute next crystal state 
	 * 
	 * @param crystal current crystal 
	 * @param temp used as supportive 2D array 
	 * @param row x-coordinate of current cell of a crystal
	 * @param col y-coordinate of current cell of a crystal
	 * @param lRow x-1
	 * @param fRow x+1
	 * @param lCol y-1
	 * @param fCol y_1
	 * @param waterCount count of water state around the current cell's neighbour
	 * @param vapourCount count of vapour state around the current cell's neighbour
	 * @param iceCount count of ice state around the current cell's neighbour
	 */
	
	protected abstract void fn(CACrystal crystal, CACell[][] temp, int row, int col, int lRow, 
			int fRow, int lCol, int fCol, int waterCount, int vapourCount, int iceCount);

	/**
	 * This method iterate through the neighbours of the current cell and 
	 * return the count of each state
	 *   
	 * @param cellArr current crystal 2D array
	 * @param row x-coordinate of current cell
	 * @param col y-coordinate of current cell
	 * @return multiple state count in the form of array
	 */
	
	private int[] calNeighbour(CACrystal cellArr, int row, int col) {

		int iceCount = 0;
		int waterCount = 0;
		int vapourCount = 0;
		int neighbour[] = new int[3];
				
		for(int i=row-1; i<row+2; i++) {
			int nRow = i;

			for(int j=col-1; j<col+2; j++) {
				int nCol = j;

				if(i==-1) {
					nRow = cellArr.getRowLen()-1;				
				}
				else if(i>=cellArr.getRowLen()-1) {
					nRow = 0;
				}

				if(j==-1) {
					nCol = cellArr.getColLen()-1;
				}
				else if(j>=cellArr.getColLen()-1) {
					nCol = 0;				
				}
								
				if(cellArr.getArr()[nRow][nCol].getCellState() == CACell.CAStates.VAPOUR) {
					vapourCount++;
				}
				else if(cellArr.getArr()[nRow][nCol].getCellState() == CACell.CAStates.WATER) {
					waterCount++;
				}
				else if(cellArr.getArr()[nRow][nCol].getCellState() == CACell.CAStates.ICE) {
					iceCount++;
				}				
			}
		}
		
		neighbour[0] = vapourCount;
		neighbour[1] = waterCount;
		neighbour[2] = iceCount;
		
		if(cellArr.getArr()[row][col].getCellState()==CACell.CAStates.VAPOUR) {
			neighbour[0] = neighbour[0] - 1;
		}

		else if(cellArr.getArr()[row][col].getCellState()==CACell.CAStates.WATER) {
			neighbour[1] = neighbour[1] - 1;
		}

		else if(cellArr.getArr()[row][col].getCellState()==CACell.CAStates.ICE) {
			neighbour[2] = neighbour[2] - 1;
		}
		
		log.config("neighbout count computed");
		return neighbour;
	}
	
}

	

					
					
