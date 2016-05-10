package com.mygdx.game.network;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Nuno on 10/05/2016.
 */
public interface ClientInterface {
    public void setId(int id);
    public int getId();
    public void setWorld(World o);
    public World getWorld();

}
