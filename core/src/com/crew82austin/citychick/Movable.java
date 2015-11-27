package com.crew82austin.citychick;

public interface Movable {
	
	public void spawn(int path);
	public void update(float dTime);
	public void deSpawn();
	public boolean isSpawned();
	
	
}
