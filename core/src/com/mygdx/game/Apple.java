package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Apple {
    private Texture appleImage;
    private Vector2 appleCoordinates;
    private Array<Rectangle> apples;
    private long lastAppleTime;

    public Apple(){
        appleImage = new Texture("apple.png");
        apples = new Array<>();
        placeApple();
    }
    public void placeApple(){
            Rectangle apple = new Rectangle();
            appleCoordinates = new Vector2(MathUtils.random(0, 800 - 64), MathUtils.random(0, 800 - 64));
            lastAppleTime = TimeUtils.nanoTime();
    }
    public void drawApple(SpriteBatch batch) {
        batch.draw(appleImage, appleCoordinates.x, appleCoordinates.y );

    }
    public void dispose() {
        appleImage.dispose();
    }

}
