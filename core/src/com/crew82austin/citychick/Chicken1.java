package com.crew82austin.citychick;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.*;
import java.util.Random;

public class Chicken1 implements Movable{
	
	private int chickenX;
	private int chickenY;
	private int pathLoc;
	private int currentFrame;
	private int currentMovPath;
	private int pathStep;
	private float frameTime;
	private float moveTime;
	private float rot;
	private boolean spawned;
	private boolean loop;
    private int chickenID;
    private final int Moves;
	private MovePath[] chickenPath;
	private SpriteSet sprite;
	private SpriteBatch chickenBatch;
	private Random rand;
	private double chickenStartTime;
	
	public Chicken1(SpriteBatch batch, String imgFile, int frame, int size, boolean looped){
		sprite = new SpriteSet(imgFile, size, size);
		chickenBatch = batch;
		spawned = false;
		currentFrame = frame;
		pathLoc = 0;	//Location in the MovePath
		pathStep = 1;	//Step through the MovePath
		loop = looped;
		Moves = 8;	//Possible number of move paths
		chickenPath = new MovePath[Moves];
		rand =  new Random(System.nanoTime());
		for(int a = 0; a < Moves; a++){
			chickenPath[a] = new MovePath(1024, 1024);
		}
		
		//Begin path definitions
		chickenPath[0].setLine( 250, 1024,  250,    0);
		chickenPath[1].setLine( 250,    0,  250, 1024);
		chickenPath[2].setLine( 752, 1024,  752,    0);
		chickenPath[3].setLine( 752,    0,  752, 1024);
		chickenPath[4].setLine(  0,   750, 1024,  750);
		chickenPath[5].setLine(1024,  750,    0,  750);
		chickenPath[6].setLine(  0,   270, 1024,  270);
		chickenPath[7].setLine(1024,  270,    0,  270);
		//End path definitions
		
		
	}
	
	
	public void draw(){
		if(spawned)
			sprite.draw(chickenBatch, chickenX, chickenY, currentFrame, rot, 1f);
	}

	/**
	 * Method that initializes the chicken on a defined MovePath and integer ID
	 * @param path
	 * @param ID
	 */
	@Override
	public void spawn(int pathID, int chickenID) {
		chickenStartTime = System.currentTimeMillis();
		if(pathID < 0){
			System.out.println("Chicken (ID "+chickenID+") determining path!");
			currentMovPath = rand.nextInt(Moves);
		}

		else if(pathID >= (chickenPath.length)){
			System.out.println("Error!. Chicken (ID "+chickenID+") movepath "+pathID+" is undefined. Canceling spawn!");
			return;
		}
		
		


		else if(pathID >= 0)
			currentMovPath = pathID;
		rot = (currentMovPath < 4) ? 0.0f : 90.0f;
		if( (currentMovPath % 2) == 0){
		  rot += 180.0;
		}

		chickenX = chickenPath[currentMovPath].getX(pathLoc);
		chickenY = chickenPath[currentMovPath].getY(pathLoc);
		spawned = true;
		System.out.println("Chicken (ID "+chickenID+") spawned on path "+currentMovPath+" at "+chickenX+","+chickenY+".");
		
		return;
		
	}

	/**
	 * Method that updates the state of the Chicken
	 */
	@Override
	public void update(float dTime) {
		frameTime += dTime;
		moveTime += dTime;
		
		if(frameTime > 0.4f){
			currentFrame++;
			frameTime = 0;
		}
		
		if(moveTime > 0.02f){
			if((pathLoc + pathStep) >= chickenPath[currentMovPath].getSize()){
				pathLoc = 0;
				if(!loop)
					deSpawn();
			}
			pathLoc += pathStep;
			chickenX = chickenPath[currentMovPath].getX(pathLoc);
			chickenY = chickenPath[currentMovPath].getY(pathLoc);
			if(pathLoc >= chickenPath[currentMovPath].getSize() -1 || ((chickenPath[currentMovPath].getX(pathLoc) < 0) || (chickenPath[currentMovPath].getY(pathLoc) < 0))){
				if(loop){
					pathLoc = 0;
					System.out.println("Chicken (ID "+chickenID+") looping!");
				}
				else{
					deSpawn();
				}
			}
			
			moveTime = 0;
		}
		
		if(currentFrame >= sprite.maxSprites())
			currentFrame = 0;
	}

	@Override
	public void deSpawn() {
		chickenX = 0;
		chickenY = 0;
		pathLoc = 0;
		spawned = false;
		chickenStartTime = 0;
		System.out.println("Chicken (ID "+chickenID+") Despawned!");
		
	}


	@Override
	public boolean isSpawned() {
		
		return spawned;
	}

	@Override
	public MovePath getMovePath() {
		
		return chickenPath[currentMovPath];
	}
	
	public int getPathLoc(){
		return pathLoc;
	}
	
	public int getID(){
		return chickenID;
	}
	
	// Returns the time the chicken has existed in milliseconds
	public double getTime(){
		if (spawned){
		return System.currentTimeMillis() - chickenStartTime;
		}else{
			return 0;
		}
		
	}
	
	

}
