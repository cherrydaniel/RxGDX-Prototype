package com.wildcherryapps.rxgdx.sources.input;

public class KeyDownEvent implements InputEvent {

    private final int keycode;

    public KeyDownEvent(int keycode) {
        this.keycode = keycode;
    }

    public int getKeycode() {
        return keycode;
    }

}
