package com.crew82austin.intersection;
/**
 * Line on the intersection where cars stop.
 * Made as white rectangle
 * @author Justin
 *
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class StopLine{
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int width;
	private int height;
	private float originX;
	private float originY;
	private boolean horiz;
	Texture stopImg;
	SpriteBatch stopBatch;
	Sprite sprite;
	
	public StopLine (int X1, int Y1, int X2, int Y2, SpriteBatch batch, String img){
		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
		stopImg = new Texture(img);
		stopBatch = batch;
		horiz = false;
		
		if(x1 != x2){
			horiz = true;
			width = Math.abs(x2 - x1);
			height = 7;
			originX = 0f;
			originY = (float)height / 2;
		}
		if(!horiz){
			
			width = 7;
			height = Math.abs(y2 - y1);
			originX = (float)width / 2;
			originY = 0f;
		}
		//////////////////////Set up Sprite
		sprite = new Sprite(stopImg, 0, 0, width, height);
		sprite.setPosition(x1, y1);
		sprite.setOrigin(originX, originY);
		if(horiz)
			sprite.rotate(0.0f);
		/////////////////////Finish Sprite setup
		System.out.println("Constructed StopLine at "+x1+","+y1+"width = "+width+"height = "+height+
					"horiz = "+horiz+" using image "+img);
	}
	
	public void draw(){
		sprite.draw(stopBatch);
		//stopBatch.draw(stopImg, x1, y1, originX, originY, width, height, 1f, 1f, rotation, 0, 0, 20, 160, false, false);
		//stopBatch.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
		return;
	}
}
