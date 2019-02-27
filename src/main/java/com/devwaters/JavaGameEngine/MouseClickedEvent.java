package com.devwaters.JavaGameEngine;

public class MouseClickedEvent extends GameEvent {
    static final int LEFT_CLICK = 987;

    public int[] coordinates;

    public MouseClickedEvent(){
        super();
    }

    public MouseClickedEvent(int[] _coordinates){
        coordinates = _coordinates;
    }

    public int getEventType(){
        return LEFT_CLICK;
    }

}
