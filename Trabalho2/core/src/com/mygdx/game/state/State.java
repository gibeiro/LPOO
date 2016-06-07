package com.mygdx.game.state;

// TODO: Auto-generated Javadoc
/**
 * Classe abstrata que representa um estado.
 */
public abstract class State {
    
    /** The sm. */
    StateManager sm;

    /**
     * Instantiates a new state.
     *
     * @param s the s
     */
    public State(StateManager s){
        this.sm = s;

    }

    /**
     * Atualiza o estado atual usando um certo delta T.
     *
     * @param dt the dt
     */
    public abstract void update(double dt);

    /**
     * Render ao estado atual.
     */
    public abstract void render();

    /**
     * Recebe input do utilizador.
     */
    public abstract void handleInput();

    /**
     * Liberta recursos usados pelo estado.
     */
    public abstract void dispose();
}
