package screens;
import com.badlogic.gdx.Game;
public class ScreenManager extends Game {
    MenuScreen menuScreen;
    GameScreen gameScreen;
    GameOverScreen gameOverScreen;

    @Override
    public void create() {
        menuScreen = new MenuScreen();
        gameScreen = new GameScreen();
        gameOverScreen = new GameOverScreen();
        setScreen(new MenuScreen());
    }
}
