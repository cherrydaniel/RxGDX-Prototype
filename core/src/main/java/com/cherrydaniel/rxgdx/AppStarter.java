package com.cherrydaniel.rxgdx;

import com.badlogic.gdx.Gdx;
import com.wildcherryapps.rxgdx.core.RxGdxStarter;
import com.wildcherryapps.rxgdx.core.schedulers.GdxSchedulers;
import com.wildcherryapps.rxgdx.sources.app.GdxAppSource;
import com.wildcherryapps.rxgdx.sources.lifecycle.DisposeEvent;
import com.wildcherryapps.rxgdx.sources.lifecycle.LifecycleEvent;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AppStarter implements RxGdxStarter {

    private static final String TAG = "AppStarter";

    private final CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    public void onStart(GdxAppSource app) {

        mDisposables.add(
                app.observeCreate()
                        .subscribeOn(Schedulers.io())
                        .observeOn(GdxSchedulers.mainThread())
                        .subscribe(e -> Gdx.app.log(TAG, "Event consumed: " + e.getClass().getSimpleName()))
        );

    }

}
