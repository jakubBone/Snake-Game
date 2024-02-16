package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen extends ScreenAdapter implements ApplicationListener {
    private Texture appleImage;
    private Array<Rectangle> apples;
    private long lastAppleTime;
    private Texture snakeHead;
    private Texture snakeBody;
    private Music snakeMoveSound;
    private Sound snakeGulpSound;
    private OrthographicCamera camera;
    private SpriteBatch batch;

        public void create() {
            appleImage = new Texture("apple.png");
            snakeHead = new Texture("snakeHead.png");
            snakeBody = new Texture("snakeBody.png");

            camera = new OrthographicCamera();
            camera.setToOrtho(false, 1200, 1200);

            batch = new SpriteBatch();

            snakeMoveSound = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
            snakeMoveSound.play();
            snakeMoveSound.setLooping(true);

            snakeGulpSound = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));

            apples = new Array<>();
            //locateApple();
        }

        public void render() {
            ScreenUtils.clear(0, 0, 0, 0);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            batch.draw(appleImage, 400, 400);
            locateApple();
            batch.draw(snakeHead, 300, 300);
            batch.draw(snakeBody, 300, 364);
            batch.draw(snakeBody, 300, 428);
            batch.end();

                Rectangle apple = new Rectangle();
                Rectangle snake = new Rectangle();

               if(apple.overlaps(snake)) {
                    snakeGulpSound.play();
                    locateApple();
                }
            }

    private void locateApple(){
        Rectangle apple = new Rectangle();
        apple.x = MathUtils.random(0, 800 - 64);
        apple.y = MathUtils.random(0, 800 - 64);
        apple.width = 64;
        apple.height = 64;
        apples.add(apple);
        lastAppleTime = TimeUtils.nanoTime();
    }
        public void dispose() {
            appleImage.dispose();
            snakeGulpSound.dispose();
            snakeMoveSound.dispose();
            batch.dispose();
        }
    }
