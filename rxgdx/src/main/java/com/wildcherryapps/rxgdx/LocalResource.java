package com.wildcherryapps.rxgdx;

import com.badlogic.gdx.utils.Disposable;

// TODO: Implement Closeable or finalize
public final class LocalResource<T extends Disposable> implements AutoCloseable {

    private static final class State<T extends Disposable> implements Runnable {

        private final T resource;

        private State(T resource) {
            this.resource = resource;
        }

        private T getResource() {
            return resource;
        }

        @Override
        public void run() {
            resource.dispose();
        }

    }

    private final State<T> state;

    public LocalResource(T resource) {
        this.state = new State<>(resource);
    }

    @Override
    public void close() {
        state.run();
    }

    public T get() {
        return state.getResource();
    }

}
