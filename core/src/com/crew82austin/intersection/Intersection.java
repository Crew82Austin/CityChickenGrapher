package com.crew82austin.intersection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * Intersection for the game. Can be thought of as the current level.
 * @author Justin
 *
 */

public class Intersection {
	
	private int intersectionX;
	private int intersectionY;
	private SpriteBatch intersectionBatch;
	private Texture intersectionImg;
	private int[] stops;
	private int[] carSpawns;
	private int nWays;	//Number of ways for the intersection (3 way, 4way, etc.)
	private int nLanes;	//Number of lanes per way
	private String intersectionName;
	private ShapeRenderer intersectionRenderer;
	private StopLine[] stop;
	
	/**
	 * Creates a standard intersection
	 * @param x
	 * @param y
	 * @param ways
	 * @param lanes
	 * @param name
	 * @param batch
	 * @param img
	 */
	public Intersection (int x, int y, int ways, int lanes, String name, SpriteBatch batch, String img){
		
		intersectionX = x;
		intersectionY = y;
		nWays = ways;
		nLanes = lanes;
		nWays = ways;
		intersectionName = name;
		intersectionBatch = batch;
		intersectionImg = new Texture(img);
		intersectionRenderer = new ShapeRenderer(100);
		intersectionRenderer.setAutoShapeType(true);
		stop = new StopLine[nWays];
		stop[0] = new StopLine(223, 300, 223, 460, intersectionBatch, "stopline.png");
		stop[1] = new StopLine(300, 800, 488, 800, intersectionBatch, "stopline.png");
		stop[2] = new StopLine(775, 490, 775, 723, intersectionBatch, "stopline.png");
		stop[3] = new StopLine(520, 233, 720, 233, intersectionBatch, "stopline.png");
		
		
		
	}
	
	/**
	 * Loads an intersection from a file.
	 * @param name
	 */
	public Intersection(String name){
		//Work in progress
	}
	
	public void draw(){
	intersectionBatch.draw(intersectionImg, intersectionX, intersectionY);
		for(int a = 0; a < nWays; a++)
			stop[a].draw();
		
		return;
	}
}
