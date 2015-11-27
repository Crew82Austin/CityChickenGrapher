package com.crew82austin.citychick;

public interface Movable {
	
	public void spawn(int path, int ID);
	public void update(float dTime);
	public void deSpawn();
	public boolean isSpawned();
	public MovePath getMovePath();
	public int getPathLoc();
	
	
}
