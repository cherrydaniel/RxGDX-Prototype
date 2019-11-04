package com.wildcherryapps.rxgdx.sources.app.adapters;

import com.wildcherryapps.rxgdx.core.RxGdxApp;
import com.wildcherryapps.rxgdx.sources.lifecycle.LifecycleEvent;

import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.functions.Cancellable;

public class GdxAppCallbackHandler<T extends LifecycleEvent> extends GdxAppCallbackAdapter implements Cancellable {

    private final RxGdxApp app;
    final ObservableEmitter<T> emitter;

    public GdxAppCallbackHandler(RxGdxApp app, ObservableEmitter<T> emitter) {
        this.app = app;
        this.emitter = emitter;

        emitter.setCancellable(this);

        // Bind
        app.addCallback(this);
    }

//    @Override
//    public void onCreate() {
//        if (!emitter.isDisposed())
//            emitter.onNext(new CreateEvent());
//    }
//
//    @Override
//    public void onResize(int width, int height) {
//        if (!emitter.isDisposed())
//            emitter.onNext(new ResizeEvent(width, height));
//    }
//
//    @Override
//    public void onRender(float delta) {
//        if (!emitter.isDisposed())
//            emitter.onNext(new RenderEvent(delta));
//    }
//
//    @Override
//    public void onPause() {
//        if (!emitter.isDisposed())
//            emitter.onNext(new PauseEvent());
//    }
//
//    @Override
//    public void onResume() {
//        if (!emitter.isDisposed())
//            emitter.onNext(new ResumeEvent());
//    }
//
//    @Override
//    public void onDispose() {
//        if (!emitter.isDisposed())
//            emitter.onNext(new DisposeEvent());
//    }

    @Override
    public void cancel() {
        app.removeCallback(this);
    }

    protected ObservableEmitter<T> getEmitter() {
        return emitter;
    }

}
