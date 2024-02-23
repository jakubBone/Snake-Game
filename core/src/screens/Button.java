package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Button {
    private Texture buttonTexture;
    private Rectangle bounds;
    private boolean isPressed;
    private boolean isTransparent;
    private static final Color NORMAL_COLOR = new Color(1, 1, 1, 1);
    private static final Color TRANSPARENT_COLOR = new Color(1, 1, 1, 0.5f);

    public Button(String texturePath, float x, float y, float width, float height) {
        buttonTexture = new Texture(Gdx.files.internal(texturePath));
        bounds = new Rectangle(x, y, width, height);
        isPressed = false;
        isTransparent = false;
    }

    public void render(SpriteBatch batch) {
        if (isTransparent) {
            batch.setColor(TRANSPARENT_COLOR);
        } else {
            batch.setColor(NORMAL_COLOR);
        }

        batch.draw(buttonTexture, bounds.x, bounds.y, bounds.width, bounds.height);
        batch.setColor(NORMAL_COLOR); // Reset color to avoid affecting subsequent rendering
    }

    public void checkTouch(Vector3 touchPos) {
        if (isTouched(touchPos)) {
            setTransparent(true);
        } else {
            setTransparent(false);
        }
    }

    public boolean isTouched(Vector3 touchPos) {
        return bounds.contains(touchPos.x, touchPos.y);
    }

    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
    }


    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void dispose() {
        buttonTexture.dispose();
    }
}