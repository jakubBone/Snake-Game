package screens;

import buttons.ButtonClickListener;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import objects.Apple;
import objects.Snake;

public class GameScreen extends Screen implements ApplicationListener {
    private Music snakeMoveSound;
    private Sound snakeGulpSound;
    private Sound gameOverSound;
    private Apple apple;
    private Snake snake;
    private int direction;
    private long lastMoveTime;
    private Texture tryAgainImage;
    public static int attempts = 3;

    public void create() {
        System.out.println(attempts);
        attempts--;
        setScreenDetails();
        snake = new Snake(new Texture("snakeHead.png"), new Texture("snakeBody.png"));
        apple = new Apple(new Texture("apple.png"));
        tryAgainImage = new Texture("tryAgainImage.png");

        snakeGulpSound = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("gameOverSound.wav"));
        snakeMoveSound = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
        snakeMoveSound.play();
        snakeMoveSound.setLooping(true);

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

        batch.begin();
        updateSnakeMovement();
        handleInput();

        snake.drawBody(batch);
        snake.drawHead(batch, direction);
        snake.checkCollision(apple, snakeGulpSound);
        apple.drawApple(batch);

        if (snake.ifCollisionDetected) {
            if(attempts == 0){
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
        tryAgainImage.dispose();
    }
}