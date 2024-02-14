package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SnakeGame extends ApplicationAdapter {

	private Music snakeMove;
	private Sound snakeGulp;
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		snakeMove = Gdx.audio.newMusic(Gdx.files.internal("snakeMove.mp3"));
		snakeGulp = Gdx.audio.newSound(Gdx.files.internal("snakeGulp.wav"));

		snakeMove.setLooping(true);
		snakeMove.play();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
