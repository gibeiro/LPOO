package com.server;

import java.io.IOException;
import java.net.Socket;
import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;

public class GameServer implements ServerInterface {

    public GameServer() {
        try {
            CallHandler callHandler = new CallHandler();
            callHandler.registerGlobal(Server.class, this);
            Server server = new Server();
            server.bind(7777, callHandler);
            server.addServerListener(new IServerListener() {

                @Override
                public void clientDisconnected(Socket socket) {
                    System.out.println("Client Disconnected: " + socket.getInetAddress());
                }

                @Override
                public void clientConnected(Socket socket) {
                    System.out.println("Client Connected: " + socket.getInetAddress());
                }
            });
            System.out.println("Server Listening");

        } catch (LipeRMIException  e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try{
            GameServer server = new GameServer();

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public String getResponse(String data) {
        System.out.println("getResponse called");
        return "Your data: " + data;
    }

}
/*
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
        if(game == null)
            return;
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
    /*
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
*/