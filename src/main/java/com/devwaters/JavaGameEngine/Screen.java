package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

class Screen extends JPanel {
    ArrayList<GameObject> gameObjectArrayList;
    boolean tempCollisionCheck = false;

    public Screen(GameObject _onlyObject) {
        setBackground(Color.BLACK);
        setSize(300, 400);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gameObjectArrayList.forEach(gameObject -> gameObject.paintObject(g) );
       paintUI(g);
    }

    private void paintUI(Graphics g){
        g.setColor(Color.RED);
        g.drawString("Current Collision: " + tempCollisionCheck, 500, 600);
    }
    public void setGraphics(Graphics g){
    }

    public void setTempCollisionCheck(boolean collisionPresent){
        tempCollisionCheck = collisionPresent;
    }

    public void setArrayList(ArrayList<GameObject> passedArray){
        gameObjectArrayList = passedArray;
    }

}//end Screen