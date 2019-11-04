package com.wildcherryapps.rxgdx.sources.input;

public class KeyUpEvent implements InputEvent {

    private final int keycode;

    public KeyUpEvent(int keycode) {
        this.keycode = keycode;
    }

    public int getKeycode() {
        return keycode;
    }

}
