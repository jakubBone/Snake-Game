package screen;
import com.badlogic.gdx.Game;
public class SnakeGame extends Game {

    public void create () {
        this.setScreen(new MenuScreen(this));
    }

    public void render () {
        super.render();
    }

    public void dispose () {

    }
}
