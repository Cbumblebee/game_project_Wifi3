package com.example.pharaohgame_try2.object;

import com.example.pharaohgame_try2.HelloApplication;
import javafx.scene.image.Image;

import java.io.IOException;

public class Object_Chest extends ParentObject {
    public Object_Chest () {
        name = "Chest";
        try {
            image = new Image(getClass().getResource("/com/example/pharaohgame_try2/objects/chest.png").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
