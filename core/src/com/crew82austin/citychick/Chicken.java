package com.crew82austin.citychick;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.*;

public class Chicken {
	
	private int chickenX;
	private int chickenY;
	private int cFrame;
	private float fTime;
	private float mTime;
	private MovePath[] cPath;
	private SpriteSet sprite;
	private SpriteBatch cBatch;
	
	public Chicken(SpriteBatch batch, String imgFile, int x, int y, int frame, int size){
		sprite = new SpriteSet(imgFile, size, size);
		cBatch = batch;
		chickenX = x;
		chickenY = y;
		cFrame = frame;
		
		for(int a = 0; a < 4; a++){
			cPath[a] = new MovePath(1024, 1024);
		}
	}
	
	public void draw(){}

}
