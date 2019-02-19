package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.awt.*;

class Screen extends JPanel {
    GameObject[] gameObjects;
    Graphics currentGraphics;

    public Screen(GameObject _onlyObject) {
        gameObjects = new GameObject[100];
        gameObjects[0] = _onlyObject;
        setBackground(Color.BLACK);
        setSize(300, 400);
    }//end constructor

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        gameObjects[0].paintObject(g);
        g.setColor(Color.BLUE);
        gameObjects[1].paintObject(g);
        g.drawString("yes", 55, 55);
    }

    public void setGraphics(Graphics g){
    }

    public void setArray(GameObject[] passedArray){
        gameObjects = passedArray;
    }

}//end Screen