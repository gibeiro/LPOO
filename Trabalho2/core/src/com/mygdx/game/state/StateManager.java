package com.mygdx.game.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateManager {
    Stack<State> states;
    public StateManager(){
        states = new Stack<State>();
    }

    public void push(State s){
        states.push(s);
    }

    public void pop(){
        states.pop();
    }

    public void update(double dt){
        states.peek().update(dt);
    }

    public void render(){
        states.peek().render();
    }

    public void handleInput(){states.peek().handleInput();}
}
