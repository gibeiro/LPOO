package com.mygdx.game.network;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.input.Inputs;

/**
 * Created by Nuno on 10/05/2016.
 */
public interface ServerInterface {
    /*
     * Retorna -1 se nao entrou, 1 se é o jogador 1, 2 se é o jogador 2
     */
    public int join(ClientInterface c);

    public int setInput(int player, Inputs i);

    public GameInfo transferWorld();

}
