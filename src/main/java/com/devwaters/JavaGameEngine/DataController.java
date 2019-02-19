package com.devwaters.JavaGameEngine;

import java.awt.*;
import javax.swing.*;


class DataController extends JFrame implements Runnable // this class will hold objects to be shared by other classes along with those classes.
{
    char[] charEvents;
    GameObject[] gameObjects;
    GameEvent[] keyEvents;

    GameEvent[] physicsEvents;

    int gameEventArraySize = 0;

    GameObject testerObject;
    EnvironmentPhysics currentEnvironmentPhysics;
    Screen currentScreen;
    GameObject onlyGameObject;
    StateProfile currentProfile;
    Graphics panelCanvas;

    public DataController(GameObject passedObject, StateProfile initialProfile) {
        gameObjects = new GameObject[100];
        charEvents = new char[100];
        keyEvents = new GameEvent[100];
        physicsEvents = new GameEvent[100];

        clearGameEvents(physicsEvents);
        clearGameEvents(keyEvents);
        clearGameObjects();

        testerObject = passedObject;
        gameObjects[0] = passedObject;

        gameObjects[1] = new GameObject(90, 90);//tester object to paint


        currentEnvironmentPhysics = new EnvironmentPhysics();
        currentScreen = new Screen(passedObject);
        currentProfile = initialProfile;
    }

    public void getKeyEvents(char[] passedArray) //change to detailed key Events. Time held down, quick presseds etc.
    {
        charEvents = passedArray;
    }

    public void processGameEvents(GameEvent[] eventsPassed) {
        //System.out.println("This is before the for loop Event" + eventsPassed[0]);
        for (int i = 0; i < eventsPassed.length; i++) {
            GameEvent currentLoopEvent = eventsPassed[i];
            if (currentLoopEvent == null) {
                currentLoopEvent = new GameEvent();
            }


            if (currentLoopEvent.getEventType() != currentLoopEvent.NULLEVENT) {
                gameObjects[currentLoopEvent.getObjectLocation()].processGameEvent(currentLoopEvent); //if this is not a null event, get array index for object and process event
                System.out.println(gameObjects[0]);
            }
        }
    }

    public void clearGameEvents(GameEvent[] events) {
        for (int i = 0; i < events.length; i++)  //sets all spots to empty game events
        {
            events[i] = new GameEvent();
        }
    }

    public void clearVectors() {
        gameObjects[0].clearVector();
    }

    public GameEvent[] charEventToKeyEvent(char[] charArray) {

        CharacterInterpreter current = new CharacterInterpreter(currentProfile);
        for (int i = 0; charArray[i] != '\0'; i++) {
            keyEvents[i] = current.getGameEvent(charArray[i]);

            if (i < keyEvents.length) {
                keyEvents[i + 1] = new GameEvent();
            }

        }
        return keyEvents;

    }

    public void run() {


        currentScreen.setArray(gameObjects);                        //this section passes arrays before any changes are made
        currentEnvironmentPhysics.setArray(gameObjects);


        clearGameEvents(physicsEvents);
        clearGameEvents(keyEvents);

        currentEnvironmentPhysics.applySpeedLimit();                // allow everything to process data
        currentEnvironmentPhysics.applyVectorDecay();


        keyEvents = charEventToKeyEvent(charEvents);                                //fix this to have an array for keyEvents
        physicsEvents = currentEnvironmentPhysics.returnChanges();    //this section creates events to be processed


        //System.out.println("this is good, the physics events are coming in \n:" + physicsEvents[0]);

        //System.out.println("before processing thsi is object: " + gameObjects[0]);
        //System.out.println("this is the event sent: " +  physicsEvents[0]);
        processGameEvents(physicsEvents);

        //System.out.println("fater processing thsi is object: " + gameObjects[0]);


        processGameEvents(keyEvents);                            //process and empty the events

        gameObjects[0].update();                                        //update locations(translate vectors)

        try {
            Thread.sleep(100);

        } catch (Exception e)

        {

        }

    }//end run

    public void clearGameObjects() {
        for (int i = 0; i < gameObjects.length; i++) {
            gameObjects[i] = new GameObject();
        }

    }

    public void recieveGraphics(Graphics g) {
        panelCanvas = g;
    }

    public String toString() {

        String outputString = "";

        for (int i = 0; charEvents[i] != '\0'; i++) {
            outputString += keyEvents[i];
        }

        return outputString;
    }

}// end PlayerContoller