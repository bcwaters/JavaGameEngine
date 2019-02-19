package com.devwaters.JavaGameEngine;


class GameEvent {
    final int VECTOR = 1;    //these final ints will indicate the type of GameEvent
    final int NULLEVENT = -1;
    final int MOVE_HERO_UP = 2;
    final int MOVE_HERO_DOWN = 3;
    final int MOVE_HERO_RIGHT = 4;
    final int MOVE_HERO_LEFT = 5;
    final int HERO_COMMAND_01 = 6;
    final int HERO_COMMAND_02 = 7;

    final int LIMITEVENT = 8;  //a limit events adds a vector to speed
    final int DECAYEVENT = 9;  //a vector is added to decay the current vector, this is used to reset the isMoving boolean
    private int eventType;
    private int objectLocation = 0; //location in the GameObjectArray, default to the hero


    Vector eventVector;

    public GameEvent() {
        eventType = NULLEVENT;
    }

    public GameEvent(int _eventType) {
        eventType = _eventType;
    }

    public GameEvent(Vector _passedVector, int _objectLocation, int _eventType) {
        eventType = _eventType;
        eventVector = _passedVector;
        objectLocation = _objectLocation;
    }

    public int getEventType() {
        return eventType;
    }

    public Vector getEventVector()

    {
        return eventVector;
    }

    public int getObjectLocation() {
        return objectLocation;
    }

    public String toString() {

        String stringToReturn = "";
        stringToReturn += "EventVector: " + eventVector + "\n ArrayLocation:" + objectLocation;
        return stringToReturn;
    }

}