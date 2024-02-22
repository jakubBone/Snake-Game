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
    protected int cols;
    protected int rows;

    public void setScreenDetails(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        screen = new Rectangle();
        batch = new SpriteBatch();

        screenSize = 768;
        cellSize = 64;

        cols = screenSize / cellSize;
        rows = screenSize / cellSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

}
