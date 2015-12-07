 package com.crew82austin.citychick;
 
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.File;

public class TextDrawer {
	BitmapFont font;
	FileHandle fileH;
	SpriteBatch tBatch;
	File file;
	public TextDrawer(SpriteBatch batch){
		font = new BitmapFont();
		tBatch = batch;
	}
	
	public TextDrawer(String fontFile, SpriteBatch batch){
		file = new File(fontFile);
		fileH= new FileHandle(file);
		font = new BitmapFont(fileH);
		tBatch = batch;
	}
	
	public void draw(float x, float y, String text){
		
		font.draw(tBatch, text, x, y);
	
	}
}
