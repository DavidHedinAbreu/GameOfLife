/*
 * Author: David Hedin-Abreu
 * Date 12/2021
 * Description: Initiates creation of the Game of Life window using Window class methods.
 * Animates the GOL fast & slow, forward & backward, using Window methods.
 * Creates and manages separate thread for these time-consuming processes.
 * Keeps track of animation time.
 */

package com.davidhedinabreu.gameoflife;

import javax.swing.SwingWorker;

public class Animation {
	private static SwingWorker animWorker;
	private Board b;
	private Window w;
	private int time;  // animation time
	
	// Constructor
	public Animation (Board b, Window w) {
		this.b = b;
		this.w = w;
	}

	public void startGOL() {
		animWorker = new SwingWorker() {
			
			@Override
			protected Boolean doInBackground() throws Exception {
				w.startGOLWindow();
				return true;  
			}
		};
		
		animWorker.execute();
	}
	
	public void stopAnimation() {
		if (animWorker != null)
			animWorker.cancel(true);
	}
	
	public void animate(int startTime, int endTime, int pace) {
		animWorker = new SwingWorker() {
			
			@Override
			protected Boolean doInBackground() throws Exception {
				if (pace > 0) {
					for(time = startTime; time < endTime && !isCancelled(); time++) {
						w.updateGOLWindow(b.getBoardStateString(time), time, pace);
					}
				} else if (pace == 0 && !isCancelled() ) {
					time = startTime;
					w.updateGOLWindow(b.getBoardStateString(time), time, pace);
				} else {
					for(time = endTime; time >= startTime && !isCancelled(); time--) {
						w.updateGOLWindow(b.getBoardStateString(time), time, -1*pace);
					}
				}
				return true;
			}
			
		};
		
		animWorker.execute();
	}
	
	public int getTime() {
		int numGen = w.getNumGen();
		if(time < 0)
			return 0;
		else if(time >=  numGen)
			return numGen - 1;
		else
			return time;
	}
}

