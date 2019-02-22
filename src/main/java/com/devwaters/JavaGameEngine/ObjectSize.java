package com.devwaters.JavaGameEngine;

//this class is to define the amount of space the object takes
// up in the modeled environment. For now this is 2D(x,y)
class ObjectSize
{
    private int height;
    private int width;
    private int heightRadius;
    private int widthRadius;


    public ObjectSize(int _width, int _height) {
        height = _height;
        width = _width;
        heightRadius = height / 2;
        widthRadius = width / 2;

    }
    public int getHeight() {return height;}
    public int getWidth() {
        return width;
    }
    public int getHeightRadius() {return heightRadius;}
    public int getWidthRadius() {return widthRadius;}
    public void setHeight(int _height) {height = _height;}
    public void setWidth(int _width) {
        width = _width;
    }

    public void updateCenterPoint(CanvasLocation newLocation){

    }

}
