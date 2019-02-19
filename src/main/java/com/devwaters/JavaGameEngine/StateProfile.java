package com.devwaters.JavaGameEngine;


class StateProfile {
    String keysUsed;
    GameEvent EventType = new GameEvent();

    char up;
    char down;
    char left;
    char right;

    char command_01;
    char command_02;

    public StateProfile(char _up, char _down, char _left, char _right, char _command_01, char _command_02) {
        up = _up;
        down = _down;
        left = _left;
        right = _right;

        command_01 = _command_01;
        command_02 = _command_02;

        keysUsed = up + "" + down + "" + left + "" + right + "" + command_01 + "" + command_02;

    }
}