package edu.neu.csye6200.ca;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

import edu.neu.csye6200.rules.CARuleOne;
import edu.neu.csye6200.rules.CARuleThree;
import edu.neu.csye6200.rules.CARuleTwo;
import edu.neu.csye6200.rules.CARules;

/**
 * This class contains the multiple Crystals and Rules
 * Each Crystal is associated with One Rule only
 * Crystal are contained in the crystal set (Crystal List)
 * Rules are contained in the rule set (Rule List)
 * Crystal set are used to hold the history of growth of a crystal 
 * It is also used to call CARules class repeatedly to generate new crystal state 
 * This class uses Singleton design pattern to generate only one object of CACrystalSet class
 * Here, there is an indirect one to one relationship has been build between on crystals and rules     
 * For Ex: crystal one is associated with CARuleOne class, crystal two is associated with CARuleTwo class and so on 
 * The more the number of distinct rules the more will be the number of crystals
 *    
 * @author Krutik Tatibandwale
 *
 */

public class CACrystalSet {

	private static Logger log = Logger.getLogger(CACrystalSet.class.getName());

	private ArrayList<CACrystal> crysList;
	private ArrayList<CARules> ruleList;
		
	private static CACrystalSet instance = null; // Static variable defined for Singleton Pattern
 
	/**
	 * Private constructor to initialize crystal and rule list
	 * Multiple CACrystal and CARule are added to the crystal and rule list respectively
	 * 
	 * @param rowLen maximum row length of the 2D array
	 * @param colLen maximum column length of the 2D array
	 * @param posX defines the initial x-coordinate of the cell having ice state
	 * @param posY defines the initial y-coordinate of the cell having ice state
	 */
	private CACrystalSet(int rowLen, int colLen, int posX, int posY) { // private constructor defined for Singleton Pattern
		
		crysList = new ArrayList<CACrystal>();
		ruleList = new ArrayList<CARules>();

		crysList.add(new CACrystal(rowLen, colLen, posX, posY));
		crysList.add(new CACrystal(rowLen, colLen, posX, posY));
		crysList.add(new CACrystal(rowLen, colLen, posX, posY));


		ruleList.add(new CARuleOne());
		ruleList.add(new CARuleTwo());
		ruleList.add(new CARuleThree());
		
		log.config("Singlton pattern observed for CACrystalSet to initialise only one object");
		log.info("CACrystal Set initialised");
		log.config("Crystal added to crystal list");
		log.config("Rules added to rule list");

	}
		
	/**
	 * Static method defined for Singleton Pattern - to initialize only one object
	 * This method gives us the way to initiate the class and also to returns the instance of it 
	 * the parameters here are used to create new crystal
	 *  
	 * @param rowLen maximum row length of the 2D array
	 * @param colLen maximum column length of the 2D array
	 * @param posX defines the initial x-coordinate of the cell having ice state
	 * @param posY defines the initial y-coordinate of the cell having ice state
	 * 
	 * @return the only CACrystalSet object
	 */
		public static CACrystalSet getInstance(int rowLen, int colLen, int posX, int posY) {
			if (instance == null) {
				instance = new CACrystalSet(rowLen, colLen, posX, posY);
			}
			return instance;
		}
	
	/**
	 * This method is used to compute the next crystal state
	 * It calls the method of CARules of an appropriate rule class to generate next crystal state
	 * It passes an appropriate crystal to an appropriate rule class
	 * For Ex: crystal one will pass as an argument to rule one
	 * To make sure this works there is an 'index' parameter which handles that
	 * it gets the same index component from crystal and the rule list   
	 * 
	 * @param index is used to get same index value for both crystal and rule list
	 * @return the newly generated crystal from the previous crystal
	 */
		
	public CACrystal computeNextCrystal(int index) {	
		log.info("Computed next crystal");
		return ruleList.get(index).genNextCrystal(crysList.get(index));
	}
	
	/**
	 * This method is used to reset the crystal to its original state
	 * This method is used when we stop the simulation
	 * It reset the current simulation crystal
	 * Used set property of array list to reset the crystal 
	 * 
	 * @param index used to know which crystal amongst the crystal list needs to be reset
	 * @param matRowLen maximum row length of the 2D array
	 * @param matColLen maximum column length of the 2D array
	 * @param corX defines the initial x-coordinate of the cell having ice state
	 * @param corY defines the initial y-coordinate of the cell having ice state
	 * @return crystal having default state
	 */
	
	public CACrystal resetCrystal(int index, int matRowLen, int matColLen, int corX, int corY) {
		
		crysList.set(index, new CACrystal(matRowLen, matColLen, corX, corY));
		log.info("Crystal gets reset to original state");
		return crysList.get(index);
	}
	
}
