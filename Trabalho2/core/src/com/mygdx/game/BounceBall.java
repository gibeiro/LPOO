package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.state.StateManager;
import com.mygdx.game.state.StateMenu;

public class BounceBall extends ApplicationAdapter {
	StateManager states;
	@Override
	public void create () {
		states = new StateManager();
		states.push(new StateMenu(states));
	}

	@Override
	public void render () {
		states.handleInput();
		states.update(Gdx.graphics.getDeltaTime());
        states.render();
	}
}
