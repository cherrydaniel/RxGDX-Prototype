package com.wildcherryapps.rxgdx.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.wildcherryapps.rxgdx.sources.app.GdxAppCallback;
import com.wildcherryapps.rxgdx.sources.app.GdxAppSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RxGdxApp implements ApplicationListener {

    private final Set<GdxAppCallback> mCallbacks;

    public RxGdxApp(RxGdxStarter starter) {
        mCallbacks = Collections.synchronizedSet(new HashSet<>());

        // Launch the user app
        starter.onStart(new GdxAppSource(this));
    }

    public void addCallback(GdxAppCallback callback) {
        mCallbacks.add(callback);
    }

    public void removeCallback(GdxAppCallback callback) {
        mCallbacks.remove(callback);
    }

    @Override
    public void create() {
        synchronized (mCallbacks) {
            for (GdxAppCallback mCallback: mCallbacks)
                mCallback.onCreate();
        }
    }

    @Override
    public void resize(int width, int height) {
        synchronized (mCallbacks) {
            for (GdxAppCallback mCallback: mCallbacks)
                mCallback.onResize(width, height);
        }
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        synchronized (mCallbacks) {
            for (GdxAppCallback mCallback: mCallbacks)
                mCallback.onRender(delta);
        }
    }

    @Override
    public void pause() {
        synchronized (mCallbacks) {
            for (GdxAppCallback mCallback: mCallbacks)
                mCallback.onPause();
        }
    }

    @Override
    public void resume() {
        synchronized (mCallbacks) {
            for (GdxAppCallback mCallback: mCallbacks)
                mCallback.onResume();
        }
    }

    @Override
    public void dispose() {
        synchronized (mCallbacks) {
            for (GdxAppCallback mCallback: mCallbacks)
                mCallback.onDispose();
        }
    }

}
