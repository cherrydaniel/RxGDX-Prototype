package com.wildcherryapps.rxgdx.sources.lifecycle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.functions.Cancellable;

public final class GdxLifecycleSource {

    private static final class LifecycleObservable implements LifecycleListener, Cancellable {

        private final ObservableEmitter<LifecycleEvent> emitter;

        private LifecycleObservable(ObservableEmitter<LifecycleEvent> emitter) {
            this.emitter = emitter;
            Gdx.app.addLifecycleListener(this);
            emitter.setCancellable(this);
        }

        @Override
        public void pause() {
            if (!emitter.isDisposed())
                emitter.onNext(new PauseEvent());
        }

        @Override
        public void resume() {
            if (!emitter.isDisposed())
                emitter.onNext(new ResumeEvent());
        }

        @Override
        public void dispose() {
            if (!emitter.isDisposed())
                emitter.onNext(new DisposeEvent());
        }

        @Override
        public void cancel() {
            Gdx.app.removeLifecycleListener(this);
        }

    }

    public static Observable<LifecycleEvent> observe() {
        return Observable.create(LifecycleObservable::new);
    }

    private GdxLifecycleSource() {
        throw new AssertionError();
    }

}
