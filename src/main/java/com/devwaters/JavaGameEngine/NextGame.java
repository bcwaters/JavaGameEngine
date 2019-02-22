package com.devwaters.JavaGameEngine;

public class NextGame {
    static StateProfile heroFocused = new StateProfile('w', 's', 'a', 'd', 'v', 'b');

    public static void main(String[] args) {
        DisplayFrame GameWindow = new DisplayFrame("GameWindow");
        GameWindow.setVisible(true);

        GameObject playerGameObject = new GameObject(800, 300, GameObject.PLAYER);
        EnvironmentModelData GameModelState = new EnvironmentModelData(playerGameObject,
                heroFocused, GameWindow);
        Thread GameModelThread = new Thread(GameModelState);


        GameModelState.setFocusable(true);
        while (true) {

            //Model
            GameModelThread.run();

            //Controller
            //Notify the GameModelState whenever a user presses a key
            GameModelState.pushKeyEventsToGameEventStack(GameWindow.getKeyEventStack());

            //View
            GameModelState.requestFocusInWindow();
            GameModelState.GameObjectLayer.repaint();
        }

    }
}