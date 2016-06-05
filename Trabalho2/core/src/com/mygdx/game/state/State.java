package com.mygdx.game.state;

/**
 * Created by Nuno on 02/05/2016.
 */
public abstract class State {
    StateManager sm;

    public State(StateManager s){
        this.sm = s;

    }
    public abstract void update(double dt);

    public abstract void render();

    public abstract void handleInput();

    public abstract void dispose();
}
