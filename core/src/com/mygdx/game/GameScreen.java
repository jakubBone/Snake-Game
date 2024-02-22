package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen extends Screen implements ApplicationListener {
    private Music snakeMoveSound;
    private Sound snakeGulpSound;
    private Apple apple;
    private Snake snake;
    private int direction;
    private long lastMoveTime;

    public void create() {
            setScreenDetails();
            snake = new Snake(new Texture("snakeHead.png"),new Texture("snakeBody.png"));
            apple = new Apple(new Texture("apple.png"));

            snakeGulpSound = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));
            snakeMoveSound = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
            snakeMoveSound.play();
            snakeMoveSound.setLooping(true);

            direction = Input.Keys.RIGHT;
            lastMoveTime = TimeUtils.nanoTime();
        }

        public void render() {
            ScreenUtils.clear(0, 0, 0, 0);
            camera.update();

            batch.setProjectionMatrix(camera.combined);

            updateSnakeMovement();
            handleInput();
            batch.begin();

            snake.drawBody(batch);
            snake.drawHead(batch, direction);

            snake.checkAppleCollision(apple);
            apple.drawApple(batch);
            batch.end();

        }

        private void handleInput(){
                if (Gdx.input.isKeyPressed(Input.Keys.UP) && direction != Input.Keys.DOWN)
                    direction = Input.Keys.UP;
                else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && direction != Input.Keys.UP)
                    direction = Input.Keys.DOWN;
                else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && direction != Input.Keys.RIGHT)
                    direction = Input.Keys.LEFT;
                else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && direction != Input.Keys.LEFT)
                    direction = Input.Keys.RIGHT;
        }

        public void updateSnakeMovement() {
            long currentTime = TimeUtils.nanoTime();
            if (currentTime - lastMoveTime > Snake.velocity) {
                snake.move(direction);
                lastMoveTime = currentTime;
            }
        }

        public void dispose() {
            snake.dispose();
            apple.dispose();
            batch.dispose();
            snakeGulpSound.dispose();
            snakeMoveSound.dispose();
        }
}
