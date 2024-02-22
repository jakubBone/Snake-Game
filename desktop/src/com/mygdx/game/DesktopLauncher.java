package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static java.lang.Thread.sleep;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("SnakeGame");
		config.setWindowedMode(800, 800);
		config.setForegroundFPS(60);
		config.useVsync(true);
		try {
			sleep(400);
		} catch(Exception e){
			e.getStackTrace();
		}
		new Lwjgl3Application(new GameScreen(), config);

	}
}