package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateGame extends State{
    public StateGame(StateManager s) {
        super(s);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(SpriteBatch s) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        s.begin();

        s.end();
    }
}
