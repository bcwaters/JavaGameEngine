package com.devwaters.JavaGameEngine;

import javax.swing.*;
import java.util.Stack;

class DisplayFrame extends JFrame {
    PlayerController PlayerKeyListener = new PlayerController();
    PlayerMouseListener clickMouseListener = new PlayerMouseListener();

    public DisplayFrame(String title) {
        setName(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 1600);
        this.addKeyListener(PlayerKeyListener);
    }
    public void changeScreen(JPanel newScreen) {
        getContentPane().removeAll();
        newScreen.addMouseListener(clickMouseListener);
        getContentPane().add(newScreen);
    }

    public Stack<Character> getKeyEventStack(){
        return PlayerKeyListener.getStack();
    }
    public Stack<int[]> getClickEventStack(){
        return clickMouseListener.getClickStack();
    }

}//end DisplayFrame