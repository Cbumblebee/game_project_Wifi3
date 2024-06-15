package com.example.pharaohgame_try2.object;

import com.example.pharaohgame_try2.ForegroundScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ParentObject {
    Image image;
    String name;
    boolean collision = false;
    public int worldX, worldY;

    public void draw(GraphicsContext gc, ForegroundScreen foregroundScreen) {
        int screenX = worldX - playerCharacter.getWorldXCoordinate() + playerCharacter.getScreenXCoordinate();
        int screenY = worldY - playerCharacter.getWorldYCoordinate() + playerCharacter.getScreenYCoordinate();


        if (worldX + bgScreen.getTileSize() * 2 > playerCharacter.getWorldXCoordinate() - playerCharacter.getScreenXCoordinate() &&
                worldX - bgScreen.getTileSize() * 2 < playerCharacter.getWorldXCoordinate() + playerCharacter.getScreenXCoordinate() &&
                worldY + bgScreen.getTileSize() * 2 > playerCharacter.getWorldYCoordinate() - playerCharacter.getScreenYCoordinate() &&
                worldY - bgScreen.getTileSize() * 2 < playerCharacter.getWorldYCoordinate() + playerCharacter.getScreenYCoordinate()) {

            bgGc.drawImage(image,screenX,screenY, bgScreen.getTileSize(), bgScreen.getTileSize());
        }
    }
}
