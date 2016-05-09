package com.mygdx.game.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.badlogic.gdx.physics.box2d.World;

public interface ClientInterface extends Remote {

    //função que envie o World
    public void sendWorld(World o) throws RemoteException;

    //função que vá buscar o input



}
