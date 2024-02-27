package screen;

import button.ButtonClickListener;
import button.MyButton;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
    private Music gameOverSound;
    private MyButton returnButton;
    private Texture gameOverImage;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Game game;

    public GameOverScreen(Game aGame) {
        game = aGame;
        create();
    }

    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 768, 768);
        batch = new SpriteBatch();

        gameOverImage = new Texture("gameOverImage.png");
        gameOverSound = Gdx.audio.newMusic(Gdx.files.internal("gameOverSound.wav"));
        gameOverSound.play();

        returnButton = new MyButton("returnButton.png", 282, 50, 200, 70);

        handleGameOverButton();
    }

    /*public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(gameOverImage, 0, 0);

        returnButton.render(batch);

        controlMouseCursonTouch();
        batch.end();
    }*/

    // "RETRUN TO MAIN MENU" button clicks handling
    public void handleGameOverButton(){
        // PLAY button click handling
        returnButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                game.setScreen(new MenuScreen(game));
                // ---------------------> Need to restore the game from 1st Screen (MenuScreen)
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(gameOverImage, 0, 0);

        returnButton.render(batch);

        controlMouseCursonTouch();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    public void pause() {
        Gdx.graphics.setContinuousRendering(false);
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void controlMouseCursonTouch(){
        // Mouse cursor position controling
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);

        // Button transparency effect applying when they are touched
        returnButton.checkTouch(touchPos);

        // Mouse click hangling
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            returnButton.checkClick(touchPos);
        }
    }


    public void dispose() {
        gameOverImage.dispose();
        gameOverSound.dispose();
        batch.dispose();
        returnButton.dispose();
    }
}
