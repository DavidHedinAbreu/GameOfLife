/*  Author: David Hedin-Abreu
 *  Date: 12/21
 *  Description: Board state for one frame/tick of Game of Life, at a particular time.
 */
 
package com.davidhedinabreu.gameoflife;

public class AgentField {
	
	private static int width, height;
	private Agent[][] population;
	
	// Constructor
	public AgentField(int w, int h) {
		width = w;
		height = h;
		population = new Agent[width][height];	
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++) {
				population[x][y] = new Agent();
			}
	}
	
	public Agent[][] getPopulation() {
		return population;
	}
	
	public void copyFrom(AgentField a) {
		Agent[][] newPop = a.getPopulation();
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++) {
				population[x][y].setLife(newPop[x][y].isAlive() );
			}
	}
	
	public void setPopulation(Agent[][] newPop) {
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++) {
				population[x][y].setLife(newPop[x][y].isAlive() );
			}
	}
	
}

