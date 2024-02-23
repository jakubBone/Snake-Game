package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Screen extends ScreenAdapter  {
    protected Texture image;
    protected OrthographicCamera camera;
    Vector3 touchPos;
    protected Rectangle screen;
    protected SpriteBatch batch;
    protected int screenSize;
    protected int cellSize;
    protected int cellsNumber;
    Button playButton;
    Button exitButton;
    Button returnButton;
    Button tryAgainButton;

    public void setScreenDetails(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 768, 768);

        playButton = new Button("playButton.png", 100, 50, 200, 70 );
        exitButton = new Button("exitButton.png", 488, 50, 200, 70 );
        //tryAgainButton = new Button("tryAgainButton", 488, 50, 200, 70);
        //returnButton = new Button("returnButton", 488, 50, 200, 70 );

        screen = new Rectangle();
        batch = new SpriteBatch();

        screenSize = 768;
        cellSize = 64;

        cellsNumber = screenSize / cellSize;
    }
}
