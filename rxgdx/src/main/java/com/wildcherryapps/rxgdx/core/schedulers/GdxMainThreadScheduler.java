package com.wildcherryapps.rxgdx.core.schedulers;

import com.badlogic.gdx.Gdx;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.Disposables;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

public class GdxMainThreadScheduler extends Scheduler {

    private static final String TAG = "GdxMainThreadScheduler";

    private static GdxMainThreadScheduler instance;
    private static GdxSchedulerWorker worker;

    static synchronized GdxMainThreadScheduler getInstance() {
        if (instance == null) {
            synchronized (GdxMainThreadScheduler.class) {
                if (instance == null) {
                    instance = new GdxMainThreadScheduler();
                    worker = new GdxSchedulerWorker();
                }
            }
        }
        return instance;
    }

    private GdxMainThreadScheduler() {
//        Gdx.app.log(TAG, "Instance created");
    }

    @Override
    public Worker createWorker() {
//        Gdx.app.log(TAG, "Providing worker");
        return worker;
    }

    private static final class GdxSchedulerWorker extends Worker {

        private volatile boolean disposed;

        @Override
        public Disposable schedule(Runnable run) {
            Objects.requireNonNull(run, "run == null");
            if (disposed) return Disposables.disposed();

            DirectSchedule direct = new DirectSchedule(run);
            direct.run();

            return direct;
        }

        @Override
        public Disposable schedule(Runnable run, long delay, TimeUnit unit) {
            Objects.requireNonNull(run, "run == null");
            Objects.requireNonNull(unit, "unit == null");
            if (disposed) return Disposables.disposed();

            if (delay <= 0) return schedule(run);

            DelayedSchedule delayed = new DelayedSchedule(new DirectSchedule(run), unit.toMillis(delay));
            delayed.start();

            return delayed;
        }

        @Override
        public void dispose() {
            disposed = true;
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }

    }

    private static final class DirectSchedule implements Runnable, Disposable {

        private volatile boolean disposed;
        private AtomicBoolean shouldRun;

        private Runnable run;

        private DirectSchedule(Runnable run) {
            this.shouldRun = new AtomicBoolean(true);
            this.run = RxJavaPlugins.onSchedule(run);
        }

        @Override
        public void run() {
            try {
                Gdx.app.postRunnable(() -> {
                    if (!disposed && shouldRun.get())
                        run.run();
                });
            } catch (Throwable t) {
                RxJavaPlugins.onError(t);
            }
        }

        @Override
        public void dispose() {
            disposed = true;
            shouldRun.set(false);
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }

    }

    private static final class DelayedSchedule extends Thread implements Disposable {

        private static final String SLEEPER_NAME = "GdxMainThreadScheduler-sleeper";

        private volatile boolean disposed;
        private AtomicBoolean shouldRun;

        private final DirectSchedule directSchedule;
        private final long delayMillis;

        private DelayedSchedule(DirectSchedule directSchedule, long delayMillis) {
            super(SLEEPER_NAME);
            this.shouldRun = new AtomicBoolean(true);
            this.directSchedule = directSchedule;
            this.delayMillis = delayMillis;
        }

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(delayMillis);
                if (!disposed && shouldRun.get())
                    directSchedule.run();
            } catch (InterruptedException e) {
                shouldRun.set(false);
            }
        }

        @Override
        public void dispose() {
            // TODO: Test nested DirectSchedule dispose()
            //       IMO We need to call dispose() here on the nested DirectSchedule
            disposed = true;
            directSchedule.dispose();
            interrupt();
            shouldRun.set(false);
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }

    }

}
