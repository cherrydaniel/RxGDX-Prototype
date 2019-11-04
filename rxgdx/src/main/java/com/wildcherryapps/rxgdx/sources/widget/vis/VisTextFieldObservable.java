package com.wildcherryapps.rxgdx.sources.widget.vis;


import com.kotcrab.vis.ui.widget.VisTextField;
import com.wildcherryapps.rxgdx.sources.widget.CompositeAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;

public final class VisTextFieldObservable {

    private static Map<VisTextField, CompositeVisTextFieldListener> sKeyTypedListeners = new HashMap<>();

    public static Observable<VisTextFieldKeyTypedEvent> keyTyped(VisTextField textField) {
        Objects.requireNonNull(textField, "textField == null");

        if (!sKeyTypedListeners.containsKey(textField) || sKeyTypedListeners.get(textField).isEmpty()) {
            CompositeVisTextFieldListener compositeListener = new CompositeVisTextFieldListener();
            sKeyTypedListeners.put(textField, compositeListener);
            textField.setTextFieldListener(compositeListener);
        }

        return Observable.create(emitter -> {

            final CompositeVisTextFieldListener compositeListener = sKeyTypedListeners.get(textField);

            if (compositeListener == null) return; // TODO: Maybe fire error here?

            final VisTextField.TextFieldListener listener = (txt, c) -> {
                if (!emitter.isDisposed())
                    emitter.onNext(new VisTextFieldKeyTypedEvent(txt, c));
            };

            compositeListener.add(listener);

            emitter.setCancellable(()
                    -> compositeListener.remove(listener));

        });

    }

    public static final class VisTextFieldKeyTypedEvent {

        private final VisTextField textField;
        private final char key;

        public VisTextFieldKeyTypedEvent(VisTextField textField, char key) {
            this.textField = textField;
            this.key = key;
        }

        public VisTextField getTextField() {
            return textField;
        }

        public char getKey() {
            return key;
        }
    }

    private static final class CompositeVisTextFieldListener
            extends CompositeAdapter<VisTextField.TextFieldListener>
            implements VisTextField.TextFieldListener {

        @Override
        public void keyTyped(VisTextField textField, char c) {
            forEach(listener -> listener.keyTyped(textField, c));
        }

    }

    private VisTextFieldObservable() {
        throw new AssertionError();
    }

}
