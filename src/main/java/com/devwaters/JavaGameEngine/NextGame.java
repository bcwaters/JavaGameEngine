package com.devwaters.JavaGameEngine;

public class NextGame {
    static StateProfile heroFocused = new StateProfile('w', 's', 'a', 'd', 'v', 'b');

    public static void main(String[] args) {

        GameObject onlyGameObject = new GameObject(55, 55);
        DataController testData = new DataController(onlyGameObject, heroFocused);

        Thread t1 = new Thread(testData);


        DisplayFrame gameWindow = new DisplayFrame("GameWindow");
        gameWindow.setVisible(true);
        gameWindow.changeScreen(testData.currentScreen);


        testData.setFocusable(true);

        while (true) {
            t1.run();
            testData.getKeyEvents(gameWindow.getStack());
            testData.requestFocusInWindow();
            testData.currentScreen.repaint();

            System.out.println(testData);
        }

    }
}