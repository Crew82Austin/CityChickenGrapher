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
	private float fTime;
	private float mTime;
	private boolean rev1;
	private boolean rev2;
	private boolean spawned;
	private boolean loop;
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
		loop = looped;
		cPath = new MovePath[4];
		rand =  new Random(System.nanoTime());
		for(int a = 0; a < 4; a++){
			cPath[a] = new MovePath(1024, 1024);
		}
		
		cPath[0].setLine(235, 1024, 235, 0);
		cPath[1].setLine(235, 0, 235, 1024);
		cPath[2].setLine(740, 1024, 740, 0);
		cPath[3].setLine(740, 0, 740, 1024);
		
		
		
	}
	
	public void determineMP(){
		System.out.println("Chicken1 determining MP!");
		cmPath = rand.nextInt(4);
		if( (cmPath % 2) == 0){
			rev2 = true;
			System.out.println("Chicken1 rev2 = "+rev2);
		}
		
	}
	
	public void draw(){
		if(spawned)
			sprite.draw(cBatch, chickenX, chickenY, cFrame, rev1, rev2, 1f);
	}

	@Override
	public void spawn(int path) {
		if(path >= 0){
			cmPath = path;
			if( (cmPath % 2) == 0){
				rev2 = true;
				System.out.println("Chicken1 rev2 = "+rev2);
			}
		}
		else if(path >= (cPath.length - 1)){
			System.out.println("Error!. Chicken1 movepath "+path+" is undefined. Canceling spawn!");
			return;
		}
		else if(path < 0)
			determineMP();
		
		chickenX = cPath[cmPath].getX(pathLoc);
		chickenY = cPath[cmPath].getY(pathLoc);
		spawned = true;
		System.out.println("Chicken1 spawned on path "+cmPath+" at "+chickenX+","+chickenY+".");
		
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
			pathLoc++;
			chickenX = cPath[cmPath].getX(pathLoc);
			chickenY = cPath[cmPath].getY(pathLoc);
			if(pathLoc >= cPath[cmPath].getSize() -1 || ((cPath[cmPath].getX(pathLoc) < 0) || (cPath[cmPath].getY(pathLoc) < 0))){
				if(loop){
					pathLoc = 0;
					System.out.println("Chicken1 looping!");
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
		System.out.println("Chicken1 Despawned!");
		
	}

	@Override
	public boolean isSpawned() {
		
		return spawned;
	}
	
	

}
