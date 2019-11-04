package com.wildcherryapps.rxgdx.sources.lifecycle;

public class RenderEvent implements LifecycleEvent {

    private final float delta;

    public RenderEvent(float delta) {
        this.delta = delta;
    }

    public float getDelta() {
        return delta;
    }

}
