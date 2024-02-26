package screen;

import button.ButtonClickListener;
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

        handleMenuButtonsClick();
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

        controlMouseCursorTouch();

        batch.end();
    }

    // "PLAY" and "EXIT" buttons clicks handling
    public void handleMenuButtonsClick(){
        playButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick() {
                Gdx.app.exit(); // -----------------------------> Need to switch to 2nd GameScreen
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

    @Override
    public void hide() {
        super.hide();
    }

    public void dispose() {
        image.dispose();
        snakeWelcomeMusic.dispose();
        batch.dispose();
        playButton.dispose();
    }
}