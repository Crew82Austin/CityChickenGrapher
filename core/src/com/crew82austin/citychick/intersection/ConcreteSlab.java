package com.crew82austin.citychick.intersection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ConcreteSlab {
	
	private int concreteX;
	private int concreteY;
	private int concreteWidth;
	private int concreteHeight;
	Texture concreteImg;
	Sprite sprite;
	SpriteBatch concreteBatch;
	
	public ConcreteSlab(int x, int y, int width, int height, SpriteBatch batch, String img){
		concreteX = x;
		concreteY = y;
		concreteWidth = width;
		concreteHeight = height;
		concreteBatch = batch;
		concreteImg = new Texture(img);
		
		sprite = new Sprite(concreteImg, 0, 0, concreteWidth, concreteHeight);
		sprite.setPosition((float)concreteX, (float)concreteY);
		System.out.println("Spawned building at "+concreteX+","+concreteY+" using image "+img);
	}
	
	public void draw(){
		 sprite.draw(concreteBatch);
		 
		 return;
	}

}
