package com.example.pharaohgame_try2;

import com.example.pharaohgame_try2.tile.TileManager;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PlayerCharacter extends DisplayedObject {
    /*private int worldXCoordinate = 0;
    private int worldYCoordinate = 0;
    public final int screenXCoordinate; //indicates, where we draw the player on the screen
    public final int screenYCoordinate;
    private int speed = 1;*/
    private int WIDTH = 70;
    private int HEIGHT = 70;
    public AnimatedImage walkingCharacterRight, walkingCharacterLeft, walkingCharacterForward, walkingCharacterBackward;
    Image[] walkingCharacterArray;
    ScreenMap bgScreen;
    /*public Rectangle solidAreaOfCharacter;
    public boolean collisionOn = false;*/
    CollisionChecker collisionChecker;

    //Konstruktor
    public PlayerCharacter (ScreenMap bgScreen, CollisionChecker collisionChecker) {
        this.bgScreen = bgScreen;
        this.collisionChecker = collisionChecker;
        setDefaultValues();

        screenXCoordinate = bgScreen.screenWidth/2 - (bgScreen.getTileSize()/2);
        screenYCoordinate = bgScreen.screenHeight/2 - (bgScreen.getTileSize()/2);

        //solidAreaOfCharacter = new Rectangle();
        solidAreaOfCharacter.setX(8);
        solidAreaOfCharacter.setY(8);
        solidAreaOfCharacter.setWidth(32);
        solidAreaOfCharacter.setHeight(32);

        walkingCharacterRight = new AnimatedImage();
        walkingCharacterLeft = new AnimatedImage();
        walkingCharacterForward = new AnimatedImage();
        walkingCharacterBackward = new AnimatedImage();

        animateAllImages();
    }
    private void setDefaultValues() {
        this.worldXCoordinate = bgScreen.getTileSize() * 23; //the starting position
        this.worldYCoordinate = bgScreen.getTileSize() * 21;
        this.speed = 4;
    }

    public void moveCharacter(TileManager tileManager) {
        //Check collision
        collisionOn = false;
        collisionChecker.checkTile(this, tileManager);

        //if collision is false, player can move
        if (collisionOn == false) {
            switch (direction) {
                case "up": //=up
                    minimizeYCoordinate();
                    break;
                case "down": //=down
                    maximizeYCoordinate();
                    break;
                case "left":
                    minimizeXCoordinate();
                    break;
                case "right":
                    maximizeXCoordinate();
                    break;
            }
        }
    }

    private void animateAllImages() {
        //~~~RIGHT~~~
        buildWalkingCharacterArray("right_");
        walkingCharacterRight.frames = walkingCharacterArray;
        walkingCharacterRight.duration = 0.100; //Framerate von Zehntelsekunden
        //~~~LEFT~~~
        buildWalkingCharacterArray("left_");
        walkingCharacterLeft.frames = walkingCharacterArray;
        walkingCharacterLeft.duration = 0.100; //Framerate von Zehntelsekunden
        //~~~FORWARD OR DOWN~~~
        buildWalkingCharacterArray("forward_");
        walkingCharacterForward.frames = walkingCharacterArray;
        walkingCharacterForward.duration = 0.100; //Framerate von Zehntelsekunden
        //~~~BACKWARD OR UP~~~
        buildWalkingCharacterArray("backward_");
        walkingCharacterBackward.frames = walkingCharacterArray;
        walkingCharacterBackward.duration = 0.100; //Framerate von Zehntelsekunden
    }
    private void buildWalkingCharacterArray(String filename) {
        walkingCharacterArray = new Image[3];
        for (int i = 0; i < 3; i++) {
            int j = i + 1;
            walkingCharacterArray[i] = new Image(getR(filename + j + ".png"));
        }
    }

    /*private String whichKeyIsActive () {
        foregroundGc.clearRect(0,0,screen.screenWidth,screen.screenHeight);
        backgroundGc.clearRect(0,0,backgroundScreen.maxScreenWidth,backgroundScreen.maxScreenHeight);
        if (currentlyActiveKeys.contains("LEFT")) {playerCharacter.minimizeXCoordinate(); return "left";}
        if (currentlyActiveKeys.contains("RIGHT")) {playerCharacter.maximizeXCoordinate(); return "right";}
        if (currentlyActiveKeys.contains("DOWN")) {playerCharacter.maximizeYCoordinate(); return "forward";}
        if (currentlyActiveKeys.contains("UP")) {playerCharacter.minimizeYCoordinate(); return "backward";}
        return "else";
    }*/

    /*public int getScreenXCoordinate() {
        return screenXCoordinate;
    }
    public int getScreenYCoordinate() {
        return screenYCoordinate;
    }
    public void minimizeXCoordinate() {
        this.worldXCoordinate = this.worldXCoordinate - this.speed;
    }
    public void maximizeXCoordinate() {
        this.worldXCoordinate = this.worldXCoordinate +  this.speed;
    }
    public void minimizeYCoordinate() {
        this.worldYCoordinate = this.worldYCoordinate - speed;
    }
    public void maximizeYCoordinate() {
        this.worldYCoordinate = this.worldYCoordinate + speed;
    }
    public int getWIDTH() {
        return WIDTH;
    }
    public int getHEIGHT() {
        return HEIGHT;
    }
    public int getWorldXCoordinate() {
        return worldXCoordinate;
    }
    public int getWorldYCoordinate() {
        return worldYCoordinate;
    }*/
}
