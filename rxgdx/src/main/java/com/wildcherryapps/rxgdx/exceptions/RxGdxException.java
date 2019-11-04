package com.wildcherryapps.rxgdx.exceptions;

public class RxGdxException extends Exception {

    public RxGdxException() {
    }

    public RxGdxException(String s) {
        super(s);
    }

    public RxGdxException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RxGdxException(Throwable throwable) {
        super(throwable);
    }

}
