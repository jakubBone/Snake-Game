package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Snake {
    private Texture headTexture;
    private Texture bodyTexture;
    private ArrayList<Vector2> bodyParts;

    public Snake(Texture headTexture, Texture bodyTexture){
        this.headTexture = headTexture;
        this.bodyTexture = bodyTexture;
        bodyParts = new ArrayList<>();
        bodyParts.add(new Vector2(400, 400)); // Initial position of the snake
    }

    public void drawBody(SpriteBatch batch, int direction) {
        for (Vector2 part : bodyParts) {
            batch.draw(bodyTexture, part.x, part.y);
        }
    }
    public void drawHead(SpriteBatch batch) {
            batch.draw(headTexture, bodyParts.get(0).x + 0, bodyParts.get(0).y -64);
    }
    public void move(int direction, boolean grow) {
        Vector2 headPos = bodyParts.get(0);

        switch(direction){
            case Input.Keys.UP:
                headPos.y += 1;
                break;
            case Input.Keys.DOWN:
                headPos.y -= 1;
                break;
            case Input.Keys.LEFT:
                headPos.x -= 1;
                break;
            case Input.Keys.RIGHT:
                headPos.x += 1;
                break;
        }

        for (int i = bodyParts.size() - 1; i > 0; i--) {
            bodyParts.set(i, new Vector2(bodyParts.get(i - 1)));
        }

        bodyParts.set(0, headPos);

    }
    public void grow() {

    }

    public void checkAppleCollision(Vector2 applePosition, Apple apple) {
        Vector2 headPos = bodyParts.get(0);

        float appleX = applePosition.x;
        float appleY = applePosition.y;
        float appleWidth = 64;
        float appleHeight = 64;

        float headX = headPos.x;
        float headY = headPos.y;

        // Check if snakeHead is outside the apple
        if (headX >= appleX && headX <= appleX + appleWidth && headY >= appleY && headY <= appleY + appleHeight) {
            grow();
            apple.respawn();
        }
    }
    public Vector2 getHeadPosition(){
        return bodyParts.get(0);
    }
    public void dispose(){
        headTexture.dispose();
        bodyTexture.dispose();
    }
}
