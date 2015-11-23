package com.crew82austin.citychick;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteSet {

	protected Texture img;
	protected int rows = 0;
	protected int columns = 0;
	protected int spritesTotal = 0;
	protected int tileWidth = 0;
	protected int tileHeight = 0;
	
	public SpriteSet(String resource, int width, int height){
		img = new Texture(resource);
		tileWidth = width;
		tileHeight = height;
		rows = img.getHeight() / tileHeight;
		columns = img.getWidth() / tileWidth;  
		spritesTotal = (img.getHeight() / tileHeight) * tileWidth;
	}
	
	public void draw(SpriteBatch batch, int x, int y, int frame, boolean reversed, float scale){
		int imgX = (frame % columns) * tileWidth;
		int imgY = (frame / columns) * tileHeight;
		batch.draw(img, x, y, tileWidth * scale, tileHeight * scale, imgX, imgY, tileWidth , tileHeight, reversed, false);
		
	}
	
	public final int maxSprites(){
		return rows * columns;
	}
}
	