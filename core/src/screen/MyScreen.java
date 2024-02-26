package screen;

import button.MyButton;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyScreen extends ScreenAdapter implements Screen {
    protected Texture image;
    protected OrthographicCamera camera;
    protected Rectangle screen;
    protected SpriteBatch batch;
    protected int screenSize;
    protected int cellSize;
    protected int cellsNumber;
    protected MyButton playButton;
    protected MyButton exitButton;
    protected MyButton returnButton;
    protected MyButton tryAgainButton;

    public void setGameScreenDetails() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 768, 768);

        playButton = new MyButton("playButton.png", 100, 50, 200, 70);
        exitButton = new MyButton("exitButton.png", 488, 50, 200, 70);
        tryAgainButton = new MyButton("tryAgainButton.png", 100, 50, 200, 70);
        returnButton = new MyButton("returnButton.png", 282, 50, 200, 70);

        batch = new SpriteBatch();

        screen = new Rectangle();
        screenSize = 768;
        cellSize = 64;
        cellsNumber = screenSize / cellSize;
    }
}

