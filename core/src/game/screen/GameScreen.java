package game.screen;

import game.button.ButtonClickListener;
import game.button.MyButton;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import game.model.Apple;
import game.model.Snake;

public class GameScreen extends ScreenSetter {
    private Music snakeMoveSound;
    private Sound snakeGulpSound;
    private Sound hitSound;
    private Texture tryAgainImage;
    private Apple apple;
    private Snake snake;
    private int direction;
    private long lastMoveTime;
    private Game game;

    public GameScreen(Game aGame) {
        game = aGame;
        create();
    }

    public void create() {
        setBasicScreenDetails();

        snake = new Snake(new Texture("snakeHead.png"), new Texture("snakeBody.png"));
        apple = new Apple(new Texture("apple.png"));
        tryAgainImage = new Texture("tryAgainImage.png");

        hitSound = Gdx.audio.newSound(Gdx.files.internal("hitSound.wav"));
        snakeGulpSound = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));
        snakeMoveSound = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
        snakeMoveSound.play();
        snakeMoveSound.setLooping(true);

        handleGameButtons();

        direction = Input.Keys.RIGHT;
        lastMoveTime = TimeUtils.nanoTime();
        Snake.attempts--;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        drawHeadUpZoneLine();

        batch.begin();
        updateSnakeMovement();
        handleInput();
        snake.drawBody(batch);
        snake.drawHead(batch, direction);
        snake.checkCollision(apple, snakeGulpSound, hitSound);
        apple.drawApple(batch);

        drawHeadUpZoneDetails();

        if (snake.ifCollisionDetected) {
            snakeMoveSound.pause();
            if(snake.attempts == 1){
                Snake.attempts = 4;
                game.setScreen(new GameOverScreen(game));
            }
            batch.draw(tryAgainImage, 0 , 0 );
            tryAgainButton.render(batch);
            exitButton.render(batch);
            controlMouseCursorTouch();
        }

        if (batch.isDrawing()) {
            batch.end();
        }
    }

    // Head-up zone with remaining attempts and snake's speed displayed
    private void drawHeadUpZoneDetails(){
        int iconWidth = snake.headTexture.getWidth();
        font.getData().setScale(1.1f);

        // Attempts icons displaying represented by snake's heads (1 attempt = 1 icon)
        for (int i = 0; i < snake.attempts; i++) {
            batch.draw(snake.headTexture,120  + (i * iconWidth ), 714, 32, 32);
            font.draw(batch, "Attemtps:", 30, 740);
            font.draw(batch, "Speed:  " + Snake.speed, 660, 740);
        }
    }

    // Line separating the playing zone from the head-up zone
    private void drawHeadUpZoneLine(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.line(0, 704, Gdx.graphics.getWidth(), 704);
        shapeRenderer.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && direction != Input.Keys.DOWN)
            direction = Input.Keys.UP;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && direction != Input.Keys.UP)
            direction = Input.Keys.DOWN;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && direction != Input.Keys.RIGHT)
            direction = Input.Keys.LEFT;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && direction != Input.Keys.LEFT)
            direction = Input.Keys.RIGHT;
    }

    // Snake's movement contol basedon the specified time interval
    // Expressed in nanoseconds and the current direction
    public void updateSnakeMovement() {
        long currentTime = TimeUtils.nanoTime();
        if (currentTime - lastMoveTime > Snake.movementIntervalTimeNano) {
            snake.move(direction);
            lastMoveTime = currentTime;
        }
    }

    // "TRY AGAIN" and "EXIT" buttons clicks handling
    public void handleGameButtons(){
        tryAgainButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                create(); // Restore the game
            }
        });
        exitButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                System.exit(0); // Exit game
            }
        });
    }

    public void controlMouseCursorTouch(){
        // Mouse cursor position controling
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);

        // Button transparency effect applying when they are touched
        tryAgainButton.checkTouch(touchPos);
        exitButton.checkTouch(touchPos);

        // Mouse click hangling
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            tryAgainButton.checkClick(touchPos);
            exitButton.checkClick(touchPos);
        }
    }

    public void dispose() {
        snake.dispose();
        apple.dispose();
        batch.dispose();
        snakeGulpSound.dispose();
        snakeMoveSound.dispose();
        tryAgainImage.dispose();
        shapeRenderer.dispose();
    }
}