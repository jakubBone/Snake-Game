package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Snake {
    public Texture headTexture;
    public  Texture bodyTexture;
    private ArrayList<Vector2> bodyParts;
    private int direction = Input.Keys.RIGHT;


    public Snake(Texture headTexture, Texture bodyTexture){
        this.headTexture = headTexture;
        this.bodyTexture = bodyTexture;

        bodyParts = new ArrayList<>();
        bodyParts.add(new Vector2(400, 400)); // Initial position of the snake
    }
    public void drawHead(SpriteBatch batch, int direction) {
        TextureRegion region;

        switch (direction) {
            case Input.Keys.UP:
                region = new TextureRegion(headTexture);
                region.flip(false, true);
                break;
            case Input.Keys.DOWN:
                region = new TextureRegion(headTexture);
                break;
            case Input.Keys.LEFT:
                region = new TextureRegion(headTexture);
                region.flip(true, false);
                break;
            case Input.Keys.RIGHT:
                region = new TextureRegion(headTexture);
                break;
            default:
                region = new TextureRegion(headTexture);
                break;
        }

        batch.draw(region, bodyParts.get(0).x, bodyParts.get(0).y);
    }

    public void drawBody(SpriteBatch batch) {
        for (Vector2 part : bodyParts) {
            batch.draw(bodyTexture, part.x, part.y);
        }
    }

    public void move(int direction) {
        Vector2 headPos = bodyParts.get(0);

        switch(direction){
            case Input.Keys.UP:
                headPos.y += 5;
                break;
            case Input.Keys.DOWN:
                headPos.y -= 5;
                break;
            case Input.Keys.LEFT:
                headPos.x -= 5;
                break;
            case Input.Keys.RIGHT:
                headPos.x += 5;
                break;
        }

        for (int i = bodyParts.size() - 1; i > 0; i--) {
            bodyParts.set(i, new Vector2(bodyParts.get(i - 1)));
        }

        bodyParts.set(0, headPos);

    }
    public void grow() {

    }
    public void checkAppleCollision(){

    }
    public Vector2 getHeadPosition(){
        return bodyParts.get(0);
    }
    public void dispose(){
        headTexture.dispose();
        bodyTexture.dispose();
    }
}
