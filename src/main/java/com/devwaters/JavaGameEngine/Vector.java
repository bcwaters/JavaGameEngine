package com.devwaters.JavaGameEngine;
class Vector{
    private int i;
    private int j;

    public Vector(int _i, int _j) {
        i = _i;
        j = _j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void addI(int x) {
        i += x;
    }

    public void addJ(int y) {
        j += y;
    }

    public void setI(int _i) {
        i = _i;
    }

    public void setJ(int _j) {
        j = _j;
    }

    public void add(Vector _Vector) {
        this.setJ(this.j + _Vector.getJ());
        this.setI(this.i + _Vector.getI());
    }


    public String toString() {
        String returnString = "";
        returnString += "i: " + i + " " + "j: " + j;
        return returnString;
    }

    public Vector getMagnitude() {
        int magI;
        int magJ;
        // thse two statements return the absolute value of the vector
        if (i < 0){
            magI = -i;
        } else {
            magI = i;
        }
        if (j < 0) {
            magJ = -j;
        } else {
            magJ = j;
        }

        return new Vector(magI, magJ);
    }
}
