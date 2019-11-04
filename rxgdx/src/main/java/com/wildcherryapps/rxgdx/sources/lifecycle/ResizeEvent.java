package com.wildcherryapps.rxgdx.sources.lifecycle;

public class ResizeEvent implements LifecycleEvent {

    private final int width, height;

    public ResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
