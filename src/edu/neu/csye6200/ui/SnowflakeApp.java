package edu.neu.csye6200.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.neu.csye6200.ca.CASimulation;

/**
 * This is the main UI class extended from the CAApp abstract class
 * It defines the various Swing components
 * The UI contains the following items:
 * JFrame object is used as main container
 * JPanel object is used to inside JFrame to show simulation
 * 3 Buttons start, pause and stop used as JButton object
 * JComboBox object used to select a rule from the list
 * JText object used to input simulation cycles
 * 
 * @author Krutik Tatibandwale
 *
 */

public class SnowflakeApp extends CAApp {

	private static Logger log = Logger.getLogger(SnowflakeApp.class.getName());

	private JButton startBtn = null;
	private JButton pauseBtn = null;
	private JButton stopBtn = null;	
	private JTextField simCount = null;
	private JComboBox comboBox = null; // select between options
	private JPanel northPanel = null;	
	private CASimulation genSim;
    
	/**
	 *  Constructor to initialize CASimulation object
	 *  add CACanvas object as subscriber
	 *  Set frame properties
	 */
	
	public SnowflakeApp() {
		super();

		genSim = CASimulation.getInstance();		
		genSim.addObserver(canvas);
		
		log.info("CACanvas class becomes subscriber to publisher CASimulation class");
		frame.setTitle("SnowFlake Simulation");
		frame.setSize(1270,715);		
		frame.setLayout(new BorderLayout());		
		frame.add(canvas, BorderLayout.CENTER);
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		frame.setVisible(false);		
		log.info("Frame properties gets set");
		log.info("Snowflake App initialised");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub		
	}
	
	/**
	 * This method is used to initialize components of UI - Buttons, ComboBox, Text field
	 * The components are then added to frame (JFrame) 
	 * The components action listener are also defined in this
	 */
	
	@Override
	public JPanel getNorthPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		
		startBtn = new JButton("Start");
		pauseBtn = new JButton("Pause");
		stopBtn = new JButton("Stop");
		
		simCount = new JTextField(5);
		simCount.setText("20");
			
		comboBox = new JComboBox();
		comboBox.addItem("Symmetric");
		comboBox.addItem("Simple");
		comboBox.addItem("Complex");
		
		log.info("Frame components initialised");
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				

				// to get the value of combo box
				String comboBoxVal = comboBox.getSelectedItem().toString(); 
				log.config("Selected Rule: " + comboBoxVal);
				int iter = 0;
				
				// to handle case if user doesn't input number of simulation cycles  
				try{
					iter = Integer.parseInt(simCount.getText());
					log.info("Simulation Cycles: " + iter);
				}
				catch(NumberFormatException f) {
					log.warning("Value not provided for simulaion cycles");
					JOptionPane.showMessageDialog(null, "Please enter simulation cycles", "Error", JOptionPane.ERROR_MESSAGE);
					f.printStackTrace();
				}
				
				// sets the crystal list index to get appropriate crystal based on combo box value
				switch(comboBoxVal) {
				
					case "Symmetric":
						canvas.setCrysIndex(0);
						genSim.setCrysSetIdx(0);
						break;
						
					case "Simple":
						canvas.setCrysIndex(1);
						genSim.setCrysSetIdx(1);
						break;
						
					case "Complex":
						canvas.setCrysIndex(2);
						genSim.setCrysSetIdx(2);
						break;
				}
														
				if(iter>0) {
					genSim.startSim(iter);
					log.info("Start button clicked");
				}
				
				setButtonStates();
				log.config("Buttons states set");
			}			
		});
		
		// used to pause the simulation
		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				genSim.pauseSim();
				log.info("Pause button clicked");
			}			
		});
		
		// used to stop/quit the simulation
		stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				genSim.quitSim();
				setButtonStates();
				log.info("Stop button clicked");
			}			
		});
		
		// this is added to prevent user to enter any char other than numbers
		// user will be able to input only numbers
		simCount.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
	                e.consume(); // consume non-numbers
	            }
	        }
	    });		
		
		northPanel.add(startBtn);
		northPanel.add(pauseBtn);
		northPanel.add(stopBtn);		
		northPanel.add(new JLabel("Select Rule:"));
		northPanel.add(comboBox);		
		northPanel.add(new JLabel("Simulation Cycles:"));
		northPanel.add(simCount);
		
		setButtonStates();
		
		log.info("Components loaded into frame");
		return northPanel;
	}
	
	/**
	 * helper method to enable/disable the buttons based on operations
	 */

	private void setButtonStates() {
		boolean running = genSim.isRunning();
		
		startBtn.setEnabled(!running);
		pauseBtn.setEnabled(running);
		stopBtn.setEnabled(running);
	}
	
	/**
	 * Main method from where program begins
	 * Creates UI object on a different thread 
	 * @param args program input arguments
	 */
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		
		catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		       // handle exception
		/**
		 * This causes the UI object to be created on event-dispatching thread rather than main thread
		 */
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				SnowflakeApp sfApp = new SnowflakeApp();
				sfApp.getFrame().setVisible(true);
				log.config("Snowflake App started at Event Dispatching Thread");
			}			
		});
		log.info("Snowflake App started");
	}
}
