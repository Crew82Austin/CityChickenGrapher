package com.crew82austin.intersection;
/**
 * Line on the intersection where cars stop.
 * Made as white rectangle
 * @author Justin
 *
 */
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class StopLine{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int rectWidth;
	
	public StopLine (int X1, int Y1, int X2, int Y2, int width, ShapeRenderer renderer){
		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
		rectWidth = width;
	}
	
	public void draw(){
		
	}
}
