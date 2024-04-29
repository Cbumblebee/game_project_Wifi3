package com.example.pharaohgame_try2;

import javafx.scene.image.Image;

public abstract class DisplayedObject {

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

}
