package com.devwaters.JavaGameEngine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

public class PlayerMouseListener extends MouseAdapter {
    Stack<int[]> clickStack = new Stack<int[]>();

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        clickStack.push(new int[]{x,y});
    }

    public Stack<int[]> getClickStack(){
        return clickStack;
    }

}
