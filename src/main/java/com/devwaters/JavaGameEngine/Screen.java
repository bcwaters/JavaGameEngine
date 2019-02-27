package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;


class Screen extends JPanel {
    ArrayList<GameObject> gameObjectArrayList;
    boolean tempCollisionCheck = false;
    GameObject player;
    DisplayFrame viewport;
    Graphics gClone;
    int[] locationClicked = new int[]{0,0,0,0};
    boolean drawMouseClicked = false;

    int xFocus;
    int yFocus;

    public Screen(ArrayList<GameObject> gameObjects, DisplayFrame _viewport){
        viewport = _viewport;
        gameObjectArrayList = gameObjects;
        this.setBackground(Color.BLACK);
        setSize(3200,1600);
        player = gameObjectArrayList.get(0);
        xFocus = 0;
        yFocus = 0;
    }

    public void paintComponent(Graphics g){
        gClone = g;
        //g.clearRect(xFocus,getY(),this.getWidth(),1200);
        this.setBackground(Color.BLACK);
        //setScreenFocus(g);
        this.setLocation(0, this.getY());
        super.paintComponent(g);
        gameObjectArrayList.forEach(gameObject -> {
            gameObject.paintObject(g);
        } );
        paintUI(g);
    }

    public void moveCameraRight(Graphics g){
        g.translate( 500 , 0);

    }

    //TODO implement pathfinding for drawing the shortpath from start to finish with no collisions
    public void setLineToDraw(int[] coordPair){
       locationClicked = coordPair;
        drawMouseClicked = true;

    }

    private void setScreenFocus(Graphics g){
        xFocus = player.location.getX() - viewport.getWidth()/2;
        yFocus = 0;
        //this.setLocation(this.getX() - xFocus ,0);
        g.translate( xFocus , 0);

    }

    private void paintUI(Graphics g){
        if(drawMouseClicked) {
            g.setColor(Color.WHITE);
            g.drawLine(locationClicked[0], locationClicked[1], gameObjectArrayList.get(0).location.getX(), gameObjectArrayList.get(0).location.getY());
        }
        g.setColor(Color.RED);
        g.setColor(Color.RED);
        g.drawString("available controls: w,s,a,d, LEFTCLICK" ,900,200 );
        g.drawString("playerLocation: " + player.location.getX() + ", " + player.location.getY(), 900,650);
        g.drawString("screenLocation: " + this.getLocation().toString(), 900,400);
        g.drawString("viewport width: " + viewport.getWidth(), 900,300);
        g.drawString("Current Collision: " + tempCollisionCheck, 900, 600);
        g.drawString("graphics location: " + g.getClipBounds().toString(), 900, 450);
        tempCollisionCheck = false;
    }
    public void setGraphics(Graphics g){
    }

    public void setTempCollisionCheck(boolean collisionPresent){
        tempCollisionCheck = collisionPresent;
    }


}//end Screen