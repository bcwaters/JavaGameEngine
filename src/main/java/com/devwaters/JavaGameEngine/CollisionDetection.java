package com.devwaters.JavaGameEngine;

//the environment physics will have a collision detection unit that returns events to add.
class CollisionDetection {

    int futureDistance;

    private GameObject object_01;  //object to change
    private GameObject object_02;    //object to compare

    public CollisionDetection() {

    }

    //two object are passed for comparison
    public boolean detectCollision(GameObject _01, GameObject _02){
        object_01 = _01;
        object_02 = _02;

        //Simple detection method by checking if squares around each object overlap
        return isBoxCollision();
    }

    public boolean isBoxCollision(){
        int minXDistance = object_01.size.getWidthRadius() + object_02.size.getWidthRadius();
        int minYDistance = object_01.size.getHeightRadius() + object_02.size.getHeightRadius();
        return (calculateXDistance() <= minXDistance && calculateYDistance() <= minYDistance);
    }

    //TODO What is this? Maybe this is to check for collission before update the object...
    private int calculateFutureDistance(GameObject _01, GameObject _02) {
        int distanceToReturn = 0;
        //I should make a location function that is pass a vector to update
        CanvasLocation futureLocation_01 = _01.getCanvasLocation();  //This is all preparation below
        CanvasLocation futureLocation_02 = _02.getCanvasLocation();
        futureLocation_01.applyVector(_01.getObjectVector());
        futureLocation_02.applyVector(_02.getObjectVector());

        return distanceToReturn;
    }

    //I am using a sqrt but i can optimize this
    private int calculateDistance() {
        return (int) Math.sqrt(Math.pow(calculateXDistance(), 2)
                + Math.pow(calculateYDistance(), 2));
    }

    private int calculateXDistance(){
        return Math.abs(object_01.location.getX() - object_02.location.getX());
    }

    private int calculateYDistance(){
        return Math.abs(object_01.location.getAxisY() - object_02.location.getAxisY());
    }

    public String toString() {
        String stringToReturn = "";
        stringToReturn += "xDistance:" + calculateXDistance() +
                "\nYdistance:" + calculateYDistance()+ "\n";
        return stringToReturn;
    }

}