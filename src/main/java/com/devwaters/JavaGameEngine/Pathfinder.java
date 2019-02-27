package com.devwaters.JavaGameEngine;


import java.util.ArrayList;
import java.util.List;

public class Pathfinder {

    GameObject placeholderLine;
    static int cellSize = 50;

    public static ArrayList<GameObject> findPath(int[] _startingPoint, int[] _endingPoint, ArrayList<GameObject> pathBlockers){

        //First check if direct path works
       // if(CollisionDetector.detectCollision())


        //new PathTree<Line>(new Line(_startingPoint[0],_startingPoint[1],_endingPoint[0],_endingPoint[1]));
        return new ArrayList<GameObject>();
    }

    private static void setPlaceholder(int x1, int y1){


    }

    private static void setCellSize(int newSize){
        cellSize = newSize;
    }


}


class PathTree<Line>{
    private Node<Line> root;

    public PathTree(Line rootData){
        root = new Node<Line>();
        root.data = rootData;
        root.children = new ArrayList<Node<Line>>();
    }
    public static class Node<Line> {
        private Line data;
        private Node<Line> parent;
        private List<Node<Line>> children;
    }

}

class Line{
    public int x1,x2,y1,y2;
    public Line(int _x1, int _y1, int _x2, int _y2){
        x1=_x1;
        x2=_x2;
        y1=_y1;
        y2=_y2;
    }

    /**
     * Converts this line
     * @return A game object representing a cell to check for collisions
     */
    public GameObject getGameObjectLine(){
        return new GameObject(  new CanvasLocation(x1, y1),
                                new ObjectSize(Pathfinder.cellSize, Pathfinder.cellSize),
                                GameObject.FIXED );
    }
}