package com.example.pharaohgame_try2;

import com.example.pharaohgame_try2.tile.Tile;
import com.example.pharaohgame_try2.tile.TileManager;

public class CollisionChecker {
    ScreenMap screen;
    //TileManager tilemanager;
    public CollisionChecker (ScreenMap screen/*, TileManager tileManager*/) {
        this.screen = screen;
        //this.tilemanager = tileManager;
    }
    public void checkTile (DisplayedObject displayedObject, TileManager tileManager) {
         int objectLeftWorldX = (int) (displayedObject.getWorldXCoordinate() + displayedObject.solidAreaOfCharacter.getX());
         int objectRightWorldX = (int) (displayedObject.getWorldXCoordinate() + displayedObject.solidAreaOfCharacter.getX() + displayedObject.solidAreaOfCharacter.getWidth());
         int objectTopWorldY = (int) (displayedObject.getWorldYCoordinate() + displayedObject.solidAreaOfCharacter.getY());
         int objectBottomWorldY = (int) (displayedObject.getWorldYCoordinate() + displayedObject.solidAreaOfCharacter.getY() + displayedObject.solidAreaOfCharacter.getHeight());

         int objectLeftCol = objectLeftWorldX / screen.getTileSize();
         int objectRightCol = objectRightWorldX / screen.getTileSize();
         int objectTopRow = objectTopWorldY / screen.getTileSize();
         int objectBottomRow = objectBottomWorldY / screen.getTileSize();

         int tileNum1, tileNum2;

         switch (displayedObject.direction) {
             //predicting the next move and see if there is collision
             case "left":
                 objectLeftCol = (objectLeftWorldX - displayedObject.speed) / screen.getTileSize();
                 tileNum1 = tileManager.mapTileNumber[objectLeftCol][objectTopRow];
                 tileNum2 = tileManager.mapTileNumber[objectLeftCol][objectBottomRow];
                 if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                     displayedObject.collisionOn  = true;
                 }
                 break;
             case "right":
                 objectRightCol = (objectRightWorldX + displayedObject.speed) / screen.getTileSize();
                 tileNum1 = tileManager.mapTileNumber[objectRightCol][objectTopRow];
                 tileNum2 = tileManager.mapTileNumber[objectRightCol][objectBottomRow];
                 if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                     displayedObject.collisionOn  = true;
                 }
                 break;
             case "up": //ok
                 objectTopRow = (objectTopWorldY - displayedObject.speed) / screen.getTileSize();
                 tileNum1 = tileManager.mapTileNumber[objectLeftCol][objectTopRow];
                 tileNum2 = tileManager.mapTileNumber[objectRightCol][objectTopRow];
                 if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                     displayedObject.collisionOn  = true;
                 }
                 break;
             case "down":
                 objectBottomRow = (objectBottomWorldY + displayedObject.speed) / screen.getTileSize();
                 tileNum1 = tileManager.mapTileNumber[objectLeftCol][objectBottomRow];
                 tileNum2 = tileManager.mapTileNumber[objectRightCol][objectBottomRow];
                 if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                     displayedObject.collisionOn  = true;
                 }
                 break;
         }
    }
}
