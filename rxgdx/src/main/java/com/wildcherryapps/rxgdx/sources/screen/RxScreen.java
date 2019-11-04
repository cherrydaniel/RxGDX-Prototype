package com.wildcherryapps.rxgdx.sources.screen;

import com.badlogic.gdx.Screen;
import com.wildcherryapps.rxgdx.sources.lifecycle.DisposeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.HideEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.PauseEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.RenderEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.ResizeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.ResumeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.ShowEvent;
import com.wildcherryapps.rxgdx.sources.screen.adapters.RxScreenCallbackHandlers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.reactivex.rxjava3.core.Observable;

public class RxScreen implements Screen {

    private final Set<RxScreenCallback> mCallbacks;

    public RxScreen() {
        mCallbacks = Collections.synchronizedSet(new HashSet<>());
    }


    // -------- Callback Add/Remove

    public void addCallback(RxScreenCallback callback) {
        mCallbacks.add(callback);
    }

    public void removeCallback(RxScreenCallback callback) {
        mCallbacks.remove(callback);
    }


    // -------- Observe Methods

    public final Observable<ShowEvent> observeShow() {
        return Observable.create(emitter
                -> RxScreenCallbackHandlers.show(this, emitter));
    }

    public final Observable<RenderEvent> observeRender() {
        return Observable.create(emitter
                -> RxScreenCallbackHandlers.render(this, emitter));
    }

    public final Observable<ResizeEvent> observeResize() {
        return Observable.create(emitter
                -> RxScreenCallbackHandlers.resize(this, emitter));
    }

    public final Observable<PauseEvent> observePause() {
        return Observable.create(emitter
                -> RxScreenCallbackHandlers.pause(this, emitter));
    }

    public final Observable<ResumeEvent> observeResume() {
        return Observable.create(emitter
                -> RxScreenCallbackHandlers.resume(this, emitter));
    }

    public final Observable<HideEvent> observeHide() {
        return Observable.create(emitter
                -> RxScreenCallbackHandlers.hide(this, emitter));
    }

    public final Observable<DisposeEvent> observeDispose() {
        return Observable.create(emitter
                -> RxScreenCallbackHandlers.dispose(this, emitter));
    }


    // -------- Screen Methods

    @Override
    public void show() {
        synchronized (mCallbacks) {
            for (RxScreenCallback callback: mCallbacks)
                callback.onShow();
        }
    }

    @Override
    public void render(float delta) {
        synchronized (mCallbacks) {
            for (RxScreenCallback callback: mCallbacks)
                callback.onRender(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        synchronized (mCallbacks) {
            for (RxScreenCallback callback: mCallbacks)
                callback.onResize(width, height);
        }
    }

    @Override
    public void pause() {
        synchronized (mCallbacks) {
            for (RxScreenCallback callback: mCallbacks)
                callback.onPause();
        }
    }

    @Override
    public void resume() {
        synchronized (mCallbacks) {
            for (RxScreenCallback callback: mCallbacks)
                callback.onResume();
        }
    }

    @Override
    public void hide() {
        synchronized (mCallbacks) {
            for (RxScreenCallback callback: mCallbacks)
                callback.onHide();
        }
    }

    @Override
    public void dispose() {
        synchronized (mCallbacks) {
            for (RxScreenCallback callback: mCallbacks)
                callback.onDispose();
        }
    }

}
