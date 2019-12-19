package edu.neu.csye6200.ca;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * This class contains the 2-Dimensional array of CACell class
 * The collection of 2-D array of CACell class called as a Crystal
 * 
 * @author Krutik Tatibandwale
 *
 */

public class CACrystal {

	private static Logger log = Logger.getLogger(CACrystal.class.getName());

	private CACell arr[][];

	/**
	 * Parameterized constructor to build an initial crystal
	 * Initially, there will be only one cell having ice state and rest other will be in vapour state  
	 * @param rowLen define the row length of the crystal
	 * @param colLen defines the column length of the crystal
	 * @param posX defines the initial x-coordinate of the cell having ice state 
	 * @param posY defines the initial y-coordinate of the cell having ice state
	 */
	
	public CACrystal(int rowLen, int colLen, int posX, int posY) {
		arr = new CACell [rowLen][colLen];
		for(int row=0; row<rowLen; row++) {
			for(int col=0; col<colLen; col++) {
				if(row==posX && col==posY) {
					arr[row][col] = new CACell(CACell.CAStates.ICE); 
				}
				else {
					arr[row][col] = new CACell(CACell.CAStates.VAPOUR); 
				}
		}
	  }	
		System.out.println();
		log.info("CACrystal initialised");
	}
		
	/**
	 * Getter method to get the entire crystal
	 * @return the 2-D array of the the CACell 
	 */
	
	public CACell[][] getArr() {
		log.config("Get crystal array called");
		return arr;
	}

	/**
	 * Setter method used to set the cells of the crystal
	 * @param arr pass 2-D array of CACell
	 */
	
	public void setArr(CACell[][] arr) {
		log.config("Set crytsal array called");
		this.arr = arr;		
	}
	
	/**
	 * Getter method to get the row length of the crystal
	 * @return returns the row length of the crystal
	 */
	
	public int getRowLen() {
		return arr.length;
	}

	/**
	 *  Getter method to get the column length of the crystal
	 * @return returns the column length of the crystal
	 */
	
	public int getColLen() {
		return arr[0].length;
	}	
}	
