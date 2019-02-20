package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.util.Stack;

class DisplayFrame extends JFrame {
    PlayerController PlayerKeyListener = new PlayerController();

    public DisplayFrame(String title) {
        setName(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        this.addKeyListener(PlayerKeyListener);
    }
    public void changeScreen(JPanel newScreen) {
        getContentPane().removeAll();
        getContentPane().add(newScreen);
    }

    public Stack<Character> getKeyEventStack(){
        return PlayerKeyListener.getStack();
    }

}//end DisplayFrame