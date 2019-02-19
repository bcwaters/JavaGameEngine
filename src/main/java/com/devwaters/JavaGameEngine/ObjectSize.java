package com.devwaters.JavaGameEngine;


class ObjectSize //this class is to define the amount of space the object takes up in the modeled environment. For now this is 2D(x,y)
{
    private int height;
    private int width;
    private int heightRadius;
    private int widthRadius;
    private CanvasLocation centerPoint;

    public ObjectSize(CanvasLocation paintLocation, int _height, int _width) {
        height = _height;
        width = _width;
        heightRadius = height / 2;
        widthRadius = width / 2;
        centerPoint = new CanvasLocation(paintLocation.getX() + widthRadius, paintLocation.getY() + heightRadius);
    }
    public int getHeight() {
        return height;

    }

    public int getWidth() {
        return width;
    }
    public void setHeight(int _height) {
        height = _height;
    }
    public void setWidth(int _width) {
        width = _width;
    }
    public CanvasLocation getCenterPoint() {
        return centerPoint;
    }
    public void updateCenterPoint(CanvasLocation newLocation){
        centerPoint.setX(newLocation.getX() + widthRadius);
        centerPoint.setY(newLocation.getY() + heightRadius);
    }

}
