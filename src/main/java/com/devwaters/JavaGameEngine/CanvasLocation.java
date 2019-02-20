package com.devwaters.JavaGameEngine;

// Game Objects are placed on this canvas location.
// Between each iteration of physics this is updated(but a seperate class is needed for momentum of object etc.)
class CanvasLocation {
    private int x;
    private int y;

    public CanvasLocation(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getAxisY(){
        return -(y-800);
    }
    public void setX(int newX) {
        x = newX;
    }
    public void setY(int newY) {
        y = newY;
    }

    //TODO vectors should not be applied within a locatin, this is a physics task
    public void applyVector(Vector vectorToApply) {
        x = x + vectorToApply.getI();
        y = y + vectorToApply.getJ();
    }

    public String toString() {
        String returnString = "";
        returnString += "( " + x;
        returnString += ", " + y + ")";
        return returnString;
    }
}