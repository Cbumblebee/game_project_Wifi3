package com.example.pharaohgame_try2;

import com.example.pharaohgame_try2.object.ParentObject;
import com.example.pharaohgame_try2.tile.TileManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;

public class HelloApplication extends Application {
    //saves the currently pressed key(s)
    static HashSet<String> currentlyActiveKeys;
    public Scene scene;
    public ForegroundScreen foregroundScreen = new ForegroundScreen(); //Canvas
    public BackgroundScreen backgroundScreen = new BackgroundScreen(); //Canvas for tiles
    public GraphicsContext foregroundGc = foregroundScreen.getGraphicsContext2D();
    public GraphicsContext backgroundGc = backgroundScreen.getGraphicsContext2D();
    public CollisionChecker collisionChecker = new CollisionChecker(foregroundScreen);
    public PlayerCharacter playerCharacter = new PlayerCharacter(backgroundScreen, collisionChecker);
    String lastActiveKey;
    TileManager tileManager = new TileManager(backgroundGc,backgroundScreen,playerCharacter);



    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);

        root.getChildren().addAll(backgroundScreen, foregroundScreen);
        prepareActionHandlers();

        //Startzeit
        final long startNanoTime = System.nanoTime();

        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                String activeKey = whichKeyIsActive();
                playerCharacter.direction = whichKeyIsActive();
                drawAnimatedImages(activeKey, t);
                /*gc.drawImage(playerCharacter.getImg(),
                             playerCharacter.getXCoordinate(),playerCharacter.getYCoordinate(),
                             playerCharacter.getWIDTH(),playerCharacter.getHEIGHT());*/
            }
        }.start();
        tileManager.draw();
        foregroundScreen.setupGame();
        stage.setTitle("2D Game");
        stage.setScene(scene);
        stage.show();
    }
    private void prepareActionHandlers() {
        currentlyActiveKeys = new HashSet<String>();
        scene.setOnKeyPressed( key -> currentlyActiveKeys.add(key.getCode().toString()));
        scene.setOnKeyReleased( key -> currentlyActiveKeys.remove(key.getCode().toString()));
    }
    private String whichKeyIsActive () {
        foregroundGc.clearRect(0,0, foregroundScreen.screenWidth, foregroundScreen.screenHeight);
        backgroundGc.clearRect(0,0,backgroundScreen.maxScreenWidth,backgroundScreen.maxScreenHeight);
        /*if (currentlyActiveKeys.contains("LEFT")) {playerCharacter.minimizeXCoordinate(); return "left";}
        if (currentlyActiveKeys.contains("RIGHT")) {playerCharacter.maximizeXCoordinate(); return "right";}
        if (currentlyActiveKeys.contains("DOWN")) {playerCharacter.maximizeYCoordinate(); return "forward";}
        if (currentlyActiveKeys.contains("UP")) {playerCharacter.minimizeYCoordinate(); return "backward";}*/
        if (currentlyActiveKeys.contains("LEFT")) {return "left";}
        if (currentlyActiveKeys.contains("RIGHT")) {return "right";}
        if (currentlyActiveKeys.contains("DOWN")) {return "down";}
        if (currentlyActiveKeys.contains("UP")) {return "up";}
        return "else";
    }
    private void drawAnimatedImages(String activeKey, double t) {
        tileManager.draw();
        playerCharacter.moveCharacter(tileManager);
        switch (activeKey) {
            case "left":
                foregroundGc.drawImage(playerCharacter.walkingCharacterLeft.getFrame(t),
                             playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                             foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                lastActiveKey = "left";
                break;
            case "right":
                foregroundGc.drawImage(playerCharacter.walkingCharacterRight.getFrame(t),
                             playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                             foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                lastActiveKey = "right";
                break;
            case "up":
                foregroundGc.drawImage(playerCharacter.walkingCharacterBackward.getFrame(t),
                        playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                        foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                lastActiveKey = "up";
                break;
            case "down":
                foregroundGc.drawImage(playerCharacter.walkingCharacterForward.getFrame(t),
                        playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                        foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                lastActiveKey = "down";
                break;
            case "else":
                if (lastActiveKey != null) {
                    switch (lastActiveKey) {
                        case "left":
                            foregroundGc.drawImage(playerCharacter.walkingCharacterLeft.getFrame(0),
                                    playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                                    foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                            break;
                        case "right":
                            foregroundGc.drawImage(playerCharacter.walkingCharacterRight.getFrame(0),
                                    playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                                    foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                            break;
                        case "up":
                            foregroundGc.drawImage(playerCharacter.walkingCharacterBackward.getFrame(0),
                                    playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                                    foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                            break;
                        case "down":
                            foregroundGc.drawImage(playerCharacter.walkingCharacterForward.getFrame(0),
                                    playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                                    foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                            break;
                    }
                } else {
                    foregroundGc.drawImage(playerCharacter.walkingCharacterRight.getFrame(0),
                            playerCharacter.getScreenXCoordinate(), playerCharacter.getScreenYCoordinate(),
                            foregroundScreen.getTileSize(), foregroundScreen.getTileSize());
                }
                break;
        }
        playerCharacter.collisionOn = false;
        collisionChecker.checkTile(playerCharacter, tileManager);
    }

    /*public void checkTile (DisplayedObject displayedObject, TileManager tileManager) {
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
            case "left":
                objectTopRow = (objectTopWorldY - displayedObject.speed) / screen.getTileSize();
                tileNum1 = tileManager.mapTileNumber[objectLeftCol][objectTopRow];
                tileNum2 = tileManager.mapTileNumber[objectRightCol][objectTopRow];
                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    displayedObject.collisionOn = true;
                }
                break;
            case "right":
                break;
            case "up":
                break;
            case "down":
                break;
        }
    }*/

    public static void main(String[] args) {
        launch();
    }
}