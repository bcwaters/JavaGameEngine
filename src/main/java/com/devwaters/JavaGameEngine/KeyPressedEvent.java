package com.devwaters.JavaGameEngine;


public class KeyPressedEvent extends GameEvent{
    static final int NULLEVENT = -1;
    static final int MOVE_HERO_UP = 2;
    static final int MOVE_HERO_DOWN = 3;
    static final int MOVE_HERO_RIGHT = 4;
    static final int MOVE_HERO_LEFT = 5;
    static final int HERO_COMMAND_01 = 6;
    static final int HERO_COMMAND_02 = 7;

    private int keyCode;

    public KeyPressedEvent(){
        super();
    }

    public KeyPressedEvent(int keyPressed){
        keyCode = keyPressed;
    }

    public int getEventType(){
        return keyCode;
    }

}
