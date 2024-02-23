package screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends Screen implements ApplicationListener {
    private Music snakeWelcomeMusic;
    public void create() {
        setScreenDetails();
        image = new Texture("menuImage.png");

        snakeWelcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
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
        playButton.render(batch);
        exitButton.render(batch);

        batch.end();

        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        playButton.checkTouch(touchPos);
        exitButton.checkTouch(touchPos);
    }

    public void dispose() {
        image.dispose();
        snakeWelcomeMusic.dispose();
        batch.dispose();
        playButton.dispose();
    }
}