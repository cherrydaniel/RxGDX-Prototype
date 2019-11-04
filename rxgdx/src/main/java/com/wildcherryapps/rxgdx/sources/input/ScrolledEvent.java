package com.wildcherryapps.rxgdx.sources.input;

public class ScrolledEvent implements InputEvent {

    private final int amount;

    public ScrolledEvent(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

}
