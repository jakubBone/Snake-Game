package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Apple {
    private Texture appleTexture;
    private Vector2 applePosition;
    private int gridSize;

    public Apple(Texture appleTexture){
        this.appleTexture = appleTexture;
        gridSize = 64;
        applePosition = dropApple();

    }

    public void drawApple(SpriteBatch batch){
        batch.draw(appleTexture, applePosition.x, applePosition.y);
    }

    public Vector2 getPos(){
        return applePosition;
    }

    public void respawn(){
        applePosition = dropApple();
    }

    public Vector2 dropApple(){
        int randomGridX = MathUtils.random(0, 768 - gridSize);
        int randomGridY = MathUtils.random(0, 768 - gridSize);

        // Round to the nearest grid 64x64
        int applePosX = Math.round(randomGridX / gridSize) * gridSize;
        int applePosY = Math.round(randomGridY / gridSize) * gridSize;

        return new Vector2(applePosX, applePosY);

    }
    public void dispose() {
        appleTexture.dispose();
    }

}
