package com.mygdx.game.network;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.input.Inputs;
import com.mygdx.game.logic.Game;

/**
 * Created by Nuno on 10/05/2016.
 */
public interface ClientInterface {
    public void setId(int id);
    public int getId();
    public void setGame(GameInfo g);
    public Game getGame();
    public Inputs getInputs();
}
