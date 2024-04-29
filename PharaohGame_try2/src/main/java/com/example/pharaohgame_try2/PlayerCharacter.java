package com.example.pharaohgame_try2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerCharacter extends DisplayedObject {
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private int WIDTH = 70;
    private int HEIGHT = 70;
    public AnimatedImage walkingCharacterRight, walkingCharacterLeft, walkingCharacterForward, walkingCharacterBackward;
    Image[] walkingCharacterArray;

    //Konstruktor
    public PlayerCharacter () {
        setDefaultValues();
        walkingCharacterRight = new AnimatedImage();
        walkingCharacterLeft = new AnimatedImage();
        walkingCharacterForward = new AnimatedImage();
        walkingCharacterBackward = new AnimatedImage();
        animateAllImages();
    }
    private void setDefaultValues() {

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

    public int getXCoordinate() {
        return xCoordinate;
    }
    public void minimizeXCoordinate() {
        this.xCoordinate--;
    }
    public void maximizeXCoordinate() {
        this.xCoordinate++;
    }
    public int getYCoordinate() {
        return yCoordinate;
    }
    public void minimizeYCoordinate() {
        this.yCoordinate--;
    }
    public void maximizeYCoordinate() {
        this.yCoordinate++;
    }
    public int getWIDTH() {
        return WIDTH;
    }
    public int getHEIGHT() {
        return HEIGHT;
    }
}
