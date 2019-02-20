package com.devwaters.JavaGameEngine;
import java.util.Hashtable;

class StateProfile {
    private Hashtable<Character, Integer > keyMapping = new Hashtable<Character, Integer >();
    public StateProfile(){
        keyMapping.put('w', KeyPressedEvent.MOVE_HERO_UP);
        keyMapping.put('s', KeyPressedEvent.MOVE_HERO_DOWN);
        keyMapping.put('a', KeyPressedEvent.MOVE_HERO_LEFT);
        keyMapping.put('d', KeyPressedEvent.MOVE_HERO_RIGHT);
        keyMapping.put('r', KeyPressedEvent.HERO_COMMAND_01);
        keyMapping.put('e', KeyPressedEvent.HERO_COMMAND_02);
    }
    public StateProfile(char _up, char _down, char _left, char _right, char _command_01, char _command_02) {

        keyMapping.put(_up, KeyPressedEvent.MOVE_HERO_UP);
        keyMapping.put(_down, KeyPressedEvent.MOVE_HERO_DOWN);
        keyMapping.put(_left, KeyPressedEvent.MOVE_HERO_LEFT);
        keyMapping.put(_right, KeyPressedEvent.MOVE_HERO_RIGHT);
        keyMapping.put(_command_01, KeyPressedEvent.HERO_COMMAND_01);
        keyMapping.put(_command_02, KeyPressedEvent.HERO_COMMAND_02);
    }
    //later this method will check the profile for focus
    public KeyPressedEvent getGameEvent(char c) {
        //I am going to find a better place to put the specific code for states and remove this
        Integer eventCode = keyMapping.get(c);
        return eventCode == null ? new KeyPressedEvent() : new KeyPressedEvent(eventCode) ;
    }
}