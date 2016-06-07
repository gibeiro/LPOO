package com.mygdx.game.state;

import java.util.Stack;

// TODO: Auto-generated Javadoc
/**
 * Organizador de estados.Contem uma pilha de estados e todas as operaçoes sao aplicadas ao estado no topo desta.
 */
public class StateManager {
    
    /** The states. */
    Stack<State> states;
    
    /**
     * Instantiates a new state manager.
     */
    public StateManager(){
        states = new Stack<State>();
    }

    /**
     * Coloca um estado novo na pilha.
     *
     * @param s the s
     */
    public void push(State s){
        states.push(s);
    }

    /**
     * Retira o estado no topo da pilha.
     */
    public void pop(){
        states.pop();
    }

    /**
     * Atualiza o estado no topo da pilha com um certo delta T.
     *
     * @param dt the dt
     */
    public void update(double dt){
        states.peek().update(dt);
    }

    /**
     * Render ao estado no topo da pilha.
     */
    public void render(){
        states.peek().render();
    }

    /**
     * Handle input.
     */
    public void handleInput(){states.peek().handleInput();}
}
