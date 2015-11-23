package com.crew82austin.citychick;

public class MovePath {
	
	private int[] pathX;
	private int[] pathY;
	
	public MovePath(int xSize, int ySize){
		pathX = new int[xSize];
		pathY = new int[ySize];
		clear();
	}
	/**
	 * 
	 * @param n
	 * @param x
	 * @param y
	 * 
	 * Sets a specific point in the path to a given value.
	 */
	public void setPoint(int n, int x, int y){
		if(n > pathX.length || n > pathY.length){
			System.out.println("Error! Maxpath size exceeded!");
			return;
		}
		pathX[n] = x;
		pathY[n] = y;
		
		return;
	}
	
	public void clear(){
		for(int a = 0; a < pathX.length; a++){
			pathX[a] = -1;
		}
		for(int b = 0; b < pathY.length; b++){
			pathY[b] = -1;
		}
		
		return;
	}
	
	public void print(){
		int lineCounter = 0;
		for(int a = 0; a < pathX.length; a++){
			System.out.print(pathX[a]+","+pathY[a]+"   ");
			lineCounter++;
			if(lineCounter > 8){
				System.out.println();
				lineCounter = 0;
			}
		}
			return;
			
	}
	
	public int getX(int n){
		return pathX[n];
	}
	
	public int getY(int n){
		return pathY[n];
	}
}
