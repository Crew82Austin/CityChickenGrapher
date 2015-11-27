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
	private boolean manSpawn;
	private boolean indv;
	private float Scale;
	private double cSpawner;
	int cMob;
	float[] timers;
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
		cMob = 0;
		
		///////////////////////////Booleans
		grid = false;	//Draw Grid
		path = false;	//Draw Path
		paused = false;	//Update Movement
		manSpawn = false; //Call a manually spawning method instead of random MOB spawning
		indv = false;  //Draw cross-hairs and IDs for each MOB
		
		///////////////////////////
		chickens = new Chicken1[8];
		timers = new float[4];
		
		
		for(int c = 0; c < chickens.length; c++)
			chickens[c] = new Chicken1(batch, "Overhead Walking Chicken.png", 0, 64, false);
		
		lines = new ShapeRenderer(500);
		lines.setAutoShapeType(true);
		Scale = 100f;
		
		
		pDraw = new PathDrawer(batch);  
		tDraw = new TextDrawer(batch);
		if(manSpawn)
			mSpawnMovables();
		
		cSpawner = (3 + Math.random() * 50);
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
		////////////////////////////End Input / Begin MOBs
		if(!manSpawn && !paused)
			spawnMovables();
		if(!paused)
			updateMovables();
		if(indv)
			drawCross();
		//End MOBs
		
		
		
	}
	
	 
	public void mSpawnMovables(){	//Called when manSpawn = true instead of spawnMovables()
			//List manual MOB spawning commands here
			chickens[0].spawn(0, 0); 
			chickens[1].spawn(1, 5);
			chickens[2].spawn(2, 3);
			chickens[3].spawn(3, 4);
			chickens[4].spawn(4, 6);
			chickens[5].spawn(5, 2);
			chickens[6].spawn(6, 20);
			chickens[7].spawn(7, 503);
		
		return;
	}
	
	
	public  void spawnMovables(){
		timers[0] += Gdx.graphics.getRawDeltaTime();
		if(timers[0] > cSpawner){
			for(int e = 0; e < chickens.length; e++){
				if(!chickens[e].isSpawned()){
					chickens[e].spawn(-1, cMob);
					cMob++;
					clearTimer(0);
					cSpawner = (7 + Math.random() * 30);
					break;
				}
				clearTimer(0);
			}
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
		
		float cX ;
		float cY;
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

	public void drawCross(){
		int cX;
		int cY;
		batch.begin();
		tDraw.draw(30f, 1020f, "Crosshairs");
		batch.end();
		lines.setColor(0, 1f, 0, 1f);
		for(int f = 0; f < chickens.length; f++){
			cX = chickens[f].getMovePath().getX(chickens[f].getPathLoc());
			cY = chickens[f].getMovePath().getY(chickens[f].getPathLoc());
			if(chickens[f].isSpawned()){
				lines.begin();
				lines.line(0, cY  , Gdx.graphics.getWidth(), cY);
				lines.line(cX, 0, cX, Gdx.graphics.getHeight());
				lines.end();
				batch.begin();
				tDraw.draw(2f, cY + 2f, ""+cY);
				tDraw.draw(cX + 2f, 20f, ""+cX);
				tDraw.draw(cX +3f, cY + 3f, "ID="+chickens[f].getID());
				batch.end();
			}
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
		if(Gdx.input.isKeyJustPressed(Input.Keys.C))
			pDraw.clear();
		if(Gdx.input.isKeyJustPressed(Input.Keys.E))
			indv = !indv;
		if(Gdx.input.isKeyJustPressed(Input.Keys.P))
			paused = !paused;
		if(Gdx.input.isKeyJustPressed(Input.Keys.F1))
			path = !path;
		if(Gdx.input.isKeyJustPressed(Input.Keys.F2))
			pDraw.print();
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
	
	public void clearTimer(int timer){
		timers[timer] = 0;
		return;
	}
}
