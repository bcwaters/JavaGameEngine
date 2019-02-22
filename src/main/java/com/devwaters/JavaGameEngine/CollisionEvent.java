package com.devwaters.JavaGameEngine;

public class CollisionEvent extends GameEvent{
    GameObject object_01;
    GameObject object_02;
    int minXDistance;
    int minYDistance;
    int xDistance;
    int yDistance;

    private static final int COLLISION_EVENT = 100;
    int eventType;
    public CollisionEvent(GameObject _01, GameObject _02) {
        object_01 = _01;
        object_02 = _02;
        //These values are already calcuted in Collision detector... could probably do this better
        minXDistance = object_01.getWidthRadius() + object_02.getWidthRadius();
        minYDistance = object_01.getHeightRadius() + object_02.getHeightRadius();
        xDistance =  Math.abs(object_01.getCenterPointX() - object_02.getCenterPointX());
        yDistance = Math.abs(object_01.getCenterPointY() - object_02.getCenterPointY());
        eventType = COLLISION_EVENT;
    }

    public int getEventType(){
        return eventType;
    }


}
