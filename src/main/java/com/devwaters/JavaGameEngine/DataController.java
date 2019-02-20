package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

// this class will hold objects to be shared by other classes along with those classes.
class DataController extends JFrame implements Runnable{

    Stack<GameEvent> gameEventStack;
    ArrayList<GameObject> gameObjectArrayList;
    EnvironmentPhysics currentEnvironmentPhysics;
    Screen currentScreen;
    StateProfile currentProfile;

    boolean collisionStatus = false;
    public DataController(GameObject playerGameObject, StateProfile initialProfile) {
        gameEventStack = new Stack<GameEvent>();

        gameObjectArrayList = new ArrayList<GameObject>();
        gameObjectArrayList.add(playerGameObject);
        //TODO add more gameobject types
        gameObjectArrayList.add(new GameObject(200, 200, 1));

        currentEnvironmentPhysics = new EnvironmentPhysics();
        currentScreen = new Screen(playerGameObject);
        currentProfile = initialProfile;
    }

    /**
     * The Main Class calls this method to add events for when users press keys
     * @param characterStack A stack of keys the user has pressed
     */
    public void pushKeyEventsToGameEventStack(Stack<java.lang.Character> characterStack) {
        while(!characterStack.isEmpty()){
            gameEventStack.push(currentProfile.getGameEvent(characterStack.pop()));
        }
    }

    public void pushPhysicsEventsToGameEventStack(Stack<PhysicsEvent> eventStack){
        while(!eventStack.isEmpty()){
            gameEventStack.push(eventStack.pop());
        }
    }

    private void processGameEvents(){
        while(!gameEventStack.isEmpty()){
            GameEvent e =  gameEventStack.pop();
            if(e == null){
                e = new GameEvent();
            }
            if(e.getEventType() != e.NULLEVENT){
                if (e instanceof KeyPressedEvent){
                    gameObjectArrayList.get(0).processPlayerCommand(e.getEventType());
                }
                if (e instanceof PhysicsEvent) {
                    PhysicsEvent physicsEvent = (PhysicsEvent) e;
                    gameObjectArrayList.get(physicsEvent.getObjectID()).applyPhysicsEvent(physicsEvent);
                }
            }
        }
    }

    public void run() {
        //TODO I think the order needs to be changed later on down the road
        runPhysics();
        processGameEvents();
        updateGameObjects();
        detectCollisions();
        updateView();
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }

    }

    private void runPhysics(){
        currentEnvironmentPhysics.setArray(gameObjectArrayList);

        // allow everything to process data
        currentEnvironmentPhysics.applySpeedLimit();
        currentEnvironmentPhysics.applyVectorDecay();
        pushPhysicsEventsToGameEventStack(currentEnvironmentPhysics.getEventStack());
    }
    private void updateView(){
        currentScreen.setArrayList(gameObjectArrayList);
    }


    //TODO this needs to compare each object with every other object
    //so probably a ForEach object.detectCollision(everyOtherObject)
    //Overhead could be cut down by checking is object is muteable... need to add that property
    // object.isMutable ? compareWithObjects() : skipComparison()
    private void detectCollisions(){
        collisionStatus = currentEnvironmentPhysics.CollisionDetector.
                detectCollision(gameObjectArrayList.get(0), gameObjectArrayList.get(1));
        currentScreen.setTempCollisionCheck(collisionStatus);
    }
    private void updateGameObjects(){
        //update locations(translate vectors)
        gameObjectArrayList.forEach(gameObject -> gameObject.update());
    }
    public String toString() {
        String outputString = "";

        return outputString;
    }
}