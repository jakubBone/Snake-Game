package game.screen;

import game.button.ButtonClickListener;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen extends ScreenSetter  {
    private Music gameOverSound;
    private Game game;

    public GameOverScreen(Game aGame) {
        game = aGame;
        create();
    }

    public void create() {
        setBasicScreenDetails();

        image = new Texture("gameOverImage.png");

        gameOverSound = Gdx.audio.newMusic(Gdx.files.internal("gameOverSound.wav"));
        gameOverSound.play();

        handleGameOverButton();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(image, 0, 0);
        returnButton.render(batch);
        controlMouseCursonTouch();

        batch.end();
    }

    // "RETRUN TO MAIN MENU" button clicks handling
    public void handleGameOverButton(){
        returnButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                game.setScreen(new MenuScreen(game)); // Return to MenuScreen

            }
        });
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
        image.dispose();
        gameOverSound.dispose();
        batch.dispose();
        returnButton.dispose();
    }
}
