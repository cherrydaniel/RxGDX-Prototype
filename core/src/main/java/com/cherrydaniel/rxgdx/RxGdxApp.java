package com.cherrydaniel.rxgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.wildcherryapps.rxgdx.core.schedulers.GdxSchedulers;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxGdxApp extends ApplicationAdapter {

    private static final String TAG = "RxGdxApp";

    private CompositeDisposable mDisposables = new CompositeDisposable();

    private long callTime, startTime, endTime;

    @Override
    public void create() {

        Gdx.app.log(TAG, "create()");
        callTime = System.nanoTime();
        mDisposables.add(
                Observable.<String>create(emitter -> {
                    Gdx.app.log(TAG, "Executing task on thread " + Thread.currentThread().getName());
                    startTime = System.nanoTime();
                    emitter.onNext("YO!");
                    emitter.onNext("Wassup?!");
                    emitter.onComplete();
                })
                .delay(2000L, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(GdxSchedulers.mainThread())
//                .observeOn(Schedulers.single())
                .subscribe(
                        s -> Gdx.app.log("TAG", s),
                        e -> Gdx.app.error("TAG", e.getMessage(), e),
                        () -> {
                            endTime = System.nanoTime();
                            Gdx.app.log("TAG", "Completed on thread "
                                    + Thread.currentThread().getName()
                                    + "! Time to start = "
                                    + TimeUnit.NANOSECONDS.toMillis(startTime - callTime)
                                    + "ms, Time from start to end = "
                                    + TimeUnit.NANOSECONDS.toMillis(endTime - startTime)
                                    + "ms");
                        }
                )
        );

    }

    @Override
    public void render() {



    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "dispose()");
        if (!mDisposables.isDisposed())
            mDisposables.dispose();
    }

}