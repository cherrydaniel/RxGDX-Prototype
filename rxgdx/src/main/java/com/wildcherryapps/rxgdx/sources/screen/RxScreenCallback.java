package com.wildcherryapps.rxgdx.sources.screen;

public interface RxScreenCallback {
    void onShow();
    void onRender(float delta);
    void onResize(int width, int height);
    void onPause();
    void onResume();
    void onHide();
    void onDispose();
}
