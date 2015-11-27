 package com.crew82austin.citychick;
 
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextDrawer {
	BitmapFont font;
	FileHandle file;
	SpriteBatch tBatch;
	
	public TextDrawer(SpriteBatch batch){
		font = new BitmapFont();
		tBatch = batch;
	}
	
	public TextDrawer(String fontFile, SpriteBatch batch){
		file = new FileHandle(fontFile);
		font = new BitmapFont(file);
		tBatch = batch;
	}
	
	public void draw(float x, float y, String text){
		
		font.draw(tBatch, text, x, y);
	}
}
