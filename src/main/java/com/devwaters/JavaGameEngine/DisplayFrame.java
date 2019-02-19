package com.devwaters.JavaGameEngine;

import javax.swing.*;

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

    public char[] getStack(){
        return currentPlayerController.getStack();
    }

}//end DisplayFrame