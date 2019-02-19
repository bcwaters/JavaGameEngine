package com.devwaters.JavaGameEngine;
//this class will add vectors of objects and detect collisions
class EnvironmentPhysics {
    //This class will receive an array of gameObjects and methods that make changes will return the updated array
    //to the dataController class
    int decayLimit = 0; //later these will be determined by a profile
    int decayAmount = 1;//
    final int MAXARRAYSIZE = 100;
    int currentEventArraySize;
    CollisionDetection collisionDetector;
    GameEvent event = new GameEvent();
    Vector eventVector; //Physics needs a storage for vector to pass along to a gameEvent
    GameObject[] gameObjects;
    GameEvent[] changes;
    public Vector speedLimit; // This will determine the max size of a vector in the current evironment. absolute value is important
    public EnvironmentPhysics(){
        currentEventArraySize = 0;

        changes = new GameEvent[MAXARRAYSIZE];
        gameObjects = new GameObject[100];
        speedLimit = new Vector(20, 20);
        // I will make a new environment physics record which hold info like speed limit gravity etc to pass in a constructor
    }

    //This Method will be moved out to Collision detection
    public void applyVectors(GameObject selectedObject, GameObject comparedObject) {
        selectedObject.addVector(comparedObject.getObjectVector());
    }

    public void clearVectors() {
        for (int i = 0; gameObjects[i].getObjectType() != gameObjects[i].NULLOBJECT; i++) {
            gameObjects[i].clearVector();
        }
    }

    public void applySpeedLimit() {
        for (int i = 0; gameObjects[i].getObjectType() != gameObjects[i].NULLOBJECT; i++) {
            if (gameObjects[i].withinVectorBounds(speedLimit)){
                //if the speedLimit is broken change object
                eventVector = gameObjects[i].applyVectorLimit(speedLimit);
                addGameEvent(new GameEvent(eventVector, i, event.LIMITEVENT));
            //adds an event to the array that is being returned. This event will contain the objects new vector
            }
        }
    }

    public void applyVectorDecay() {

        System.out.println("applyVector Decay called");
        Vector vectorToApply;
        for (int i = 0; i < gameObjects.length; i++) {
            if (gameObjects[i].getObjectType() != gameObjects[i].NULLOBJECT) {
                vectorToApply = gameObjects[i].applyVectorDecay(decayLimit, decayAmount);
            } else {
                vectorToApply = new Vector(0, 0);
            }
           // System.out.println("DECAY VECTOR: " + vectorToApply);
            if (gameObjects[i].getObjectType() != gameObjects[i].NULLOBJECT) {
                System.out.println("VECTORDECAY APPLIED: ");
                addGameEvent(new GameEvent(vectorToApply, i, event.DECAYEVENT));
            }
        }
    }

    public void addGameEvent(GameEvent _event) {
        if (currentEventArraySize < MAXARRAYSIZE) {
            changes[currentEventArraySize] = _event;
            currentEventArraySize++;
        }
    }

    public void setArray(GameObject[] passedArray) {

        gameObjects = passedArray;
    }

    public void clearGameEventArray() {
        for (int i = 0; i < MAXARRAYSIZE; i++) {
            changes[i] = new GameEvent();
        }
    }

    //this returns an array of gameEvents to be processed
    public GameEvent[] returnChanges() {
        GameEvent[] filler = new GameEvent[MAXARRAYSIZE];
        for (int i = 0; i < MAXARRAYSIZE; i++) {
            filler[i] = changes[i];        //clear current array and send a copy
        }

        currentEventArraySize = 0;
        //System.out.println("returnChange called event: " + filler[0]);
        clearGameEventArray();
        return filler;
    }

}