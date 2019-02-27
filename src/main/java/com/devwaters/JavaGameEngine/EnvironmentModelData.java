package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.SynchronousQueue;

// this class will hold objects to be shared by other classes along with those classes.
class EnvironmentModelData extends JFrame implements Runnable{

    Stack<GameEvent> gameEventStack;
    ArrayList<GameObject> gameObjectArrayList;
    EnvironmentPhysics currentEnvironmentPhysics;
    Screen GameObjectLayer;
    Screen UILayer;
    StateProfile currentProfile;
    DisplayFrame viewport;

    boolean collisionStatus = false;
    public EnvironmentModelData(GameObject playerGameObject, StateProfile initialProfile, DisplayFrame _viewport) {
        gameEventStack = new Stack<GameEvent>();
        gameObjectArrayList = new ArrayList<GameObject>();
        gameObjectArrayList.add(playerGameObject);
        //TODO add more gameobject types
        gameObjectArrayList.add(new GameObject(200, 200, GameObject.FIXED));
        currentEnvironmentPhysics = new EnvironmentPhysics();
        viewport = _viewport;
        GameObjectLayer = new Screen(gameObjectArrayList, viewport);
        currentProfile = initialProfile;
        makePlatforms();

        //Change the screen according to state... ie paused
        viewport.changeScreen(GameObjectLayer);

    }

    private void makePlatforms(){
        int j = -1;
        while(j++<5) {
            gameObjectArrayList.add(new GameObject(new CanvasLocation(5*j+(j*200), 500),
                    new ObjectSize(200, 20), GameObject.FIXED));
        }
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

    public void pushMouseEventsToGameEventStack(Stack<int[]> clickStack) {
        while(!clickStack.isEmpty()){
            gameEventStack.push(new MouseClickedEvent(clickStack.pop()));
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
                  if(e.getEventType() == KeyPressedEvent.HERO_COMMAND_01){
                        GameObjectLayer.moveCameraRight(GameObjectLayer.gClone);
                    }
                    gameObjectArrayList.get(0).processPlayerCommand(e.getEventType());
                }
                if (e instanceof PhysicsEvent) {
                    PhysicsEvent physicsEvent = (PhysicsEvent) e;
                    gameObjectArrayList.get(physicsEvent.getObjectID()).applyPhysicsEvent(physicsEvent);
                }

                if (e instanceof CollisionEvent) {
                    handleCollisionEvent((CollisionEvent)e);
                }

                if (e instanceof MouseClickedEvent) {
                    MouseClickedEvent mouseEvent = (MouseClickedEvent)e;
                    System.out.println(mouseEvent.coordinates[0] + ", " + mouseEvent.coordinates[1]);
                    GameObjectLayer.setLineToDraw(new int[]{
                            mouseEvent.coordinates[0], mouseEvent.coordinates[1]});
                }
                //collsioinEvent do nothing
                //doNothing(e)
            }
        }
    }

    public void handleCollisionEvent(CollisionEvent e){

        int adjustedX =  e.minXDistance - e.xDistance;
        int adjustedY =  e.minYDistance - e.yDistance;
        boolean _01isTop = e.object_01.location.getY() <= e.object_02.location.getY();
        boolean _01isRight = e.object_01.location.getX() >= e.object_02.location.getX();

        if(e.object_01.getObjectType() == GameObject.PLAYER){
            //adjust object to fix collision

            int xDirection = _01isRight?1:-1;
            int yDirection = _01isTop?-1:1;
            int currentX = e.object_01.location.getX();
            int currentY = e.object_01.location.getY();

            //make smallest adjustment possible
            if(adjustedX<=adjustedY){
            e.object_01.setCanvasLocation(
                  currentX + (xDirection * (adjustedX +2)),
                  currentY + yDirection );
                e.object_01.getObjectVector().addI(-e.object_01.getObjectVector().getI());
                while(CollisionDetector.detectXCollision(e.object_01, e.object_02)){
                    e.object_01.setCanvasLocation(e.object_01.location.getX() + xDirection,
                            e.object_02.location.getY());
                }
            }else{
                e.object_01.setCanvasLocation(
                        currentX + xDirection,
                        currentY + (yDirection * (adjustedY +2))
                );
                e.object_01.getObjectVector().addJ(-e.object_01.getObjectVector().getJ());
                while(CollisionDetector.detectYCollision(e.object_01, e.object_02)){
                    e.object_01.setCanvasLocation(e.object_01.location.getX(),
                            e.object_02.location.getY() + yDirection);
                }
            }
        }

        if(e.object_02.getObjectType() == GameObject.PLAYER){
            int xDirection = _01isRight?1:-1;
            int yDirection = _01isTop?1:-1;
            int currentX = e.object_02.location.getX();
            int currentY = e.object_02.location.getY();

            //make smallest adjustment possible
            if(adjustedX<=adjustedY){
                e.object_02.setCanvasLocation(
                        currentX + (-xDirection * (adjustedX +1)),
                        currentY - yDirection);
            }else{
                e.object_02.setCanvasLocation(
                        currentX - xDirection,
                        currentY + (-yDirection * (adjustedY +1))
                );
            }
        }
    }


    public void run() {
        //TODO I think the order needs to be changed later on down the road
        runPhysics();
        detectCollisions();
        processGameEvents();
        updateGameObjects();


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

    //TODO this needs to compare each object with every other object
    //so probably a ForEach object.detectCollision(everyOtherObject)
    //Overhead could be cut down by checking is object is muteable... need to add that property
    // object.isMutable ? compareWithObjects() : skipComparison()
    private void detectCollisions(){
        //hardcoded... fix this
        GameObject player = gameObjectArrayList.get(0);
        gameObjectArrayList.forEach(gameObject -> {
            if(gameObject!=player){
               if(CollisionDetector.detectCollision(player, gameObject)){
                   GameObjectLayer.setTempCollisionCheck(true);
                    gameEventStack.push(new CollisionEvent(player, gameObject));
               }else{
               }
            }
        });



//        collisionStatus = CollisionDetector.
//                detectCollision(gameObjectArrayList.get(0), gameObjectArrayList.get(1));
//        GameObjectLayer.setTempCollisionCheck(collisionStatus);
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