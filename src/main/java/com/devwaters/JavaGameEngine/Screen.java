package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;


class Screen extends JPanel {
    ArrayList<GameObject> gameObjectArrayList;
    boolean tempCollisionCheck = false;
    GameObject player;
    DisplayFrame viewport;
    Graphics gClone;

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

    private void setScreenFocus(Graphics g){
        xFocus = player.location.getX() - viewport.getWidth()/2;
        yFocus = 0;
        //this.setLocation(this.getX() - xFocus ,0);
        g.translate( xFocus , 0);

    }

    private void paintUI(Graphics g){
        g.setColor(Color.RED);
        g.drawString("playerLocation: " + player.location.getX() + ", " + player.location.getY(), 500,650);
        g.drawString("screenLocation: " + this.getLocation().toString(), 500,400);
        g.drawString("viewport width: " + viewport.getWidth(), 500,300);
        g.drawString("Current Collision: " + tempCollisionCheck, 500, 600);
        g.drawString("graphics location: " + g.getClipBounds().toString(), 500, 450);
        tempCollisionCheck = false;
    }
    public void setGraphics(Graphics g){
    }

    public void setTempCollisionCheck(boolean collisionPresent){
        tempCollisionCheck = collisionPresent;
    }


}//end Screen