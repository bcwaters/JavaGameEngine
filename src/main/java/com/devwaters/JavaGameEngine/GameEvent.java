package com.devwaters.JavaGameEngine;

//This needs to be refactored for clarity.
//Instead of storing the type locally have a KeyPressedEvent and a Physics Event
//TODO make an abstract class... just need to figure out what to do with undefined events
class GameEvent {

    static final int NULLEVENT = -1;
    private int eventType;

    public GameEvent() {
        eventType = NULLEVENT;
    }

    public int getEventType() {
        return eventType;
    }

    public String toString() {

        String stringToReturn = "";
        return stringToReturn;
    }

}