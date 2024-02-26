package screen;

import button.ButtonClickListener;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen extends MyScreen implements ApplicationListener  {
    private Music gameOverSound;

    public void create() {

        setGameScreenDetails();

        image = new Texture("gameOverImage.png");
        gameOverSound = Gdx.audio.newMusic(Gdx.files.internal("gameOverSound.wav"));
        gameOverSound.play();

        handleGameOverButton();
    }

    public void render() {

        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(image, screen.x, screen.y);

        returnButton.render(batch);

        controlMouseCursonTouch();
        batch.end();
    }

    // "RETRUN TO MAIN MENU" button clicks handling
    public void handleGameOverButton(){
        // PLAY button click handling
        returnButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                // ---------------------> Need to restore the game from 1st Screen (MenuScreen)
            }
        });
    }
    public void pause() {
        Gdx.graphics.setContinuousRendering(false);
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
