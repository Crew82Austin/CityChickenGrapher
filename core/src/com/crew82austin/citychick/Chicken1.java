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
	private boolean rev1;
	private boolean rev2;
	private boolean spawned;
	private boolean loop;
    private int chickenID;
    private final int Moves;
	private MovePath[] chickenPath;
	private SpriteSet sprite;
	private SpriteBatch chickenBatch;
	private Random rand;
	
	public Chicken1(SpriteBatch batch, String imgFile, int frame, int size, boolean looped){
		sprite = new SpriteSet(imgFile, size, size);
		chickenBatch = batch;
		spawned = false;
		currentFrame = frame;
		rev1 = false;
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
		chickenPath[0].setLine(235, 1024, 235, 0);
		chickenPath[1].setLine(235, 0, 235, 1024);
		chickenPath[2].setLine(720, 1024, 720, 0);
		chickenPath[3].setLine(720, 0, 720, 1024);
		chickenPath[4].setLine(0, 740, 1024, 740);
		chickenPath[5].setLine(1024, 740, 0, 740);
		chickenPath[6].setLine(0, 250, 1024, 250);
		chickenPath[7].setLine(1024, 250, 0, 250);
		//End path definitions
		
		
	}
	
	
	public void draw(){
		if(spawned)
			sprite.draw(chickenBatch, chickenX, chickenY, currentFrame, rev1, rev2, 1f);
	}

	/**
	 * Method that initializes the chicken on a defined MovePath and integer ID
	 * @param path
	 * @param ID
	 */
	@Override
	public void spawn(int pathID, int chickenID) {
		
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

			if( (currentMovPath % 2) == 0){
				rev2 = true;
				System.out.println("Chicken (ID "+chickenID+") rev2 = "+rev2);
			}
			else{
				rev2 = false;
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
	
	

}
