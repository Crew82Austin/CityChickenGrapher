package com.crew82austin.citychick;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.*;
import java.util.Random;

public class Chicken1 implements Movable{
	
	private int chickenX;
	private int chickenY;
	private int pathLoc;
	private int cFrame;
	private int cmPath;
	private int pathStep;
	private float fTime;
	private float mTime;
	private boolean rev1;
	private boolean rev2;
	private boolean spawned;
	private boolean loop;
    private int cID;
    private final int MOVES;
	private MovePath[] cPath;
	private SpriteSet sprite;
	private SpriteBatch cBatch;
	private Random rand;
	
	public Chicken1(SpriteBatch batch, String imgFile, int frame, int size, boolean looped){
		sprite = new SpriteSet(imgFile, size, size);
		cBatch = batch;
		spawned = false;
		cFrame = frame;
		rev1 = false;
		pathLoc = 0;
		pathStep = 1;
		loop = looped;
		MOVES = 8;
		cPath = new MovePath[MOVES];
		rand =  new Random(System.nanoTime());
		for(int a = 0; a < MOVES; a++){
			cPath[a] = new MovePath(1024, 1024);
		}
		
		cPath[0].setLine(235, 1024, 235, 0);
		cPath[1].setLine(235, 0, 235, 1024);
		cPath[2].setLine(720, 1024, 720, 0);
		cPath[3].setLine(720, 0, 720, 1024);
		cPath[4].setLine(0, 740, 1024, 740);
		cPath[5].setLine(1024, 740, 0, 740);
		cPath[6].setLine(0, 250, 1024, 250);
		cPath[7].setLine(1024, 250, 0, 250);
		
		
		
	}
	
	public void determineMP(){
		System.out.println("Chicken (ID "+cID+") determining MP!");
		cmPath = rand.nextInt(MOVES);
		if( (cmPath % 2) == 0){
			rev2 = true;
			System.out.println("Chicken (ID "+cID+") rev2 = "+rev2);
		}
		else{
			rev2 = false;
		}
		
	}
	
	public void draw(){
		if(spawned)
			sprite.draw(cBatch, chickenX, chickenY, cFrame, rev1, rev2, 1f);
	}

	@Override
	public void spawn(int path, int ID) {
		cID = ID;
		
		if(path >= 0){
			cmPath = path;
			if( (cmPath % 2) == 0){
				rev2 = true;
				System.out.println("Chicken (ID "+cID+") rev2 = "+rev2);
			}
			else{
				rev2 = false;
			}
		}
		else if(path >= (cPath.length)){
			System.out.println("Error!. Chicken (ID "+cID+") movepath "+path+" is undefined. Canceling spawn!");
			return;
		}
		else if(path < 0)
			determineMP();
		
		chickenX = cPath[cmPath].getX(pathLoc);
		chickenY = cPath[cmPath].getY(pathLoc);
		spawned = true;
		System.out.println("Chicken (ID "+cID+")spawned on path "+cmPath+" at "+chickenX+","+chickenY+".");
		
		return;
		
	}

	@Override
	public void update(float dTime) {
		fTime += dTime;
		mTime += dTime;
		
		if(fTime > 0.4f){
			cFrame++;
			fTime = 0;
		}
		
		if(mTime > 0.02f){
			if((pathLoc + pathStep) >= cPath[cmPath].getSize()){
				pathLoc = 0;
				if(!loop)
					deSpawn();
			}
			pathLoc += pathStep;
			chickenX = cPath[cmPath].getX(pathLoc);
			chickenY = cPath[cmPath].getY(pathLoc);
			if(pathLoc >= cPath[cmPath].getSize() -1 || ((cPath[cmPath].getX(pathLoc) < 0) || (cPath[cmPath].getY(pathLoc) < 0))){
				if(loop){
					pathLoc = 0;
					System.out.println("Chicken (ID "+cID+") looping!");
				}
				else{
					deSpawn();
				}
			}
			
			mTime = 0;
		}
		
		if(cFrame >= sprite.maxSprites())
			cFrame = 0;
	}

	@Override
	public void deSpawn() {
		chickenX = 0;
		chickenY = 0;
		pathLoc = 0;
		spawned = false;
		System.out.println("Chicken (ID "+cID+") Despawned!");
		
	}

	@Override
	public boolean isSpawned() {
		
		return spawned;
	}

	@Override
	public MovePath getMovePath() {
		
		return cPath[cmPath];
	}
	
	public int getPathLoc(){
		return pathLoc;
	}
	
	public int getID(){
		return cID;
	}
	
	

}
