package com.devwaters.JavaGameEngine;

public class NextGame {
    static StateProfile heroFocused = new StateProfile('w', 's', 'a', 'd', 'v', 'b');

    public static void main(String[] args) {

        GameObject playerGameObject = new GameObject(55, 55, GameObject.HERO);
        DataController GameState = new DataController(playerGameObject, heroFocused);
        Thread t1 = new Thread(GameState);
        DisplayFrame gameWindow = new DisplayFrame("GameWindow");
        gameWindow.setVisible(true);
        gameWindow.changeScreen(GameState.currentScreen);
        GameState.setFocusable(true);
        while (true) {

            //Model
            t1.run();

            //Controller
            //Notify the GameState whenever a user presses a key
            GameState.pushKeyEventsToGameEventStack(gameWindow.getKeyEventStack());

            //View
            GameState.requestFocusInWindow();
            GameState.currentScreen.repaint();
        }

    }
}