package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Apple {
    private Texture appleTexture;
    private Vector2 applePosition;
    private Screen screen;

    public Apple(Texture appleTexture){
        this.appleTexture = appleTexture;
        screen = new Screen();
        //applePosition = new Vector2(MathUtils.random(0, 800 - appleTexture.getWidth()), MathUtils.random(0, 800 - appleTexture.getHeight()));
        applePosition = new Vector2(MathUtils.random(0, screen.cols - screen.cellSize), MathUtils.random(0, screen.cols - screen.windowSideSize));

    }
    /*public Apple(Texture appleTexture){
        this.appleTexture = appleTexture;
        applePosition = new Vector2(MathUtils.random(0, 800 - appleTexture.getWidth()), MathUtils.random(0, 800 - appleTexture.getHeight()));
    }*/

    public void drawApple(SpriteBatch batch){
        batch.draw(appleTexture, applePosition.x, applePosition.y);
    }

    public Vector2 getPosition(){
        return applePosition;
    }

    public void respawn(){
        applePosition.set(MathUtils.random(0, 800 - 64), MathUtils.random(0, 800 - 64));
    }
    public void dispose() {
        appleTexture.dispose();
    }

}
