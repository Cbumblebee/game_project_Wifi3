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
}
