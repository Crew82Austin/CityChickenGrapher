package com.crew82austin.intersection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Building {
	private int buildingX;
	private int buildingY;
	private int buildingWidth;
	private int buildingHeight;
	Texture buildingImg;
	Sprite sprite;
	SpriteBatch buildingBatch;
	
	public Building(int x, int y, int width, int height, SpriteBatch batch, String img){
		buildingX = x;
		buildingY = y;
		buildingWidth = width;
		buildingHeight = height;
		buildingBatch = batch;
		buildingImg = new Texture(img);
		
		sprite = new Sprite(buildingImg, 0, 0, buildingWidth, buildingHeight);
		sprite.setPosition((float)buildingX, (float)buildingY);
		System.out.println("Spawned building at "+buildingX+","+buildingY+" using image "+img);
	}
	
	public void draw(){
		 sprite.draw(buildingBatch);
		 
		 return;
	}
}
