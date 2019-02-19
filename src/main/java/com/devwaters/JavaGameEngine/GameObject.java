package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.awt.*;

class GameObject extends JComponent  // This class represents heros, walls, anything physical in the game
{
    CanvasLocation location;
    ObjectSize size;
    GameEvent event = new GameEvent();
    private int objectType;
    // this does not have an object id because the object id represent a location in a array.

    public final int NULLOBJECT = -1;
    public final int HERO = 1;

    public final int MOVEUP = event.MOVE_HERO_UP;
    public final int MOVEDOWN = event.MOVE_HERO_DOWN;
    public final int MOVERIGHT = event.MOVE_HERO_RIGHT;
    public final int MOVELEFT = event.MOVE_HERO_LEFT;
    public final int COMMAND_01 = event.HERO_COMMAND_01;
    public final int COMMAND_02 = event.HERO_COMMAND_02;

    boolean isMoving;

    //Allow for multiple animation states to be defined, each unique to the object
    Image objectPicture;
    //For now an image will be used
    Vector objectVector; //A vector will be used to represent momentum in this class.


    public GameObject() //make a null object
    {
        objectType = NULLOBJECT;
    }

    public GameObject(int x, int y)//constructor for testing
    {
        objectType = HERO;
        location = new CanvasLocation(x, y);
        objectVector = new Vector(0, 0);
        isMoving = false;
        size = new ObjectSize(location, 50, 50);
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

    public void paintObject(Graphics g) {
        g.fillOval(location.getX(), location.getY(), size.getHeight(), size.getWidth());
        g.setColor(Color.RED);
        g.fillOval(size.getCenterPoint().getX(), size.getCenterPoint().getY(), 2, 2);
    }

    public void setLocation(int x, int y) {
        location = new CanvasLocation(x, y);
    }
    public void processGameEvent(GameEvent passedEvent) {
        //System.out.println("object called to process event: +" + passedEvent);
        if (passedEvent.getEventType() == MOVEUP) {
            moveUp();
        }
        if (passedEvent.getEventType() == MOVEDOWN) {
            moveDown();
        }
        if (passedEvent.getEventType() == MOVERIGHT) {
            moveRight();
        }
        if (passedEvent.getEventType() == MOVELEFT) {
            moveLeft();
        }
        if (passedEvent.getEventType() == passedEvent.LIMITEVENT) {
            addVector(passedEvent.getEventVector());
        }
        if (passedEvent.getEventType() == passedEvent.DECAYEVENT) {
            addVector(passedEvent.getEventVector());
            isMoving = false;
        }
    }

    public void moveUp() // This will add a vector to the object, that way all location updates are processed within the event processor
    {
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
