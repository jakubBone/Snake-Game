package game.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Snake {
    public Texture headTexture;
    public  Texture bodyTexture;
    private ArrayList<Vector2> bodyParts;
    public static long movementIntervalTimeNano = 500000000;
    public static int speed = 1;
    public boolean ifCollisionDetected;
    public static int attempts = 4;
    private static boolean collisionSoundPlayed;

    public Snake(Texture headTexture, Texture bodyTexture){
        collisionSoundPlayed = false;
        this.headTexture = headTexture;
        this.bodyTexture = bodyTexture;
        ifCollisionDetected = false;
        bodyParts = new ArrayList<>();
        bodyParts.add(new Vector2(384,384)); // Initial position of the snake
    }

    public void drawHead(SpriteBatch batch, int direction) {
        TextureRegion region;
        float headX = bodyParts.get(0).x;
        float headY = bodyParts.get(0).y;

        // Snake's head turning depending to the current movement direction
        // Turn is necessary only for UP and DOWN directions
        switch (direction) {
            case Input.Keys.UP:
                region = new TextureRegion(headTexture);
                region.flip(false, true);
                break;
            case Input.Keys.DOWN:
                region = new TextureRegion(headTexture);
                region.flip(true, false);
                break;
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
            default:
                region = new TextureRegion(headTexture);
                break;
        }
        batch.draw(region, headX, headY);
    }

    public void drawBody(SpriteBatch batch) {
        for (Vector2 body : bodyParts) {
            batch.draw(bodyTexture, body.x, body.y);
        }
    }

    public void move(int direction) {
        Vector2 previousPosition = new Vector2(bodyParts.get(0));

        // Snake's moves depending of direction
        // 64px is width and length of snakeBody.png
        switch(direction){
            case Input.Keys.UP:
                bodyParts.get(0).y +=64 ;
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

        // All of snake's body elements moves behind the head
        // It simulates snake move
        for (int i = 1; i < bodyParts.size(); i++) {
            Vector2 currentPosition = new Vector2(bodyParts.get(i));
            bodyParts.set(i, new Vector2(previousPosition));
            previousPosition.set(currentPosition);
        }

    }

    // Add a new snake body element and increase speed all the time when apple is eaten
    public void grow() {
        Vector2 tail = bodyParts.get(bodyParts.size() - 1);
        bodyParts.add(new Vector2(tail.x, tail.y));
        increaseVelocity();
    }

    public void checkCollision(Apple apple, Sound gulp, Sound hitSound) {
        Vector2 headPos = bodyParts.get(0);

        // Apple collision checking
        if(headPos.equals(apple.getPos())) {
            gulp.play();
            grow();
            apple.respawn();
        }

        // Snake body collision checking
        for(int i = 2; i < bodyParts.size(); i++){
                if(headPos.equals(bodyParts.get(i ))) {
                    resetVelocity();
                    playCollisionSound(hitSound);
                }
        }

        // Wall collision checking
        if(headPos.x < 0 || headPos.x >= 768 || headPos.y < 0 || headPos.y >= 704) {
            resetVelocity();
            playCollisionSound(hitSound);
        }

    }

    // Interval starts from 500000000 nanosecomds decreases when the apple is eaten
    // Interval decrease = speed increase
    public void increaseVelocity(){
        if (movementIntervalTimeNano >= 100000000) {
            movementIntervalTimeNano -= 20000000;
            speed++;
        }
    }

    public void resetVelocity(){
            ifCollisionDetected = true;
            movementIntervalTimeNano = 500000000;
            speed = 1;
    }

    public void playCollisionSound(Sound hitSound){
        if (!collisionSoundPlayed && attempts > 1) {
            hitSound.play();
            collisionSoundPlayed = true;
        }
    }

    public void dispose(){
        headTexture.dispose();
        bodyTexture.dispose();
    }
}