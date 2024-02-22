package screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen extends Screen implements ApplicationListener {
    private Music gameOverSound;

    public void create() {
        setScreenDetails();
        image = new Texture("gameOverImage.png");
        gameOverSound = Gdx.audio.newMusic(Gdx.files.internal("gameOverSound.wav"));
        gameOverSound.play();
    }

    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(image, screen.x, screen.y);
        batch.end();
    }

    public void dispose() {
        image.dispose();
        gameOverSound.dispose();
        batch.dispose();
    }
}
