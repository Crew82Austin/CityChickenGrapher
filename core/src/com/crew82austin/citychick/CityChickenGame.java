package com.crew82austin.citychick;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class CityChickenGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer lines;
	private boolean grid;
	private boolean path;
	private boolean paused;
	private float Scale;
	int frame; 
	float frameTime;
	float chickenYT;
	int chickenX = 235;
	int chickenY = 0;
	PathDrawer pDraw;
	TextDrawer tDraw;
	Chicken1[] chickens;
	/*SelectBox rMen;
	Object[] rMenItems = new Object[20]; //Stuff for a menu
	Skin skin;*/
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Chicken Xing Wire 1.1.png");
		
		grid = false;
		path = false;
		paused = false;
		
		//chickens = new Object();
		chickens = new Chicken1[4];
		for(int c = 0; c < chickens.length; c++)
			chickens[c] = new Chicken1(batch, "Overhead Walking Chicken.png", 0, 64, false);
		lines = new ShapeRenderer(500);
		lines.setAutoShapeType(true);
		Scale = 100f;
		frame = 0;
		frameTime = 0;
		chickenYT = 0;
		
		pDraw = new PathDrawer(batch);
		tDraw = new TextDrawer(batch);
		
		spawnMovables();
		/*skin = new Skin();
		
		rMen = new SelectBox(new SelectBox.SelectBoxStyle(font, Color.BLACK, Drawable.class, ));
		 rMenItems[0] = new Label("Hello", skin);
		 rMen.setItems(rMenItems);*/ //Stuff For a Menu
	}

	@Override
	public void render () {
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		///////////////////////////////Begin Draw
		batch.begin();
		batch.draw(img, 0, 0);
		drawMovables();
		if(path)
			pDraw.draw();
		if(grid)
			tDraw.draw(900f, 1014f, "Scale = "+Scale);
		if(paused)
			tDraw.draw(0, 1014f, "Paused");
		batch.end();
		
		/////////////////////////////End Draw / Begin Input
		handleInput();
		////////////////////////////End Input
		updateMovables();
		
		/*if(frame >= chickens.maxSprites() - 1)
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
			chickenY = 0;*/
		
	}
	
	 
	public void spawnMovables(){
		for(int e = 0; e < chickens.length; e++){
			if(!chickens[e].isSpawned())
				chickens[e].spawn(-1);
		}
			
		return;
	}
	
	
	public void drawMovables(){
		for(int e = 0; e < chickens.length; e++){
			if(chickens[e].isSpawned())
				chickens[e].draw();
		}
	}
	
	public void updateMovables(){
		for(int d = 0; d < chickens.length; d++){
			if(chickens[d].isSpawned())
				chickens[d].update(Gdx.graphics.getRawDeltaTime());
		}
		return;
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

	public void handleInput(){

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
			System.out.println("Exit by Escape!");
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			pDraw.mark(Gdx.input.getX(), Gdx.input.getY());
			//System.out.println(Gdx.input.getX()+"-"+Gdx.input.getY());
		}
	/*	if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
			rMen.draw(batch, 1f);	//Stuff for a menu
		}*/
		if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
			grid = !grid;
		if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
			pDraw.clear();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.P))
			paused = !paused;
		if(Gdx.input.isKeyJustPressed(Input.Keys.F1))
			path = !path;
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
	}
	
}
