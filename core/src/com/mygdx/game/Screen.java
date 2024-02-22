package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Screen extends ScreenAdapter  {
    protected Texture image;
    protected OrthographicCamera camera;
    protected Rectangle screen;
    protected SpriteBatch batch;
    protected int screenSize;
    protected int cellSize;
    protected int cellsNumber;

    public void setScreenDetails(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 768, 768);

        screen = new Rectangle();
        batch = new SpriteBatch();

        screenSize = 768;
        cellSize = 64;

        cellsNumber = screenSize / cellSize;
    }
}
