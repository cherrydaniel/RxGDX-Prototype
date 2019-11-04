package com.wildcherryapps.rxgdx.sources.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.functions.Cancellable;

public final class GdxInputSource {

    private static final class InputObservable implements InputProcessor, Cancellable {

        private final ObservableEmitter<InputEvent> emitter;

        private InputObservable(ObservableEmitter<InputEvent> emitter) {
            this.emitter = emitter;

            // Plug this processor to the app
            InputProcessor currentProcessor = Gdx.input.getInputProcessor();
            InputMultiplexer multiplexer;

            if (currentProcessor instanceof InputMultiplexer) {
                multiplexer = (InputMultiplexer) currentProcessor;
            } else if (currentProcessor == null) {
                multiplexer = new InputMultiplexer();
            } else {
                multiplexer = new InputMultiplexer(currentProcessor);
            }

            multiplexer.addProcessor(this);
            Gdx.input.setInputProcessor(multiplexer);

            emitter.setCancellable(this);
        }

        @Override
        public boolean keyDown(int keycode) {
            if (!emitter.isDisposed()) {
                emitter.onNext(new KeyDownEvent(keycode));
                return true;
            }
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            if (!emitter.isDisposed()) {
                emitter.onNext(new KeyUpEvent(keycode));
                return true;
            }
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            if (!emitter.isDisposed()) {
                emitter.onNext(new KeyTypedEvent(character));
                return true;
            }
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (!emitter.isDisposed()) {
                emitter.onNext(new TouchDownEvent(screenX, screenY, pointer, button));
                return true;
            }
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if (!emitter.isDisposed()) {
                emitter.onNext(new TouchUpEvent(screenX, screenY, pointer, button));
                return true;
            }
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            if (!emitter.isDisposed()) {
                emitter.onNext(new TouchDraggedEvent(screenX, screenY, pointer));
                return true;
            }
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            if (!emitter.isDisposed()) {
                emitter.onNext(new MouseMovedEvent(screenX, screenY));
                return true;
            }
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            if (!emitter.isDisposed()) {
                emitter.onNext(new ScrolledEvent(amount));
                return true;
            }
            return false;
        }

        @Override
        public void cancel() {
            InputProcessor currentProcessor = Gdx.input.getInputProcessor();
            if (currentProcessor instanceof InputMultiplexer) {
                ((InputMultiplexer) currentProcessor).removeProcessor(this);
            } else if (currentProcessor.equals(this)) {
                Gdx.input.setInputProcessor(null);
            }
        }

    }

    public static Observable<InputEvent> observe() {
        return Observable.create(InputObservable::new);
    }

    private GdxInputSource() {
        throw new AssertionError();
    }

}
