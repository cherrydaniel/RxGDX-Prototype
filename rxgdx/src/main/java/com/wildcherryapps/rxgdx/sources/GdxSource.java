package com.wildcherryapps.rxgdx.sources;

import com.wildcherryapps.rxgdx.sources.input.GdxInputSource;
import com.wildcherryapps.rxgdx.sources.input.InputEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.GdxLifecycleSource;
import com.wildcherryapps.rxgdx.sources.lifecycle.LifecycleEvent;

import io.reactivex.rxjava3.core.Observable;

public final class GdxSource {

    public static Observable<LifecycleEvent> lifecycle() {
        return GdxLifecycleSource.observe();
    }

    public static Observable<InputEvent> input() {
        return GdxInputSource.observe();
    }

    private GdxSource() {
        throw new AssertionError();
    }

}
