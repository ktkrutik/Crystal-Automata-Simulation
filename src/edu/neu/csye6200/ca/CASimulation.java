package edu.neu.csye6200.ca;

import java.util.Observable;
import java.util.logging.Logger;

/**
 * This class uses Observable design pattern and so extends from Observable class
 * It act as a publisher 
 * It also implements Runnable class for thread generation (simulation thread)
 * Simulation start from this class and runs on different thread than UI thread 
 * This publishes simulation on a simulation thread
 * All the playback operations of the simulation are defined in this class
 * 
 * @author Krutik Tatibandwale
 *
 */

public class CASimulation extends Observable implements Runnable {
	
	private static Logger log = Logger.getLogger(CASimulation.class.getName());

	private final int matRowLen = 43;
	private final int matColLen = 84;
	private final int corX = matRowLen/2;
	private final int corY = matColLen/2;		
	
	private Thread simThread = null;
	private Runnable simRun = null;
	private int simRunCount = 0;
	private boolean paused = false; // I'm paused, so don't do work
	private boolean done = false; // I'm done, so stop my work loop 
	private boolean running = false; // set true if we are running	

	private int crysSetIdx;
	private CACrystalSet holdCrystal;
	private static CASimulation instance = null;

	/**
	 * Private constructor to initialize the CACrystalSet only object 
	 * Also used to initialize the Runnable object
	 */
	
	private CASimulation() {
		holdCrystal = CACrystalSet.getInstance(matRowLen, matColLen, corX, corY); // static method called for Singleton Pattern
		this.simRun = this;
		log.info("Simulation initialised");
	}
		
	public CASimulation(Runnable simRun) {
		this.simRun = simRun;
	}
	
	/**
	 * Static method defined for Singleton Pattern - to initialize only one object
	 * This method gives us the way to initiate the class and also to returns the instance of it 
	 * 
	 * @return the only CASimulation object
	 */
	
	public static CASimulation getInstance() {
		if(instance == null) {
			instance = new CASimulation();
		}
		return instance;
	}
	
	/**
	 * This setter method is used to set the crystal set index variable 
	 * based on the combo box value
	 * so that it helps to get the appropriate crystal from the crystal set
	 *  
	 * @param crysSetIdx crystal set index
	 */
	public void setCrysSetIdx(int crysSetIdx) {
		this.crysSetIdx = crysSetIdx;
		log.config("Crystal index set to " + crysSetIdx);
	}	

	public boolean isRunning() {
		return running;
	}

	/**
	 *This method activated on click on start button
	 *This starts the simulation
	 *it create a new thread on which simulation runs
	 * 
	 * @param iter defines the no. of iteration / simulation cycles 
	 */
	
	public void startSim(int iter) {
		simRunCount = iter;
		done = false;
		if(simThread == null) {
			simThread = new Thread(simRun);
			log.info("Simulation Thread Created");
			if(!simThread.isAlive()) {
				simThread.start();
				log.info("Simulation started on thread " + simThread);
			}
			running = true;
		}
	}	
	
	/**
	 * This method activates on click on pause button 
	 * It is used to pauses the simulation 
	 */
	
	public void pauseSim() {
		paused = !paused;
		log.info("Simulation Paused");
	}
	
	/**
	 * This method activates on click on stop button
	 * This is used to stop the simulation 
	 * It does this by setting the thread to null
	 * and resetting the current crystal to its original state
	 */
	
	public void quitSim() {
		
		done = true;
		running = false;
		simThread = null;
		log.info("Simulated stopped");

		crystalChanged(holdCrystal.resetCrystal(crysSetIdx, matRowLen, matColLen, corX, corY));
		log.config("Crystal reset to original state");
	}
	
	/**
	 * Helper method used to slow down the execution of simulation 
	 * @param millis time in milli seconds
	 */
	
	public void delayThread(long millis) {
		try {
			simThread.sleep(millis);
		} catch (InterruptedException e) {
			log.warning("Thread delayed");
			e.printStackTrace();
		}
	}

	/**
	 * This method initiated on thread.start() function
	 */
	
	@Override
	public void run() {
		int ctr = 0;
		
		while(!done) {
			ctr++;
			if(!paused) {
				doWork();
			}
			delayThread(500L);	
			if(ctr == simRunCount) {
				done = true;
			}
		}
	}
	
	/**
	 * This method calls to computeNextCrystal method CACrystalSet class
	 * to generate the next crystal state
	 * this next crystal is then passed to crystalChanged method
	 * to notify subscribers that the current crystal is changed
	 */
	
	public void doWork() {
		crystalChanged(holdCrystal.computeNextCrystal(crysSetIdx));
		log.config("Crystal Changed");
	}
	
	/**
	 * This method contains methods of Observable class
	 * These methods are used to notify the subscriber when some change happens
	 * 
	 * @param changedCrystal this is a changed crystal which gets passed to subscriber
	 */
	
	public void crystalChanged(CACrystal changedCrystal) {
		log.info("Observer pattern observed");
		setChanged();
		notifyObservers(changedCrystal);
		log.info("Changed crystal published to subscriber");
	}	
	
}
