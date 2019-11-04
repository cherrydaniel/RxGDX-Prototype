package com.wildcherryapps.rxgdx.sources.input;

public class TouchDraggedEvent implements InputEvent {

    private final int screenX, screenY, pointer;

    public TouchDraggedEvent(int screenX, int screenY, int pointer) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.pointer = pointer;
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

}
