package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter implements ApplicationListener {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Music snakeMoveSound;
    private Sound snakeGulpSound;
    private Apple apple;
    private Snake snake;
    private int direction;


        public void create() {
            snake = new Snake(new Texture("snakeHead.png"),new Texture("snakeBody.png"));
            apple = new Apple(new Texture("apple.png"));

            snakeGulpSound = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));
            snakeMoveSound = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
            snakeMoveSound.play();
            snakeMoveSound.setLooping(true);

            camera = new OrthographicCamera();
            camera.setToOrtho(false, 1200, 1200);

            batch = new SpriteBatch();

            direction = Input.Keys.RIGHT;

        }

        public void render() {
            ScreenUtils.clear(0, 0, 0, 0);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            handleInput();
            snake.move(direction, false);
            snake.checkAppleCollision(apple.getPosition(), apple);
            batch.begin();
            snake.drawHead(batch);
            snake.drawBody(batch, direction);
            apple.drawApple(batch);

            batch.end();
        }

        public void handleInput(){
            if(Gdx.input.isKeyPressed(Input.Keys.UP) )
                direction = Input.Keys.UP;
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) )
                direction = Input.Keys.DOWN;
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) )
                direction = Input.Keys.LEFT;
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) )
                direction = Input.Keys.RIGHT;

        }

        public void dispose() {
            snake.dispose();
            apple.dispose();
            batch.dispose();
            snakeGulpSound.dispose();
            snakeMoveSound.dispose();
        }
}
