package com.mygdx.game.state;

/**
 * Classe abstrata que representa um estado
 */
public abstract class State {
    StateManager sm;

    public State(StateManager s){
        this.sm = s;

    }

    /**
     * Atualiza o estado atual usando um certo delta T
     */
    public abstract void update(double dt);

    /**
     * Render ao estado atual
     */
    public abstract void render();

    /**
     * Recebe input do utilizador
     */
    public abstract void handleInput();

    /**
     * Liberta recursos usados pelo estado
     */
    public abstract void dispose();
}
