/*  Author: David Hedin-Abreu
 *  Date: 12/19/21
 *  Description: Game of Life unit
 */
 
package com.davidhedinabreu.gameoflife;

 public class Agent {
	 
	 private boolean life = false;
	 	 
	 public void die() {
		 life = false;
	 }
	 
	 public void spawn() {
		 life = true;
	 }
 	 
	 public boolean isAlive() {
		 return life;
	 }
	 
	 public void setLife(boolean val) {
		 life = val;
	 }
}
