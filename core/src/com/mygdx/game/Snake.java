package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private Texture snakeHead;
    private Texture snakeBody;
    private Music snakeMoveSound;
    private Sound snakeGulpSound;
    private ArrayList<Rectangle> bodyParts;
    private int direction;
    private float speed;

    public Snake(){
        snakeHead = new Texture("snakeHead.png");
        snakeBody = new Texture("snakeBody.png");
        snakeMoveSound = Gdx.audio.newMusic(Gdx.files.internal("snakeMoveSound.mp3"));
        playSnakeMove();
        snakeGulpSound = Gdx.audio.newSound(Gdx.files.internal("snakeGulpSound.wav"));
    }

    public void move() {
        // Move the snake according to its current direction and speed
    }

    public void grow() {
        // Increase the length of the snake
    }

    /*public void changeDirection(Direction newDirection) {
        // Change the direction of the snake
    }*/

    public void update(){

    }
    public void drawSnake(SpriteBatch batch){
        batch.draw(snakeHead, 300, 300);
        batch.draw(snakeBody, 300, 364);
        batch.draw(snakeBody, 300, 428);
    }

    private void playSnakeMove(){
        snakeMoveSound.play();
        snakeMoveSound.setLooping(true);
    }
    public void playSnakeGulp(){
        snakeGulpSound.play();
    }

    public void dispose(){
        snakeGulpSound.dispose();
        snakeMoveSound.dispose();
    }

}
