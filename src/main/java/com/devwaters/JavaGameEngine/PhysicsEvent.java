package com.devwaters.JavaGameEngine;

public class PhysicsEvent extends GameEvent{

    static final int NULLEVENT = -1;

    //PhysicsEvents
    static final int VECTOR = 1;    //these final ints will indicate the type of GameEvent
    static final int LIMITEVENT = 8;  //a limit events adds a vector to speed
    static final int DECAYEVENT = 9;  //a vector is added to decay the current vector, this is used to reset the isMoving boolean

    private int eventType;
    private int objectLocation = 0; //location in the GameObjectArray, default to the hero
    Vector eventVector;
    private int objectID = 0; //location in the GameObjectArray, default to the hero

    public PhysicsEvent(){
        super();
    }

    public PhysicsEvent(Vector _passedVector, int _objectLocation, int _eventType) {
        eventType = _eventType;
        eventVector = _passedVector;
        objectLocation = _objectLocation;
    }

    public Vector getEventVector(){
        return eventVector;
    }

    //TODO this represents a position in the game object array up in the data controller
    //pretty sure there is a better way to do this
    public int getObjectID() {
        return objectID;
    }

    public int getEventType(){
        return eventType;
    }
}
