package screens;

import buttons.ButtonClickListener;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends MyScreen implements ApplicationListener  {
    private Music snakeWelcomeMusic;

    public void create() {
        setGameScreenDetails();
        image = new Texture("menuImage.png");

        snakeWelcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        snakeWelcomeMusic.setLooping(true);
        snakeWelcomeMusic.play();

        // PLAY button click handling
        playButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                //
            }
        });

        // EXIT button click handling
        exitButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                System.exit(0); // Exit the application when EXIT is clicked
            }
        });
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

        // Get touch mouse position
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);


        // Check if buttons are touched to apply transparency effect on them
        playButton.checkTouch(touchPos);
        exitButton.checkTouch(touchPos);

        // Handle mouse click
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            playButton.checkClick(touchPos);
            exitButton.checkClick(touchPos);
        }

        batch.end();
    }

    public void dispose() {
        image.dispose();
        snakeWelcomeMusic.dispose();
        batch.dispose();
        playButton.dispose();
    }
}