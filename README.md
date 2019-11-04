# RxGDX-Prototype

This is a prototype for a library that will integrate RxJava into libGDX using a custom libGDX cross-platform Scheduler and utility classes for observing various libGDX components and UI elements.

## Installation

This library is still in development and will be released soon.

## Usage

### GdxSchedulers

The core of the library is the unique Scheduler for libGDX.
It can be used in RxJava simply as:

```java
Observable.create(...)
                .subscribeOn(Schedulers.io())
                .observeOn(GdxSchedulers.mainThread()) // This is the libGDX main thread scheduler
                .subscribe(...)
```

### RxGdxStarter

For writing a fully reactive app - instead of using the traditional libGDX `ApplicationListener` callback methods, you can provide an object containing all the observable sources corresponding to these callback methods.

1. In your **core** module: Create a class that implements `RxGdxStarter`

```java
public class AppStarter implements RxGdxStarter {
    
    @Override
    public void onStart(GdxAppSource source) {
        // TODO(developer): Use "source" to observe application callbacks
    }
    
}
```

2. In your **lwjgl3** module: Go to the `Lwjgl3Launcher` class and create the application using `RxGdxApp` and your `AppStarter`:

```java
public class Lwjgl3Launcher {
	public static void main(String[] args) {
		createApplication();
	}

	private static Lwjgl3Application createApplication() {
		return new Lwjgl3Application(new RxGdxApp(new AppStarter()), getDefaultConfiguration()); // Pass your starter class to the RxGdxApp constructor
	}

	private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
		Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
		// Configure...
		return configuration;
	}
}
```

3. In your **android** module: Go to the `AndroidLauncher` class and create the application in the same way

```java
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
		initialize(new RxGdxApp(new AppStarter()), configuration); // <-- Right here
	}
}
```

## TODO

- Test scheduler performance
- Test lifecycle sources
- Create observable sources for net requests
- Create observable sources for scene2d UI components
- Create observable sources for visUI components
