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
        bodyParts.add(new Vector2(400, 800)); // Initial position of the snake
        bodyParts.add(new Vector2(400, 864)); // Initial position of the snake's body part
    }

    public void drawSnake(SpriteBatch batch) {
        for (Vector2 part : bodyParts) {
            batch.draw(bodyTexture, part.x , part.y );
        }
    }
    public void move(int direction, boolean grow) {
        Vector2 headPos = bodyParts.get(0);
        Vector2 newPos = new Vector2(headPos);

        switch(direction){
            case Input.Keys.UP:
                newPos.y += 1;
                break;
            case Input.Keys.DOWN:
                newPos.y -= 1;
                break;
            case Input.Keys.LEFT:
                newPos.x -= 1;
                break;
            case Input.Keys.RIGHT:
                newPos.x += 1;
                break;
        }

       bodyParts.add(0, newPos);

        if(!grow) {
            bodyParts.remove(bodyParts.size() - 1);
        }
    }

    public void grow() {

    }
    public Vector2 getHeadPosition(){
        return bodyParts.get(0);
    }

    public void dispose(){
        headTexture.dispose();
        bodyTexture.dispose();
    }
}
