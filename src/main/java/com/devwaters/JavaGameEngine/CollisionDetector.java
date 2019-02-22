package com.devwaters.JavaGameEngine;

//the environment physics will have a collision detection unit that returns events to add.
class CollisionDetector {

    int futureDistance;
    public CollisionDetector() {

    }

    /**
     * Checks for collision between two objects
     * @param object_01 Object to be modified upon a collision
     * @param object_02 Object to check
     * @return returns 0 for no collision, 1 for collision found. this is to allow boolean addition
     */
    public static boolean detectCollision(GameObject object_01, GameObject object_02){
        //Simple detection method by checking if squares around each object overlap
        return checkBoxCollision(object_01, object_02);

    }

    public static boolean checkBoxCollision(GameObject object_01, GameObject object_02){
        int minXDistance = object_01.getWidthRadius() + object_02.getWidthRadius();
        int minYDistance = object_01.getHeightRadius() + object_02.getHeightRadius();

        return (detectXCollision(object_01,object_02) && detectYCollision(object_01, object_02));
    }

    public static boolean detectXCollision(GameObject object_01, GameObject object_02){
        int minXDistance = object_01.getWidthRadius() + object_02.getWidthRadius();
        return (calculateXDistance(object_01, object_02) < minXDistance);
    }
    public static boolean detectYCollision(GameObject object_01, GameObject object_02){
        int minYDistance = object_01.getHeightRadius() + object_02.getHeightRadius();
        return (calculateYDistance(object_01, object_02) < minYDistance);
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

//    //I am using a sqrt but i can optimize this
//    private int calculateDistance() {
//        return (int) Math.sqrt(Math.pow(calculateXDistance(), 2)
//                + Math.pow(calculateYDistance(), 2));
//    }

    private static int calculateXDistance(GameObject object_01, GameObject object_02){
        int xDistance =  Math.abs(object_01.getCenterPointX() - object_02.getCenterPointX());
        return xDistance;
    }

    private static int calculateYDistance(GameObject object_01, GameObject object_02){
        int yDistance = Math.abs(object_01.getCenterPointY() - object_02.getCenterPointY());
        return yDistance;
    }

}