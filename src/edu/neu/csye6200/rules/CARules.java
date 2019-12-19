package edu.neu.csye6200.rules;

import edu.neu.csye6200.ca.CACrystal;

/**
 * This is rule interface used to implement different rules
 * @author Krutik Tatibandwale
 *
 */

public interface CARules {

	public CACrystal genNextCrystal(CACrystal d);
}
