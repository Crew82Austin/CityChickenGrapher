package com.crew82austin.citychick;

public class MovePath {
	
	private int[] pathX;
	private int[] pathY;
	
	public MovePath(int xSize, int ySize){
		pathX = new int[xSize + 1];
		pathY = new int[ySize + 1];
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
	
	
	public void setLine(int x1, int y1, int x2, int y2){
		int slope = 0;
		boolean vert = true;
		
		if((x2 - x1) != 0){
			slope = (y2 - y1) / (x2 - x1);
			vert = false;
		}
		if(!vert){
			for(int a = 0; a < Math.abs(x2 - x1) + 1; a++){
				if(a >= 1025)
					break;
				pathX[a] = x1 + a;
				pathY[a] = y1 + (a * slope);
			}
		}
		
		if(vert && (y2 - y1) < 0){
			for(int b = 0; b < Math.abs(y2 - y1) + 1; b++){
				if(b >= 1025)
					break;
				pathX[b] = x1;
				pathY[b] = y1 - b;
			}
		}
		else if(vert && (y2 - y1) > 0){
			for(int b = 0; b < Math.abs(y2 - y1) + 1; b++){ 
				if(b >= 1025)
					break;
				pathX[b] = x1;
				pathY[b] = y1 + b;
			}
		}
		
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
		for(int a = 0; a < 1024; a++){
			if(pathX[a] >=0 && pathY[a] >= 0){
				System.out.print(pathX[a]+","+pathY[a]+"   ");
				lineCounter++;
			}
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
	
	public int getSize(){
		int w = pathX.length;
		int z = pathY.length;
		if(w < z)
			return w;
		else if(w > z)
			return z;
		
		return w;
	}
}

