package com.crew82austin.citychick.intersection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.crew82austin.citychick.*;

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
	
	/**
	 * Get a MovePath representing a the center of the crosswalk
	 * @param boolean reverse
	 * @return MovePath
	 */
	public MovePath getCenterLineMove(boolean reverse){
		MovePath path = new MovePath(2000, 2000);
		float rotRad = (crossRotation - 180f)* ((float)Math.PI/180);
		double xDiff = Math.cos(rotRad) * height;
		double yDiff = Math.sin(rotRad) * height;
		int xStart = crossX + (width / 2);
		
		if(!reverse )
			path.setLine(xStart, crossY, xStart + (int)xDiff, crossY + (int)yDiff);
		else if (reverse)
			path.setLine(xStart + (int)xDiff, crossY + (int)yDiff, xStart, crossY);
		
		System.out.println("(CrossWalk) returning MovePath rotRad = "+rotRad+" xDiff/yDiff "+xDiff+"/"
				+yDiff+" xStart = "+xStart+" reversed "+reverse);
		
		return path;
		
	}
	
}
