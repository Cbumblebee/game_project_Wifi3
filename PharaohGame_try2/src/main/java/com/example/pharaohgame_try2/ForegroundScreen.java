package com.example.pharaohgame_try2;

import com.example.pharaohgame_try2.object.ParentObject;

public class ForegroundScreen extends ScreenMap {

    public ObjectSetter objectSetter = new ObjectSetter(this);
    public ParentObject obj[] = new ParentObject[10]; //i can have max 10 objects displayed

    public void setupGame () {
        objectSetter.setObject(this);
    }
}
