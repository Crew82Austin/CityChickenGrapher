package com.crew82austin.citychick;

/**
 * Interface for MOBs 
 * @author Justin
 *
 */
public interface Movable {
	
	public void spawn(int path, int ID); //Initializes objects
	public void update(float dTime);	//Updates objects states
	public void deSpawn();//de-initializes object
	public boolean isSpawned();	
	public MovePath getMovePath();
	public int getPathLoc();
	
	
}
