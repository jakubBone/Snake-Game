package screens;

import buttons.ButtonClickListener;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen extends MyScreen implements ApplicationListener {
    private Music gameOverSound;
    public void create() {
        setScreenDetails();
        image = new Texture("gameOverImage.png");
        gameOverSound = Gdx.audio.newMusic(Gdx.files.internal("gameOverSound.wav"));
        gameOverSound.play();

        // PLAY button click handling
        returnButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                // ----> Restore the game from 1st Screen (MenuScreen) <-----
            }
        });
    }

    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(image, screen.x, screen.y);

        returnButton.render(batch);

        // Get touch mouse position
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);

        returnButton.checkTouch(touchPos);

        // Handle mouse click
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            returnButton.checkClick(touchPos);
        }
        batch.end();
    }

    public void dispose() {
        image.dispose();
        gameOverSound.dispose();
        batch.dispose();
        returnButton.dispose();
    }
}
