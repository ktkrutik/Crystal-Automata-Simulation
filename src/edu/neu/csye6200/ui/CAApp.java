package edu.neu.csye6200.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This is an abstract class used to abstract out common functionalities amongst UI class(Sonwflake App)
 * 
 * @author Krutik Tatibandwale
 *
 */

public abstract class CAApp implements ActionListener, WindowListener {
	protected JFrame frame = null;
	protected CACanvas canvas = null;
	
	private Logger log = Logger.getLogger(CAApp.class.getName());
	
	/**
	 * Constructor to JFrame and CACanvas objects
	 */
	public CAApp() {
		initGUI();
	}
	
    public void initGUI() {
    	frame = new JFrame();
		canvas = new CACanvas();

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame.DISPOSE_ON_CLOSE)
		
		// Permit the app to hear about the window opening
		frame.addWindowListener(this); 		
		
		log.info("Initiated main frame");
    }
    
    /**
     * Getter method to get the JFrame object
     * @return return the JFrame object
     */
    public JFrame getFrame() {
		return frame;
	}

	public abstract JPanel getNorthPanel() ;

}
