package com.mygdx.game.server;

import com.mygdx.game.client.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import com.mygdx.game.BounceBall;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private ArrayList<ClientInterface> players = new ArrayList<ClientInterface>();
    public BounceBall game = new BounceBall();

    protected Server() throws RemoteException {
        super();
    }

    @Override
    public int join(ClientInterface client) throws RemoteException {
        if(players.size() == 2)
            return -1;
        else{
            players.add(client);
            int n = players.size() - 1;
            System.out.println("Player " + n + " joined." );
            return n;
        }
    }

    public static void main(String args[]) {
        try {

            Server server = new Server();
            Naming.rebind("rmi://localhost:1099/Server", server);
            System.err.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
