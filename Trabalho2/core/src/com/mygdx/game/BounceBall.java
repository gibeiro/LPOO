package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.state.StateManager;
import com.mygdx.game.state.StateMenu;

// TODO: Auto-generated Javadoc
/**
 * Utilizador do jogo na plataforma android.
 */
public class BounceBall extends ApplicationAdapter {
	
	/** The states. */
	StateManager states;
	
	/** The s. */
	Sound s;
	
	/**
	 * Creates the.
	 */
	@Override
	public void create () {
		states = new StateManager();
		states.push(new StateMenu(states));
	}

	/**
	 * Render.
	 */
	@Override
	public void render () {
		states.handleInput();
		states.update(Gdx.graphics.getDeltaTime());
        states.render();
	}
}
