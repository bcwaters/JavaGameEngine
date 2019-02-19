package com.devwaters.JavaGameEngine;

class CollisionDetection //the environment physics will have a collision detection unit that returns events to add.
{

    int currentDistance;
    int futureDistance;

    int xDistance;
    int yDistance;

    int object_01_WidthRadius;
    int object_02_WidthRadius;
    int object_01_LengthRadius;
    int object_02_LengthRadius;

    GameObject object_01;  //object to change

    GameObject object_02;    //object to compare

    GameEvent event = new GameEvent(); // added this to access event types for passing

    public CollisionDetection() {
        object_01 = new GameObject(20, 30);
        object_01 = new GameObject(50, 50);

        xDistance = calculateXDistance(object_01.getCanvasLocation(), object_02.getCanvasLocation());
        yDistance = calculateYDistance(object_01.getCanvasLocation(), object_02.getCanvasLocation());
    }

    public CollisionDetection(GameObject _01, GameObject _02)//this is a tester constructor, won't be used
    {
        object_01 = _01;
        object_02 = _02;


        xDistance = calculateXDistance(object_01.getCanvasLocation(), object_02.getCanvasLocation());
        yDistance = calculateYDistance(object_01.getCanvasLocation(), object_02.getCanvasLocation());

    }

    public void detectCollision(GameObject _01, GameObject _02) //two object are passed for comparison
    {
        object_01 = _01;
        object_02 = _02;
    }

    public int calculateFutureDistance(GameObject _01, GameObject _02) {
        int distanceToReturn = 0;


        CanvasLocation futureLocation_01 = _01.getCanvasLocation();  //This is all preparation below
        CanvasLocation futureLocation_02 = _02.getCanvasLocation();

        futureLocation_01.applyVector(_01.getObjectVector()); //I should make a location function that is pass a vector to update
        futureLocation_02.applyVector(_02.getObjectVector());


        //futureDistance =  calculateDistance();		//Almost did an approach recursive in nature, changed mind
        return distanceToReturn;
    }

    public int calculateDistance(CanvasLocation location1, CanvasLocation location2)  //I am using a sqrt but i can optimize this
    {
        return (int) Math.sqrt(Math.pow(calculateXDistance(location1, location2), 2) + Math.pow(calculateYDistance(location1, location2), 2));
    }

    public int calculateXDistance(CanvasLocation location1, CanvasLocation location2) {
        return Math.abs(location1.getY() - location2.getY());
    }

    public int calculateYDistance(CanvasLocation location1, CanvasLocation location2) {
        return Math.abs(location1.getY() - location2.getY());
    }

    public String toString() {
        String stringToReturn = "";
        stringToReturn += "xDistance:" + xDistance + "\nYdistance:" + yDistance + "\n Distance apart:" + currentDistance;
        return stringToReturn;
    }

}