package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class GameObject extends JComponent  // This class represents heros, walls, anything physical in the game
{
    CanvasLocation location;
    ObjectSize size;

    private int objectType;
    // this does not have an object id because the object id represent a location in a array.

    static final int NULLOBJECT = -1;
    static final int HERO = 1;

    boolean isMoving;

    //Allow for multiple animation states to be defined, each unique to the object
    Image objectPicture;
    //For now an image will be used
    Vector objectVector; //A vector will be used to represent momentum in this class.

    //make a null object
    public GameObject() {
        objectType = NULLOBJECT;
    }

    //constructor for testing
    public GameObject(int x, int y, int _objectType)
    {
        objectType = _objectType;
        location = new CanvasLocation(x, y);
        objectVector = new Vector(0, 0);
        isMoving = false;
        size = new ObjectSize(location, 50, 50);
    }

    public ObjectSize getObjectSize(){
        return size;
    }
    public boolean withinVectorBounds(Vector limit) {

        Vector magnitude = objectVector.getMagnitude();
        boolean testBool = !(magnitude.getI() < limit.getI() && magnitude.getJ() < limit.getJ());
        // returns a boolean indicating if i or j is too big
        return testBool;
    }

    public Vector applyVectorDecay(int decayLimit, int decayAmount) {
        int currentI = objectVector.getI();
        int currentJ = objectVector.getJ();
        int newVectorI = 0;
        int newVectorJ = 0;

        if (!isMoving){
            //is moving represents wheter or not a control request has been made
            if (Math.abs(currentI) > decayLimit) {
                if (currentI > 0) {
                    newVectorI = -decayAmount; // i am skeptical of this change nvm this works now, NewVector should be called change amount
                } else {
                    newVectorI = decayAmount;
                }
            }
            if (Math.abs(currentJ) > decayLimit) {
                if (currentJ > 0) {
                    newVectorJ = -decayAmount;
                } else {
                    newVectorJ = decayAmount;
                }
            }
        }
        isMoving = false; //Reset to false, which won't be retriggered until moving stops for one thread cycle
        return new Vector(newVectorI, newVectorJ);

    }

    private Color getRandomColor(){
        int R = ThreadLocalRandom.current().nextInt(0, 250);
        int G = ThreadLocalRandom.current().nextInt(0, 250);
        int B = ThreadLocalRandom.current().nextInt(0, 250);
        return new Color(R,G,B);
    }
    public void paintObject(Graphics g) {
        g.fillOval(location.getX(), location.getY(), size.getHeight(), size.getWidth());
        g.setColor(getRandomColor());
        g.fillOval(size.getCenterPoint().getX(), size.getCenterPoint().getY(), 2, 2);
    }

    public void setLocation(int x, int y) {
        location = new CanvasLocation(x, y);
    }

    //TODO this needs to be moved up in state so that the datacontroller
    //does something like GameObject.moveUp() when a key is pressed
    public void processPlayerCommand(int keyCode) {
        if (keyCode == KeyPressedEvent.MOVE_HERO_UP) {
            moveUp();
        }
        if (keyCode == KeyPressedEvent.MOVE_HERO_DOWN) {
            moveDown();
        }
        if (keyCode == KeyPressedEvent.MOVE_HERO_RIGHT) {
            moveRight();
        }
        if (keyCode == KeyPressedEvent.MOVE_HERO_LEFT) {
            moveLeft();
        }
    }

    //TODO just like keyevent it is like that this state can be pulled up to data controller
    //so datacontroller would call GameObject.addVector(value)
    public void applyPhysicsEvent(PhysicsEvent e){
        if (e.getEventType() == PhysicsEvent.LIMITEVENT) {
            addVector(e.getEventVector());
        }
        if (e.getEventType() == PhysicsEvent.DECAYEVENT) {
            addVector(e.getEventVector());
            isMoving = false;
        }
    }
    // This will add a vector to the object, that way all location updates are processed within the event processor
    public void moveUp(){
        isMoving = true;
        objectVector.addJ(-1);
    }

    public void moveDown() {
        isMoving = true;
        objectVector.addJ(1);
    }

    public void moveRight() {
        isMoving = true;
        objectVector.addI(1);
    }

    public void setIsMoving(boolean movingState) {
        isMoving = movingState;
    }

    public void moveLeft() {
        isMoving = true;
        objectVector.addI(-1);
    }

    public void addVector(Vector vectorToAdd) {
        objectVector.add(vectorToAdd);
    }
    public Vector applyVectorLimit(Vector limit) {
        int newI = 0;
        int newJ = 0;
        if (objectVector.getMagnitude().getI() > limit.getI()) {
            if (objectVector.getI() > 0) {
                newI = limit.getI() - objectVector.getI();
                //the differnce between this added to a vector will result in the limit vector
            }
            if (objectVector.getI() < 0){
                newI = (-1) * (objectVector.getI() + limit.getI());
            }
        } else {
            newI = 0;
        }
        if (objectVector.getMagnitude().getJ() > limit.getJ()) {
            if (objectVector.getJ() > 0) {
                newJ = limit.getJ() - objectVector.getJ();
                //the differnce between this added to a vector will result in the limit vector
            }
            if (objectVector.getJ() < 0) {
                newJ = (-1) * (objectVector.getJ() + limit.getJ());
            }
        } else {
            newJ = 0;
        }
        System.out.println("A vector is being applied: " + new Vector(newI, newJ));
        return new Vector(newI, newJ);
    }

    public Vector getObjectVector() {
        return objectVector;
    }
    // This will apply vectors to locations
    public void update(){
        CanvasLocation updatedLocation = new CanvasLocation(location.getX() + objectVector.getI(), location.getY() + objectVector.getJ());
        this.setLocation(updatedLocation.getX(), updatedLocation.getY());
        size.updateCenterPoint(location);
    }
    public String toString() {
        String returnString = "";
        returnString += "Location: " + location + "\n vector: " + objectVector;
        return returnString;
    }

    public void clearVector() {
        objectVector = new Vector(0, 0);
    }

    public int getObjectType()

    {
        return objectType;
    }

    public CanvasLocation getCanvasLocation() {
        return location;
    }

}
