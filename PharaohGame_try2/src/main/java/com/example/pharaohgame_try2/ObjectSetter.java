package com.example.pharaohgame_try2;

import com.example.pharaohgame_try2.object.Object_Chest;

public class ObjectSetter {
    ForegroundScreen foregroundscreen;
    public ObjectSetter (ForegroundScreen foregroundscreen) {
        this.foregroundscreen = foregroundscreen;
    }

    public void setObject (ForegroundScreen foregroundscreen) {
        foregroundscreen.obj[0] = new Object_Chest();
        foregroundscreen.obj[0].worldX = 23 * foregroundscreen.tileSize;
        foregroundscreen.obj[0].worldY = 7 * foregroundscreen.tileSize;

        foregroundscreen.obj[1] = new Object_Chest();
        foregroundscreen.obj[1].worldX = 23 * foregroundscreen.tileSize;
        foregroundscreen.obj[1].worldY = 40 * foregroundscreen.tileSize;
    }
}
