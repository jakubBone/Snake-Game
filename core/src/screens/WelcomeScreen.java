package screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class WelcomeScreen extends Screen implements ApplicationListener {
    private Music snakeWelcomeMusic;
    public void create() {
        setScreenDetails();

        image = new Texture("welcomeImage.png");

        snakeWelcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("welcomeMusic.mp3"));
        snakeWelcomeMusic.setLooping(true);
        snakeWelcomeMusic.play();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(image, screen.x, screen.y);
        batch.end();
    }

    public void dispose() {
        image.dispose();
        snakeWelcomeMusic.dispose();
        batch.dispose();
    }
}
