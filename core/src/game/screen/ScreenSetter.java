package game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.button.MyButton;

public class ScreenSetter implements Screen {
    protected Texture image;
    protected OrthographicCamera camera;
    protected ShapeRenderer shapeRenderer;
    protected SpriteBatch batch;
    protected  BitmapFont font;
    protected MyButton playButton;
    protected MyButton exitButton;
    protected MyButton returnButton;
    protected MyButton tryAgainButton;

    // The method is responsible for init basic elements required to Screen rending
    public void setBasicScreenDetails() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 768, 768);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        playButton = new MyButton("playButton.png", 100, 50, 200, 70);
        exitButton = new MyButton("exitButton.png", 488, 50, 200, 70);
        tryAgainButton = new MyButton("tryAgainButton.png", 100, 50, 200, 70);
        returnButton = new MyButton("returnButton.png", 282, 50, 200, 70);
        font = new BitmapFont();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
