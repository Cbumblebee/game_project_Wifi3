package com.example.pharaohgame_try2;

import com.example.pharaohgame_try2.tile.TileManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class ScreenMap extends Canvas {
    final int originalTileSize = 16; //16x16 px
    final int scale = 3; //making it higher for screens w more resolution
    final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 px
    final int screenHeight = tileSize * maxScreenRow; //576 px

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    final int maxScreenWidth = tileSize * maxWorldCol;
    final int maxScreenHeight = tileSize * maxWorldRow;

    //Collision Checker
    //public CollisionChecker collisionChecker = new CollisionChecker(this);

    public ScreenMap() {
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
    }
    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getMaxScreenWidth() {
        return maxScreenWidth;
    }

    public int getMaxScreenHeight() {
        return maxScreenHeight;
    }
}
