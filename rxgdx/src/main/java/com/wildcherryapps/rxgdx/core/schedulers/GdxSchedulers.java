package com.wildcherryapps.rxgdx.core.schedulers;

public final class GdxSchedulers {

    public static GdxMainThreadScheduler mainThread() {
        return GdxMainThreadScheduler.getInstance();
    }

}
