package game;
import com.badlogic.gdx.Game;
import game.screen.MenuScreen;

public class SnakeGame extends Game {

    public void create () {
        this.setScreen(new MenuScreen(this));
    }

    public void render () {
        super.render();
    }

    public void dispose () {
        super.dispose();

    }
}
