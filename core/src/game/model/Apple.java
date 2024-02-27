package game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Apple {

    private Texture appleTexture;
    private Vector2 applePosition;
    private int gridSize;

    public Apple(Texture appleTexture, ArrayList<Vector2> bodyParts){

        this.appleTexture = appleTexture;
        gridSize = 64;
        applePosition = dropApple(bodyParts);

    }

    public void drawApple(SpriteBatch batch){
        batch.draw(appleTexture, applePosition.x, applePosition.y);
    }

    public Vector2 getPos(){
        return applePosition;
    }

    public void respawn(ArrayList<Vector2> bodyParts){
            applePosition = dropApple(bodyParts);
    }

    // The method randomizes apple position until it's out of the snake body
    public Vector2 dropApple(ArrayList<Vector2> bodyParts) {
        float randomGridX, randomGridY;
        int applePosX, applePosY;

        do {
            randomGridX = MathUtils.random(0, 768 - gridSize);
            randomGridY = MathUtils.random(0, 768 - gridSize);

            // Round apple position to the nearest grid 64x64
            applePosX = Math.round(randomGridX / gridSize) * gridSize;
            applePosY = Math.round(randomGridY / gridSize) * gridSize;
        }
        while (bodyParts.contains(new Vector2(applePosX, applePosY)));

        return new Vector2(applePosX, applePosY);
    }

    public void dispose() {
        appleTexture.dispose();
    }
}
