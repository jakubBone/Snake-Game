package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class SnakeGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture welcomeImage;
	private Texture appleImage;

	private Music snakeWelcomeMusic;
	private Music snakeMoveMusic;
	private Sound snakeGulpSound;

	private OrthographicCamera camera;
	private Rectangle rectange;

	private Array<Rectangle> apples;
	private long lastAppleTime;


	@Override
	public void create () {
		welcomeImage = new Texture("apple.png");
		appleImage = new Texture("apple.png");

		camera = new OrthographicCamera();
		camera.setToOrtho(false,1200 , 1200);

		batch = new SpriteBatch();

		snakeWelcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("welcomeMusic.mp3"));
		snakeMoveMusic = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
		snakeGulpSound = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));
		snakeWelcomeMusic.setLooping(true);
		snakeWelcomeMusic.play();

		// WelcomeImage
		rectange = new Rectangle();
		rectange.x = 0;
		rectange.y = 20;
		rectange.height = 64;
		rectange.width = 64;

		apples = new Array<>();
		spawnLastApple();

	}

	//Rendering
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(welcomeImage, rectange.x, rectange.y);
		for(Rectangle apple: apples) {
			batch.draw(appleImage, apple.x, apple.y);
		}
		batch.end();

		// Touch
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			rectange.x = touchPos.x - 400;
			rectange.y = touchPos.y - 400;
		}

		// Press Key
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) rectange.x -= 800 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) rectange.x += 800 * Gdx.graphics.getDeltaTime();
		if(rectange.x < 0) rectange.x = 0;
		if(rectange.x > 800 - 400) rectange.x = 800 - 400;

		if(Gdx.input.isKeyPressed(Input.Keys.UP)) rectange.y += 800 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) rectange.y -= 800 * Gdx.graphics.getDeltaTime();
		if(rectange.y < 0) rectange.y = 0;
		if(rectange.y > 800 - 400) rectange.y = 800 - 400;

		// check if we need to create a new apple
		if(TimeUtils.nanoTime() - lastAppleTime > 1000000000) spawnLastApple();
		// move the apples, remove any that are beneath the bottom edge of
		// the screen or that hit the down placed apple. In the latter case we play back
		// a sound effect as well.
		for (Iterator<Rectangle> iter = apples.iterator(); iter.hasNext(); ) {
			Rectangle apple = iter.next();
			apple.y -= 64 * Gdx.graphics.getDeltaTime();
			if(apple.y + 64 < 0) iter.remove();

			if(apple.overlaps(rectange)) {
				snakeGulpSound.play();
				iter.remove();
			}
		}
	}
	// Placing the apple
	private void spawnLastApple(){
		Rectangle apple = new Rectangle();
		apple.x = MathUtils.random(0, 800 - 64);
		apple.y = 800;
		apple.width = 64;
		apple.height = 64;
		apples.add(apple);
		lastAppleTime = TimeUtils.nanoTime();
	}
	@Override
	public void dispose () {
		welcomeImage.dispose();
		appleImage.dispose();
		snakeGulpSound.dispose();
		snakeMoveMusic.dispose();
		batch.dispose();
	}
}

