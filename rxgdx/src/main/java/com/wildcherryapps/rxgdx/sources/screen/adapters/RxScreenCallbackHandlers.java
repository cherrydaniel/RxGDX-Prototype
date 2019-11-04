package com.wildcherryapps.rxgdx.sources.screen.adapters;

import com.wildcherryapps.rxgdx.sources.lifecycle.DisposeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.HideEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.LifecycleEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.PauseEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.RenderEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.ResizeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.ResumeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.ShowEvent;
import com.wildcherryapps.rxgdx.sources.screen.RxScreen;

import io.reactivex.rxjava3.core.ObservableEmitter;

/**
 * Utility class for providing {@link RxScreen} handlers for each {@link LifecycleEvent}
 */
public final class RxScreenCallbackHandlers {

    public static void show(RxScreen screen, ObservableEmitter<ShowEvent> emitter) {
        new RxScreenCallbackHandler<ShowEvent>(screen, emitter) {
            @Override
            public void onShow() {
                if (!emitter.isDisposed())
                    emitter.onNext(new ShowEvent());
            }
        };
    }

    public static void render(RxScreen screen, ObservableEmitter<RenderEvent> emitter) {
        new RxScreenCallbackHandler<RenderEvent>(screen, emitter) {
            @Override
            public void onRender(float delta) {
                if (!emitter.isDisposed())
                    emitter.onNext(new RenderEvent(delta));
            }
        };
    }

    public static void resize(RxScreen screen, ObservableEmitter<ResizeEvent> emitter) {
        new RxScreenCallbackHandler<ResizeEvent>(screen, emitter) {
            @Override
            public void onResize(int width, int height) {
                if (!emitter.isDisposed())
                    emitter.onNext(new ResizeEvent(width, height));
            }
        };
    }

    public static void pause(RxScreen screen, ObservableEmitter<PauseEvent> emitter) {
        new RxScreenCallbackHandler<PauseEvent>(screen, emitter) {
            @Override
            public void onPause() {
                if (!emitter.isDisposed())
                    emitter.onNext(new PauseEvent());
            }
        };
    }

    public static void resume(RxScreen screen, ObservableEmitter<ResumeEvent> emitter) {
        new RxScreenCallbackHandler<ResumeEvent>(screen, emitter) {
            @Override
            public void onResume() {
                if (!emitter.isDisposed())
                    emitter.onNext(new ResumeEvent());
            }
        };
    }

    public static void hide(RxScreen screen, ObservableEmitter<HideEvent> emitter) {
        new RxScreenCallbackHandler<HideEvent>(screen, emitter) {
            @Override
            public void onHide() {
                if (!emitter.isDisposed())
                    emitter.onNext(new HideEvent());
            }
        };
    }

    public static void dispose(RxScreen screen, ObservableEmitter<DisposeEvent> emitter) {
        new RxScreenCallbackHandler<DisposeEvent>(screen, emitter) {
            @Override
            public void onDispose() {
                if (!emitter.isDisposed())
                    emitter.onNext(new DisposeEvent());
            }
        };
    }

    private RxScreenCallbackHandlers() {
        throw new AssertionError();
    }

}
