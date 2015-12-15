package com.crew82austin.intersection;
/**
 * Line on the intersection where cars stop.
 * Made as white rectangle
 * @author Justin
 *
 */
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class StopLine{
	
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private boolean horiz;
	Texture img;
	SpriteBatch stopBatch;
	
	public StopLine (float X1, float Y1, float X2, float Y2, SpriteBatch batch){
		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
		img = new Texture("stopline.png");
		stopBatch = batch;
		horiz = true;
		if(x1 != x2)
			horiz = false;
			
	}
	
	public void draw(){
		float rotation = 0f;
		if(horiz)
			rotation = 90f;
		
		stopBatch.draw(img, x1, y1, 0f, 0f, (float)Math.abs(x2 - x1), (float)Math.abs(y2 - y1), 1f, 1f, rotation, 0, 0, 20, 160, false, false);
		
		return;
	}
}
