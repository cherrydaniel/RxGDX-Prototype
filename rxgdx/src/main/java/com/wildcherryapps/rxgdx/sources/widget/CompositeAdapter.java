package com.wildcherryapps.rxgdx.sources.widget;

import com.cherrydaniel.rxgdx.widget.utils.Consumer;

import java.util.HashSet;
import java.util.Set;

public class CompositeAdapter<T> {

    private Set<T> set;

    public CompositeAdapter() {
        set = new HashSet<>();
    }

    public void add(T element) {
        set.add(element);
    }

    public void remove(T element) {
        set.remove(element);
    }

    public boolean contains(T element) {
        return set.contains(element);
    }

    public void clear() {
        set.clear();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public void forEach(Consumer<T> consumer) {
        for (T element: set)
            consumer.accept(element);
    }

}
