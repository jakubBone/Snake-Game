package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class WelcomeScreen extends ScreenAdapter implements ApplicationListener {
    private Texture welcomeImage;
    private Music snakeWelcomeMusic;
    private OrthographicCamera camera;
    private Rectangle screen;
    private SpriteBatch batch;

    public void create() {
        welcomeImage = new Texture("welcomeImage.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        screen = new Rectangle();
        batch = new SpriteBatch();

        snakeWelcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("welcomeMusic.mp3"));
        snakeWelcomeMusic.setLooping(true);
        snakeWelcomeMusic.play();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(welcomeImage, screen.x, screen.y);
        batch.end();
    }

    public void dispose() {
        welcomeImage.dispose();
        snakeWelcomeMusic.dispose();
        batch.dispose();
    }
}
