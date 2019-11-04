package com.wildcherryapps.rxgdx.sources.input;

public class KeyTypedEvent implements InputEvent {

    private final char character;

    public KeyTypedEvent(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

}
