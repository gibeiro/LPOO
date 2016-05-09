package com.mygdx.game.server;

import com.mygdx.game.client.ClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import com.mygdx.game.BounceBall;

public interface ServerInterface extends Remote {
    public int join(ClientInterface c)throws RemoteException;
    //função que envie o input ao servidor
}