package com.devwaters.JavaGameEngine;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

class PlayerController implements KeyListener {
    char[] charArray;
    Stack<Character> keyPressedStack;
    int arraySize;
    final int MAXSIZE = 100;
    String charsAdded;
    char convertedEvent;

    public PlayerController() {
        charArray = new char[MAXSIZE];
        keyPressedStack = new Stack<Character>();
        arraySize = 0;
        charsAdded = "";
    }
    public void addToArray(char c) {
        if (arraySize < MAXSIZE) {
            charArray[arraySize] = c;
            arraySize++;
        }
        keyPressedStack.push(c);
    }

    //TODO i believe the Keyevent needs to check for keycodes instead
    //This will allow all keys on the keyboard
    public void keyPressed(KeyEvent e) {
        //This checks if the char is in the string
        if (charsAdded.indexOf(convertedEvent) == -1){
            this.addToArray(convertedEvent);
            charsAdded += convertedEvent;
        }
    }

    public void keyTyped(KeyEvent e) {
        convertedEvent = e.getKeyChar();
        if (charsAdded.indexOf(convertedEvent) == -1){
            this.addToArray(convertedEvent);
            charsAdded += convertedEvent;
        }
    }

    public void keyReleased(KeyEvent e) {
        convertedEvent = e.getKeyChar();
        if (charsAdded.indexOf(convertedEvent) == -1){
            this.addToArray(convertedEvent);
            charsAdded += convertedEvent;
        }
    }
    //TODO change to a stack.  Also empties the array and resets the Charadded string
    public Stack<Character> getStack(){
        char[] passArray = charArray;
        arraySize = 0;
        charArray = new char[11];
        charsAdded = "";
        return keyPressedStack;
    }

}
