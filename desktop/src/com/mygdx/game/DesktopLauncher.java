package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import screen.GameOverScreen;
import screen.GameScreen;
import screen.MenuScreen;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("SnakeGame");
		config.setWindowedMode(768, 768);
		config.setForegroundFPS(60);
		config.useVsync(true);


		/*MenuScreen menuScreen = new MenuScreen();
		GameScreen gameScreen = new GameScreen();
		GameOverScreen gameOverScreen = new GameOverScreen();*/



			//new Lwjgl3Application(new MenuScreen(), config);
			//new Lwjgl3Application(new GameScreen(), config);
			new Lwjgl3Application(new GameOverScreen(), config);

	}
}