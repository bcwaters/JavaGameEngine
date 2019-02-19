package com.devwaters.JavaGameEngine;

import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.event.*;


class PlayerController implements KeyListener {
    char[] charArray;
    int arraySize;
    final int MAXSIZE = 100;
    String charsAdded;
    char convertedEvent;

    public PlayerController() {
        charArray = new char[MAXSIZE];

        arraySize = 0;
        charsAdded = "";

    }

    public void addToArray(char c) {

        if (arraySize < MAXSIZE) {
            charArray[arraySize] = c;
            arraySize++;
        }
    }


    public void keyPressed(KeyEvent e) {
        convertedEvent = e.getKeyChar();

        if (charsAdded.indexOf(convertedEvent) == -1) //This checks if the char is in the string
        {
            this.addToArray(convertedEvent);
            charsAdded += convertedEvent;
        }

    }

    public void keyTyped(KeyEvent e) {
        convertedEvent = e.getKeyChar();

        if (charsAdded.indexOf(convertedEvent) == -1) {
            this.addToArray(convertedEvent);
            charsAdded += convertedEvent;
        }

    }

    public void keyReleased(KeyEvent e) {
        convertedEvent = e.getKeyChar();

        if (charsAdded.indexOf(convertedEvent) == -1) {

            this.addToArray(convertedEvent);
            charsAdded += convertedEvent;
        }
    }

    public char[] getStack() //change to a stack.  Also empties the array and resets the Charadded string
    {
        char[] passArray = charArray;
        arraySize = 0;
        charArray = new char[11];
        charsAdded = "";
        return passArray;
    }

}
