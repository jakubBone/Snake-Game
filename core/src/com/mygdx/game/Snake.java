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
        for (Vector2 body : bodyParts) {
            batch.draw(bodyTexture, body.x, body.y);
        }
    }


    public void move(int direction) {
        Vector2 previousPosition = new Vector2(bodyParts.get(0));

        switch(direction){
            case Input.Keys.UP:
                bodyParts.get(0).y += 64;
                break;
            case Input.Keys.DOWN:
                bodyParts.get(0).y -= 64;
                break;
            case Input.Keys.LEFT:
                bodyParts.get(0).x -= 64;
                break;
            case Input.Keys.RIGHT:
                bodyParts.get(0).x += 64;
                break;
        }

        for (int i = 1; i < bodyParts.size(); i++) {
            Vector2 currentPosition = new Vector2(bodyParts.get(i));
            bodyParts.set(i, new Vector2(previousPosition));
            previousPosition.set(currentPosition);
        }

    }
    public void grow() {
        Vector2 tail = bodyParts.get(bodyParts.size() - 1);
        bodyParts.add(new Vector2(tail.x, tail.y));
    }
    public void checkAppleCollision(Apple apple) {
        float appleX = apple.getPosition().x;
        float appleY = apple.getPosition().y;
        float headX = bodyParts.get(0).x;
        float headY = bodyParts.get(0).y;

        float distance = Vector2.dst(headX, headY, appleX, appleY);
        float collisionRadius = 50;

        if (distance < collisionRadius) {
            grow();
            apple.respawn();
        }
    }
    public void dispose(){
        headTexture.dispose();
        bodyTexture.dispose();
    }
}
