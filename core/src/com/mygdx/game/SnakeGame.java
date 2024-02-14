package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class SnakeGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture welcomeImage;
	private Music snakeWelcome;
	private Music snakeMove;
	private Sound snakeGulp;
	private OrthographicCamera camera;
	private Rectangle rectange;
	
	@Override
	public void create () {
		welcomeImage = new Texture("welcomeImage.png");

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);

		batch = new SpriteBatch();

		snakeWelcome = Gdx.audio.newMusic(Gdx.files.internal("welcomeMusic.mp3"));
		snakeMove = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
		snakeGulp = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));
		snakeWelcome.setLooping(true);
		snakeWelcome.play();


		rectange = new Rectangle();
		rectange.x = 800 / 2 - 64 / 2;
		rectange.y = 20;
		rectange.height = 64;
		rectange.width = 64;

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(welcomeImage, rectange.x, rectange.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		welcomeImage.dispose();
	}
}
