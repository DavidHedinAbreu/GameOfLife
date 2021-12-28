/*  Author: David Hedin-Abreu
 *  Date: 12/19/21
 *  Description: Keeps track of the status (live/dead) of the various agents in a particular frame/tick
 *  of the Game of Life, and generates all frames/ticks from time = 0 to the limit specified by the user.
 */
 
package com.davidhedinabreu.gameoflife;

 public class Board {
	 
	 private AgentField[] generations;
	 private Agent[][] newPop;
	 private int[][] neighbors;
	 private Window window;

	 private final int width, height;
	 private final int numGen, loneliness = 1, happiness = 3;
	 	 
	 // Constructor
	 public Board (int w, int h, int gens) {
		 width = w;
		 height = h;
		 numGen = gens;
		 generations = new AgentField[numGen];  // Where all generations of the Game of Life will be stored
		 newPop = new Agent[width][height];  // A temporary 2-D agent array
		 neighbors = new int[width][height];  // Keeps track of number of neighbors at each board position
		 
		 // Initialize generations with AgentFields
		 for (int t = 0; t < numGen; t++) 
			generations[t] = new AgentField(width, height);
		
		// Populate the first generation (generation[0]), and newPop, with agents, 
		// seed the first generation with live agents at random, and initialize neighbors with zero values
		Agent[][] initialPop = new Agent[width][height];
		 for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				neighbors[x][y] = 0;
				initialPop[x][y] = new Agent();
				newPop[x][y] = new Agent();
				if(Math.random() < 0.5)
					initialPop[x][y].spawn();
				}
		generations[0].setPopulation(initialPop);
		
		// Create all generations to user's specified limit
		makeGenerationsToEnd(1, numGen);
	 }

	// Creates (or re-creates if editing has occurred) all generations from startTime to the end of time
	// Note: generations are created from the generation prior to startTime, so
	// startTime - 1 should be the edited generation.
	 private void makeGenerationsToEnd(int startTime, int numGen) {
		 for(int time = startTime; time < numGen; time++) {
			 generate(time);
		 }
	 }
	 
	// Helper method for makeGenerationsToEnd
	 private void generate(int time) {
		 generations[time].copyFrom(generations[time - 1]);		 
		 updateNeighborsForBoard(time);
		 newPop = generations[time].getPopulation();
		 for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				if (neighbors[x][y] <= loneliness && newPop[x][y].isAlive() ) 
					 newPop[x][y].die();
				else if (neighbors[x][y] == happiness && !newPop[x][y].isAlive() ) 
					 newPop[x][y].spawn();
				else if (neighbors[x][y] > happiness && newPop[x][y].isAlive() ) 
					 newPop[x][y].die();
			}
		generations[time].setPopulation(newPop);
	 }

	 // Helper method for updateNeighborsForBoard
	 private void countNeighborsAtPosition(int xLoc, int yLoc, int time) {
		 neighbors[xLoc][yLoc] = 0;
		 for (int x = xLoc - 1; x <= xLoc + 1; x++) {
			for (int y = yLoc - 1; y <= yLoc + 1; y++) {
				if( !(
					(x == xLoc && y == yLoc) ||
					x < 0 ||
					x >= width ||
					y < 0 ||
					y >= height
					)
				) {	
					if (generations[time].getPopulation()[x][y].isAlive() ) 
						neighbors[xLoc][yLoc]++;
				}
			}
		 }
	 }
	 
	 // Updates neighbors, using helper method.
	 private void updateNeighborsForBoard(int time) {
		 for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				countNeighborsAtPosition(x, y, time);
			}
	 }

	// Prepares a String representing the state of the board for a particular frame/tick/time,
	// from the AgentField at that particular time 
	 public String getBoardStateString(int time) {
		 String contents = "";
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (generations[time].getPopulation()[x][y].isAlive() ) 
					contents += "* ";
				else 
					contents += "  ";
				}
				contents += "\n";
		}		
		return contents;
	 }

 }
	 

