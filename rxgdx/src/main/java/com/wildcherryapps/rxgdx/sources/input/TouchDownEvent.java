package com.wildcherryapps.rxgdx.sources.input;

public class TouchDownEvent implements InputEvent {

    private final int screenX, screenY, pointer, button;

    public TouchDownEvent(int screenX, int screenY, int pointer, int button) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.pointer = pointer;
        this.button = button;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getPointer() {
        return pointer;
    }

    public int getButton() {
        return button;
    }

}
