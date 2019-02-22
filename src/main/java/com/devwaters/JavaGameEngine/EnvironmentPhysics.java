package com.devwaters.JavaGameEngine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

//this class will add vectors of objects and detect collisions
class EnvironmentPhysics {
    //This class will receive an array of gameObjects and methods that make changes will return the updated array
    //to the dataController class
    int decayLimit = 0; //later these will be determined by a profile
    int decayAmount = 1;//
    int currentEventArraySize;

    Vector eventVector; //Physics needs a storage for vector to pass along to a gameEvent
    ArrayList<GameObject> gameObjectArrayList;
    Stack<PhysicsEvent> changesStack;

    public Vector speedLimit; // This will determine the max size of a vector in the current evironment. absolute value is important
    public EnvironmentPhysics(){
        currentEventArraySize = 0;
        changesStack = new Stack<PhysicsEvent>();
       
        speedLimit = new Vector(20, 20);
        // I will make a new environment physics record which hold info like speed limit gravity etc to pass in a constructor
    }

    //This Method will be moved out to Collision detection
    public void applyVectors(GameObject selectedObject, GameObject comparedObject) {
        selectedObject.addVector(comparedObject.getObjectVector());
    }

    public void clearVectors() {
        for (int i = 0; i<gameObjectArrayList.size(); i++) {
            gameObjectArrayList.get(i).clearVector();
        }
    }

    //TODO use Foreach and Filter instead
    public void applySpeedLimit(){
        for (int i = 0; i<gameObjectArrayList.size(); i++) {
            if (gameObjectArrayList.get(i).withinVectorBounds(speedLimit)){
                //if the speedLimit is broken change object
                eventVector = gameObjectArrayList.get(i).applyVectorLimit(speedLimit);
                addGameEvent(new PhysicsEvent(eventVector, i, PhysicsEvent.LIMITEVENT));
            //adds an event to the array that is being returned. This event will contain the objects new vector
            }
        }
    }

    public void applyVectorDecay() {

        Vector vectorToApply;
        for (int i = 0; i < gameObjectArrayList.size(); i++) {
            if (gameObjectArrayList.get(i).getObjectType() != GameObject.NULLOBJECT) {
                vectorToApply = gameObjectArrayList.get(i).applyVectorDecay(decayLimit, decayAmount);
            } else {
                vectorToApply = new Vector(0, 0);
            }
            if (gameObjectArrayList.get(i).getObjectType() != GameObject.NULLOBJECT) {

                addGameEvent(new PhysicsEvent(vectorToApply, i, PhysicsEvent.DECAYEVENT));
            }
        }
    }

    public void addGameEvent(PhysicsEvent _event) {
        changesStack.push(_event);
    }
    public void setArray(ArrayList<GameObject> passedArray) {
        gameObjectArrayList = passedArray;
    }
    public Stack<PhysicsEvent> getEventStack(){
        return changesStack;
    }


}