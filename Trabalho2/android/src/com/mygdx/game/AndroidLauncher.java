package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.BounceBall;

// TODO: Auto-generated Javadoc
/**
 * Launcher de client.
 */
public class AndroidLauncher extends AndroidApplication {
	
	/**
	 * On create.
	 *
	 * @param savedInstanceState the saved instance state
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BounceBall(), config);
	}
}
