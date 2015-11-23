package com.crew82austin.citychick;

import java.io.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
public class PathDrawer {

	
	ShapeRenderer colorPath;
	int pathLoc = 0;
	MovePath path;
	
	public PathDrawer(){
		colorPath = new ShapeRenderer(1000);
		colorPath.setAutoShapeType(true);
		path = new MovePath(1024, 1024);
	}
	
	/*public void test(){
		try{
		FileOutputStream stream = new FileOutputStream("Paths.txt");
		
		
		PrintStream out = new PrintStream(stream);
		System.setOut(out);
		
		
		System.out.print("Hello There!");
		out.close();
		}
		catch(FileNotFoundException e){}
		return;
		
	}*/   //Testing for File output
	
	public void mark(int x, int y){
		y = Gdx.graphics.getHeight() - y;
		path.setPoint(pathLoc, x, y);
		pathLoc++;
	}
	
	public void clear(){
		path.clear();
		pathLoc = 0;
		return;
	}
	
	public void print(){
		path.print();
	}
	public void draw(){
	
		colorPath.begin();
		for(int a = 0; a < pathLoc; a++){
			colorPath.point((float)path.getX(a), (float)path.getY(a), 0);
		}
		colorPath.end();
		
	}
}
