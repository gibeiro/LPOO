package com.mygdx.game.server;

import com.mygdx.game.client.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import com.mygdx.game.BounceBall;
import com.mygdx.game.game.Game;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private ArrayList<ClientInterface> players = new ArrayList<ClientInterface>();
    private Game game;

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
            if(players.size() == 2)
                game = new Game();
            return n;
        }
    }


    public static void main(String args[]) {
        try {
            Server server = new Server();
            Naming.rebind("rmi://localhost:1099/Server", server);
            System.err.println("Server ready");

            while(server.players.size() != 2){
            }

            server.game = new Game();

            while(!server.game.gameEnd){
                server.game.update();
                server.game.
            }

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
