package com.wildcherryapps.rxgdx.sources.app;

public interface GdxAppCallback {
    void onCreate();
    void onResize(int width, int height);
    void onRender(float delta);
    void onPause();
    void onResume();
    void onDispose();
}
