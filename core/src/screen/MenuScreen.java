package screen;

import button.ButtonClickListener;
import button.MyButton;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    protected Texture menuImage;
    private Music snakeWelcomeMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private MyButton playButton;
    private MyButton exitButton;
    private Game game;

    public MenuScreen(Game aGame) {
        game = aGame;
        create();
    }
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 768, 768);
        batch = new SpriteBatch();

        menuImage = new Texture("menuImage.png");

        snakeWelcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        snakeWelcomeMusic.setLooping(true);
        snakeWelcomeMusic.play();

        playButton = new MyButton("playButton.png", 100, 50, 200, 70);
        exitButton = new MyButton("exitButton.png", 488, 50, 200, 70);

        handleMenuButtonsClick();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(menuImage, 0, 0);

        playButton.render(batch);
        exitButton.render(batch);

        controlMouseCursorTouch();

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    /*public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(menuImage, 0, 0);

        playButton.render(batch);
        exitButton.render(batch);

        controlMouseCursorTouch();

        batch.end();
    }*/

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    // "PLAY" and "EXIT" buttons clicks handling
    public void handleMenuButtonsClick(){
        playButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                snakeWelcomeMusic.stop();
                game.setScreen(new GameScreen(game)); // -----------------------------> Need to switch to 2nd GameScreen
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
        playButton.checkTouch(touchPos);
        exitButton.checkTouch(touchPos);

        // Mouse click hangling
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            playButton.checkClick(touchPos);
            exitButton.checkClick(touchPos);
        }
    }

    public void dispose() {
        menuImage.dispose();
        snakeWelcomeMusic.dispose();
        batch.dispose();
        playButton.dispose();
    }
}