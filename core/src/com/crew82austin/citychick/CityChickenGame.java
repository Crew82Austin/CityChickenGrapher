package com.crew82austin.citychick;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CityChickenGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer lines;
	private boolean grid;
	private float Scale;
	int frame;
	SpriteSet chickens; 
	float frameTime;
	float chickenYT;
	int chickenX = 235;
	int chickenY = 0;
	PathDrawer pDraw;
	Table table;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Chicken Xing Wire 1.1.png");
		grid = true;
		lines = new ShapeRenderer(500);
		lines.setAutoShapeType(true);
		chickens = new SpriteSet("Overhead Walking Chicken.png", 64, 64);
		Scale = 100f;
		frame = 0;
		frameTime = 0;
		chickenYT = 0;
		pDraw = new PathDrawer();
		table = new Table();
		 
	}

	@Override
	public void render () {
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		chickens.draw(batch, chickenX, chickenY, frame, false, 1);
		pDraw.draw();
		batch.end();
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
			System.out.println("Exit by Escape!");
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			pDraw.mark(Gdx.input.getX(), Gdx.input.getY());
			//System.out.println(Gdx.input.getX()+"-"+Gdx.input.getY());
		}
		/*if(Gdx.input.isKeyButtonPressed(Input.Buttons.RIGHT)){
			table.draw(batch, 1f);
		}*/
		if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
			grid = !grid;
		if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
			pDraw.clear();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F2)){
			pDraw.print();
		}
		if(grid)
			drawGrid();
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			Scale += 10f;
					
			System.out.println("Scale = "+Scale);
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
			Scale -= 10f;
			
			if(Scale < 1)
				Scale = 1;
			if(((Scale % 10) != 0) && Scale < 10)
				Scale = 10;
			
			System.out.println("Scale = "+Scale);
		}
		
		if(frame >= chickens.maxSprites() - 1)
			frame = 0;
		frameTime += Gdx.graphics.getDeltaTime();
		chickenYT += Gdx.graphics.getDeltaTime();
		
		if(chickenYT > 0.02f){
			chickenY += 1;
			chickenYT = 0;
			//System.out.println("chickenY = "+chickenY);    Un-comment to display chicken y
		}
		if(frameTime >  0.5f){
			frame++;
			frameTime = 0;
		}
		
		if(chickenY > 1024)
			chickenY = 0;
		
	}
	
	public void drawGrid(){

		lines.setColor(0.02f, 1f, 0.98f, 1f);
		for(int a = 0; a < (Gdx.graphics.getWidth() / Scale); a++)
		{
			lines.begin();
			lines.line((float)(a * Scale), 0, (float)(a * Scale), Gdx.graphics.getHeight());
			lines.end();
		}
		
		for(int b = 0; b < (Gdx.graphics.getHeight() / Scale); b++)
		{
			lines.begin();
			lines.line(0, (float)(b * Scale), Gdx.graphics.getWidth(), (float)(b * Scale));
			lines.end();
		}
		
	}
	
	
}
