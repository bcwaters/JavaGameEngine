package com.devwaters.JavaGameEngine;
//this class will be used by StateProfile record and can be thought of as profiles
class CharacterInterpreter {
    GameEvent event = new GameEvent(); // this event is used to get eventCodes
    StateProfile profile;
    //StateProfiles will have a focusedId which can represent a menu, hero focus, vehicle focus, etc

    //That way gameEvents return can be generalized
    public CharacterInterpreter(StateProfile _stateProfile) {
        profile = _stateProfile;
    }

    public GameEvent getGameEvent(char c)  //later this method will check the profile for focus
    {//I am going to find a better place to put the specific code for states and remove this
        if (profile.keysUsed.indexOf(c) != -1){
            if (c == profile.up) {
                return new GameEvent(event.MOVE_HERO_UP);
            }
            if (c == profile.down) {
                return new GameEvent(event.MOVE_HERO_DOWN);
            }
            if (c == profile.left) {
                return new GameEvent(event.MOVE_HERO_LEFT);
            }
            if (c == profile.right) {
                return new GameEvent(event.MOVE_HERO_RIGHT);
            }

        }

        return new GameEvent();
    }

}
