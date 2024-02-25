package screens;

import buttons.ButtonClickListener;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import objects.Apple;
import objects.Snake;

public class GameScreen extends MyScreen implements ApplicationListener {
    private Music snakeMoveSound;
    private Sound snakeGulpSound;

    private Sound hitSound;
    private ShapeRenderer shapeRenderer;
    private Texture tryAgainImage;
    private BitmapFont font;

    private Apple apple;
    private Snake snake;

    private int direction;
    private long lastMoveTime;

    public void create() {
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        snake = new Snake(new Texture("snakeHead.png"), new Texture("snakeBody.png"));
        apple = new Apple(new Texture("apple.png"));
        tryAgainImage = new Texture("tryAgainImage.png");

        hitSound = Gdx.audio.newSound(Gdx.files.internal("hitSound.wav"));
        snakeGulpSound = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));
        snakeMoveSound = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
        snakeMoveSound.play();
        snakeMoveSound.setLooping(true);

        Snake.attempts--;
        setGameScreenDetails();

        // TRY AGAIN button click handling
        tryAgainButton.setClickListener(new ButtonClickListener() {

            @Override
            public void onClick() {
                create(); // Restore the game when TRY AGAIN button is clicked
            }
        });

        // EXIT button click handling
        exitButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                System.exit(0); // Exit the application when EXIT button is clicked
            }
        });

        direction = Input.Keys.RIGHT;
        lastMoveTime = TimeUtils.nanoTime();
    }

    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        drawBorderLine();

        batch.begin();
        updateSnakeMovement();
        handleInput();
        snake.drawBody(batch);
        snake.drawHead(batch, direction);
        snake.checkCollision(apple, snakeGulpSound, hitSound);
        apple.drawApple(batch);

        drawAttemptsDetails();


        if (snake.ifCollisionDetected) {
            if(snake.attempts == 1){
                Gdx.app.exit();
            }
            batch.draw(tryAgainImage, screen.x, screen.y);
            tryAgainButton.render(batch);
            exitButton.render(batch);

            // Get touch mouse position
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            // Check if buttons are touched to apply transparency effect on them
            tryAgainButton.checkTouch(touchPos);
            exitButton.checkTouch(touchPos);
            // Handle mouse click
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                tryAgainButton.checkClick(touchPos);
                exitButton.checkClick(touchPos);
            }
        }
            if (batch.isDrawing()) {
                batch.end();
            }
        }
    private void drawBorderLine(){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.line(0, 704, Gdx.graphics.getWidth(), 704);
            shapeRenderer.end();
    }
    private void drawAttemptsDetails(){
        int iconWidth = snake.headTexture.getWidth();
        font.getData().setScale(1.1f);

        // Rysuj ikony węża w pętli
        for (int i = 0; i < snake.attempts; i++) {
            batch.draw(snake.headTexture,120  + (i * iconWidth ), 714, 32, 32);
            font.draw(batch, "Attemtps:", 30, 740);
            font.draw(batch, "Velocity:  " + Snake.velocity, 660, 740);
        }
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

    public void updateSnakeMovement() {
        long currentTime = TimeUtils.nanoTime();
        if (currentTime - lastMoveTime > Snake.movementIntervalTimeNano) {
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
        tryAgainImage.dispose();
        shapeRenderer.dispose();
    }
}