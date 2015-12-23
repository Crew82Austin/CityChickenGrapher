package com.crew82austin.intersection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CrossWalk {

	private int crossX;
	private int crossY;
	private int width;
	private int height;
	Sprite sprite;
	private float crossRotation;
	Texture crossImg;
	SpriteBatch crossBatch;
	
	public CrossWalk(int x, int y, int length, float rotation, SpriteBatch batch, String img){
		
		crossX = x;
		crossY = y;
		crossBatch = batch;
		crossImg = new Texture(img);
		width = 40;
		height = length;
		crossRotation = rotation;
		
		sprite = new Sprite(crossImg, 0, 0, width, height);
		sprite.setPosition(x, y);
		sprite.setRotation(crossRotation);
		sprite.setOrigin(0, 0);
		                      
		System.out.println("Spawned crosswalk at "+x+","+y+" with length "+length+" and rotation "+
					crossRotation+" using image "+img);
	}
	
	public void draw(){
		sprite.draw(crossBatch);
		
		return;
	}
	
}
