package com.wildcherryapps.rxgdx.sources.input;

public class MouseMovedEvent implements InputEvent {

    private final int screenX, screenY;

    public MouseMovedEvent(int screenX, int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

}
