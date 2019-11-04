package com.wildcherryapps.rxgdx.sources.app;

import com.wildcherryapps.rxgdx.core.RxGdxApp;
import com.wildcherryapps.rxgdx.sources.app.adapters.GdxAppCallbackHandler;
import com.wildcherryapps.rxgdx.sources.lifecycle.CreateEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.DisposeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.PauseEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.RenderEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.ResizeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.ResumeEvent;

import io.reactivex.rxjava3.core.Observable;

public final class GdxAppSource {

    private final RxGdxApp app;

    public GdxAppSource(RxGdxApp app) {
        this.app = app;
    }

    public Observable<CreateEvent> observeCreate() {
        return Observable.create(emitter -> new GdxAppCallbackHandler<CreateEvent>(app, emitter) {
            @Override
            public void onCreate() {
                if (!getEmitter().isDisposed())
                    getEmitter().onNext(new CreateEvent());
            }
        });
    }

    public Observable<ResizeEvent> observeResize() {
        return Observable.create(emitter -> new GdxAppCallbackHandler<ResizeEvent>(app, emitter) {
            @Override
            public void onResize(int width, int height) {
                if (!getEmitter().isDisposed())
                    getEmitter().onNext(new ResizeEvent(width, height));
            }
        });
    }

    public Observable<RenderEvent> observeRender() {
        return Observable.create(emitter -> new GdxAppCallbackHandler<RenderEvent>(app, emitter) {
            @Override
            public void onRender(float delta) {
                if (!getEmitter().isDisposed())
                    getEmitter().onNext(new RenderEvent(delta));
            }
        });
    }

    public Observable<PauseEvent> observePause() {
        return Observable.create(emitter -> new GdxAppCallbackHandler<PauseEvent>(app, emitter) {
            @Override
            public void onPause() {
                if (!getEmitter().isDisposed())
                    getEmitter().onNext(new PauseEvent());
            }
        });
    }

    public Observable<ResumeEvent> observeResume() {
        return Observable.create(emitter -> new GdxAppCallbackHandler<ResumeEvent>(app, emitter) {
            @Override
            public void onResume() {
                if (!getEmitter().isDisposed())
                    getEmitter().onNext(new ResumeEvent());
            }
        });
    }

    public Observable<DisposeEvent> observeDispose() {
        return Observable.create(emitter -> new GdxAppCallbackHandler<DisposeEvent>(app, emitter) {
            @Override
            public void onDispose() {
                if (!getEmitter().isDisposed())
                    getEmitter().onNext(new DisposeEvent());
            }
        });
    }

}
