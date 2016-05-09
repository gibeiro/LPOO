package com.mygdx.game.server;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Inputs;
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

    @Override
    public void handleInput(int id,Inputs i) throws RemoteException {
        if(id == 0){
            game.player1.movingLeft = i.movingLeft;
            game.player1.movingRight = i.movingRight;
            game.player1.jump = i.jump;
        }else if(id == 1){
            game.player2.movingLeft = i.movingLeft;
            game.player2.movingRight = i.movingRight;
            game.player2.jump = i.jump;
        }
    }

    public void sendWorlds() throws RemoteException{
        players.get(0).sendWorld(game.world);
        players.get(1).sendWorld(game.world);
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
                server.game.update(Gdx.graphics.getDeltaTime());
            }

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
