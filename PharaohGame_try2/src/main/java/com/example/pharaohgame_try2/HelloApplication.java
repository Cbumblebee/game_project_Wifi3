package com.example.pharaohgame_try2;

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
    public PlayerCharacter playerCharacter = new PlayerCharacter();
    public ScreenMap screen = new ScreenMap(); //Canvas
    public ScreenMap backgroundScreen = new ScreenMap(); //Canvas for tiles
    public GraphicsContext gc = screen.getGraphicsContext2D();
    public GraphicsContext backgroundGc = backgroundScreen.getGraphicsContext2D();
    String lastActiveKey;
    TileManager tileManager = new TileManager(backgroundGc,backgroundScreen);

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);

        root.getChildren().addAll(backgroundScreen, screen);
        prepareActionHandlers();

        //Startzeit
        final long startNanoTime = System.nanoTime();

        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                String activeKey = whichKeyIsActive();
                drawAnimatedImages(activeKey, t);
                /*gc.drawImage(playerCharacter.getImg(),
                             playerCharacter.getXCoordinate(),playerCharacter.getYCoordinate(),
                             playerCharacter.getWIDTH(),playerCharacter.getHEIGHT());*/
            }
        }.start();
        tileManager.draw();
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
        gc.clearRect(0,0,screen.screenWidth,screen.screenHeight);

        if (currentlyActiveKeys.contains("LEFT")) {playerCharacter.minimizeXCoordinate(); return "left";}
        if (currentlyActiveKeys.contains("RIGHT")) {playerCharacter.maximizeXCoordinate(); return "right";}
        if (currentlyActiveKeys.contains("DOWN")) {playerCharacter.maximizeYCoordinate(); return "forward";}
        if (currentlyActiveKeys.contains("UP")) {playerCharacter.minimizeYCoordinate(); return "backward";}
        return "else";
    }
    private void drawAnimatedImages(String activeKey, double t) {
        switch (activeKey) {
            case "left":
                gc.drawImage(playerCharacter.walkingCharacterLeft.getFrame(t),
                             playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                lastActiveKey = "left";
                break;
            case "right":
                gc.drawImage(playerCharacter.walkingCharacterRight.getFrame(t),
                             playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                lastActiveKey = "right";
                break;
            case "backward":
                gc.drawImage(playerCharacter.walkingCharacterBackward.getFrame(t),
                        playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                lastActiveKey = "backward";
                break;
            case "forward":
                gc.drawImage(playerCharacter.walkingCharacterForward.getFrame(t),
                        playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                lastActiveKey = "forward";
                break;
            case "else":
                if (lastActiveKey != null) {
                    switch (lastActiveKey) {
                        case "left":
                            gc.drawImage(playerCharacter.walkingCharacterLeft.getFrame(0),
                                    playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                            break;
                        case "right":
                            gc.drawImage(playerCharacter.walkingCharacterRight.getFrame(0),
                                    playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                            break;
                        case "backward":
                            gc.drawImage(playerCharacter.walkingCharacterBackward.getFrame(0),
                                    playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                            break;
                        case "forward":
                            gc.drawImage(playerCharacter.walkingCharacterForward.getFrame(0),
                                    playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                            break;
                    }
                } else {
                    gc.drawImage(playerCharacter.walkingCharacterRight.getFrame(0),
                            playerCharacter.getXCoordinate(), playerCharacter.getYCoordinate());
                }
                break;
        }
    }
    public static void main(String[] args) {
        launch();
    }
}