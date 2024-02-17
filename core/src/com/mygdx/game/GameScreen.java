package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter implements ApplicationListener {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    Apple apple;
    Snake snake;

        public void create() {
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 1200, 1200);
            batch = new SpriteBatch();
            apple = new Apple();
            snake = new Snake();
        }

        public void render() {
            ScreenUtils.clear(0, 0, 0, 0);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            apple.drawApple(batch);
            snake.drawSnake(batch);
            batch.end();

                Rectangle appleRectangle = new Rectangle();
                Rectangle snakeRectangle = new Rectangle();

               if(appleRectangle.overlaps(snakeRectangle)) {
                    snake.playSnakeGulp();
                    apple.placeApple();
                }
            }
        public void dispose() {

        }
    }
