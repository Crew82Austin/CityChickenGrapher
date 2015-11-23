package com.crew82austin.citychick.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.crew82austin.citychick.CityChickenGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1024;
		config.width = 1024;
		config.resizable = false;
		new LwjglApplication(new CityChickenGame(), config);
	}
}
