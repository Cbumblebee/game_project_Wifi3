package com.example.pharaohgame_try2;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class DisplayedObject {

    public int worldXCoordinate = 0;
    public int worldYCoordinate = 0;
    public int screenXCoordinate; //indicates, where we draw the player on the screen
    public int screenYCoordinate;
    public int speed = 1;
    public Rectangle solidAreaOfCharacter;
    public boolean collisionOn = false;
    public String direction;


    public DisplayedObject () {
        solidAreaOfCharacter = new Rectangle();
    }

    //getResources - Method
    public String getR(String filename) {
        String str = String.valueOf(HelloApplication.class.getResource(filename));
        try {
            return str;
        } catch (Exception e) {
            System.out.println("img not found");
            return str;
        }
    }





    //Getter, Setter, Change Coordinates
    public int getScreenXCoordinate() {
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
    public int getWorldXCoordinate() {
        return worldXCoordinate;
    }
    public int getWorldYCoordinate() {
        return worldYCoordinate;
    }
}
