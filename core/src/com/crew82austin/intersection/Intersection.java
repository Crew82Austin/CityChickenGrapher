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
	private Building[] buildings;
	private ConcreteSlab[] concretes;
	private CrossWalk[] crosswalks;
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
		
		buildings = new Building[4];
		buildings[0] = new Building(0, 0, 200, 200, intersectionBatch, "building.png");
		buildings[1] = new Building(0, 824, 200, 200, intersectionBatch, "building.png");
		buildings[2] = new Building(824, 824, 200, 200, intersectionBatch, "building.png");
		buildings[3] = new Building(824, 0, 200, 200, intersectionBatch, "building.png");
		
		concretes = new ConcreteSlab[4];
		concretes[0] = new ConcreteSlab(0,0, 300, 300, intersectionBatch, "concrete.png");
		concretes[1] = new ConcreteSlab(0, 724, 300, 300, intersectionBatch, "concrete.png");
		concretes[2] = new ConcreteSlab(724, 724, 300, 300, intersectionBatch, "concrete.png");
		concretes[3] = new ConcreteSlab (724, 0, 300, 300, intersectionBatch, "concrete.png");
		
		crosswalks = new CrossWalk[4];
		crosswalks[0] = new CrossWalk(240, 300, 424, 0, intersectionBatch, "crosswalk.png");
		crosswalks[1] = new CrossWalk(300, 770, 424, 270f, intersectionBatch, "crosswalk.png");
		crosswalks[2] = new CrossWalk(727, 300, 424, 0, intersectionBatch, "crosswalk.png");
		crosswalks[3] = new CrossWalk(300, 285, 424, 270f, intersectionBatch, "crosswalk.png");
		
		
	}
	
	/**
	 * Loads an intersection from a file.
	 * @param name
	 */
	public Intersection(String name){
		//Work in progress
	}
	
	public void draw(){
	//intersectionBatch.draw(intersectionImg, intersectionX, intersectionY);
		int a;
		for( a = 0; a < 4; a++)
			concretes[a].draw();
		for( a = 0; a < nWays; a++)
			stop[a].draw();
		
		for(a = 0; a < 4; a++)
			buildings[a].draw();
		
		for(a = 0; a < 4; a++)
			crosswalks[a].draw();
		
		return;
	}
}
