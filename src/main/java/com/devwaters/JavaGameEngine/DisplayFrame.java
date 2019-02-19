package com.devwaters.JavaGameEngine;

import javax.swing.*;
import javax.swing.JPanel;

class DisplayFrame extends JFrame {
    PlayerController currentPlayerController = new PlayerController();
    char currentChar = 's';

    public DisplayFrame(String title) {
        setName(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        this.addKeyListener(currentPlayerController);
    }

    public void changeScreen(JPanel newScreen) {
        getContentPane().removeAll();
        getContentPane().add(newScreen);
    }


    public char[] getStack() //change to stack later
    {
        return currentPlayerController.getStack();
    }

}//end DisplayFrame