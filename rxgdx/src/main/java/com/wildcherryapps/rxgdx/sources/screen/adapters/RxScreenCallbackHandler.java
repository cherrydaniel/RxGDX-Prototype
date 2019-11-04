package com.wildcherryapps.rxgdx.sources.screen.adapters;

import com.wildcherryapps.rxgdx.sources.lifecycle.LifecycleEvent;
import com.wildcherryapps.rxgdx.sources.screen.RxScreen;

import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.functions.Cancellable;

public class RxScreenCallbackHandler<T extends LifecycleEvent> extends RxScreenCallbackAdapter implements Cancellable {

    private final RxScreen screen;
    final ObservableEmitter<T> emitter;

    RxScreenCallbackHandler(RxScreen screen, ObservableEmitter<T> emitter) {
        this.screen = screen;
        this.emitter = emitter;
        screen.addCallback(this);
        emitter.setCancellable(this);
    }

    @Override
    public void cancel() {
        screen.removeCallback(this);
    }

}
