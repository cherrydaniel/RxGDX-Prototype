package com.cherrydaniel.rxgdx.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cherrydaniel.rxgdx.AppStarter;
import com.wildcherryapps.rxgdx.core.RxGdxApp;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
		initialize(new RxGdxApp(new AppStarter()), configuration);
	}
}